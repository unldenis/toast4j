package com.github.unldenis.toast4j;

import lombok.Getter;

/**
 * Duration - Insert description here.
 *
 * @author Denis Mehilli
 * @creation 16/01/2025
 */
@Getter
public enum Duration {
  SHORT("short"),
  LONG("value")
  ;

  private final String value;

  Duration(String value) {
    this.value = value;
  }

}
