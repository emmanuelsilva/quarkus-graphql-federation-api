package dev.emmanuel.graphql;

import dev.emmanuel.graphql.types.Account;
import dev.emmanuel.graphql.types.Customer;
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
