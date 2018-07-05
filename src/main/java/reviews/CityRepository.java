package reviews;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Long> {

	Collection<City> findByCountry(Country country);

	Collection<City> findByCountryId(long id);

	Collection<City> findByYear(Year year1);

	Collection<City> findByYearId(long id);

}
