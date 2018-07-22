package reviews;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

	Collection<City> findByCountry(Country country);

	Collection<City> findByCountryId(long id);

	Collection<City> findByYear(Year year);

	Collection<City> findByYearId(long id);

	Collection<City> findByTagsContains(Tag tag);

	Collection<City> findByTagsId(long id);

	Optional<City> findByCityName(String cityName);

}
