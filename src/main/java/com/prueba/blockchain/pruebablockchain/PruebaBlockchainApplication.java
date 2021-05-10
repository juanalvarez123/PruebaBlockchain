package com.prueba.blockchain.pruebablockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class PruebaBlockchainApplication {

  public static void main(String[] args) {
    SpringApplication.run(PruebaBlockchainApplication.class, args);
  }
}
