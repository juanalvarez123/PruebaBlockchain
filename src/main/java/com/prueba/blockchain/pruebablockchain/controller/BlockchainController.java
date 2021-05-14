package com.prueba.blockchain.pruebablockchain.controller;

import com.prueba.blockchain.pruebablockchain.service.IntegerKeyService;
import com.prueba.blockchain.pruebablockchain.service.Tp1Service;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlockchainController {

  private final IntegerKeyService integerKeyService;

  private final Tp1Service tp1Service;

  public BlockchainController(IntegerKeyService integerKeyService,
      Tp1Service tp1Service) {
    this.integerKeyService = integerKeyService;
    this.tp1Service = tp1Service;
  }

  @PostMapping("intkey")
  public ResponseEntity createIntegerKeyTransaction() throws NoSuchAlgorithmException {
    integerKeyService.createTransaction();
    return ResponseEntity.ok().build();
  }

  @PostMapping("tp1")
  public ResponseEntity createTp1Transaction() throws NoSuchAlgorithmException, IOException {
    tp1Service.createTransaction();
    return ResponseEntity.ok().build();
  }
}
