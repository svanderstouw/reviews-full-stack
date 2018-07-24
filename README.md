# Reviews Full Stack Project

This project creates a reviews site that categorizes reviews of different European cities and makes them available by tag, by country, and by year visited.  Users can add comments to each of the reviews.

**Entities**

City, Tag, Comment, Country, and Year classes are setup as Entities with OneToMany, ManyToOne, and ManyToMany relationships.

**Repository Instances**

The five repository interfaces extend the CRUD Repository.  The CityRepository also contains findByCountry, findByCountryId, findByYear, findByYearId, findByTagsContains, and findByTagsId.  The CommentRepository adds findByCity and findByCityId and the TagRepository adds findByCitiesContains and findByCitiesId.

**Review Controller Class**

This class has findAll and findOne methods for each repository that are then mapped to group and individual HTML pages.

**Review Rest Controller Class**

This class has Get Mapping for finding all of the Tags, finding all of the Tags for a City, and finding all of the Comments for a City.  It also contains Put Mapping for adding and removing Tags, and Post Mapping for creating Comments.

**Review Populator Class**

This class populates the five repositories with data.

**HTML Pages**

These eight pages use Thymeleaf to display information from the Controller Class and are formatted with HTML and CSS/Flexbox/Grid.

**JavaScript Files**

These files utilize JSON and AJAX to add and render Tags and Comments to reviews, and to remove Tags from one review or all of the reviews.