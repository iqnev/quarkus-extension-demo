package org.iqnev.demo.client.extension.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import org.iqnev.demo.client.extension.runtime.beans.DemoClientProducer;

class DemoClientExtensionProcessor {

  private static final String FEATURE = "demo-client-extension";

  @BuildStep
  FeatureBuildItem feature() {
    return new FeatureBuildItem(FEATURE);
  }

  @BuildStep
  AdditionalBeanBuildItem indexBeans() {
    return new AdditionalBeanBuildItem(DemoClientProducer.class);
  }
}
