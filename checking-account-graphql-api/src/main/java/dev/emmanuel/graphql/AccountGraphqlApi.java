package dev.emmanuel.graphql;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;

@GraphQLApi
public class AccountGraphqlApi {

  @Query("account")
  @Description("Get Account")
  public Account account(int customerId) {
    return new Account("iban-" + customerId);
  }

  @Query
  public Customer dummy() {
    return null;
  }

}
