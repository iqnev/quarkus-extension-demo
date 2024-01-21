package org.iqnev.demo.client.extension.deployment;

import io.quarkus.arc.deployment.AdditionalBeanBuildItem;
import io.quarkus.arc.deployment.BeanContainerBuildItem;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import org.iqnev.demo.client.extension.runtime.beans.DemoClientProducer;
import org.iqnev.demo.client.extension.runtime.beans.DemoClientRecorder;
import org.iqnev.demo.client.extension.runtime.config.DemoClientConfig;

class DemoClientExtensionProcessor {

  private static final String FEATURE = "demo-client-extension";

  @BuildStep
  FeatureBuildItem feature() {
    return new FeatureBuildItem(FEATURE);
  }

  @BuildStep
  AdditionalBeanBuildItem indexBeans() {
    return AdditionalBeanBuildItem.unremovableOf(DemoClientProducer.class);
  }

  @BuildStep
  @Record(ExecutionTime.STATIC_INIT)
  void initializeDemoClient(BeanContainerBuildItem beanContainer, DemoClientRecorder demoClientRecorder, DemoClientConfig demoClientConfig) {

    demoClientRecorder.initDemoClient(beanContainer.getValue(), demoClientConfig);
  }
}
