# Reviews Full Stack Project

This project create a reviews site that categorizes reviews of different European cities.

**Review Class**

This class has instance variables for an ID number, city name, country name, image URL, photo caption, date visited, and the text of the review.  It also contains a constructor and getters for each variable.

**Review Repository Class**

This class creates Review instances and populates a Map that stores the reviews.  It has findOne() and findAll() methods.

**Review Controller Class**

This class has a findAllReviews() method mapped to a url that puts all of the reviews into a model, forwarding to a “reviews” template.  It also has a findOneReview() method mapped to a url including an id parameter that puts one of the reviews into the model, forwarding to a “review” template. This method expects an “id” query parameter in order to select a specific review.

**Reviews and Review HTML Pages**

These two pages are Thymeleaf templates that display information from the Review instances found in the Review Repository.