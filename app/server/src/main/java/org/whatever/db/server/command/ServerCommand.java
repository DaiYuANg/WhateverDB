package org.whatever.db.server.command;

import io.avaje.inject.Component;
import lombok.RequiredArgsConstructor;
import org.whatever.db.server.provider.ManifestVersionProvider;
import org.whatever.db.server.MapDBServer;
import picocli.CommandLine;

@CommandLine.Command(name = "MapDbServer", helpCommand = true, versionProvider = ManifestVersionProvider.class)
@RequiredArgsConstructor
@Component
public class ServerCommand implements Runnable {

  private final MapDBServer mapDBServer;

  @CommandLine.Spec
  CommandLine.Model.CommandSpec spec;

  @CommandLine.Option(names = {"-p", "--port"}, description = "server port")
  Integer flag;

  @Override
  public void run() {
    System.err.println(spec);
    mapDBServer.startServer();
  }
}
