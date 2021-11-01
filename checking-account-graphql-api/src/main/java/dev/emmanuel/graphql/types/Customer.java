package dev.emmanuel.graphql.types;

import io.smallrye.graphql.api.federation.Extends;
import io.smallrye.graphql.api.federation.External;
import io.smallrye.graphql.api.federation.Key;

@Key(fields = "id")
@Extends
public class Customer {

  @External
  private String id;

  private Account account;

  public Customer() {

  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }
}
