package reviews;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class City {

	@Id
	@GeneratedValue
	private long id;
	
	private String cityName;
	private String imageUrl;
	private String photoCaption;
	
	@Lob
	private String reviewText;
	
	@ManyToOne
	private Country country;
	
	@ManyToOne
	private Year year;
	
	public long getId() {
		return id;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public String getPhotoCaption() {
		return photoCaption;
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public String getCountryName() {
		return country.getCountryName();
	}
	
	public int getYear() {
		return year.getYear();
	}
	
	protected City() {
		
	}

	public City(String cityName, String imageUrl, String photoCaption, String reviewText, Country country, Year year) {
		this.cityName = cityName;
		this.imageUrl = imageUrl;
		this.photoCaption = photoCaption;
		this.reviewText = reviewText;
		this.country = country;
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
		City other = (City) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
