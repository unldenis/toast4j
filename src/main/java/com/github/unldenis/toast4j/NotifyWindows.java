package com.github.unldenis.toast4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * NotifyWindows - Insert description here.
 *
 * @author Denis Mehilli
 * @creation 16/01/2025
 */
class NotifyWindows implements Notify {

  @Override
  public void push(Notification notification) throws Toast4jException {
    try {
      String xml = buildXML(notification);

      File tempFile = File.createTempFile("toast4j-", ".ps1");
      tempFile.deleteOnExit();
      Path path = tempFile.toPath();

      Files.write(path, xml.getBytes());

      // Start the process
      executeProcess("PowerShell", "-ExecutionPolicy", "Bypass", "-File", path.toString());

    } catch (IOException | InterruptedException e) {
      throw new Toast4jException(e);
    }
  }

  /**
   * Builds an XML template based on the notification details.
   *
   * This method generates an XML string representing the toast notification.
   * The output can be used in a PowerShell script to send the notification.
   *
   * @return A string containing the XML template of the notification.
   */
  private String buildXML(Notification notification) throws IOException {
    StringBuilder buffer = new StringBuilder();

    // Initial imports (required to use WinRT APIs in PowerShell)
    buffer.append("[Windows.UI.Notifications.ToastNotificationManager, Windows.UI.Notifications, ContentType = WindowsRuntime] | Out-Null\n")
        .append("[Windows.UI.Notifications.ToastNotification, Windows.UI.Notifications, ContentType = WindowsRuntime] | Out-Null\n")
        .append("[Windows.Data.Xml.Dom.XmlDocument, Windows.Data.Xml.Dom.XmlDocument, ContentType = WindowsRuntime] | Out-Null\n\n");

    // Define the application ID
    buffer.append("$APP_ID = '").append(notification.getAppID()).append("'\n\n");

    // Toast XML template
    buffer.append("$template = @\"\n")
        .append("<toast activationType='").append(notification.getActivationType())
        .append("' launch='").append(notification.getActivationArguments())
        .append("' duration='").append(notification.getToastDuration().getValue()).append("'>\n")
        .append("    <visual>\n")
        .append("        <binding template='ToastGeneric'>\n");

    // Add image if present
    if (notification.getIcon() != null) {
      buffer.append("            <image placement='appLogoOverride' src='").append(notification.getIcon().toRealPath().toString()).append("' />\n");
    }

    // Add title if present
    if (notification.getTitle() != null && !notification.getTitle().isEmpty()) {
      buffer.append("            <text><![CDATA[").append(notification.getTitle()).append("]]></text>\n");
    }

    // Add message if present
    if (notification.getMessage() != null && !notification.getMessage().isEmpty()) {
      buffer.append("            <text><![CDATA[").append(notification.getMessage()).append("]]></text>\n");
    }

    buffer.append("        </binding>\n")
        .append("    </visual>\n");

    // Configure audio
    if (!notification.getToastAudio().equals(Audio.SILENT)) {
      buffer.append("    <audio src='").append(notification.getToastAudio().getValue()).append("' loop='").append(notification.isLoop()).append("' />\n");
    } else {
      buffer.append("    <audio silent='true' />\n");
    }

    // Add actions if present
    if (notification.getActions() != null && notification.getActions().length > 0) {
      buffer.append("    <actions>\n");
      for (Action action : notification.getActions()) {
        buffer.append("        <action activationType='").append(action.getType())
            .append("' content='").append(action.getLabel())
            .append("' arguments='").append(action.getArguments()).append("' />\n");
      }
      buffer.append("    </actions>\n");
    }

    // Close the toast XML
    buffer.append("</toast>\n")
        .append("\"@\n\n");

    // Command to load and send the notification
    buffer.append("$xml = New-Object Windows.Data.Xml.Dom.XmlDocument\n")
        .append("$xml.LoadXml($template)\n")
        .append("$toast = New-Object Windows.UI.Notifications.ToastNotification $xml\n")
        .append("[Windows.UI.Notifications.ToastNotificationManager]::CreateToastNotifier($APP_ID).Show($toast)\n");

    return buffer.toString();
  }
}
