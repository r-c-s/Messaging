package rv.messaging.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
public class Message {

    @Id
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime date;
}
