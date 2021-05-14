package com.prueba.blockchain.pruebablockchain.consumers.validator;

import com.prueba.blockchain.pruebablockchain.configuration.ValidatorFeignConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ValidatorConsumer {

  private final ValidatorFeignConfiguration validatorFeignConfiguration;

  public ValidatorConsumer(ValidatorFeignConfiguration validatorFeignConfiguration) {
    this.validatorFeignConfiguration = validatorFeignConfiguration;
  }

  public void postTransaction(byte[] batchListBytes) {
    validatorFeignConfiguration.getValidatorClient().postBatchList(batchListBytes);
  }
}
