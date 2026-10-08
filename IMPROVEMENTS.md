# modulith-example

## Overview
Modular monolith template demonstrating event-driven architecture with a simplified banking application: payment commands, deposit events, account processing, and transaction history.

## Scorecard

| Dimension | Rating | Notes |
|-----------|--------|-------|
| Build system | A | Gradle 9.8.0, 83 dependency refs (75 libraries, 8 plugins), excellent |
| Code quality | A | Strong DDD, clean architecture, hexagonal adapters |
| Test coverage | B+ | 29 test files across 47 modules, contract + integration tests |
| Documentation | A- | Comprehensive README with scenario, module types, completion status |
| Dependency freshness | A | All current |
| Modularity | A+ | 47 modules, exemplary layer separation per module |
| Maintainability | A | Production-quality code, clear patterns |

## Structure
- 47 content modules with per-module layers: `domain/model`, `application/model`, `adapters/driving/http`, `adapters/driven/pulsar`, `module/implementation`, `module/test/specification`
- 58 production files, 29 test files
- Patterns: DDD, hexagonal/ports-and-adapters, event choreography, CQRS (partial)

## Issues
- CQRS incomplete: Query endpoints not implemented (noted in README TODO)
- No error handling / saga / compensation patterns shown
- No distributed tracing integration despite OpenTelemetry being available
- Single account-event-processor could bottleneck with scale

## Potential Improvements
1. Implement Query endpoints to complete the CQRS pattern
2. Add saga/compensation patterns for failure handling
3. Demonstrate horizontal scaling (partition strategy)
4. Integrate OpenTelemetry for distributed tracing end-to-end
5. Show event versioning and schema evolution
6. Add performance/load testing examples
7. Showcase idempotent ingestion end to end, together with the real payment processing (the always-success stub and the
   fresh event id per request mean a retried payment is applied twice today): reuse the client's invocation id as the
   idempotency key and keep a rotating table of seen events keyed by (`InvocationContext.idempotency` id, event type,
   account), checked and recorded in the same transaction as the account update and pruned past a retention window,
   so client retries, stale re-posts and replays are applied once
