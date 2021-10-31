package dev.emmanuel.graphql.federation.quarkus.extension;


import graphql.schema.GraphQLSchema;
import io.smallrye.graphql.spi.config.Config;
import org.eclipse.microprofile.graphql.GraphQLApi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@GraphQLApi
@ApplicationScoped
public class GraphqlFederationSupport {

  public GraphQLSchema.Builder beforeSchemaBuild(@Observes GraphQLSchema.Builder builder) {
    System.out.println("Federation is " + Config.get().isFederationEnabled());
    return builder;
  }

}
