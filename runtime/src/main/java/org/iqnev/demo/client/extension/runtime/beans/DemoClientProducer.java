package org.iqnev.demo.client.extension.runtime.beans;

import io.quarkus.arc.DefaultBean;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.iqnev.demo.client.extension.runtime.DemoClient;
import org.iqnev.demo.client.extension.runtime.config.DemoClientConfig;

@ApplicationScoped
public class DemoClientProducer {

  final DemoClientConfig demoClientConfig;

  public DemoClientProducer(DemoClientConfig demoClientConfig) {
    this.demoClientConfig = demoClientConfig;
  }

  @Produces
  @Singleton
  @DefaultBean
  public DemoClient createClient() {
    return new DemoClient(demoClientConfig.demoMessage());
  }
}
