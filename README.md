# HL7 MMG Validator with Ktor (Example)
Example of HL7 MMG validation

## Repo Summary

This repo code contains 4 major parts:

- __HL7 MMG validation code in ValidatorMMg__ package, this is currently the same logic as it was developed here: [data-exchange-hl7/deprecated/fn-mmg-validator](https://github.com/CDCgov/data-exchange-hl7/tree/develop/deprecated/fn-mmg-validator)

- __MMGs__ .json assets loader interface, Dependency 1. The current MMG loader implementation is just as an example, __LoaderMMG__
   
- PHINVads __Vocabulary__ interface, Dependency 2. The current Vocab loader implementation is just an example, __LoaderVocab__

- Ktor server is just used an example to produce and visualize the validation report using tools such as postman. This repo was started with the ktor sample project.

## To start the server locally

### Precursory
- Java SDK, developed with java version "17.0.12" 
- Kotlinc compiler, developed with info: kotlinc-jvm 2.1.0 (JRE 17.0.12+8-LTS-286)
- Gradle build tool, developed with Gradle 8.11.1

### VS Code Extensions
- Extension Pack for Java
- Gradle for Java, to clean, build, run
- Kotlin Language

Gradle to clean, build, and run.

## ktor-sample
[Boiler plate ktor sample project, this code base was started with ktor sample]

This project was created using the [Ktor Project Generator](https://start.ktor.io).

Here are some useful links to get you started:

- [Ktor Documentation](https://ktor.io/docs/home.html)
- [Ktor GitHub page](https://github.com/ktorio/ktor)
- The [Ktor Slack chat](https://app.slack.com/client/T09229ZC6/C0A974TJ9). You'll need to [request an invite](https://surveys.jetbrains.com/s3/kotlin-slack-sign-up) to join.

## Features

Here's a list of features included in this project:

| Name                                               | Description                                                 |
| ----------------------------------------------------|------------------------------------------------------------- |
| [Routing](https://start.ktor.io/p/routing-default) | Allows to define structured routes and associated handlers. |

## Building & Running

To build or run the project, use one of the following tasks:

| Task                          | Description                                                          |
| -------------------------------|---------------------------------------------------------------------- |
| `./gradlew test`              | Run the tests                                                        |
| `./gradlew build`             | Build everything                                                     |
| `buildFatJar`                 | Build an executable JAR of the server with all dependencies included |
| `buildImage`                  | Build the docker image to use with the fat JAR                       |
| `publishImageToLocalRegistry` | Publish the docker image locally                                     |
| `run`                         | Run the server                                                       |
| `runDocker`                   | Run using the local docker image                                     |

If the server starts successfully, you'll see the following output:

```
2024-12-04 14:32:45.584 [main] INFO  Application - Application started in 0.303 seconds.
2024-12-04 14:32:45.682 [main] INFO  Application - Responding at http://0.0.0.0:8080
```

