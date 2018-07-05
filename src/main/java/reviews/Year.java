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
	
	public Year() {
		
	}

	public Year(int year) {
		this.year = year;
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
		Year other = (Year) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
