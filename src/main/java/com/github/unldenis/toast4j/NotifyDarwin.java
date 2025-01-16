package com.github.unldenis.toast4j;

import java.io.IOException;

/**
 * NotifyDarwin - Insert description here.
 *
 * @author Denis Mehilli
 * @creation 16/01/2025
 */
class NotifyDarwin implements Notify {

  @Override
  public void push(Notification notification) throws Toast4jException {
    try {
      // Construct the AppleScript command
      String script = String.format("display notification \"%s\" with title \"%s\"", notification.getMessage(), notification.getTitle());

      // Use the `osascript` utility to execute the script
      executeProcess("osascript", "-e", script);
    } catch (IOException | InterruptedException e) {
      throw new Toast4jException(e);
    }
  }
}
