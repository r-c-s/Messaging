package rv.messaging.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rv.messaging.models.Message;

import java.util.List;

public interface MessagesRepository extends MongoRepository<Message, String>, MessagesRepositoryCustom {

    List<Message> findByTo(String to);
    List<Message> findByFrom(String from);
}
