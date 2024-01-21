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

  private volatile DemoClient demoClient;

  void initialize(final DemoClient demoClient) {
    this.demoClient = demoClient;
  }

  @Produces
  @Singleton
  @DefaultBean
  public DemoClient createClient() {
    return demoClient;
  }
}
