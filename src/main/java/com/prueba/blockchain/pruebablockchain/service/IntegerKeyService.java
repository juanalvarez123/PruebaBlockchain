package com.prueba.blockchain.pruebablockchain.service;

import co.nstant.in.cbor.CborBuilder;
import co.nstant.in.cbor.CborEncoder;
import co.nstant.in.cbor.CborException;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.protobuf.ByteString;
import com.prueba.blockchain.pruebablockchain.consumers.sawtooth.SawtoothConsumer;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import sawtooth.sdk.protobuf.Batch;
import sawtooth.sdk.protobuf.BatchHeader;
import sawtooth.sdk.protobuf.BatchList;
import sawtooth.sdk.protobuf.Transaction;
import sawtooth.sdk.protobuf.TransactionHeader;
import sawtooth.sdk.signing.PrivateKey;
import sawtooth.sdk.signing.Secp256k1Context;
import sawtooth.sdk.signing.Signer;

@Service
public class IntegerKeyService implements ITransactionService {

  private final SawtoothConsumer sawtoothConsumer;

  public IntegerKeyService(SawtoothConsumer sawtoothConsumer) {
    this.sawtoothConsumer = sawtoothConsumer;
  }

  @Override
  public void createTransaction() throws NoSuchAlgorithmException {
    // Creating a Private Key and Signer
    Secp256k1Context context = new Secp256k1Context();
    PrivateKey privateKey = context.newRandomPrivateKey();
    Signer signer = new Signer(context, privateKey);

    // Encoding Your Payload
    ByteArrayOutputStream payload = new ByteArrayOutputStream();
    try {
      new CborEncoder(payload).encode(new CborBuilder()
          .addMap()
          .put("Verb", "set")
          .put("Name", "jsae")
          .put("Value", 31)
          .end()
          .build());
    } catch (CborException e) {
      e.printStackTrace();
    }
    byte[] payloadBytes = payload.toByteArray();

    // Building the Transaction

    // 1. Build the input/output
    // Se conforma por:
    // * Tiene 70 caracteres de longitud.
    // * Los 6 primeros caracteres son los 6 primeros caracteres del hash de "intkey".
    // * Los 64 restantes por los últimos 64 caracteres del hash de "Name" (El nombre de la entrada).
    String input1 = Hashing.sha512().hashString("intkey", StandardCharsets.UTF_8).toString();
    String input2 = Hashing.sha512().hashString("jsae", StandardCharsets.UTF_8).toString();
    String input = input1.substring(0, 6) + input2.substring(input2.length() - 64);

    // 2. Create the Transaction Header
    TransactionHeader header = TransactionHeader.newBuilder()
        .setSignerPublicKey(signer.getPublicKey().hex())
        .setFamilyName("intkey")
        .setFamilyVersion("1.0")
        .addInputs(input)
        .addOutputs(input)
        .setPayloadSha512(hash(payload))
        .setBatcherPublicKey(signer.getPublicKey().hex())
        .setNonce(UUID.randomUUID().toString())
        .build();

    // 3. Create the Transaction
    String signature = signer.sign(header.toByteArray());

    Transaction transaction = Transaction.newBuilder()
        .setHeader(header.toByteString())
        .setPayload(ByteString.copyFrom(payloadBytes))
        .setHeaderSignature(signature)
        .build();

    // Building the Batch

    // 1. Create the BatchHeader
    List<Transaction> transactions = new ArrayList<>();
    transactions.add(transaction);

    BatchHeader batchHeader = BatchHeader.newBuilder()
        .setSignerPublicKey(signer.getPublicKey().hex())
        .addAllTransactionIds(
            transactions
                .stream()
                .map(Transaction::getHeaderSignature)
                .collect(Collectors.toList())
        )
        .build();

    // 2. Create the Batch
    String batchSignature = signer.sign(batchHeader.toByteArray());

    Batch batch = Batch.newBuilder()
        .setHeader(batchHeader.toByteString())
        .addAllTransactions(transactions)
        .setHeaderSignature(batchSignature)
        .build();

    // 3. Encode the Batch(es) in a BatchList
    byte[] batchListBytes = BatchList.newBuilder()
        .addBatches(batch)
        .build()
        .toByteArray();

    sawtoothConsumer.postTransaction(batchListBytes);
  }

  private String hash(ByteArrayOutputStream input) throws NoSuchAlgorithmException {
    MessageDigest digest = MessageDigest.getInstance("SHA-512");
    digest.reset();
    digest.update(input.toByteArray());
    return BaseEncoding.base16().lowerCase().encode(digest.digest());
  }
}
