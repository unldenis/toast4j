package com.github.unldenis.toast4j;


import java.io.IOException;

/**
 * Notify - Insert description here.
 *
 * @author Denis Mehilli
 * @creation 16/01/2025
 */
interface Notify {

  class Internal {
    // cross-platform
    static Notify INSTANCE;

    static {
      switch (OsCheck.getOperatingSystemType()) {
        case Windows:
          INSTANCE = new NotifyWindows();
          break;
        case MacOS:
          INSTANCE = new NotifyDarwin();
          break;
        case Linux:
          INSTANCE = new NotifyUnix();
          break;
        case Other:
      }
    }
  }

  void push( Notification notification) throws Toast4jException;


  /**
   * Finds the first available executable from a list of names.
   *
   * @param commands Array of command names to search for.
   * @return The name of the first found command, or null if none are found.
   */
  default String findExecutable(String... commands) {
    for (String command : commands) {
      try {
        Process process = new ProcessBuilder("which", command).start();
        if (process.waitFor() == 0) {
          return command;
        }
      } catch (Exception ignored) {}
    }
    return null;
  }

  default void executeProcess(String... command) throws IOException, InterruptedException {
    ProcessBuilder processBuilder = new ProcessBuilder(command);
    processBuilder.redirectErrorStream(true);

    // Start the process
    Process process = processBuilder.start();

    // Wait for the process to complete
    int exitCode = process.waitFor();
    if (exitCode != 0) {
      throw new IOException("Failed to send notification. Exit code: " + exitCode);
    }

  }



}
