package dev.emmanuel.graphql.types;

import io.smallrye.graphql.api.federation.Key;

@Key(fields = "iban")
public class Account {
  private String iban;

  public Account() {

  }

  public Account(String iban) {
    this.iban = iban;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }
}
