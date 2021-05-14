package com.prueba.blockchain.pruebablockchain.consumers.sawtooth;

import com.prueba.blockchain.pruebablockchain.configuration.SawtoothFeignConfiguration;
import org.springframework.stereotype.Service;

@Service
public class SawtoothConsumer {

  private final SawtoothFeignConfiguration sawtoothFeignConfiguration;

  public SawtoothConsumer(SawtoothFeignConfiguration sawtoothFeignConfiguration) {
    this.sawtoothFeignConfiguration = sawtoothFeignConfiguration;
  }

  public void postTransaction(byte[] batchListBytes) {
    sawtoothFeignConfiguration.getSawtoothClient().postBatchList(batchListBytes);
  }
}
