## toast4j

`toast4j` provides a cross-platform library for sending desktop notifications.

### Examples

```java
public static void main(String[] args) {

  try {
    Notification notification = Notification.builder()
        .appID("Toast4j")
        .title("Welcome back")
        .message("How are you?")
        .build();

    notification.push();
  } catch (Toast4jException e) {
    // 
  }

}
```
