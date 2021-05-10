package com.prueba.blockchain.pruebablockchain.consumers.validator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("validator-client")
public interface IValidatorClient {

  @PostMapping(value = "/batches", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  Object postBatchList(@RequestBody byte[] payload);

}
