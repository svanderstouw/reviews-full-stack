package reviews;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {

	Collection<Tag> findByCitiesContains(City city);

	Collection<Tag> findByCitiesId(long id);

	Optional<Tag> findByTagName(String tagName);

}
