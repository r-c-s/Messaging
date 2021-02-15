package rcs.messaging.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import rcs.messaging.models.Message;
import rcs.messaging.models.MessageSendRequest;
import rcs.messaging.repositories.MessagesRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private MessagesRepository repository;

    public MessageService(MessagesRepository repository) {
        this.repository = repository;
    }

    public void sendMessage(String sender, MessageSendRequest request, LocalDateTime date) {
        // todo: check if receiver exists
        Message message = new Message(
                new ObjectId(),
                sender,
                request.getTo(),
                request.getSubject(),
                request.getBody(),
                date,
                false);

        repository.save(message);
    }

    public List<Message> getInbox(String user, boolean unreadOnly) {
        List<Message> messages = repository.getInbox(user, unreadOnly);
        Set<ObjectId> ids = messages.stream()
                .map(Message::get_id)
                .collect(Collectors.toSet());
        repository.markAsRead(ids);
        return messages;
    }

    public List<Message> getOutbox(String user, boolean unreadOnly) {
        return repository.getOutbox(user, unreadOnly);
    }
}
