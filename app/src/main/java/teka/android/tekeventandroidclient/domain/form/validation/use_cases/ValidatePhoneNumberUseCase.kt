package teka.android.tekeventandroidclient.domain.form.validation.use_cases

import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.domain.form.functions.isNumber
import teka.android.tekeventandroidclient.domain.form.models.ValidationResult
import teka.android.tekeventandroidclient.domain.form.validation.UiText


class ValidatePhoneNumberUseCase : BaseUseCase<String, ValidationResult> {

    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePhoneCanNotBeBlank),
            )
        }

        if (!isNumber(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePhoneNumberShouldBeContentJustDigit),
            )
        }

        if (input.length != 10) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePhoneMustBeEqualTo10),
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}