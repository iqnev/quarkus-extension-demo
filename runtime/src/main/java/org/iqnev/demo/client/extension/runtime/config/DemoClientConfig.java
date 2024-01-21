package org.iqnev.demo.client.extension.runtime.config;

import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;
import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "demo.client")
@ConfigRoot(phase = ConfigPhase.RUN_TIME)
public interface DemoClientConfig {

  /**
   *
   * @return
   */
  String demoMessage();
}
