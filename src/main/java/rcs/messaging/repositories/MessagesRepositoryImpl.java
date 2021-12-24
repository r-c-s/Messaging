package rcs.messaging.repositories;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import rcs.messaging.models.Message;

import java.util.List;
import java.util.Set;

public class MessagesRepositoryImpl implements MessagesRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MessagesRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Message> getInbox(String user, boolean unreadOnly) {
        Query query = Query.query(Criteria.where(Message.Fields.to).is(user));
        if (unreadOnly) {
            query = query.addCriteria(Criteria.where(Message.Fields.read).is(false));
        }
        return mongoTemplate.find(query, Message.class);
    }

    @Override
    public List<Message> getOutbox(String user, boolean unreadOnly) {
        Query query = Query.query(Criteria.where(Message.Fields.from).is(user));
        if (unreadOnly) {
            query = query.addCriteria(Criteria.where(Message.Fields.read).is(false));
        }
        return mongoTemplate.find(query, Message.class);
    }

    @Override
    public void markAsRead(Set<ObjectId> ids) {
        mongoTemplate.updateMulti(
                Query.query(Criteria.where(Message.Fields._id).in(ids)),
                Update.update(Message.Fields.read, true),
                Message.class);
    }
}
