package rv.messaging.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import rv.messaging.models.Message;
import rv.messaging.models.MessageSendRequest;
import rv.messaging.repositories.MessagesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    private MessagesRepository repository;

    public MessageService(MessagesRepository repository) {
        this.repository = repository;
    }

    public void sendMessage(String sender, MessageSendRequest request, LocalDateTime date) {
        Message message = new Message(
                new ObjectId(),
                sender,
                request.getTo(),
                request.getSubject(),
                request.getBody(),
                date);

        repository.save(message);
    }

    public List<Message> getInbox(String user) {
        return repository.findByTo(user);
    }

    public List<Message> getOutbox(String user) {
        return repository.findByFrom(user);
    }
}
