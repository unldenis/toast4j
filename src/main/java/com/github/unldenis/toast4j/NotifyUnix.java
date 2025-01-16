package com.github.unldenis.toast4j;

import java.io.IOException;
import java.nio.file.Path;

/**
 * NotifyUnix - Insert description here.
 *
 * @author Denis Mehilli
 * @creation 16/01/2025
 */
class NotifyUnix implements Notify {

  @Override
  public void push(Notification notification) throws Toast4jException {
    // Try to send a notification with `notify-send` or `sw-notify-send`
    if (!tryNotifySend(notification.getTitle(),notification.getMessage(), notification.getIcon())) {
      // Fallback to `kdialog` if `notify-send` is unavailable
      if (!tryKDialog(notification.getTitle(),notification.getMessage(), notification.getIcon())) {
        throw new Toast4jException("No supported notification tools (notify-send, kdialog) found on this system.");
      }
    }
  }


  /**
   * Attempts to send a notification using `notify-send` or `sw-notify-send`.
   *
   * @param title   The title of the notification.
   * @param message The message to display in the notification.
   * @param icon    The path to the icon file (can be null).
   * @return True if the notification was sent successfully, otherwise false.
   */
  private boolean tryNotifySend(String title, String message, Path icon) {
    try {
      String command = findExecutable("sw-notify-send", "notify-send");
      if (command == null) {
        return false; // Neither `sw-notify-send` nor `notify-send` found
      }

      if(icon == null) {
        executeProcess(command, title, message);
      } else  {
        executeProcess(command, title, message, "-i", icon.toString());
      }

      return true;
    } catch (IOException | InterruptedException e) {
      return false;
    }
  }

  /**
   * Attempts to send a notification using `kdialog`.
   *
   * @param title   The title of the notification.
   * @param message The message to display in the notification.
   * @param icon    The path to the icon file (can be null).
   * @return True if the notification was sent successfully, otherwise false.
   */
  private boolean tryKDialog(String title, String message, Path icon) {
    try {
      String command = findExecutable("kdialog");
      if (command == null) {
        return false; // `kdialog` not found
      }

      if(icon == null) {
        executeProcess(command, "--title", title, "--passivepopup", message, "10");
      } else  {
        executeProcess(command, "--title", title, "--passivepopup", message, "10", "--icon", icon.toString());
      }

      return true;
    } catch (IOException | InterruptedException e) {
      return false;
    }
  }

}
