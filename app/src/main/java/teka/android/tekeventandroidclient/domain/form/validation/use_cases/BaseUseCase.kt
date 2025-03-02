package teka.android.tekeventandroidclient.domain.form.validation.use_cases

interface BaseUseCase<In, Out> {
    fun execute(input: In): Out
}