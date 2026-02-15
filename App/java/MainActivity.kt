package com.bovinescan.app

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import java.io.ByteArrayOutputStream

class MainActivity : BaseActivity() {

    private lateinit var imagePreview: ImageView
    private lateinit var uploadPlaceholder: LinearLayout
    private lateinit var btnUploadImage: MaterialButton
    private lateinit var btnUseCamera: MaterialButton
    private lateinit var btnIdentifyBreed: MaterialButton
    private lateinit var btnCheckBiometric: MaterialButton
    private lateinit var btnReset: MaterialButton
    private lateinit var btnAbout: ImageButton
    private lateinit var btnSettings: ImageButton

    private var selectedBitmap: Bitmap? = null
    private var isVerificationMode: Boolean = false

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
                onImageSelected(bitmap)
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.error_loading_image), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            if (isVerificationMode) {
                navigateToResults(it, isMuzzleId = true, isVerificationOnly = true)
            } else {
                onImageSelected(it)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            cameraLauncher.launch(null)
        } else {
            Toast.makeText(this, getString(R.string.camera_permission_required), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        imagePreview = findViewById(R.id.imagePreview)
        uploadPlaceholder = findViewById(R.id.uploadPlaceholder)
        btnUploadImage = findViewById(R.id.btnUploadImage)
        btnUseCamera = findViewById(R.id.btnUseCamera)
        btnIdentifyBreed = findViewById(R.id.btnIdentifyBreed)
        btnCheckBiometric = findViewById(R.id.btnCheckBiometric)
        btnReset = findViewById(R.id.btnReset)
        btnAbout = findViewById(R.id.btnAbout)
        btnSettings = findViewById(R.id.btnSettings)
    }

    private fun setupListeners() {
        btnUploadImage.setOnClickListener {
            isVerificationMode = false
            galleryLauncher.launch("image/*")
        }

        btnUseCamera.setOnClickListener {
            isVerificationMode = false
            launchCamera()
        }

        btnIdentifyBreed.setOnClickListener {
            selectedBitmap?.let { bitmap ->
                navigateToResults(bitmap, isMuzzleId = false, isVerificationOnly = false)
            }
        }

        btnCheckBiometric.setOnClickListener {
            isVerificationMode = true
            launchCamera()
        }

        btnReset.setOnClickListener {
            resetUI()
        }

        btnAbout.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        btnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    private fun launchCamera() {
        val cameraPermission = android.Manifest.permission.CAMERA
        if (androidx.core.content.ContextCompat.checkSelfPermission(this, cameraPermission) 
            == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            cameraLauncher.launch(null)
        } else {
            requestPermissionLauncher.launch(cameraPermission)
        }
    }

    private fun onImageSelected(bitmap: Bitmap) {
        selectedBitmap = bitmap
        imagePreview.setImageBitmap(bitmap)
        uploadPlaceholder.visibility = View.GONE
        btnIdentifyBreed.isEnabled = true
        btnReset.visibility = View.VISIBLE
    }

    private fun resetUI() {
        selectedBitmap = null
        imagePreview.setImageBitmap(null)
        uploadPlaceholder.visibility = View.VISIBLE
        btnIdentifyBreed.isEnabled = false
        btnReset.visibility = View.GONE
    }

    private fun navigateToResults(bitmap: Bitmap, isMuzzleId: Boolean, isVerificationOnly: Boolean) {
        // Convert bitmap to byte array to pass to next activity
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()

        val intent = Intent(this, ResultsActivity::class.java)
        intent.putExtra("image", byteArray)
        intent.putExtra("isMuzzleId", isMuzzleId)
        intent.putExtra("isVerificationOnly", isVerificationOnly)
        startActivity(intent)
    }
}
