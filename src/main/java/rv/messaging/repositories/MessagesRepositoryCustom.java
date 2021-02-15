package rv.messaging.repositories;

import org.bson.types.ObjectId;

import java.util.Set;

public interface MessagesRepositoryCustom {

    void markAsRead(Set<ObjectId> ids);
}
