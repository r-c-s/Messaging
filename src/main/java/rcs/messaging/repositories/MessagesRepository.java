package rcs.messaging.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import rcs.messaging.models.Message;

public interface MessagesRepository extends MongoRepository<Message, String>, MessagesRepositoryCustom {
}
