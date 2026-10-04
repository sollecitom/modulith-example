package sollecitom.services.modulith_example.shared.account.domain.model.event

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS
import sollecitom.libs.swissknife.core.domain.currency.known.pence
import sollecitom.services.modulith_example.shared.account.domain.model.reference.InternalAccountNumber

@TestInstance(PER_CLASS)
class AccountEventDataExtensionsTests {

    @Test
    fun `the inbound payment of an outbound payment credits the target account from the source account`() {

        val outboundPayment = OutboundPayment(sourceAccount = InternalAccountNumber("111111111111111"), amount = 2500.pence, targetAccount = InternalAccountNumber("222222222222222"))

        val inboundPayment = outboundPayment.inbound()

        assertThat(inboundPayment).isEqualTo(InboundPayment(targetAccount = InternalAccountNumber("222222222222222"), amount = 2500.pence, sourceAccount = InternalAccountNumber("111111111111111")))
    }
}
