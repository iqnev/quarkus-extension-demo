package org.iqnev.demo.client.extension.runtime.beans;

import io.quarkus.arc.runtime.BeanContainer;
import io.quarkus.runtime.annotations.Recorder;
import org.iqnev.demo.client.extension.runtime.DemoClient;
import org.iqnev.demo.client.extension.runtime.config.DemoClientConfig;

@Recorder
public class DemoClientRecorder {

  public void initDemoClient(
      final BeanContainer container, final DemoClientConfig demoClientConfig) {
    final DemoClient demoClient = createDemoClient(demoClientConfig);

    final DemoClientProducer demoClientProducer = container.beanInstance(DemoClientProducer.class);

    demoClientProducer.initialize(demoClient);
  }

  private DemoClient createDemoClient(final DemoClientConfig demoClientConfig) {
    return new DemoClient(demoClientConfig.demoMessage());
  }
}
