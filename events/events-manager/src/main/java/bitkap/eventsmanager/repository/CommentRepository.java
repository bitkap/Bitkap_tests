package bitkap.eventsmanager.repository;

import bitkap.eventsmanager.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

}
