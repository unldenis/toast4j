package com.github.unldenis.toast4j;

import com.github.unldenis.toast4j.Notify.Internal;
import java.nio.file.Path;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;

/**
 * Notification - Class representing a toast notification for Windows.
 * <p>
 * This class allows you to configure a toast notification with various details
 * such as title, message, image, audio, and actions. The notification can be
 * converted into an XML template used for sending notifications via PowerShell.
 * <p>
 * Author: Denis Mehilli
 * Created on: 16/01/2025
 */
@AllArgsConstructor
@Getter
@Builder
public class Notification {

  // The application ID generating the notification
  @Builder.Default
  private String appID = "toast4j";

  // The title of the notification
  @Builder.Default
  private String title = "";

  // The message of the notification
  @Builder.Default
  private String message = "";

  // Path to the icon to be displayed in the notification
  @Builder.Default
  private Path icon = null;

  // Activation arguments associated with the notification (e.g., URL or commands)
  @Builder.Default
  private String activationArguments = "https://www.google.com/";

  // Interactive actions available in the notification
  @Builder.Default
  private Action[] actions = new Action[0];

  // Activation type (e.g., protocol to open a URL)
  @Builder.Default
  private String activationType = "protocol";

  // Duration of the notification display (short or long)
  @Builder.Default
  private Duration toastDuration = Duration.SHORT;

  // Type of audio to play with the notification
  @Builder.Default
  private Audio toastAudio = Audio.DEFAULT;

  // Whether the audio should loop
  @Builder.Default
  private boolean loop = false;


  public void push() throws Toast4jException {
    if(Internal.INSTANCE == null) {
      throw new Toast4jException("Unsupported operating system");
    }
    Internal.INSTANCE.push(this);
  }

}
