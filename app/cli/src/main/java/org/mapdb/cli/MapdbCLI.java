package org.mapdb.cli;

import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine;

@CommandLine.Command(name = "mapdb", mixinStandardHelpOptions = true)
public class MapdbCLI implements Runnable {
  static {
    AnsiConsole.systemInstall();
  }

  public static void main(String... args) {
    int exitCode = new CommandLine(new MapdbCLI()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public void run() {

  }
}