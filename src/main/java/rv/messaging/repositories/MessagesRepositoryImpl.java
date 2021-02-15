package rv.messaging.repositories;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import rv.messaging.models.Message;

import java.util.Set;

public class MessagesRepositoryImpl implements MessagesRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    public MessagesRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void markAsRead(Set<ObjectId> ids) {
        mongoTemplate.updateMulti(
                Query.query(Criteria.where(Message.Fields._id).in(ids)),
                Update.update(Message.Fields.read, true),
                Message.class);
    }
}
