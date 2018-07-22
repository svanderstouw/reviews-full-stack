package reviews;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TagsRestController {
	
	@Resource
	public CityRepository cityRepo;
	
	@Resource
	public TagRepository tagRepo;
	
	@GetMapping("/tags")
	public Iterable<Tag> findAllTags() {
		return tagRepo.findAll();
	}
	
	@GetMapping("/tags/{id}")
	public Iterable<Tag> findAllTagsByCityId(@PathVariable long id) {
		return tagRepo.findByCitiesId(id);
	}

	@PutMapping("/addTag/{tagName}/city/{cityId}")
	public Iterable<Tag> addTagToCity(
			@PathVariable("tagName") String tagName,
			@PathVariable("cityId") long cityId
		) {
		Optional<Tag> tagOptional = tagRepo.findByTagName(tagName);
		Tag tagToAdd;
		
		if (!tagOptional.isPresent()) {
			tagToAdd = tagRepo.save(new Tag(tagName));
		} else {
			tagToAdd = tagOptional.get();
		}
		
		Optional<City> currentCityOptional = cityRepo.findById(cityId);
		City currentCity = currentCityOptional.get();
		Collection<Tag> currentCityTags = tagRepo.findByCitiesId(cityId);

		if (!currentCityTags.contains(tagToAdd)) {
			currentCity.getTags().add(tagToAdd);
			currentCity = cityRepo.save(currentCity);
		}
		
		return tagRepo.findByCitiesId(cityId);
	}
	
	@PutMapping("/removeTag/{tagName}/city/{cityId}")
	public Iterable<Tag> removeTagFromCity(
			@PathVariable("tagName") String tagName,
			@PathVariable("cityId") long cityId
		) {
		Optional<Tag> tagOptional = tagRepo.findByTagName(tagName);
		Tag tagToRemove;
		
		if (tagOptional.isPresent()) {
			tagToRemove = tagOptional.get();
			Optional<City> currentCityOptional = cityRepo.findById(cityId);
			City currentCity = currentCityOptional.get();
			Collection<Tag> currentCityTags = tagRepo.findByCitiesId(cityId);

			if (currentCityTags.contains(tagToRemove)) {
				currentCity.getTags().remove(tagToRemove);
				currentCity = cityRepo.save(currentCity);
			}		
		}
		
		return tagRepo.findByCitiesId(cityId);
	}
	
	@PutMapping("/removeOneTag/{tagName}")
	public Iterable<Tag> removeOneTag(
			@PathVariable("tagName") String tagName
		) {
		Optional<Tag> tagOptional = tagRepo.findByTagName(tagName);
		Tag tagToRemove = tagOptional.get();
		
		if (tagOptional.isPresent()) {
			Collection<City> cities = cityRepo.findByTagsContains(tagToRemove);
			for (City city : cities) {
				city.getTags().remove(tagToRemove);
				city = cityRepo.save(city);
			}
			
			tagRepo.delete(tagToRemove);
		}
		
		return tagRepo.findAll();
	}
	
	
}
