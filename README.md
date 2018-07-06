# Reviews Full Stack Project

This project creates a reviews site that categorizes reviews of different European cities and makes them available by country and by year visited.

**Entities**

City, Country, and Year classes are setup as Entities with OneToMany and ManyToOne relationships.

**Repository Instances**

The three repository instances extend the CRUD Repository with the CityRepository adding findByCountry, findByCountryId, findByYear, and findByYearId. 

**Review Controller Class**

This class has findAll and findOne methods for each repository that are then mapped to group and individual HTML pages.

**Review Populator Class**

This class populates the three repositories with data.

**HTML Pages**

These six pages are Thymeleaf templates that display information from the Controller Class and are formatted with HTML and CSS/Flexbox/Grid.