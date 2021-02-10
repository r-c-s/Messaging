package rv.messaging.models;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Message {

    @Id
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime date;
}
