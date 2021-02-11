package rv.messaging.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "messages")
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Message {

    @Id
    private ObjectId _id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime date;
}
