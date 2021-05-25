# Microservicio Spring Boot usando el API de Sawtooth 

## Descripción

En este ejemplo podrás desplegar:
* Un blockchain usando el framework de **Hyperledger Sawtooth** junto Docker y docker-compose.
* Un microservicio construido con Java y Spring Boot que usa el SDK de **Hyperledger Sawtooth** para comunicarse con el API de Sawtooth.
  
En el microservicio, la implementación inicial es con la famiia `IntegerKey` y aún está pendiente (TODO: por mejorar) la implementación con la familia `tp1`.

## Links

* [Usar Docker para levantar un nodo de Sawtooth](https://sawtooth.hyperledger.org/docs/core/releases/latest/app_developers_guide/docker.html)
* [Crear y enviar transacciones en Java](https://sawtooth.hyperledger.org/docs/sdk-java/nightly/master/using_java_sdk.html)
* [IntegerKey family](https://sawtooth.hyperledger.org/docs/core/releases/latest/transaction_family_specifications/integerkey_transaction_family.html)

## Instrucciones

1) Desplegar **Hyperledger Sawtooth** con Docker:

```bash
cd docker-compose
docker-compose -f sawtooth-default.yaml up
```

**Opcional:** Para desplegar **Hyperledger Sawtooth** que contiene la familia `tp1` hay que hacerlo con [este proyecto](https://github.com/juanalvarez123/custom-sawtooth-transaction-processor).

2) Correr el microservicio:

```bash
./gradlew bootRun
```

3) Crear una transacción (Ejecutando desde Postman): 

```bash
# Familia IntegerKey
POST http://localhost:8080/intkey

# Familia tp1 (TODO: Por mejorar)
POST http://localhost:8080/tp1
```
