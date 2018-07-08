package reviews;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController underTest;
	
	@Mock
	private City city;
	
	@Mock
	private City anotherCity;
	
	@Mock
	private CityRepository cityRepo;
	
	@Mock
	private Country country;
	
	@Mock
	private Country anotherCountry;
	
	@Mock
	private CountryRepository countryRepo;
	
	@Mock
	private Year year;
	
	@Mock
	private Year anotherYear;
	
	@Mock
	private YearRepository yearRepo;
	
	@Mock
	private Model model;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAddSingleCityToModel() throws CityNotFoundException {
		long arbitraryCityId = 1;
		when(cityRepo.findById(arbitraryCityId)).thenReturn(Optional.of(city));
		
		underTest.findOneCity(arbitraryCityId, model, "test");
		verify(model).addAttribute("cities", city);
	}
	
	@Test
	public void shouldAddAllCitiesToModel () {
		Collection<City> allCities = Arrays.asList(city, anotherCity);
		when(cityRepo.findAll()).thenReturn(allCities);
		
		underTest.findAllCities(model);
		verify(model).addAttribute("cities", allCities);
	}
	
	@Test
	public void shouldAddSingleCountryToModel() throws CountryNotFoundException {
		long arbitraryCountryId = 1;
		when(countryRepo.findById(arbitraryCountryId)).thenReturn(Optional.of(country));
		
		underTest.findOneCountry(arbitraryCountryId, model);
		verify(model).addAttribute("countries", country);
	}
	
	@Test
	public void shouldAddAllCountriesToModel() {
		Collection<Country> allCountries = Arrays.asList(country, anotherCountry);
		when(countryRepo.findAll()).thenReturn(allCountries);
		
		underTest.findAllCountries(model);
		verify(model).addAttribute("countries", allCountries);
	}
	
	@Test
	public void shouldAddSingleYearToModel() throws YearNotFoundException {
		long arbitraryYearId = 1;
		when(yearRepo.findById(arbitraryYearId)).thenReturn(Optional.of(year));
		
		underTest.findOneYear(arbitraryYearId, model);
		verify(model).addAttribute("years", year);
	}
	
	@Test
	public void shouldAddAllYearsToModel() {
		Collection<Year> allYears = Arrays.asList(year, anotherYear);
		when(yearRepo.findAll()).thenReturn(allYears);
		
		underTest.findAllYears(model);
		verify(model).addAttribute("years", allYears);
	}
	
}
