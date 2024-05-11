package org.whatever.db.server;

import lombok.extern.slf4j.Slf4j;
import org.whatever.db.server.command.ServerCommand;
import org.whatever.db.server.factory.CommandFactory;
import picocli.CommandLine;

@Slf4j
public class Application {
  private static final CommandLine cmd = new CommandLine(ServerCommand.class, new CommandFactory());

  static {
    log.atInfo().log("MapDB Starting....");
  }

  public static void main(String[] args) {
    cmd.execute(args);
    log.atInfo().log("MapDB Started");
  }
}
