package sollecitom.services.modulith_example.modules.payment_command_endpoint.adapters.driven.nats

import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isTrue
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import sollecitom.libs.swissknife.core.test.utils.testProvider
import sollecitom.libs.swissknife.core.utils.CoreDataGenerator
import sollecitom.libs.swissknife.ddd.test.utils.asEvent
import sollecitom.libs.swissknife.test.utils.assertions.failedThrowing
import sollecitom.services.modulith_example.shared.account.domain.model.event.SendPaymentCommand
import sollecitom.services.modulith_example.shared.account.domain.test.utils.create

@TestInstance(PER_CLASS)
class StubbedSendPaymentProcessingResultSubscriberTests : CoreDataGenerator by CoreDataGenerator.testProvider {

    @Test
    fun `a failure while awaiting the result fails the processing result`() = runTest {

        val subscriber = StubbedSendPaymentProcessingResultSubscriber { error("Result could not be awaited") }
        val command = SendPaymentCommand.create().asEvent()

        val processingResult = with(subscriber) { command.processingResult() }
        val wasCompletedImmediately = processingResult.isCompleted
        val outcome = runCatching { processingResult.await() }

        assertThat(wasCompletedImmediately).isTrue()
        assertThat(outcome).failedThrowing<IllegalStateException>().hasMessage("Result could not be awaited")
    }
}
