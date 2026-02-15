package com.bovinescan.app

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ops.ResizeOp.ResizeMethod
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.min
import android.util.Log

class CattleClassifier private constructor(
    private val interpreter: Interpreter,
    private val labels: List<String>,
    private val inputSize: Int,
    private val inputDataType: org.tensorflow.lite.DataType,
    private val outputShapes: List<IntArray>,
    private val outputDataTypes: List<org.tensorflow.lite.DataType>,
    val modelType: ModelType = ModelType.BREED
) {

    enum class ModelType {
        BREED, MUZZLE
    }

    companion object {
        private const val BREED_MODEL_FILE = "retrained_yolov8_model_float32.tflite"
        private const val BREED_LABELS_FILE = "labels.txt"
        
        private const val MUZZLE_MODEL_FILE = "muzzle_classifier.tflite"
        private const val MUZZLE_LABELS_FILE = "muzzle_labels.txt"

        private const val PIXEL_SIZE = 3
        
        // Defaults for Breed model (0 to 1 normalization used via ResizeOp but handled in classify)
        private const val BREED_IMAGE_MEAN = 127.5f
        private const val BREED_IMAGE_STD = 127.5f

        fun create(context: Context, modelType: ModelType = ModelType.BREED): CattleClassifier {
            try {
                val modelFile = if (modelType == ModelType.MUZZLE) MUZZLE_MODEL_FILE else BREED_MODEL_FILE
                val labelsFile = if (modelType == ModelType.MUZZLE) MUZZLE_LABELS_FILE else BREED_LABELS_FILE

                // Load model
                val model = FileUtil.loadMappedFile(context, modelFile)
                val interpreter = Interpreter(model)

                // Determine input and output tensor properties
                val inputTensor = interpreter.getInputTensor(0)
                val inputShape = inputTensor.shape()
                // Assuming H and W are at index 1 and 2 for NHWC format
                val dynamicInputSize = inputShape[1]
                val dynamicInputDataType = inputTensor.dataType()
                
                // Determine all output tensor properties
                val outputCount = interpreter.outputTensorCount
                val dynamicOutputShapes = mutableListOf<IntArray>()
                val dynamicOutputDataTypes = mutableListOf<org.tensorflow.lite.DataType>()
                
                for (i in 0 until outputCount) {
                    val tensor = interpreter.getOutputTensor(i)
                    val shape = tensor.shape()
                    val dataType = tensor.dataType()
                    dynamicOutputShapes.add(shape)
                    dynamicOutputDataTypes.add(dataType)
                    Log.d("CattleClassifier", "Output $i: Shape=${shape.contentToString()}, Type=$dataType")
                }

                // Load labels
                val labels = try {
                    FileUtil.loadLabels(context, labelsFile)
                } catch (e: Exception) {
                    if (modelType == ModelType.MUZZLE) {
                        (1..24).map { it.toString() }
                    } else {
                        listOf(
                            "Holstein", "Jersey", "Angus", "Hereford", 
                            "Simmental", "Charolais", "Brahman", "Limousin"
                        )
                    }
                }

                return CattleClassifier(interpreter, labels, dynamicInputSize, dynamicInputDataType, dynamicOutputShapes, dynamicOutputDataTypes, modelType)
            } catch (e: Exception) {
                e.printStackTrace()
                throw RuntimeException("Error loading TFLite model: ${e.message}")
            }
        }

        // Breed facts database
        private val breedFacts = mapOf(
            "alambadi" to "A hardy draught breed from Tamil Nadu, known for its massive build and endurance in hilly forest grazing.",
            "amritmahal" to "One of India's most powerful draught breeds, historically reaching fame as elite military transport animals.",
            "ayrshire" to "A Scottish dairy breed prized for its hardiness, longevity, and efficient conversion of grass into high-quality milk.",
            "bachaur" to "Small but sturdy draught cattle from Bihar, exceptionally well-suited for medium agricultural work in local plains.",
            "badri" to "The first certified breed of Uttarakhand, these small mountain cattle produce medicinal milk rich in A2 protein.",
            "banni" to "A remarkable buffalo breed from Kutch, Gujarat, adapted for nocturnal grazing and high milk yield in arid zones.",
            "bargur" to "Highly agile and spirited cattle from Erode, known for their speed in navigating the steep terrain of the Western Ghats.",
            "bargur_buffalo" to "A resilient buffalo breed found in the Western Ghats, valued for its resistance to local forest diseases.",
            "belahi" to "Migratory cattle from the Haryana foothills, known for their versatility in both milk production and draught work.",
            "bhadawari" to "A copper-colored buffalo breed famous for having the highest fat content in its milk among all buffaloes.",
            "binjharpuri" to "A dependable dual-purpose breed from Odisha, valued for its draught power and steady milk supply.",
            "brahmaputra" to "Native Assamese cattle essential for local paddy cultivation and resilient to the high rainfall of the river valley.",
            "brown_swiss" to "One of the oldest dairy breeds, known for its large frame, docile nature, and milk ideal for cheese-making.",
            "chilika" to "Unique buffaloes from Odisha that graze on submerged aquatic weeds in the saline brackish waters of Chilika Lake.",
            "dagri" to "Compact hill cattle from Gujarat, specifically adapted for agricultural operations on steep mountain terraces.",
            "dangi" to "Specialized for heavy monsoon regions, these cattle have oily skin that protects them from rain and flint-like hooves.",
            "deoni" to "A premier dual-purpose breed from Maharashtra, prized for its symmetrical build, strength, and good milk yield.",
            "gangatari" to "A versatile breed from the fertile plains of the Ganges, known for its resilience and steady temperament.",
            "gaolao" to "Distinguished by their height and elegance, these cattle were historically used for fast transport in Maharashtra.",
            "ghumsari" to "A dependable local breed from Odisha, essential for sustenance farming and draught work in coastal tracts.",
            "gir" to "World-famous dairy icon from Gujarat, known for its massive domed forehead and exceptional heat/disease resistance.",
            "guernsey" to "Prized for its 'Golden' milk rich in beta-carotene, the Guernsey is a highly efficient producer of high-quality dairy.",
            "hallikar" to "The standard of Indian draught power, these cattle are renowned for their incredible endurance and trotting speed.",
            "hariana" to "The backbone of North Indian agriculture, these versatile cattle are excellent for both dairy and farm work.",
            "himachali_pahari" to "Small but hardy mountain cattle from Himachal Pradesh, perfectly adapted to high-altitude cold climates.",
            "holstein_friesian" to "The world's highest-production dairy breed, recognizable by its iconic black and white markings.",
            "jaffrabadi" to "The largest and most massive buffalo breed, known for its enormous size and production of high-fat milk.",
            "jersey" to "Smaller dairy cows that produce the richest milk with exceptional butterfat and protein content.",
            "kangayam" to "Powerful work animals from Tamil Nadu, celebrated for their strength in heavy ploughing and Jallikattu.",
            "kankrej" to "Large, massive cattle from Kutch with a unique 'Sawai Chal' swinging gait and powerful lyre-shaped horns.",
            "kasargod" to "A resilient dwarf breed from Kerala, known for its intelligence, low maintenance, and high-quality milk.",
            "kenkatha" to "Sturdy draught cattle from Bundelkhand, adapted to harsh environments and poor quality forage.",
            "khariar" to "Small and agile cattle from Odisha, specifically adapted to undulating and hilly agricultural landscapes.",
            "kherigarh" to "Active and fast-moving cattle from Uttar Pradesh, ideal for light agricultural work and quick transport.",
            "khillari" to "A premier draught breed of Maharashtra, renowned for its incredible speed, endurance, and bow-shaped horns.",
            "konkan_kapila" to "Resilient coastal cattle adapted to heavy rains and humid environments of the Maharashtra Konkan belt.",
            "kosali" to "Small Central Indian cattle, highly agile and sturdy, providing essential draught power in undulating terrain.",
            "krishna_valley" to "Bred for the heavy clay soils of the Krishna River, these are among India's strongest and heaviest bullocks.",
            "ladakhi" to "Dwarf cattle that thrive at high altitudes, exhibiting incredible resistance to hypoxia and extreme cold.",
            "lakhimi" to "Indispensable Assamese cattle, uniquely adapted to wet plains and high moisture humid environments.",
            "malnad_gidda" to "Small dwarf cattle from the Western Ghats, essential for humid forest regions and heavy rainfall areas.",
            "malvi" to "Compact and muscular cattle from the Malwa plateau, prized for their strength in heavy agricultural labor.",
            "mehsana" to "A high-yielding buffalo from Gujarat, favored for its long lactation cycle and regular breeding.",
            "mewati" to "Versatile cattle from the Mewat region, known for their docility and reliability in both tillage and dairy.",
            "motu" to "Resilient dwarf cattle from southern Odisha, specifically adapted to poor grazing and harsh hilly conditions.",
            "murrah" to "The 'Black Gold' of India, widely considered the world's best dairy buffalo breed for milk production.",
            "nagori" to "Renowned for their swiftness, these upstanding cattle were historically the first choice for fast carting.",
            "nagpuri" to "A staple of central India, this buffalo is known for its flat horns and adaptability to hot climates.",
            "nari" to "Hardy mountain cattle from the Aravalli range, prized for their versatility in work and milk production.",
            "nili_ravi" to "A premier dairy buffalo from Punjab, recognized by its 'Panj-Kalyan' white markings and high milk quality.",
            "nimari" to "Powerful Central Indian workhorses, combining the strength of mountain breeds with great endurance.",
            "ongole" to "World-renowned for their hardiness, these massive cattle are the ancestors of many global beef breeds.",
            "poda_thirupu" to "Fierce and hardy bullocks from Telangana forests, used for heavy ploughing in challenging terrain.",
            "ponwar" to "Spirited mountain-foothill cattle from Uttar Pradesh, known for their speed, stamina, and agility.",
            "pulikulam" to "Famous for their role in Jallikattu, these agile cattle are bred for courage and farm work in Tamil Nadu.",
            "punganur" to "The world's smallest humped cattle, producing milk with an incredible 8% fat content from minimal feed.",
            "purnea" to "Indispensable for the rural economy of Bihar, these cattle are reliable, docile, and low-maintenance.",
            "rathi" to "The 'Kamadhenu' of the Thar Desert, providing steady milk production in Rajasthan's extreme climate.",
            "red_dane" to "A robust Danish dairy breed, known for its uniform deep red coat and high-volume milk production.",
            "red_sindhi" to "One of the most widely distributed tropical dairy breeds, celebrated for heat and tick resistance.",
            "red_kandhari" to "Prized for their power in heavy tillage, these uniform dark red cattle are icons of Nanded, Maharashtra.",
            "sahiwal" to "The premier Zebu dairy breed of the subcontinent, exported globally for its unmatched milk quality.",
            "shweta_kapila" to "Unique all-white cattle from Goa, perfectly adapted to humid coastal climates and resilient to local pests.",
            "siri" to "Himalayan workhorses with a thick coat of hair, protected from the extreme cold and heavy mountain rains.",
            "surti" to "An economical buffalo from Gujarat, producing high-fat milk with exceptionally low maintenance costs.",
            "tharparkar" to "A champion of arid climates, able to sustain high milk production on poor quality desert forage.",
            "thutho" to "Hardy mountain cattle from Nagaland, specifically adapted to steep terrain and high rainfall conditions.",
            "toda" to "A semi-wild buffalo breed from the Nilgiris, central to the culture and survival of the Toda tribe.",
            "umblachery" to "Specialized for wetlands, these cattle are the essential workhorses of the Tamil Nadu rice paddy fields.",
            "vechur" to "The Guinness World Record smallest cow, prized for its medicinal A2 milk and incredible efficiency.",
            "angus" to "World-famous for superior beef quality and marbling, the Angus is a hornless, highly efficient beef breed.",
            "hereford" to "Easily recognized by its white face and red body, the Hereford is a docile and hardy beef production leader.",
            "simmental" to "A versatile dual-purpose breed, excellent for both beef and dairy, known for rapid growth and efficiency.",
            "charolais" to "Large, heavily muscled beef cattle from France, valued for exceptional growth and lean meat quality.",
            "limousin" to "Known as the 'butcher's breed', providing high carcass yields and lean beef with great efficiency."
        )

        fun getBreedFacts(breedName: String): String {
            return breedFacts[breedName.lowercase()] ?: "A distinctive cattle breed with unique characteristics and qualities."
        }
    }

    fun classify(bitmap: Bitmap): AnalysisResult {
        val startTime = System.currentTimeMillis()

        // Preprocess image using TFLite Support Library (more robust)
        val tensorImage = TensorImage(inputDataType)
        tensorImage.load(bitmap)
        
        val builder = ImageProcessor.Builder()
            .add(ResizeOp(inputSize, inputSize, ResizeMethod.BILINEAR))
            
        if (modelType == ModelType.MUZZLE) {
            // ImageNet Normalization: (x - mean) / std
            // mean = [0.485, 0.456, 0.406], std = [0.229, 0.224, 0.225]
            // In TFLite Support NormalizeOp(mean, std) calculates: (val - mean) / std
            // But inputs are 0-255, so we scale inputs by 255 first or adjust mean/std
            builder.add(NormalizeOp(
                floatArrayOf(123.675f, 116.28f, 103.53f), 
                floatArrayOf(58.395f, 57.12f, 57.375f)
            ))
        } else {
            // Breed model default: Scale to [0, 1]
            builder.add(NormalizeOp(0.0f, 255.0f))
        }
            
        val imageProcessor = builder.build()
        val processedImage = imageProcessor.process(tensorImage)

        // Run inference
        val outputs = mutableMapOf<Int, Any>()
        for (i in outputShapes.indices) {
            val buffer = TensorBuffer.createFixedSize(outputShapes[i], outputDataTypes[i])
            outputs[i] = buffer.buffer.rewind()
        }
        
        interpreter.runForMultipleInputsOutputs(arrayOf(processedImage.buffer), outputs)

        // Parse results using unified logic
        val breedResults = parseOutputs(outputs)
        val bestResult = breedResults.firstOrNull() ?: com.bovinescan.app.models.BreedResult("Unknown", 0.0f)

        return AnalysisResult(
            breedName = bestResult.breedName,
            confidence = (bestResult.confidence * 100).toInt(),
            inferenceTime = System.currentTimeMillis() - startTime,
            modelName = if (modelType == ModelType.MUZZLE) "Cow Muzzle ID (Biometric)" else "Breed Classification AI",
            quickFacts = if (modelType == ModelType.MUZZLE) "Cow ID matched from muzzle patterns." else getBreedFacts(bestResult.breedName)
        )
    }
    
    fun classifyMultiple(bitmap: Bitmap): List<com.bovinescan.app.models.BreedResult> {
        // Preprocess image
        val tensorImage = TensorImage(inputDataType)
        tensorImage.load(bitmap)
        
        val builder = ImageProcessor.Builder()
            .add(ResizeOp(inputSize, inputSize, ResizeMethod.BILINEAR))
            
        if (modelType == ModelType.MUZZLE) {
            builder.add(NormalizeOp(
                floatArrayOf(123.675f, 116.28f, 103.53f), 
                floatArrayOf(58.395f, 57.12f, 57.375f)
            ))
        } else {
            builder.add(NormalizeOp(0.0f, 255.0f))
        }
            
        val imageProcessor = builder.build()
        val processedImage = imageProcessor.process(tensorImage)

        // Run inference
        val outputs = mutableMapOf<Int, Any>()
        for (i in outputShapes.indices) {
            val buffer = TensorBuffer.createFixedSize(outputShapes[i], outputDataTypes[i])
            outputs[i] = buffer.buffer.rewind()
        }
        
        interpreter.runForMultipleInputsOutputs(arrayOf(processedImage.buffer), outputs)

        return parseOutputs(outputs).take(5)
    }

    private fun parseOutputs(outputs: Map<Int, Any>): List<com.bovinescan.app.models.BreedResult> {
        val results = mutableListOf<com.bovinescan.app.models.BreedResult>()
        
        // Case 1: Multiple outputs (TFLite Task/Detection format)
        if (outputs.size >= 3) {
            // Standard Ultralytics NMS Output Order: 0:Boxes, 1:Scores, 2:Classes, 3:Count
            // We'll use these as fallbacks but try to verify by shape
            var scoresIdx = 1
            var classesIdx = 2
            
            for (i in outputShapes.indices) {
                val shape = outputShapes[i]
                // Detection scores for YOLOv8 NMS can be [1, 100] or [1, 100, Classes]
                if (shape.size == 3 && shape[2] == labels.size) {
                    scoresIdx = i
                }
                // Classes are usually [1, 100] and contain indices
                // If we see [1, 100] at index 2, it's likely the Classes tensor
                else if (shape.size == 2 && shape[1] > 1 && i == 2) {
                    classesIdx = i
                }
            }
            
            val sBuf = outputs[scoresIdx] as? ByteBuffer
            val cBuf = outputs[classesIdx] as? ByteBuffer
            
            if (sBuf != null && cBuf != null) {
                sBuf.rewind()
                val scores = FloatArray(sBuf.remaining() / 4)
                sBuf.asFloatBuffer().get(scores)
                
                cBuf.rewind()
                val classes = FloatArray(cBuf.remaining() / 4)
                cBuf.asFloatBuffer().get(classes)

                val maxScoresPerClass = FloatArray(labels.size) { 0.0f }
                
                // If scores is [1, 100] and classes is [1, 100]
                if (classes.size == scores.size) {
                    for (i in classes.indices) {
                        val classIdx = classes[i].toInt()
                        val confidence = scores[i]
                        if (classIdx in labels.indices) {
                            if (confidence > maxScoresPerClass[classIdx]) {
                                maxScoresPerClass[classIdx] = confidence
                            }
                        }
                    }
                } else if (scores.size == classes.size * labels.size) {
                    // If scores is [1, 100, num_classes]
                    val numBoxes = classes.size
                    val numLabels = labels.size
                    for (boxIdx in 0 until numBoxes) {
                        for (labelIdx in 0 until numLabels) {
                            val score = scores[boxIdx * numLabels + labelIdx]
                            if (score > maxScoresPerClass[labelIdx]) {
                                maxScoresPerClass[labelIdx] = score
                            }
                        }
                    }
                }
                
                for (i in labels.indices) {
                    results.add(com.bovinescan.app.models.BreedResult(labels[i], maxScoresPerClass[i]))
                }
            }
        } else {
            // Case 2: Single output (Classification or Single-Tensor Detection)
            val buffer = outputs[0] as ByteBuffer
            buffer.rewind()
            val probabilities = FloatArray(buffer.remaining() / 4)
            buffer.asFloatBuffer().get(probabilities)

            if (probabilities.size > 1000) {
                // YOLOv8 detection tensor [1, 4+classes, 8400]
                val numClasses = labels.size
                val numAnchors = probabilities.size / (4 + numClasses)
                val maxConfidences = FloatArray(numClasses) { 0.0f }
                
                for (classIdx in 0 until numClasses) {
                    for (anchorIdx in 0 until numAnchors) {
                        val score = probabilities[(4 + classIdx) * numAnchors + anchorIdx]
                        if (score > maxConfidences[classIdx]) {
                            maxConfidences[classIdx] = score
                        }
                    }
                }
                for (i in 0 until numClasses) {
                    results.add(com.bovinescan.app.models.BreedResult(labels[i], maxConfidences[i]))
                }
            } else {
                // Standard Classification
                val count = min(labels.size, probabilities.size)
                for (i in 0 until count) {
                    results.add(com.bovinescan.app.models.BreedResult(labels[i], probabilities[i]))
                }
            }
        }
        
        return results.sortedByDescending { it.confidence }
    }

    private fun softmax(logits: FloatArray): FloatArray {
        val exp = FloatArray(logits.size)
        var sum = 0.0f
        
        val maxLogit = logits.maxOrNull() ?: 0.0f
        for (i in logits.indices) {
            exp[i] = kotlin.math.exp(logits[i] - maxLogit)
            sum += exp[i]
        }
        
        if (sum == 0.0f) return logits
        
        for (i in logits.indices) {
            exp[i] /= sum
        }
        return exp
    }

    fun close() {
        interpreter.close()
    }
}

