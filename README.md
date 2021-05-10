# Prueba Blockchain

## Descripción

Contiene un ejemplo de uso del SDK de Hyperledger Sawtooth para Java. La implementación inicial es con la famiia `IntegerKey`.

## Links

* [Usar Docker para levantar un nodo de Sawtooth](https://sawtooth.hyperledger.org/docs/core/releases/latest/app_developers_guide/docker.html)
* [Crear y enviar transacciones en Java](https://sawtooth.hyperledger.org/docs/sdk-java/nightly/master/using_java_sdk.html)
* [IntegerKey family](https://sawtooth.hyperledger.org/docs/core/releases/latest/transaction_family_specifications/integerkey_transaction_family.html)

## Instrucciones

1) Levantar Hyperledger Sawtooth con Docker:

```bash
cd docker-compose
docker-compose -f sawtooth-default.yaml up
```

2) Correr este proyecto:

```bash
./gradlew bootRun --debug-jvm
```

3) Crear una transacción ejecutando desde Postman: 

```bash
POST http://localhost:8080
```
