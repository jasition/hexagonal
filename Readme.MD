# Skeleton project of Hexagonal Architecture
A project skeleton that enforces hexagonal architecture.

+ parent
  + core
  + behaviour-test
  + performance-test
  + adapter
  + application

## What is Hexagonal Architecture?
Well, [Hexagonal Architecture](https://en.wikipedia.org/wiki/Hexagonal_architecture_(software)) is a way separate 
concerns of a system so each concern is addressed separately and loosely within its level of abstraction. One major 
benefit is that, the change to the service is kept to the minimal, because it is now possible to modify one module of
the system without the fear of breaking the others.

If you are new to this architecture, you can start reading from this [blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).
    
## Core
The module to host the pure Kotlin *core logic* and its corresponding *unit tests*, without any dependency to external 
technology except for the language construct and test tooling. This module addresses *business concern* only.

## Behaviour Test
The module to host the *behaviour tests* for the pure Kotlin core logic, without any dependency to external technology 
except for the language construct and test tooling. All combinations of business cases should be tested here. 
This module addresses *business concern* only.

## Performance Test
The module to host the *performance tests* for the pure Kotlin core logic, without any dependency to external technology
except for the language construct and test tooling. All combinations of business cases should be tested here. 
This module addresses *performance concern* only.

## Adapter
The module to host the adapters to the ports, integration tests and contract tests. They will involve a lot of 
technology that communicates with external resources, services and systems. This module addresses the concern of 
providing *APIs to the external*, *data transformation* between the core and the external world, and how to the data are 
*transported*.

## Application
The module to inject dependency between ports and adapter, and package them as a runnable application. This module 
addresses the concern of *how everything is glued together, service startup, resource connection, security, alerts and 
monitoring, deployment pipeline, and release orchestration*.
