package com.bovinescan.app

import com.bovinescan.app.models.LocalBreedData

object BreedDataStore {

    fun getBreedData(breedName: String): LocalBreedData {
        return when (breedName.lowercase()) {
            "alambadi" -> LocalBreedData(
                physicalTraits = R.string.breed_alambadi_physicalTraits,
                hornShape = R.string.breed_alambadi_hornShape,
                earShape = R.string.breed_alambadi_earShape,
                coatColor = R.string.breed_alambadi_coatColor,
                origin = R.string.breed_alambadi_origin,
                purpose = R.string.breed_alambadi_purpose,
                milkYield = R.string.breed_alambadi_milkYield,
                specialTraits = R.string.breed_alambadi_specialTraits,
                description = R.string.breed_alambadi_description
            )
            "amritmahal" -> LocalBreedData(
                physicalTraits = R.string.breed_amritmahal_physicalTraits,
                hornShape = R.string.breed_amritmahal_hornShape,
                earShape = R.string.breed_amritmahal_earShape,
                coatColor = R.string.breed_amritmahal_coatColor,
                origin = R.string.breed_amritmahal_origin,
                purpose = R.string.breed_amritmahal_purpose,
                milkYield = R.string.breed_amritmahal_milkYield,
                specialTraits = R.string.breed_amritmahal_specialTraits,
                description = R.string.breed_amritmahal_description
            )
            "ayrshire" -> LocalBreedData(
                physicalTraits = R.string.breed_ayrshire_physicalTraits,
                hornShape = R.string.breed_ayrshire_hornShape,
                earShape = R.string.breed_ayrshire_earShape,
                coatColor = R.string.breed_ayrshire_coatColor,
                origin = R.string.breed_ayrshire_origin,
                purpose = R.string.breed_ayrshire_purpose,
                milkYield = R.string.breed_ayrshire_milkYield,
                specialTraits = R.string.breed_ayrshire_specialTraits,
                description = R.string.breed_ayrshire_description
            )
            "bachaur" -> LocalBreedData(
                physicalTraits = R.string.breed_bachaur_physicalTraits,
                hornShape = R.string.breed_bachaur_hornShape,
                earShape = R.string.breed_bachaur_earShape,
                coatColor = R.string.breed_bachaur_coatColor,
                origin = R.string.breed_bachaur_origin,
                purpose = R.string.breed_bachaur_purpose,
                milkYield = R.string.breed_bachaur_milkYield,
                specialTraits = R.string.breed_bachaur_specialTraits,
                description = R.string.breed_bachaur_description
            )
            "badri" -> LocalBreedData(
                physicalTraits = R.string.breed_badri_physicalTraits,
                hornShape = R.string.breed_badri_hornShape,
                earShape = R.string.breed_badri_earShape,
                coatColor = R.string.breed_badri_coatColor,
                origin = R.string.breed_badri_origin,
                purpose = R.string.breed_badri_purpose,
                milkYield = R.string.breed_badri_milkYield,
                specialTraits = R.string.breed_badri_specialTraits,
                description = R.string.breed_badri_description
            )
            "banni" -> LocalBreedData(
                physicalTraits = R.string.breed_banni_physicalTraits,
                hornShape = R.string.breed_banni_hornShape,
                earShape = R.string.breed_banni_earShape,
                coatColor = R.string.breed_banni_coatColor,
                origin = R.string.breed_banni_origin,
                purpose = R.string.breed_banni_purpose,
                milkYield = R.string.breed_banni_milkYield,
                specialTraits = R.string.breed_banni_specialTraits,
                description = R.string.breed_banni_description
            )
            "bargur" -> LocalBreedData(
                physicalTraits = R.string.breed_bargur_physicalTraits,
                hornShape = R.string.breed_bargur_hornShape,
                earShape = R.string.breed_bargur_earShape,
                coatColor = R.string.breed_bargur_coatColor,
                origin = R.string.breed_bargur_origin,
                purpose = R.string.breed_bargur_purpose,
                milkYield = R.string.breed_bargur_milkYield,
                specialTraits = R.string.breed_bargur_specialTraits,
                description = R.string.breed_bargur_description
            )
            "bargur_buffalo" -> LocalBreedData(
                physicalTraits = R.string.breed_bargur_buffalo_physicalTraits,
                hornShape = R.string.breed_bargur_buffalo_hornShape,
                earShape = R.string.breed_bargur_buffalo_earShape,
                coatColor = R.string.breed_bargur_buffalo_coatColor,
                origin = R.string.breed_bargur_buffalo_origin,
                purpose = R.string.breed_bargur_buffalo_purpose,
                milkYield = R.string.breed_bargur_buffalo_milkYield,
                specialTraits = R.string.breed_bargur_buffalo_specialTraits,
                description = R.string.breed_bargur_buffalo_description
            )
            "belahi" -> LocalBreedData(
                physicalTraits = R.string.breed_belahi_physicalTraits,
                hornShape = R.string.breed_belahi_hornShape,
                earShape = R.string.breed_belahi_earShape,
                coatColor = R.string.breed_belahi_coatColor,
                origin = R.string.breed_belahi_origin,
                purpose = R.string.breed_belahi_purpose,
                milkYield = R.string.breed_belahi_milkYield,
                specialTraits = R.string.breed_belahi_specialTraits,
                description = R.string.breed_belahi_description
            )
            "bhadawari" -> LocalBreedData(
                physicalTraits = R.string.breed_bhadawari_physicalTraits,
                hornShape = R.string.breed_bhadawari_hornShape,
                earShape = R.string.breed_bhadawari_earShape,
                coatColor = R.string.breed_bhadawari_coatColor,
                origin = R.string.breed_bhadawari_origin,
                purpose = R.string.breed_bhadawari_purpose,
                milkYield = R.string.breed_bhadawari_milkYield,
                specialTraits = R.string.breed_bhadawari_specialTraits,
                description = R.string.breed_bhadawari_description
            )
            "binjharpuri" -> LocalBreedData(
                physicalTraits = R.string.breed_binjharpuri_physicalTraits,
                hornShape = R.string.breed_binjharpuri_hornShape,
                earShape = R.string.breed_binjharpuri_earShape,
                coatColor = R.string.breed_binjharpuri_coatColor,
                origin = R.string.breed_binjharpuri_origin,
                purpose = R.string.breed_binjharpuri_purpose,
                milkYield = R.string.breed_binjharpuri_milkYield,
                specialTraits = R.string.breed_binjharpuri_specialTraits,
                description = R.string.breed_binjharpuri_description
            )
            "brahmaputra" -> LocalBreedData(
                physicalTraits = R.string.breed_brahmaputra_physicalTraits,
                hornShape = R.string.breed_brahmaputra_hornShape,
                earShape = R.string.breed_brahmaputra_earShape,
                coatColor = R.string.breed_brahmaputra_coatColor,
                origin = R.string.breed_brahmaputra_origin,
                purpose = R.string.breed_brahmaputra_purpose,
                milkYield = R.string.breed_brahmaputra_milkYield,
                specialTraits = R.string.breed_brahmaputra_specialTraits,
                description = R.string.breed_brahmaputra_description
            )
            "brown_swiss" -> LocalBreedData(
                physicalTraits = R.string.breed_brown_swiss_physicalTraits,
                hornShape = R.string.breed_brown_swiss_hornShape,
                earShape = R.string.breed_brown_swiss_earShape,
                coatColor = R.string.breed_brown_swiss_coatColor,
                origin = R.string.breed_brown_swiss_origin,
                purpose = R.string.breed_brown_swiss_purpose,
                milkYield = R.string.breed_brown_swiss_milkYield,
                specialTraits = R.string.breed_brown_swiss_specialTraits,
                description = R.string.breed_brown_swiss_description
            )
            "chilika" -> LocalBreedData(
                physicalTraits = R.string.breed_chilika_physicalTraits,
                hornShape = R.string.breed_chilika_hornShape,
                earShape = R.string.breed_chilika_earShape,
                coatColor = R.string.breed_chilika_coatColor,
                origin = R.string.breed_chilika_origin,
                purpose = R.string.breed_chilika_purpose,
                milkYield = R.string.breed_chilika_milkYield,
                specialTraits = R.string.breed_chilika_specialTraits,
                description = R.string.breed_chilika_description
            )
            "dagri" -> LocalBreedData(
                physicalTraits = R.string.breed_dagri_physicalTraits,
                hornShape = R.string.breed_dagri_hornShape,
                earShape = R.string.breed_dagri_earShape,
                coatColor = R.string.breed_dagri_coatColor,
                origin = R.string.breed_dagri_origin,
                purpose = R.string.breed_dagri_purpose,
                milkYield = R.string.breed_dagri_milkYield,
                specialTraits = R.string.breed_dagri_specialTraits,
                description = R.string.breed_dagri_description
            )
            "dangi" -> LocalBreedData(
                physicalTraits = R.string.breed_dangi_physicalTraits,
                hornShape = R.string.breed_dangi_hornShape,
                earShape = R.string.breed_dangi_earShape,
                coatColor = R.string.breed_dangi_coatColor,
                origin = R.string.breed_dangi_origin,
                purpose = R.string.breed_dangi_purpose,
                milkYield = R.string.breed_dangi_milkYield,
                specialTraits = R.string.breed_dangi_specialTraits,
                description = R.string.breed_dangi_description
            )
            "deoni" -> LocalBreedData(
                physicalTraits = R.string.breed_deoni_physicalTraits,
                hornShape = R.string.breed_deoni_hornShape,
                earShape = R.string.breed_deoni_earShape,
                coatColor = R.string.breed_deoni_coatColor,
                origin = R.string.breed_deoni_origin,
                purpose = R.string.breed_deoni_purpose,
                milkYield = R.string.breed_deoni_milkYield,
                specialTraits = R.string.breed_deoni_specialTraits,
                description = R.string.breed_deoni_description
            )
            "gangatari" -> LocalBreedData(
                physicalTraits = R.string.breed_gangatari_physicalTraits,
                hornShape = R.string.breed_gangatari_hornShape,
                earShape = R.string.breed_gangatari_earShape,
                coatColor = R.string.breed_gangatari_coatColor,
                origin = R.string.breed_gangatari_origin,
                purpose = R.string.breed_gangatari_purpose,
                milkYield = R.string.breed_gangatari_milkYield,
                specialTraits = R.string.breed_gangatari_specialTraits,
                description = R.string.breed_gangatari_description
            )
            "gaolao" -> LocalBreedData(
                physicalTraits = R.string.breed_gaolao_physicalTraits,
                hornShape = R.string.breed_gaolao_hornShape,
                earShape = R.string.breed_gaolao_earShape,
                coatColor = R.string.breed_gaolao_coatColor,
                origin = R.string.breed_gaolao_origin,
                purpose = R.string.breed_gaolao_purpose,
                milkYield = R.string.breed_gaolao_milkYield,
                specialTraits = R.string.breed_gaolao_specialTraits,
                description = R.string.breed_gaolao_description
            )
            "ghumsari" -> LocalBreedData(
                physicalTraits = R.string.breed_ghumsari_physicalTraits,
                hornShape = R.string.breed_ghumsari_hornShape,
                earShape = R.string.breed_ghumsari_earShape,
                coatColor = R.string.breed_ghumsari_coatColor,
                origin = R.string.breed_ghumsari_origin,
                purpose = R.string.breed_ghumsari_purpose,
                milkYield = R.string.breed_ghumsari_milkYield,
                specialTraits = R.string.breed_ghumsari_specialTraits,
                description = R.string.breed_ghumsari_description
            )
            "gir" -> LocalBreedData(
                physicalTraits = R.string.breed_gir_physicalTraits,
                hornShape = R.string.breed_gir_hornShape,
                earShape = R.string.breed_gir_earShape,
                coatColor = R.string.breed_gir_coatColor,
                origin = R.string.breed_gir_origin,
                purpose = R.string.breed_gir_purpose,
                milkYield = R.string.breed_gir_milkYield,
                specialTraits = R.string.breed_gir_specialTraits,
                description = R.string.breed_gir_description
            )
            "guernsey" -> LocalBreedData(
                physicalTraits = R.string.breed_guernsey_physicalTraits,
                hornShape = R.string.breed_guernsey_hornShape,
                earShape = R.string.breed_guernsey_earShape,
                coatColor = R.string.breed_guernsey_coatColor,
                origin = R.string.breed_guernsey_origin,
                purpose = R.string.breed_guernsey_purpose,
                milkYield = R.string.breed_guernsey_milkYield,
                specialTraits = R.string.breed_guernsey_specialTraits,
                description = R.string.breed_guernsey_description
            )
            "hallikar" -> LocalBreedData(
                physicalTraits = R.string.breed_hallikar_physicalTraits,
                hornShape = R.string.breed_hallikar_hornShape,
                earShape = R.string.breed_hallikar_earShape,
                coatColor = R.string.breed_hallikar_coatColor,
                origin = R.string.breed_hallikar_origin,
                purpose = R.string.breed_hallikar_purpose,
                milkYield = R.string.breed_hallikar_milkYield,
                specialTraits = R.string.breed_hallikar_specialTraits,
                description = R.string.breed_hallikar_description
            )
            "hariana" -> LocalBreedData(
                physicalTraits = R.string.breed_hariana_physicalTraits,
                hornShape = R.string.breed_hariana_hornShape,
                earShape = R.string.breed_hariana_earShape,
                coatColor = R.string.breed_hariana_coatColor,
                origin = R.string.breed_hariana_origin,
                purpose = R.string.breed_hariana_purpose,
                milkYield = R.string.breed_hariana_milkYield,
                specialTraits = R.string.breed_hariana_specialTraits,
                description = R.string.breed_hariana_description
            )
            "himachali_pahari", "himachali pahari" -> LocalBreedData(
                physicalTraits = R.string.breed_himachali_pahari_physicalTraits,
                hornShape = R.string.breed_himachali_pahari_hornShape,
                earShape = R.string.breed_himachali_pahari_earShape,
                coatColor = R.string.breed_himachali_pahari_coatColor,
                origin = R.string.breed_himachali_pahari_origin,
                purpose = R.string.breed_himachali_pahari_purpose,
                milkYield = R.string.breed_himachali_pahari_milkYield,
                specialTraits = R.string.breed_himachali_pahari_specialTraits,
                description = R.string.breed_himachali_pahari_description
            )
            "holstein", "holstein_friesian" -> LocalBreedData(
                physicalTraits = R.string.breed_holstein_physicalTraits,
                hornShape = R.string.breed_holstein_hornShape,
                earShape = R.string.breed_holstein_earShape,
                coatColor = R.string.breed_holstein_coatColor,
                origin = R.string.breed_holstein_origin,
                purpose = R.string.breed_holstein_purpose,
                milkYield = R.string.breed_holstein_milkYield,
                specialTraits = R.string.breed_holstein_specialTraits,
                description = R.string.breed_holstein_description
            )
            "jaffrabadi" -> LocalBreedData(
                physicalTraits = R.string.breed_jaffrabadi_physicalTraits,
                hornShape = R.string.breed_jaffrabadi_hornShape,
                earShape = R.string.breed_jaffrabadi_earShape,
                coatColor = R.string.breed_jaffrabadi_coatColor,
                origin = R.string.breed_jaffrabadi_origin,
                purpose = R.string.breed_jaffrabadi_purpose,
                milkYield = R.string.breed_jaffrabadi_milkYield,
                specialTraits = R.string.breed_jaffrabadi_specialTraits,
                description = R.string.breed_jaffrabadi_description
            )
            "jersey" -> LocalBreedData(
                physicalTraits = R.string.breed_jersey_physicalTraits,
                hornShape = R.string.breed_jersey_hornShape,
                earShape = R.string.breed_jersey_earShape,
                coatColor = R.string.breed_jersey_coatColor,
                origin = R.string.breed_jersey_origin,
                purpose = R.string.breed_jersey_purpose,
                milkYield = R.string.breed_jersey_milkYield,
                specialTraits = R.string.breed_jersey_specialTraits,
                description = R.string.breed_jersey_description
            )
            "kangayam" -> LocalBreedData(
                physicalTraits = R.string.breed_kangayam_physicalTraits,
                hornShape = R.string.breed_kangayam_hornShape,
                earShape = R.string.breed_kangayam_earShape,
                coatColor = R.string.breed_kangayam_coatColor,
                origin = R.string.breed_kangayam_origin,
                purpose = R.string.breed_kangayam_purpose,
                milkYield = R.string.breed_kangayam_milkYield,
                specialTraits = R.string.breed_kangayam_specialTraits,
                description = R.string.breed_kangayam_description
            )
            "kankrej" -> LocalBreedData(
                physicalTraits = R.string.breed_kankrej_physicalTraits,
                hornShape = R.string.breed_kankrej_hornShape,
                earShape = R.string.breed_kankrej_earShape,
                coatColor = R.string.breed_kankrej_coatColor,
                origin = R.string.breed_kankrej_origin,
                purpose = R.string.breed_kankrej_purpose,
                milkYield = R.string.breed_kankrej_milkYield,
                specialTraits = R.string.breed_kankrej_specialTraits,
                description = R.string.breed_kankrej_description
            )
            "kasargod" -> LocalBreedData(
                physicalTraits = R.string.breed_kasargod_physicalTraits,
                hornShape = R.string.breed_kasargod_hornShape,
                earShape = R.string.breed_kasargod_earShape,
                coatColor = R.string.breed_kasargod_coatColor,
                origin = R.string.breed_kasargod_origin,
                purpose = R.string.breed_kasargod_purpose,
                milkYield = R.string.breed_kasargod_milkYield,
                specialTraits = R.string.breed_kasargod_specialTraits,
                description = R.string.breed_kasargod_description
            )
            "kenkatha" -> LocalBreedData(
                physicalTraits = R.string.breed_kenkatha_physicalTraits,
                hornShape = R.string.breed_kenkatha_hornShape,
                earShape = R.string.breed_kenkatha_earShape,
                coatColor = R.string.breed_kenkatha_coatColor,
                origin = R.string.breed_kenkatha_origin,
                purpose = R.string.breed_kenkatha_purpose,
                milkYield = R.string.breed_kenkatha_milkYield,
                specialTraits = R.string.breed_kenkatha_specialTraits,
                description = R.string.breed_kenkatha_description
            )
            "khariar" -> LocalBreedData(
                physicalTraits = R.string.breed_khariar_physicalTraits,
                hornShape = R.string.breed_khariar_hornShape,
                earShape = R.string.breed_khariar_earShape,
                coatColor = R.string.breed_khariar_coatColor,
                origin = R.string.breed_khariar_origin,
                purpose = R.string.breed_khariar_purpose,
                milkYield = R.string.breed_khariar_milkYield,
                specialTraits = R.string.breed_khariar_specialTraits,
                description = R.string.breed_khariar_description
            )
            "kherigarh" -> LocalBreedData(
                physicalTraits = R.string.breed_kherigarh_physicalTraits,
                hornShape = R.string.breed_kherigarh_hornShape,
                earShape = R.string.breed_kherigarh_earShape,
                coatColor = R.string.breed_kherigarh_coatColor,
                origin = R.string.breed_kherigarh_origin,
                purpose = R.string.breed_kherigarh_purpose,
                milkYield = R.string.breed_kherigarh_milkYield,
                specialTraits = R.string.breed_kherigarh_specialTraits,
                description = R.string.breed_kherigarh_description
            )
            "khillari" -> LocalBreedData(
                physicalTraits = R.string.breed_khillari_physicalTraits,
                hornShape = R.string.breed_khillari_hornShape,
                earShape = R.string.breed_khillari_earShape,
                coatColor = R.string.breed_khillari_coatColor,
                origin = R.string.breed_khillari_origin,
                purpose = R.string.breed_khillari_purpose,
                milkYield = R.string.breed_khillari_milkYield,
                specialTraits = R.string.breed_khillari_specialTraits,
                description = R.string.breed_khillari_description
            )
            "konkan_kapila" -> LocalBreedData(
                physicalTraits = R.string.breed_konkan_kapila_physicalTraits,
                hornShape = R.string.breed_konkan_kapila_hornShape,
                earShape = R.string.breed_konkan_kapila_earShape,
                coatColor = R.string.breed_konkan_kapila_coatColor,
                origin = R.string.breed_konkan_kapila_origin,
                purpose = R.string.breed_konkan_kapila_purpose,
                milkYield = R.string.breed_konkan_kapila_milkYield,
                specialTraits = R.string.breed_konkan_kapila_specialTraits,
                description = R.string.breed_konkan_kapila_description
            )
            "kosali" -> LocalBreedData(
                physicalTraits = R.string.breed_kosali_physicalTraits,
                hornShape = R.string.breed_kosali_hornShape,
                earShape = R.string.breed_kosali_earShape,
                coatColor = R.string.breed_kosali_coatColor,
                origin = R.string.breed_kosali_origin,
                purpose = R.string.breed_kosali_purpose,
                milkYield = R.string.breed_kosali_milkYield,
                specialTraits = R.string.breed_kosali_specialTraits,
                description = R.string.breed_kosali_description
            )
            "krishna_valley" -> LocalBreedData(
                physicalTraits = R.string.breed_krishna_valley_physicalTraits,
                hornShape = R.string.breed_krishna_valley_hornShape,
                earShape = R.string.breed_krishna_valley_earShape,
                coatColor = R.string.breed_krishna_valley_coatColor,
                origin = R.string.breed_krishna_valley_origin,
                purpose = R.string.breed_krishna_valley_purpose,
                milkYield = R.string.breed_krishna_valley_milkYield,
                specialTraits = R.string.breed_krishna_valley_specialTraits,
                description = R.string.breed_krishna_valley_description
            )
            "ladakhi" -> LocalBreedData(
                physicalTraits = R.string.breed_ladakhi_physicalTraits,
                hornShape = R.string.breed_ladakhi_hornShape,
                earShape = R.string.breed_ladakhi_earShape,
                coatColor = R.string.breed_ladakhi_coatColor,
                origin = R.string.breed_ladakhi_origin,
                purpose = R.string.breed_ladakhi_purpose,
                milkYield = R.string.breed_ladakhi_milkYield,
                specialTraits = R.string.breed_ladakhi_specialTraits,
                description = R.string.breed_ladakhi_description
            )
            "lakhimi" -> LocalBreedData(
                physicalTraits = R.string.breed_lakhimi_physicalTraits,
                hornShape = R.string.breed_lakhimi_hornShape,
                earShape = R.string.breed_lakhimi_earShape,
                coatColor = R.string.breed_lakhimi_coatColor,
                origin = R.string.breed_lakhimi_origin,
                purpose = R.string.breed_lakhimi_purpose,
                milkYield = R.string.breed_lakhimi_milkYield,
                specialTraits = R.string.breed_lakhimi_specialTraits,
                description = R.string.breed_lakhimi_description
            )
            "malnad_gidda" -> LocalBreedData(
                physicalTraits = R.string.breed_malnad_gidda_physicalTraits,
                hornShape = R.string.breed_malnad_gidda_hornShape,
                earShape = R.string.breed_malnad_gidda_earShape,
                coatColor = R.string.breed_malnad_gidda_coatColor,
                origin = R.string.breed_malnad_gidda_origin,
                purpose = R.string.breed_malnad_gidda_purpose,
                milkYield = R.string.breed_malnad_gidda_milkYield,
                specialTraits = R.string.breed_malnad_gidda_specialTraits,
                description = R.string.breed_malnad_gidda_description
            )
            "malvi" -> LocalBreedData(
                physicalTraits = R.string.breed_malvi_physicalTraits,
                hornShape = R.string.breed_malvi_hornShape,
                earShape = R.string.breed_malvi_earShape,
                coatColor = R.string.breed_malvi_coatColor,
                origin = R.string.breed_malvi_origin,
                purpose = R.string.breed_malvi_purpose,
                milkYield = R.string.breed_malvi_milkYield,
                specialTraits = R.string.breed_malvi_specialTraits,
                description = R.string.breed_malvi_description
            )
            "mehsana" -> LocalBreedData(
                physicalTraits = R.string.breed_mehsana_physicalTraits,
                hornShape = R.string.breed_mehsana_hornShape,
                earShape = R.string.breed_mehsana_earShape,
                coatColor = R.string.breed_mehsana_coatColor,
                origin = R.string.breed_mehsana_origin,
                purpose = R.string.breed_mehsana_purpose,
                milkYield = R.string.breed_mehsana_milkYield,
                specialTraits = R.string.breed_mehsana_specialTraits,
                description = R.string.breed_mehsana_description
            )
            "mewati" -> LocalBreedData(
                physicalTraits = R.string.breed_mewati_physicalTraits,
                hornShape = R.string.breed_mewati_hornShape,
                earShape = R.string.breed_mewati_earShape,
                coatColor = R.string.breed_mewati_coatColor,
                origin = R.string.breed_mewati_origin,
                purpose = R.string.breed_mewati_purpose,
                milkYield = R.string.breed_mewati_milkYield,
                specialTraits = R.string.breed_mewati_specialTraits,
                description = R.string.breed_mewati_description
            )
            "motu" -> LocalBreedData(
                physicalTraits = R.string.breed_motu_physicalTraits,
                hornShape = R.string.breed_motu_hornShape,
                earShape = R.string.breed_motu_earShape,
                coatColor = R.string.breed_motu_coatColor,
                origin = R.string.breed_motu_origin,
                purpose = R.string.breed_motu_purpose,
                milkYield = R.string.breed_motu_milkYield,
                specialTraits = R.string.breed_motu_specialTraits,
                description = R.string.breed_motu_description
            )
            "murrah" -> LocalBreedData(
                physicalTraits = R.string.breed_murrah_physicalTraits,
                hornShape = R.string.breed_murrah_hornShape,
                earShape = R.string.breed_murrah_earShape,
                coatColor = R.string.breed_murrah_coatColor,
                origin = R.string.breed_murrah_origin,
                purpose = R.string.breed_murrah_purpose,
                milkYield = R.string.breed_murrah_milkYield,
                specialTraits = R.string.breed_murrah_specialTraits,
                description = R.string.breed_murrah_description
            )
            "nagori" -> LocalBreedData(
                physicalTraits = R.string.breed_nagori_physicalTraits,
                hornShape = R.string.breed_nagori_hornShape,
                earShape = R.string.breed_nagori_earShape,
                coatColor = R.string.breed_nagori_coatColor,
                origin = R.string.breed_nagori_origin,
                purpose = R.string.breed_nagori_purpose,
                milkYield = R.string.breed_nagori_milkYield,
                specialTraits = R.string.breed_nagori_specialTraits,
                description = R.string.breed_nagori_description
            )
            "nagpuri" -> LocalBreedData(
                physicalTraits = R.string.breed_nagpuri_physicalTraits,
                hornShape = R.string.breed_nagpuri_hornShape,
                earShape = R.string.breed_nagpuri_earShape,
                coatColor = R.string.breed_nagpuri_coatColor,
                origin = R.string.breed_nagpuri_origin,
                purpose = R.string.breed_nagpuri_purpose,
                milkYield = R.string.breed_nagpuri_milkYield,
                specialTraits = R.string.breed_nagpuri_specialTraits,
                description = R.string.breed_nagpuri_description
            )
            "nari" -> LocalBreedData(
                physicalTraits = R.string.breed_nari_physicalTraits,
                hornShape = R.string.breed_nari_hornShape,
                earShape = R.string.breed_nari_earShape,
                coatColor = R.string.breed_nari_coatColor,
                origin = R.string.breed_nari_origin,
                purpose = R.string.breed_nari_purpose,
                milkYield = R.string.breed_nari_milkYield,
                specialTraits = R.string.breed_nari_specialTraits,
                description = R.string.breed_nari_description
            )
            "nili_ravi" -> LocalBreedData(
                physicalTraits = R.string.breed_nili_ravi_physicalTraits,
                hornShape = R.string.breed_nili_ravi_hornShape,
                earShape = R.string.breed_nili_ravi_earShape,
                coatColor = R.string.breed_nili_ravi_coatColor,
                origin = R.string.breed_nili_ravi_origin,
                purpose = R.string.breed_nili_ravi_purpose,
                milkYield = R.string.breed_nili_ravi_milkYield,
                specialTraits = R.string.breed_nili_ravi_specialTraits,
                description = R.string.breed_nili_ravi_description
            )
            "nimari" -> LocalBreedData(
                physicalTraits = R.string.breed_nimari_physicalTraits,
                hornShape = R.string.breed_nimari_hornShape,
                earShape = R.string.breed_nimari_earShape,
                coatColor = R.string.breed_nimari_coatColor,
                origin = R.string.breed_nimari_origin,
                purpose = R.string.breed_nimari_purpose,
                milkYield = R.string.breed_nimari_milkYield,
                specialTraits = R.string.breed_nimari_specialTraits,
                description = R.string.breed_nimari_description
            )
            "ongole" -> LocalBreedData(
                physicalTraits = R.string.breed_ongole_physicalTraits,
                hornShape = R.string.breed_ongole_hornShape,
                earShape = R.string.breed_ongole_earShape,
                coatColor = R.string.breed_ongole_coatColor,
                origin = R.string.breed_ongole_origin,
                purpose = R.string.breed_ongole_purpose,
                milkYield = R.string.breed_ongole_milkYield,
                specialTraits = R.string.breed_ongole_specialTraits,
                description = R.string.breed_ongole_description
            )
            "poda_thirupu" -> LocalBreedData(
                physicalTraits = R.string.breed_poda_thirupu_physicalTraits,
                hornShape = R.string.breed_poda_thirupu_hornShape,
                earShape = R.string.breed_poda_thirupu_earShape,
                coatColor = R.string.breed_poda_thirupu_coatColor,
                origin = R.string.breed_poda_thirupu_origin,
                purpose = R.string.breed_poda_thirupu_purpose,
                milkYield = R.string.breed_poda_thirupu_milkYield,
                specialTraits = R.string.breed_poda_thirupu_specialTraits,
                description = R.string.breed_poda_thirupu_description
            )
            "ponwar" -> LocalBreedData(
                physicalTraits = R.string.breed_ponwar_physicalTraits,
                hornShape = R.string.breed_ponwar_hornShape,
                earShape = R.string.breed_ponwar_earShape,
                coatColor = R.string.breed_ponwar_coatColor,
                origin = R.string.breed_ponwar_origin,
                purpose = R.string.breed_ponwar_purpose,
                milkYield = R.string.breed_ponwar_milkYield,
                specialTraits = R.string.breed_ponwar_specialTraits,
                description = R.string.breed_ponwar_description
            )
            "pulikulam" -> LocalBreedData(
                physicalTraits = R.string.breed_pulikulam_physicalTraits,
                hornShape = R.string.breed_pulikulam_hornShape,
                earShape = R.string.breed_pulikulam_earShape,
                coatColor = R.string.breed_pulikulam_coatColor,
                origin = R.string.breed_pulikulam_origin,
                purpose = R.string.breed_pulikulam_purpose,
                milkYield = R.string.breed_pulikulam_milkYield,
                specialTraits = R.string.breed_pulikulam_specialTraits,
                description = R.string.breed_pulikulam_description
            )
            "punganur" -> LocalBreedData(
                physicalTraits = R.string.breed_punganur_physicalTraits,
                hornShape = R.string.breed_punganur_hornShape,
                earShape = R.string.breed_punganur_earShape,
                coatColor = R.string.breed_punganur_coatColor,
                origin = R.string.breed_punganur_origin,
                purpose = R.string.breed_punganur_purpose,
                milkYield = R.string.breed_punganur_milkYield,
                specialTraits = R.string.breed_punganur_specialTraits,
                description = R.string.breed_punganur_description
            )
            "purnea" -> LocalBreedData(
                physicalTraits = R.string.breed_purnea_physicalTraits,
                hornShape = R.string.breed_purnea_hornShape,
                earShape = R.string.breed_purnea_earShape,
                coatColor = R.string.breed_purnea_coatColor,
                origin = R.string.breed_purnea_origin,
                purpose = R.string.breed_purnea_purpose,
                milkYield = R.string.breed_purnea_milkYield,
                specialTraits = R.string.breed_purnea_specialTraits,
                description = R.string.breed_purnea_description
            )
            "rathi" -> LocalBreedData(
                physicalTraits = R.string.breed_rathi_physicalTraits,
                hornShape = R.string.breed_rathi_hornShape,
                earShape = R.string.breed_rathi_earShape,
                coatColor = R.string.breed_rathi_coatColor,
                origin = R.string.breed_rathi_origin,
                purpose = R.string.breed_rathi_purpose,
                milkYield = R.string.breed_rathi_milkYield,
                specialTraits = R.string.breed_rathi_specialTraits,
                description = R.string.breed_rathi_description
            )
            "red_dane" -> LocalBreedData(
                physicalTraits = R.string.breed_red_dane_physicalTraits,
                hornShape = R.string.breed_red_dane_hornShape,
                earShape = R.string.breed_red_dane_earShape,
                coatColor = R.string.breed_red_dane_coatColor,
                origin = R.string.breed_red_dane_origin,
                purpose = R.string.breed_red_dane_purpose,
                milkYield = R.string.breed_red_dane_milkYield,
                specialTraits = R.string.breed_red_dane_specialTraits,
                description = R.string.breed_red_dane_description
            )
            "red_sindhi" -> LocalBreedData(
                physicalTraits = R.string.breed_red_sindhi_physicalTraits,
                hornShape = R.string.breed_red_sindhi_hornShape,
                earShape = R.string.breed_red_sindhi_earShape,
                coatColor = R.string.breed_red_sindhi_coatColor,
                origin = R.string.breed_red_sindhi_origin,
                purpose = R.string.breed_red_sindhi_purpose,
                milkYield = R.string.breed_red_sindhi_milkYield,
                specialTraits = R.string.breed_red_sindhi_specialTraits,
                description = R.string.breed_red_sindhi_description
            )
            "red_kandhari" -> LocalBreedData(
                physicalTraits = R.string.breed_red_kandhari_physicalTraits,
                hornShape = R.string.breed_red_kandhari_hornShape,
                earShape = R.string.breed_red_kandhari_earShape,
                coatColor = R.string.breed_red_kandhari_coatColor,
                origin = R.string.breed_red_kandhari_origin,
                purpose = R.string.breed_red_kandhari_purpose,
                milkYield = R.string.breed_red_kandhari_milkYield,
                specialTraits = R.string.breed_red_kandhari_specialTraits,
                description = R.string.breed_red_kandhari_description
            )
            "sahiwal" -> LocalBreedData(
                physicalTraits = R.string.breed_sahiwal_physicalTraits,
                hornShape = R.string.breed_sahiwal_hornShape,
                earShape = R.string.breed_sahiwal_earShape,
                coatColor = R.string.breed_sahiwal_coatColor,
                origin = R.string.breed_sahiwal_origin,
                purpose = R.string.breed_sahiwal_purpose,
                milkYield = R.string.breed_sahiwal_milkYield,
                specialTraits = R.string.breed_sahiwal_specialTraits,
                description = R.string.breed_sahiwal_description
            )
            "shweta_kapila" -> LocalBreedData(
                physicalTraits = R.string.breed_shweta_kapila_physicalTraits,
                hornShape = R.string.breed_shweta_kapila_hornShape,
                earShape = R.string.breed_shweta_kapila_earShape,
                coatColor = R.string.breed_shweta_kapila_coatColor,
                origin = R.string.breed_shweta_kapila_origin,
                purpose = R.string.breed_shweta_kapila_purpose,
                milkYield = R.string.breed_shweta_kapila_milkYield,
                specialTraits = R.string.breed_shweta_kapila_specialTraits,
                description = R.string.breed_shweta_kapila_description
            )
            "siri" -> LocalBreedData(
                physicalTraits = R.string.breed_siri_physicalTraits,
                hornShape = R.string.breed_siri_hornShape,
                earShape = R.string.breed_siri_earShape,
                coatColor = R.string.breed_siri_coatColor,
                origin = R.string.breed_siri_origin,
                purpose = R.string.breed_siri_purpose,
                milkYield = R.string.breed_siri_milkYield,
                specialTraits = R.string.breed_siri_specialTraits,
                description = R.string.breed_siri_description
            )
            "surti" -> LocalBreedData(
                physicalTraits = R.string.breed_surti_physicalTraits,
                hornShape = R.string.breed_surti_hornShape,
                earShape = R.string.breed_surti_earShape,
                coatColor = R.string.breed_surti_coatColor,
                origin = R.string.breed_surti_origin,
                purpose = R.string.breed_surti_purpose,
                milkYield = R.string.breed_surti_milkYield,
                specialTraits = R.string.breed_surti_specialTraits,
                description = R.string.breed_surti_description
            )
            "tharparkar" -> LocalBreedData(
                physicalTraits = R.string.breed_tharparkar_physicalTraits,
                hornShape = R.string.breed_tharparkar_hornShape,
                earShape = R.string.breed_tharparkar_earShape,
                coatColor = R.string.breed_tharparkar_coatColor,
                origin = R.string.breed_tharparkar_origin,
                purpose = R.string.breed_tharparkar_purpose,
                milkYield = R.string.breed_tharparkar_milkYield,
                specialTraits = R.string.breed_tharparkar_specialTraits,
                description = R.string.breed_tharparkar_description
            )
            "thutho" -> LocalBreedData(
                physicalTraits = R.string.breed_thutho_physicalTraits,
                hornShape = R.string.breed_thutho_hornShape,
                earShape = R.string.breed_thutho_earShape,
                coatColor = R.string.breed_thutho_coatColor,
                origin = R.string.breed_thutho_origin,
                purpose = R.string.breed_thutho_purpose,
                milkYield = R.string.breed_thutho_milkYield,
                specialTraits = R.string.breed_thutho_specialTraits,
                description = R.string.breed_thutho_description
            )
            "toda" -> LocalBreedData(
                physicalTraits = R.string.breed_toda_physicalTraits,
                hornShape = R.string.breed_toda_hornShape,
                earShape = R.string.breed_toda_earShape,
                coatColor = R.string.breed_toda_coatColor,
                origin = R.string.breed_toda_origin,
                purpose = R.string.breed_toda_purpose,
                milkYield = R.string.breed_toda_milkYield,
                specialTraits = R.string.breed_toda_specialTraits,
                description = R.string.breed_toda_description
            )
            "umblachery" -> LocalBreedData(
                physicalTraits = R.string.breed_umblachery_physicalTraits,
                hornShape = R.string.breed_umblachery_hornShape,
                earShape = R.string.breed_umblachery_earShape,
                coatColor = R.string.breed_umblachery_coatColor,
                origin = R.string.breed_umblachery_origin,
                purpose = R.string.breed_umblachery_purpose,
                milkYield = R.string.breed_umblachery_milkYield,
                specialTraits = R.string.breed_umblachery_specialTraits,
                description = R.string.breed_umblachery_description
            )
            "vechur" -> LocalBreedData(
                physicalTraits = R.string.breed_vechur_physicalTraits,
                hornShape = R.string.breed_vechur_hornShape,
                earShape = R.string.breed_vechur_earShape,
                coatColor = R.string.breed_vechur_coatColor,
                origin = R.string.breed_vechur_origin,
                purpose = R.string.breed_vechur_purpose,
                milkYield = R.string.breed_vechur_milkYield,
                specialTraits = R.string.breed_vechur_specialTraits,
                description = R.string.breed_vechur_description
            )
            else -> LocalBreedData(
                physicalTraits = R.string.breed_default_physicalTraits,
                hornShape = R.string.breed_default_hornShape,
                earShape = R.string.breed_default_earShape,
                coatColor = R.string.breed_default_coatColor,
                origin = R.string.breed_default_origin,
                purpose = R.string.breed_default_purpose,
                milkYield = R.string.breed_default_milkYield,
                specialTraits = R.string.breed_default_specialTraits,
                description = R.string.breed_default_description
            )
        }
    }
}
