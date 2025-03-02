package teka.android.tekeventandroidclient.domain.form.validation.use_cases

import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.domain.form.functions.isPasswordValid
import teka.android.tekeventandroidclient.domain.form.models.ValidationResult
import teka.android.tekeventandroidclient.domain.form.validation.UiText


class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToConsistOfAtLeastEightCharacters),
            )
        }

        if (!isPasswordValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resId = R.string.strThePasswordNeedsToContainAtLeastOneLetterAndDigit),
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}