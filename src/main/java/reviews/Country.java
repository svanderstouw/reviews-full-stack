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
	
	@OneToMany(mappedBy = "country")
	private Collection<City> cities;
	
	public long getId() {
		return id;
	}
	
	public String getCountryName() {
		return countryName;
	}

	public Collection<City> getCities() {	
		return cities;
	}
	
	public Country() {
		
	}
	
	public Country(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
