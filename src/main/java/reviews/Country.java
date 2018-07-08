package reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Country {
	
	@Id
	@GeneratedValue
	private long id;

	private String countryName;
	private String imageUrl;
	
	@OneToMany(mappedBy = "country")
	private Collection<City> cities;
	
	public long getId() {
		return id;
	}
	
	public String getCountryName() {
		return countryName;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public Collection<City> getCities() {	
		return cities;
	}
	
	protected Country() {
		
	}
	
	public Country(String countryName, String imageUrl) {
		this.countryName = countryName;
		this.imageUrl = imageUrl;
	}


}
