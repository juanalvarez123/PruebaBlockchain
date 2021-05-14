package com.prueba.blockchain.pruebablockchain.consumers.sawtooth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("sawtooth-client")
public interface ISawtoothClient {

  @PostMapping(value = "/batches", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  Object postBatchList(@RequestBody byte[] payload);
}
