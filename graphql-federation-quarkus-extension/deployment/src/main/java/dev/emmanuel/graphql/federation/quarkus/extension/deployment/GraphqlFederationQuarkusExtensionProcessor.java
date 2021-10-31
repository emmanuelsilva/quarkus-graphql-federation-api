package dev.emmanuel.graphql.federation.quarkus.extension.deployment;

import dev.emmanuel.graphql.federation.quarkus.extension.EntitiesRecorder;
import dev.emmanuel.graphql.federation.quarkus.extension.GraphqlFederationSupport;
import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.ApplicationIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.vertx.http.deployment.NonApplicationRootPathBuildItem;
import io.quarkus.vertx.http.deployment.RouteBuildItem;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.DotName;
import org.jboss.logging.Logger;

import java.util.stream.Collectors;

import static io.quarkus.deployment.annotations.ExecutionTime.RUNTIME_INIT;

class GraphqlFederationQuarkusExtensionProcessor {

    private static final String FEATURE = "graphql-federation-quarkus-extension";
    private static final Logger LOG = Logger.getLogger(GraphqlFederationQuarkusExtensionProcessor.class.getName());
    private static final DotName KEY = DotName.createSimple("io.smallrye.graphql.federation.api.Key");

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    EntitiesBuildItem findKeyDirectives(ApplicationIndexBuildItem index) {
        var entities = new EntitiesBuildItem();
        System.out.println("Finding keys");

        for (AnnotationInstance keyAnnotation : index.getIndex().getAnnotations(KEY)) {
            var annotationTarget = keyAnnotation.target().asClass();
            LOG.info("found " + keyAnnotation + " on " + annotationTarget);
            entities.add(annotationTarget.name().toString());
        }

        return entities;
    }

    @BuildStep
    @Record(RUNTIME_INIT)
    RouteBuildItem devModeRoute(
      NonApplicationRootPathBuildItem nonApp,
      EntitiesBuildItem entitiesBuildItem,
      EntitiesRecorder entitiesRecorder
    ) {

        LOG.info("Generating dev rule for the following entities: " +
          entitiesBuildItem.getEntities().stream().collect(Collectors.joining(",")));

        return nonApp.routeBuilder()
          .route(FEATURE)
          .handler(entitiesRecorder.createHandler(entitiesBuildItem.getEntities()))
          .displayOnNotFoundPage()
          .build();
    }

    @BuildStep
    AdditionalBeanBuildItem beans() {
        LOG.info("adding custom federation bean");
        return new AdditionalBeanBuildItem(GraphqlFederationSupport.class);
    }

}
