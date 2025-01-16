package com.github.unldenis.toast4j;

import lombok.Getter;

/**
 * Audio - Enum representing supported notification sounds.
 * This enum maps various audio types used for Windows notifications.
 *
 * Each constant corresponds to a specific sound event that can be used
 * in toast notifications.
 *
 * Example:
 * Audio.DEFAULT -> Default notification sound.
 * Audio.SILENT -> No sound.
 *
 * Author: Denis Mehilli
 * Created on: 16/01/2025
 */
@Getter
public enum Audio {
  DEFAULT("ms-winsoundevent:Notification.Default"),
  IM("ms-winsoundevent:Notification.IM"),
  MAIL("ms-winsoundevent:Notification.Mail"),
  REMINDER("ms-winsoundevent:Notification.Reminder"),
  SMS("ms-winsoundevent:Notification.SMS"),
  LOOPING_ALARM("ms-winsoundevent:Notification.Looping.Alarm"),
  LOOPING_ALARM2("ms-winsoundevent:Notification.Looping.Alarm2"),
  LOOPING_ALARM3("ms-winsoundevent:Notification.Looping.Alarm3"),
  LOOPING_ALARM4("ms-winsoundevent:Notification.Looping.Alarm4"),
  LOOPING_ALARM5("ms-winsoundevent:Notification.Looping.Alarm5"),
  LOOPING_ALARM6("ms-winsoundevent:Notification.Looping.Alarm6"),
  LOOPING_ALARM7("ms-winsoundevent:Notification.Looping.Alarm7"),
  LOOPING_ALARM8("ms-winsoundevent:Notification.Looping.Alarm8"),
  LOOPING_ALARM9("ms-winsoundevent:Notification.Looping.Alarm9"),
  LOOPING_ALARM10("ms-winsoundevent:Notification.Looping.Alarm10"),
  LOOPING_CALL("ms-winsoundevent:Notification.Looping.Call"),
  LOOPING_CALL2("ms-winsoundevent:Notification.Looping.Call2"),
  LOOPING_CALL3("ms-winsoundevent:Notification.Looping.Call3"),
  LOOPING_CALL4("ms-winsoundevent:Notification.Looping.Call4"),
  LOOPING_CALL5("ms-winsoundevent:Notification.Looping.Call5"),
  LOOPING_CALL6("ms-winsoundevent:Notification.Looping.Call6"),
  LOOPING_CALL7("ms-winsoundevent:Notification.Looping.Call7"),
  LOOPING_CALL8("ms-winsoundevent:Notification.Looping.Call8"),
  LOOPING_CALL9("ms-winsoundevent:Notification.Looping.Call9"),
  LOOPING_CALL10("ms-winsoundevent:Notification.Looping.Call10"),
  SILENT("silent");

  /**
   * -- GETTER --
   *  Returns the string value associated with the audio type.
   *
   * @return The string representation of the audio type.
   */
  private final String value;

  /**
   * Constructor for the Audio enum.
   *
   * @param value The string representation of the audio type.
   */
  Audio(String value) {
    this.value = value;
  }

  /**
   * Returns an Audio enum constant based on the provided name.
   * If the name does not match any constant, the default audio (DEFAULT) is returned.
   *
   * @param name The name of the audio type.
   * @return The corresponding Audio enum constant, or DEFAULT if not found.
   */
  public static Audio fromName(String name) {
    for (Audio audio : values()) {
      if (audio.name().equalsIgnoreCase(name)) {
        return audio;
      }
    }
    return DEFAULT; // Fallback to default audio
  }
}
