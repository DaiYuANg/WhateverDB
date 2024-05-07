package org.mapdb.server.factory;

import org.mapdb.server.context.DIContext;
import picocli.CommandLine;

import java.util.NoSuchElementException;

public class CommandFactory implements CommandLine.IFactory {
  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return DIContext.INSTANCE.getBeanScope().get(aClass);
    } catch (NoSuchElementException ex) {
      return CommandLine.defaultFactory().create(aClass);
    }
  }
}
