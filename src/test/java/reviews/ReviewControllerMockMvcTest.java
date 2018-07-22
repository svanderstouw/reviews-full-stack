package reviews;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerMockMvcTest {
	
	@Resource
	private MockMvc mvc;
	
	@MockBean
	private CityRepository cityRepo;
	
	@MockBean
	private CountryRepository countryRepo;
	
	@MockBean
	private YearRepository yearRepo;
	
	@MockBean
	private TagRepository tagRepo;
	
	@MockBean
	private CommentRepository commentRepo;
	
	@Mock
	private City city;
	@Mock
	private City anotherCity;
	
	@Mock
	private Country country;
	@Mock
	private Country anotherCountry;
	
	@Mock
	private Year year;
	@Mock
	private Year anotherYear;
	
	@Mock
	private Tag tag;
	@Mock
	private Tag anotherTag;
	
	@Test
	public void shouldRoutetoSingleCityView() throws Exception {
		long arbitraryCityId = 1;
		when(cityRepo.findById(arbitraryCityId)).thenReturn(Optional.of(city));
		mvc.perform(get("/city?id=1")).andExpect(view().name(is("city")));
	}
	
	@Test
	public void shouldBeOkForSingleCity() throws Exception {
		long arbitraryCityId = 1;
		when(cityRepo.findById(arbitraryCityId)).thenReturn(Optional.of(city));
		mvc.perform(get("/city?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleCity() throws Exception {
		mvc.perform(get("/city?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleCityIntoModel() throws Exception {
		when(cityRepo.findById(1L)).thenReturn(Optional.of(city));
		mvc.perform(get("/city?id=1")).andExpect(model().attribute("cities", is(city)));
	}
	
	@Test
	public void shouldRouteToAllCitiesView() throws Exception {
		mvc.perform(get("/show-cities")).andExpect(view().name(is("cities")));
	}
	
	@Test
	public void shouldBeOkForAllCities() throws Exception {
		mvc.perform(get("/show-cities")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllCitiesIntoModel() throws Exception {
		Collection<City> allCities = Arrays.asList(city, anotherCity);
		when(cityRepo.findAll()).thenReturn(allCities);
		mvc.perform(get("/show-cities")).andExpect(model().attribute("cities", is(allCities)));
	}
	
	@Test
	public void shouldRoutetoSingleCountryView() throws Exception {
		long arbitraryCountryId = 42;
		when(countryRepo.findById(arbitraryCountryId)).thenReturn(Optional.of(country));
		mvc.perform(get("/country?id=42")).andExpect(view().name(is("country")));
	}
	
	@Test
	public void shouldBeOkForSingleCountry() throws Exception {
		long arbitraryCountryId = 42;
		when(countryRepo.findById(arbitraryCountryId)).thenReturn(Optional.of(country));
		mvc.perform(get("/country?id=42")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForSingleCountry() throws Exception {
		mvc.perform(get("/country?id=42")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleCountryIntoModel() throws Exception {
		when(countryRepo.findById(1L)).thenReturn(Optional.of(country));
		mvc.perform(get("/country?id=1")).andExpect(model().attribute("countries", is(country)));
	}
	
	@Test
	public void shouldRouteToAllCountriesView() throws Exception {
		mvc.perform(get("/show-countries")).andExpect(view().name(is("countries")));
	}
	
	@Test
	public void shouldBeOkForAllCountries() throws Exception {
		mvc.perform(get("/show-countries")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllCountriesIntoModel() throws Exception {
		Collection<Country> allCountries = Arrays.asList(country, anotherCountry);
		when(countryRepo.findAll()).thenReturn(allCountries);
		mvc.perform(get("/show-countries")).andExpect(model().attribute("countries", is(allCountries)));
	}
	
	@Test
	public void shouldRoutetoSingleYearView() throws Exception {
		long arbitraryYearId = 1;
		when(yearRepo.findById(arbitraryYearId)).thenReturn(Optional.of(year));
		mvc.perform(get("/year?id=1")).andExpect(view().name(is("year")));
	}
	
	@Test
	public void shouldBeOkForYearCity() throws Exception {
		long arbitraryYearId = 1;
		when(yearRepo.findById(arbitraryYearId)).thenReturn(Optional.of(year));
		mvc.perform(get("/year?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForYearCity() throws Exception {
		mvc.perform(get("/year?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleYearIntoModel() throws Exception {
		when(yearRepo.findById(1L)).thenReturn(Optional.of(year));
		mvc.perform(get("/year?id=1")).andExpect(model().attribute("years", is(year)));
	}
	
	@Test
	public void shouldRouteToAllYearsView() throws Exception {
		mvc.perform(get("/show-years")).andExpect(view().name(is("years")));
	}
	
	@Test
	public void shouldBeOkForAllYears() throws Exception {
		mvc.perform(get("/show-years")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldPutAllYearsIntoModel() throws Exception {
		Collection<Year> allYears = Arrays.asList(year, anotherYear);
		when(yearRepo.findAll()).thenReturn(allYears);
		mvc.perform(get("/show-years")).andExpect(model().attribute("years", is(allYears)));
	}
	
	@Test
	public void shouldRoutetoSingleTagView() throws Exception {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(view().name(is("tag")));
	}
	
	@Test
	public void shouldBeOkForTagCity() throws Exception {
		long arbitraryTagId = 1;
		when(tagRepo.findById(arbitraryTagId)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldNotBeOkForTagCity() throws Exception {
		mvc.perform(get("/tag?id=1")).andExpect(status().isNotFound());
	}
	
	@Test
	public void shouldPutSingleTagIntoModel() throws Exception {
		when(tagRepo.findById(1L)).thenReturn(Optional.of(tag));
		mvc.perform(get("/tag?id=1")).andExpect(model().attribute("tags", is(tag)));
	}
	
}
