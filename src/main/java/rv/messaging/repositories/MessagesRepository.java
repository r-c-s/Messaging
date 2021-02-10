package rv.messaging.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rv.messaging.models.Message;

public interface MessagesRepository extends MongoRepository<Message, String> {
}
