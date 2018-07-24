package reviews;

import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

	@Resource
	CityRepository cityRepo;

	@Resource
	CountryRepository countryRepo;

	@Resource
	YearRepository yearRepo;
	
	@Resource
	TagRepository tagRepo;
	
	@Resource
	CommentRepository commentRepo;

	@RequestMapping("/city")
	public String findOneCity(@RequestParam(value = "id") long id, Model model,
			@RequestParam(value = "backTo", required = false) String backTo) throws CityNotFoundException {
		Optional<City> city = cityRepo.findById(id);

		if (city.isPresent()) {
			model.addAttribute("cities", city.get());
			model.addAttribute("backTo", backTo);
			return ("city");
		}
		throw new CityNotFoundException();
	}

	@RequestMapping("/show-cities")
	public String findAllCities(Model model) {
		model.addAttribute("cities", cityRepo.findAll());
		return ("cities");
	}

	@RequestMapping("/country")
	public String findOneCountry(@RequestParam(value = "id") long id, Model model) throws CountryNotFoundException {
		Optional<Country> country = countryRepo.findById(id);

		if (country.isPresent()) {
			model.addAttribute("countries", country.get());
			return ("country");
		}
		throw new CountryNotFoundException();
	}

	@RequestMapping("/show-countries")
	public String findAllCountries(Model model) {
		model.addAttribute("countries", countryRepo.findAll());
		return ("countries");
	}

	@RequestMapping("/year")
	public String findOneYear(@RequestParam(value = "id") long id, Model model) throws YearNotFoundException {
		Optional<Year> year = yearRepo.findById(id);

		if (year.isPresent()) {
			model.addAttribute("years", year.get());
			return ("year");
		}
		throw new YearNotFoundException();
	}

	@RequestMapping("/show-years")
	public String findAllYears(Model model) {
		model.addAttribute("years", yearRepo.findAll());
		return ("years");
	}

	@RequestMapping("/tag")
	public String findOneTag(@RequestParam(value = "id") long id, Model model) throws TagNotFoundException {
		Optional<Tag> tag = tagRepo.findById(id);

		if (tag.isPresent()) {
			model.addAttribute("tags", tag.get());
			return ("tag");
		}
		throw new TagNotFoundException();
	}

	@RequestMapping("/")
	public String findAllTags(Model model) {
		model.addAttribute("tags", tagRepo.findAll());
		return ("index");
	}
	
	

}
