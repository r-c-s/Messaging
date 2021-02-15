package rcs.messaging.repositories;

import org.bson.types.ObjectId;
import rcs.messaging.models.Message;

import java.util.List;
import java.util.Set;

public interface MessagesRepositoryCustom {

    List<Message> getInbox(String user, boolean unreadOnly);
    List<Message> getOutbox(String user, boolean unreadOnly);
    void markAsRead(Set<ObjectId> ids);
}
