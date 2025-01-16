package com.github.unldenis;

import com.github.unldenis.toast4j.Notification;
import com.github.unldenis.toast4j.Toast4jException;

public class Main {

  public static void main(String[] args) throws Toast4jException {

    Notification notification = Notification.builder()
        .appID("EasyML")
        .title("Bentornato")
        .message("Ciao Denis")
        .build();

    notification.push();
  }
}