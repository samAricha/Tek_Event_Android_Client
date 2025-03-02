package teka.android.tekeventandroidclient.domain.form.validation.use_cases

import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.domain.form.functions.isEmailValid
import teka.android.tekeventandroidclient.domain.form.models.ValidationResult
import teka.android.tekeventandroidclient.domain.form.validation.UiText

class ValidateEmailUseCase: BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strTheEmailCanNotBeBlank)
            )
        }
        if (!isEmailValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThatsNotAValidEmail)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}