# MMG Validation 

## Context

#### .NET SDK, https://github.com/CDCgov/cs-sdk-dotnet

#### XLR Pipeline, https://github.com/cdcent/xlr-cs-pipeline, SPARK based (Databricks)

#### DEX HL7, https://github.com/CDCgov/data-exchange-hl7

#### DEX HL7 MMG Validator, (Deprecated), https://github.com/CDCgov/data-exchange-hl7/tree/develop/deprecated/fn-mmg-validator 

## HL7 Validation in 2 Stages based on __standards__: Structural (NIST), Content (MMG)

#### Structural 
- NIST IGAMT v2 (profile creation workspace), https://hl7v2-igamt-2.nist.gov/home 
- IGAMT profiles: https://github.com/CDCgov/data-exchange-hl7/tree/develop/fns-hl7-pipeline/fn-structure-validator/src/main/resources/profiles

#### Content, MMG Validation
- https://ndc.services.cdc.gov/message-mapping-guides/
- Generic Excel, https://ndc.services.cdc.gov/mmgpage/generic-v2-0-message-mapping-guide/
- Generic JSON, MMGs in .json format are available from CDC MMGAT API

- Vocab, PHIN VADS, https://phinvads.cdc.gov/vads/SearchVocab.action 

## Demo
- MMG Validator with Ktor example, this project: https://github.com/CDCgov/hl7-mmg-validator-ktor