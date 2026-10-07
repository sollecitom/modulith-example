package sollecitom.services.modulith_example.modules.account_event_processor.application.model

import sollecitom.libs.pillar.messaging.domain.event.processing.EventHandler
import sollecitom.libs.pillar.messaging.domain.event.processing.byType
import sollecitom.libs.swissknife.core.utils.CoreDataGenerator
import sollecitom.libs.swissknife.correlation.core.domain.context.InvocationContext
import sollecitom.libs.swissknife.messaging.domain.event.processing.ProcessEvent
import sollecitom.libs.swissknife.messaging.domain.event.processing.processAsCompositeEvent
import sollecitom.libs.swissknife.messaging.domain.message.ReceivedMessage
import sollecitom.libs.swissknife.messaging.domain.message.into
import sollecitom.libs.swissknife.messaging.domain.message.properties.MessagePropertyNames
import sollecitom.libs.swissknife.messaging.domain.message.publisher.MessagePublisher
import sollecitom.services.modulith_example.shared.account.domain.model.event.*

interface Application : EventHandler<AccountEvent> {

    companion object
}

private class ApplicationImplementation(private val publisher: MessagePublisher<AccountEvent>, coreDataGenerator: CoreDataGenerator, messagePropertyNames: MessagePropertyNames) : Application, MessagePropertyNames by messagePropertyNames, CoreDataGenerator by coreDataGenerator {

    private val handler = EventHandler.byType<AccountEvent>(
        mapOf(
            Deposit.type to ProcessEvent { message -> processDeposit(message.into()) },
            SendPaymentCommand.type to ProcessEvent { message -> processSendPaymentCommand(message.into()) },
            InboundPayment.type to ProcessEvent { message -> processInboundPayment(message.into()) },
            OutboundPayment.type to ProcessEvent { message -> processOutboundPayment(message.into()) },
        )
    )

    override val handledTypes get() = handler.handledTypes

    context(_: InvocationContext<*>)
    override suspend fun invoke(event: ReceivedMessage<AccountEvent>) = handler(event)

    context(_: InvocationContext<*>)
    private suspend fun processDeposit(message: ReceivedMessage<DepositEvent>) = message.processAsCompositeEvent { data, event ->

        // TODO increase balance for target account, if it exists. If not, publish AccountNotFoundError
    }

    context(_: InvocationContext<*>)
    private suspend fun processSendPaymentCommand(message: ReceivedMessage<SendPaymentCommandReceived>) = message.processAsCompositeEvent { data, event ->

        // TODO if target account or source account don't exist, publish AccountNotFoundError
        // TODO if the source account doesn't belong to the caller's customer (from the event's invocation context), publish NotAccountOwner
        // TODO if there's balance, publish outbound payment
        // TODO if there's no balance, publish NotEnoughBalance
    }

    context(_: InvocationContext<*>)
    private suspend fun processInboundPayment(message: ReceivedMessage<InboundPaymentEvent>) = message.processAsCompositeEvent { data, event ->

        // TODO increase balance for target account, if it exists. If not, publish AccountNotFoundError
    }

    context(_: InvocationContext<*>)
    private suspend fun processOutboundPayment(message: ReceivedMessage<OutboundPaymentEvent>) = message.processAsCompositeEvent { data, event ->

        // TODO decrease balance (already checked)
        // TODO publish inbound payment (which will be processed as inbound on another partition)
    }
}

context(generator: CoreDataGenerator, propertyNames: MessagePropertyNames)
fun Application.Companion.create(publisher: MessagePublisher<AccountEvent>): Application = ApplicationImplementation(publisher = publisher, coreDataGenerator = generator, messagePropertyNames = propertyNames)