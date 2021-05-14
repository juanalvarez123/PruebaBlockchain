package com.prueba.blockchain.pruebablockchain.configuration;

import com.prueba.blockchain.pruebablockchain.consumers.sawtooth.ISawtoothClient;
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
public class SawtoothFeignConfiguration {

  @Getter
  private ISawtoothClient sawtoothClient;

  public SawtoothFeignConfiguration(
      Decoder decoder,
      Encoder encoder,
      Contract contract,
      @Value("${sawtooth.api.url}") String sawtoothApiUrl) {
    this.sawtoothClient =
        Feign.builder()
            .encoder(encoder)
            .decoder(decoder)
            .contract(contract)
            .target(ISawtoothClient.class, sawtoothApiUrl);
  }
}
