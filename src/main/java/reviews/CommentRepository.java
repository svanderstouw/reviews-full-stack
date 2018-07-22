package reviews;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {

	Collection<Comment> findByCity(City city);

	Collection<Comment> findByCityId(long id);

	Optional<Comment> findByCommentText(String commentText);

}
