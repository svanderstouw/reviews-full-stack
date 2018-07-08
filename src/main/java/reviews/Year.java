package reviews;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Year {

	@Id
	@GeneratedValue
	private long id;
	
	private int year;
	
	@OneToMany(mappedBy = "year")
	private Collection<City> cities;
	
	public long getId() {
		return id;
	}
	
	public int getYear() {
		return year;
	}
	
	public Collection<City> getCities() {	
		return cities;
	}
	
	protected Year() {
		
	}

	public Year(int year) {
		this.year = year;
	}

}
