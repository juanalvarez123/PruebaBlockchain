package com.prueba.blockchain.pruebablockchain.configuration;

import com.prueba.blockchain.pruebablockchain.consumers.validator.IValidatorClient;
import feign.Contract;
import feign.Feign;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

@Component
@Import(FeignClientsConfiguration.class)
public class ValidatorFeignConfiguration {

  @Getter
  private IValidatorClient validatorClient;

  public ValidatorFeignConfiguration(
      Decoder decoder,
      Encoder encoder,
      Contract contract,
      @Value("${validator.url}") String validatorUrl) {
    this.validatorClient =
        Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(IValidatorClient.class, validatorUrl);
  }
}
