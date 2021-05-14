package com.prueba.blockchain.pruebablockchain.model;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationDTO implements Serializable {

  private String authorizationId;
  private String doctorSign;
  private String description;
}
