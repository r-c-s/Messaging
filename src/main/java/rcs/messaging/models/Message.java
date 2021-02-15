package rcs.messaging.models;

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

    public static class Fields {
        public static final String _id = "_id";
        public static final String from = "from";
        public static final String to = "to";
        public static final String subject = "subject";
        public static final String body = "body";
        public static final String date = "date";
        public static final String read = "read";
    }

    @Id
    private ObjectId _id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private LocalDateTime date;
    private boolean read;
}
