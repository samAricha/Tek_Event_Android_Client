package teka.android.tekeventandroidclient.domain.form.models

import teka.android.tekeventandroidclient.domain.form.validation.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)