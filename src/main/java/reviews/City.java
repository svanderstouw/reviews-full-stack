package reviews;

import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToMany
	private Collection<Tag> tags;
	
	@JsonIgnore
	@OneToMany(mappedBy = "city")
	private Collection<Comment> comments;
	
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
	
	public Collection<Tag> getTags() {
		return tags;
	}
	
	public Collection<Comment> getComments() {
		return comments;
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

	public City(String cityName, String imageUrl, String photoCaption, String reviewText, Country country, Year year, Tag...tags) {
		this.cityName = cityName;
		this.imageUrl = imageUrl;
		this.photoCaption = photoCaption;
		this.reviewText = reviewText;
		this.country = country;
		this.year = year;
		this.tags = new HashSet<>(Arrays.asList(tags));
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
