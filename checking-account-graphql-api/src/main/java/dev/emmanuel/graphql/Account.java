package dev.emmanuel.graphql;

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
