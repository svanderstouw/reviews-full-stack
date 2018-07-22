package reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ReviewsJPAMappingsTest {
	
	@Resource
	private TestEntityManager entityManager;
	
	@Resource
	private CountryRepository countryRepo;
	
	@Resource
	private CityRepository cityRepo;
	
	@Resource
	private YearRepository yearRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Test
	public void shouldSaveAndLoadCountry() {
		Country country = countryRepo.save(new Country("country", "imageURL"));
		long countryId = country.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Country> result = countryRepo.findById(countryId);
		country = result.get();
		assertThat(country.getCountryName(), is("country"));
	}
	
	@Test
	public void shouldGenerateCountryId() {
		Country country = countryRepo.save(new Country("country", "imageURL"));
		long countryId = country.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(countryId, is(greaterThan(0L)));	
	}
	
	@Test
	public void shouldSaveAndLoadCity() {
		Country country = new Country("country name", "imageURL");
		countryRepo.save(country);
		Year year = new Year(2017);
		yearRepo.save(year);
		
		City city = new City("city name", "imageURL", "photo caption", "review text", country, year);
		city = cityRepo.save(city);
		long cityId = city.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<City> result = cityRepo.findById(cityId);
		city = result.get();
		assertThat(city.getCityName(), is("city name"));
	}
	
	@Test
	public void shouldEstablishCityToCountryRelationship() {
		Country country = new Country("country name", "imageURL");
		countryRepo.save(country);
		long countryId = country.getId();
		Year year = new Year(2017);
		yearRepo.save(year);
		
		City city1 = new City("city name 1", "imageURL", "photo caption", "review text", country, year);
		cityRepo.save(city1);
		
		City city2 = new City("city name 2", "imageURL", "photo caption", "review text", country, year);
		cityRepo.save(city2);
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Country> result = countryRepo.findById(countryId);
		country = result.get();
		assertThat(country.getCities(), containsInAnyOrder(city1, city2));
	}
	
	@Test
	public void shouldFindCitiesForCountry() {
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Country england = countryRepo.save(new Country("England", "imageURL"));
		Year year = new Year(2017);
		yearRepo.save(year);
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, year));
		City colmar = cityRepo.save(new City("Colmar", "imageURL", "photo caption", "review text", france, year));
		City london = cityRepo.save(new City("England", "imageURL", "photo caption", "review text", england, year));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<City> citiesForCountry = cityRepo.findByCountry(france);
		
		assertThat(citiesForCountry, containsInAnyOrder(paris, colmar));
	}
	
	@Test
	public void shouldFindCitiesForCountryId() {
		Country france = countryRepo.save(new Country("France", "imageURL"));
		long countryId = france.getId();
		Country england = countryRepo.save(new Country("England", "imageURL"));
		Year year = new Year(2017);
		yearRepo.save(year);
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, year));
		City colmar = cityRepo.save(new City("Colmar", "imageURL", "photo caption", "review text", france, year));
		City london = cityRepo.save(new City("England", "imageURL", "photo caption", "review text", england, year));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<City> citiesForCountry = cityRepo.findByCountryId(countryId);
		
		assertThat(citiesForCountry, containsInAnyOrder(paris, colmar));
	}
	
	@Test
	public void shouldSaveAndLoadYear() {
		Year year = yearRepo.save(new Year(2017));
		long yearId = year.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Year> result = yearRepo.findById(yearId);
		year = result.get();
		assertThat(year.getYear(), is(2017));
	}
	
	@Test
	public void shouldEstablishCityToYearRelationship() {
		Country country = new Country("country name", "imageURL");
		countryRepo.save(country);
		Year year = new Year(2017);
		yearRepo.save(year);
		long yearId = year.getId();
		
		City city1 = new City("city name 1", "imageURL", "photo caption", "review text", country, year);
		cityRepo.save(city1);
		
		City city2 = new City("city name 2", "imageURL", "photo caption", "review text", country, year);
		cityRepo.save(city2);
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Year> result = yearRepo.findById(yearId);
		year = result.get();
		assertThat(year.getCities(), containsInAnyOrder(city1, city2));
	}
	
	@Test
	public void shouldFindCitiesForYear() {
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Country england = countryRepo.save(new Country("England", "imageURL"));
		Year y2017 = new Year(2017);
		yearRepo.save(y2017);
		Year y2015 = new Year(2015);
		yearRepo.save(y2015);
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, y2017));
		City colmar = cityRepo.save(new City("Colmar", "imageURL", "photo caption", "review text", france, y2015));
		City london = cityRepo.save(new City("England", "imageURL", "photo caption", "review text", england, y2015));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<City> citiesForYear = cityRepo.findByYear(y2015);
		
		assertThat(citiesForYear, containsInAnyOrder(colmar, london));
	}
	
	@Test
	public void shouldFindCitiesForYearId() {
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Country england = countryRepo.save(new Country("England", "imageURL"));
		Year y2017 = new Year(2017);
		yearRepo.save(y2017);
		Year y2015 = new Year(2015);
		yearRepo.save(y2015);
		long yearId = y2015.getId();
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, y2017));
		City colmar = cityRepo.save(new City("Colmar", "imageURL", "photo caption", "review text", france, y2015));
		City london = cityRepo.save(new City("England", "imageURL", "photo caption", "review text", england, y2015));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<City> citiesForYear = cityRepo.findByYearId(yearId);
		
		assertThat(citiesForYear, containsInAnyOrder(colmar, london));
	}
	
	@Test
	public void shouldSaveAndLoadTag() {
		Tag tag = tagRepo.save(new Tag("tagName"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Tag> result = tagRepo.findById(tagId);
		tag = result.get();
		assertThat(tag.getName(), is("tagName"));
	}
	
	@Test
	public void shouldGenerateTagId() {
		Tag tag = tagRepo.save(new Tag("tagName"));
		long tagId = tag.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		assertThat(tagId, is(greaterThan(0L)));	
	}
	
	@Test
	public void shouldFindCitiesForTag() {
		Tag tag1 = tagRepo.save(new Tag("Tag 1"));
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Year y2017 = yearRepo.save(new Year(2017));
		
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, y2017, tag1));
		City colmar = cityRepo.save(new City("Colmar", "imageURL", "photo caption", "review text", france, y2017, tag1));
		City avignon = cityRepo.save(new City("Avignon", "imageURL", "photo caption", "review text", france, y2017));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<City> citiesForTag = cityRepo.findByTagsContains(tag1);
		assertThat(citiesForTag, containsInAnyOrder(paris, colmar));
	}
	
	@Test
	public void shouldFindCitiesForTagId() {
		Tag tag2 = tagRepo.save(new Tag("Tag 2"));
		long tagId = tag2.getId();
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Year y2017 = yearRepo.save(new Year(2017));
		
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, y2017));
		City colmar = cityRepo.save(new City("Colmar", "imageURL", "photo caption", "review text", france, y2017, tag2));
		City avignon = cityRepo.save(new City("Avignon", "imageURL", "photo caption", "review text", france, y2017, tag2));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<City> citiesForTag = cityRepo.findByTagsId(tagId);
		assertThat(citiesForTag, containsInAnyOrder(avignon, colmar));
	}
	
	@Test
	public void shouldFindTagsForCity() {
		Tag tag1 = tagRepo.save(new Tag("Tag 1"));
		Tag tag2 = tagRepo.save(new Tag("Tag 2"));
		Tag tag3 = tagRepo.save(new Tag("Tag 3"));
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Year y2017 = yearRepo.save(new Year(2017));
		
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, y2017, tag1, tag3));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Tag> tags = tagRepo.findByCitiesContains(paris);
		assertThat(tags, containsInAnyOrder(tag3, tag1));	
	}
	
	@Test
	public void shouldFindTagsForCityId() {
		Tag tag1 = tagRepo.save(new Tag("Tag 1"));
		Tag tag2 = tagRepo.save(new Tag("Tag 2"));
		Tag tag3 = tagRepo.save(new Tag("Tag 3"));
		Country france = countryRepo.save(new Country("France", "imageURL"));
		Year y2017 = yearRepo.save(new Year(2017));
		
		City paris = cityRepo.save(new City("Paris", "imageURL", "photo caption", "review text", france, y2017, tag1, tag3));
		long cityId = paris.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Tag> tags = tagRepo.findByCitiesId(cityId);
		assertThat(tags, containsInAnyOrder(tag3, tag1));	
	}
	
	@Test
	public void shouldSaveAndLoadComment() {
		Comment comment = commentRepo.save(new Comment("example"));
		long commentId = comment.getId();
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<Comment> result = commentRepo.findById(commentId);
		comment = result.get();
		assertThat(comment.getCommentText(), is("example"));
	}
	
	@Test
	public void shouldEstablishCommentToCityRelationship() {
		Country country = countryRepo.save(new Country("country name", "imageURL"));
		Year year = yearRepo.save(new Year(2017));
		City city = cityRepo.save(new City("city name 1", "imageURL", "photo caption", "review text", country, year));
		long cityId = city.getId();
		
		Comment comment1 = commentRepo.save(new Comment("example", "author", city));
		Comment comment2 = commentRepo.save(new Comment("example2", "author2", city));
		
		entityManager.flush();
		entityManager.clear();
		
		Optional<City> result = cityRepo.findById(cityId);
		city = result.get();
		assertThat(city.getComments(), containsInAnyOrder(comment1, comment2));
	}
	
	@Test
	public void shouldFindCommentsForCity() {
		Country country = countryRepo.save(new Country("country name", "imageURL"));
		Year year = yearRepo.save(new Year(2017));
		City city = cityRepo.save(new City("city name 1", "imageURL", "photo caption", "review text", country, year));
		City city2 = cityRepo.save(new City("city name 2", "imageURL", "photo caption", "review text", country, year));

		Comment comment1 = commentRepo.save(new Comment("example", "author", city));
		Comment comment2 = commentRepo.save(new Comment("example2", "author2", city2));
		Comment comment3 = commentRepo.save(new Comment("example3", "author3", city));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Comment> commentsForCity = commentRepo.findByCity(city);
		assertThat(commentsForCity, containsInAnyOrder(comment1, comment3));
	}
	
	@Test
	public void shouldFindCommentsForCityId() {
		Country country = countryRepo.save(new Country("country name", "imageURL"));
		Year year = yearRepo.save(new Year(2017));
		City city = cityRepo.save(new City("city name 1", "imageURL", "photo caption", "review text", country, year));
		City city2 = cityRepo.save(new City("city name 2", "imageURL", "photo caption", "review text", country, year));
		long cityId = city2.getId();
		
		Comment comment1 = commentRepo.save(new Comment("example", "author", city));
		Comment comment2 = commentRepo.save(new Comment("example2", "author2", city2));
		Comment comment3 = commentRepo.save(new Comment("example3", "author3", city2));
		
		entityManager.flush();
		entityManager.clear();
		
		Collection<Comment> commentsForCity = commentRepo.findByCityId(cityId);
		assertThat(commentsForCity, containsInAnyOrder(comment2, comment3));
	}

}
