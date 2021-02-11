package rv.messaging.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageSendRequest {

    private String to;
    private String subject;
    private String body;
}
