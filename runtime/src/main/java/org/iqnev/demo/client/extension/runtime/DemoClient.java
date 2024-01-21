package org.iqnev.demo.client.extension.runtime;

public class DemoClient {

  private final String demoMessage;

  public DemoClient(final String demoMessage) {
    this.demoMessage = demoMessage;
  }

  public String doSomeDemoAction(final String msg) {
    System.out.printf("############## HERE ################");

    return  msg + demoMessage;
  }
}
