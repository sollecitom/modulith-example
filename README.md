# Modulith Example

A modular monolith to act as template for Kotlin services.

## Scenario

The service showcases a banking application. In this much simplified example:

- Account holders can initiate payments to other account holders.
    - These are rejected if the sender doesn't have enough balance.

- The system can receive external events that inform it of deposits made by an account holder.

- Account holders can read their current balance and the history of their transactions.

In all cases, operations are only allowed when performed by the relevant party e.g., when the user owns the source account of a `send-payment` command, or when the user owns the account of the `get-transaction-history` query.

## Modules

This example uses modules to keep adding functionality without increasing the cognitive load too much.

### Types

There are different module types, which are the archetypes for every sort of component in an event-driven architecture.

#### Command Endpoint

- Receives commands from outside the system, typically over a push-based protocol e.g., HTTP or gRPC.
- Publishes the received commands as events, so that the system can react to them.
- Awaits for the processing result of a command, typically via NATS, and then returns it to its client synchronously.

#### Query Endpoint

- Processes queries from clients outside the system, typically over a push-based protocol e.g., HTTP or gRPC.
- Materializes the relevant events in views that project the needed state for the queries.
- Returns this projected state whenever a query is requested.

#### Event Endpoint

- Receives events from outside the system, typically over a push-based protocol e.g., HTTP or gRPC.
- These events are facts happened outside the system, are of interest, and cannot be rejected, as they're not commands. Examples include webhooks from external systems e.g., Stripe, etc.
- Publishes these received events, so that the system can react to them.

#### Event Processor

- Processes events by applying business logic.
- Publishes other events in reaction to the events being processed. These successor events can indicate a successful or a failed processing result.
- Aggregates relevant events into projected state, if necessary, to help with the processing.

#### Event Sink

- Reacts to events by communicating to other systems.
- These can include updating data lakes, bridging with external systems, and notifying clients via webhooks.

### List

#### Done

##### Payment Command Endpoint

- A [command endpoint](#command-endpoint) that accepts `send-payment` commands over HTTP, and publishes them.
- If the command is rejected, the endpoint informs the client.

##### Deposit Event Endpoint

- An [event endpoint](#event-endpoint) that accepts `deposit` events over HTTP, and publishes them.

##### Account Event Processor

- An [event processor](#event-processor) that processes all the events for an account, and publishes the results of the processing.
- If the current balance wouldn't cover a payment, the `send-payment` command is rejected.
- If the target account is equal to the source account, the `send-payment` command is rejected.

#### To Do

##### Transaction History Query Endpoint

- Aggregates the transaction history for an account.
- Returns the paginated transaction history for an account, over HTTP.

##### Current Balance Query Endpoint

- Projects the current balance from the transaction history.
- Returns the current balance for an account, over HTTP.

##### Tinybird Event Sink

- Adds the events to a Tinybird instance.
- (I'm still unsure whether this is worth it, so only maybe)

## SDKs

There are also example SDKs in Kotlin.

### Account Holder SDK

This SDK allows a client to perform the operations an account holder can do, including the `send-payment` command, and the `get-current-balance` and `get-transaction-history` queries.

### Deposits SDK

This SDK allows a client to inform the system of a deposit that happened on an account.