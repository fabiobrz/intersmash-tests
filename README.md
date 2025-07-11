# Intersmash Tests

Intersmash test cases.

## Overview

The goal is to have a common repository for Intersmash test cases, which are executed to verify complex interoperability 
scenarios between middleware services in Cloud platform environments, most notably OpenShift.

Intersmash Tests leverage the [Intersmash framework](https://github.com/Intersmash/intersmash) to provision the tested 
scenarios, including both service runtimes - like Kafka, Infinispan or Keycloak - and 
[Intersmash Applications](https://github.com/Intersmash/intersmash-applications), for instance WildFly deployments.

The tests in the repository can be executed by enabling specific Maven profiles in order to use either community or 
product deliverables, like images or Helm Charts, see the [Profiles section](#profiles).

## Tests

Tests are executed by default on Kubernetes, and with community bits for applications, with the Maven Failsafe Plugin 
using the [global-test.properties](global-test.properties) file to configure Intersmash framework in order to use 
community deliverables and Kubernetes specifics, like for example the default OLM namespace and catalog source.

### Running the tests

The simplest test execution can be performed via a `mvn clean install` command.

### Implemented tests

#### [WildFly (JBoss EAP XP) MiroProfile Reactive Messaging + Kafka (Streams for ApacheKafka)](wildfly-microprofile-reactive-messaging-kafka/src/test/java/org/jboss/intersmash/tests/wildfly/microprofile/reactive/messaging/kafka/WildflyMicroProfileReactiveMessagingPerConnectorSecuredTests.java)

This test validates an interoperability use case based on a WildFly (JBoss EAP XP) MicroProfile Reactive
Messaging application, which interacts with a remote Kafka (Streams for Apache Kafka) service.
See the [WildflyMicroProfileReactiveMessagingPerConnectorSecuredTests](wildfly-microprofile-reactive-messaging-kafka/src/test/java/org/jboss/intersmash/tests/wildfly/microprofile/reactive/messaging/kafka/WildflyMicroProfileReactiveMessagingPerConnectorSecuredTests.java) class Javadoc for more details.

## Profiles

### Executing tests based on target platform

- `k8s`

Adding `-Pk8s` to the build will make JUnit exclude tests that are expected to run on OpenShift only, as for 
instance those that involve an s2i build.

- `openshift`

Adding `-Popenshift` to the build will make JUnit exclude tests that are expected to run on Kubernetes only. 

When this profile is enabled, the Maven Failsafe Plugin is configured to use the 
[global-test.openshift.properties](./global-test.openshift.properties) 
file, so that the Intersmash framework will run tests on OpenShift, and leverage OpenShift cluster specifics - like the 
default OLM namespace and catalog source - rather than the Kubernetes ones.

### WildFly/JBoss EAP/JBoss EAP XP related profiles

#### Executing tests based on the target distribution

By default, tests involving WildFly and the related products (i.e. JBoss EAP and JBoss EAP XP) are executed by using
the community version of the involved applications (WildFly) and cloud related deliverables, e.g.: images, Helm Charts
etc.

- `wildfly.build-stream.jboss-eap.81`

When this profile is enabled, _application descriptors_ that implement the
[WildflyApplicationConfiguration](./intersmash-tests-core/src/main/java/org/jboss/intersmash/tests/wildfly/WildflyApplicationConfiguration.java)
interface will generate additional Maven args that will be forwarded to a remote s2i build, so that the tested
application will be built accordingly.
Additionally, the Maven Failsafe Plugin will use the 
[global-test.eap-81.openshift.properties](./global-test.eap-81.openshift.properties)
file in order to configure the Intersmash framework, so that JBoss EAP 8.1.x cloud deliverables - e.g.: images and Helm 
Charts -  will be used during the test execution.


- `wildfly.build-stream.jboss-eap-xp.5`

When this profile is enabled, _application descriptors_ that implement the
[WildflyApplicationConfiguration](./intersmash-tests-core/src/main/java/org/jboss/intersmash/tests/wildfly/WildflyApplicationConfiguration.java)
interface will generate additional Maven args that will be forwarded to a remote s2i build, so that the tested
application will be built accordingly.
Additionally, the Maven Failsafe Plugin will use the
[global-test.eap-xp5.openshift.properties](./global-test.eap-xp5.openshift.properties)
file in order to configure the Intersmash framework, so that JBoss EAP XP 5.x cloud deliverables - e.g.: images and Helm
Charts -  will be used during the test execution.

## Modules

### [intersmash-tests-core](./intersmash-tests-core)

This module contains annotations used to decorate test classes, specifically JUnit 5 `@Tag` Java interfaces which can be 
used to selectively execute groups of tests.

### [style-config](./style-config)

Utility module that holds the resources needed to perform code style validation and formatting. 