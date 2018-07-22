package reviews;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReviewPopulator implements CommandLineRunner{

	@Resource
	private CityRepository cityRepo;
	
	@Resource
	private CountryRepository countryRepo;
	
	@Resource
	private YearRepository yearRepo;
	
	@Resource
	private TagRepository tagRepo;
	
	@Resource
	private CommentRepository commentRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		Country france = new Country("France", "france.jpg");
		france = countryRepo.save(france);
		Country netherlands = new Country("Netherlands", "netherlands.jpg");
		netherlands = countryRepo.save(netherlands);
		Country portugal = new Country("Portugal", "portugal.jpg");
		portugal = countryRepo.save(portugal);
		Country uk = new Country("United Kingdom", "uk.jpg");
		uk = countryRepo.save(uk);
		
		Year y2014 = new Year(2014);
		y2014 = yearRepo.save(y2014);
		Year y2015 = new Year(2015);
		y2015 = yearRepo.save(y2015);
		Year y2017 = new Year(2017);
		y2017 = yearRepo.save(y2017);
		
		Tag city = new Tag("city");
		city = tagRepo.save(city);
		Tag town = new Tag("town");
		town = tagRepo.save(town);
		Tag canals = new Tag("canals");
		canals = tagRepo.save(canals);
		Tag museums = new Tag("museums");
		museums = tagRepo.save(museums);
		Tag beach = new Tag("beach");
		beach = tagRepo.save(beach);
		
		City amsterdam = new City("Amsterdam", "amsterdam640.jpg",
				"Prinsengracht (Prince's Canal) with the Westerkerk Tower in the background",
				"Amsterdam still looks much like it did in the 1600s — the Dutch Golden Age — when it was the world's richest city, an international sea-trading port, and the cradle of capitalism. Wealthy, democratic burghers built a city upon millions of pilings, creating a wonderland of canals lined with trees and townhouses topped with fancy gables. Today's Amsterdam is still a city of good living, with cozy cafés, great art, street-corner jazz, stately history, and a spirit of live and let live. It's a place where carillons chime quaintly from spires towering above coffeeshops where yuppies go to smoke pot. Take it all in, then pause to watch the clouds blow past stately old gables — and see the Golden Age reflected in a quiet canal.",
				netherlands, y2015, city, museums, canals);
		amsterdam = cityRepo.save(amsterdam);
		City colmar = new City("Colmar", "colmar640.jpg", 
				"A canal in La Petite Venise", 
				"Colmar, Alsace's most beautiful city, feels made for wonder-struck tourists — its essentially traffic-free city center is a fantasy of steep pitched roofs, pastel stucco, and aged timbers. Plus, it offers a few heavyweight sights in a comfortable, midsize-town package. The town's distinctly French shutters, combined with the ye-olde German half-timbering, give Colmar an intriguing ambience. Antiques shops welcome browsers, homeowners fuss over their geraniums, and hoteliers hurry down the sleepy streets to pick up fresh croissants in time for breakfast.",
				france, y2014, town, canals);
		colmar = cityRepo.save(colmar);
		City london = new City("London", "london640.jpg", 
				"Big Ben and the London Underground",
				"London is the L.A., D.C., and N.Y.C. of Britain — a living, breathing, thriving organism…a coral reef of humanity. Blow through the city on a double-decker bus, and take a pinch-me-I'm-in-London walk through the West End. Ogle the crown jewels at the Tower of London, hear the chimes of Big Ben, and see the Houses of Parliament in action. Cruise the Thames River, and take a spin on the London Eye. Hobnob with the tombstones in Westminster Abbey, visit with Leonardo, Botticelli, and Rembrandt in the National Gallery, and explore Harry Potter's stomping grounds at the film studio in Leavesden. Enjoy Shakespeare in a replica of the Globe Theatre and marvel at a glitzy, fun musical at a modern-day theater. Whisper across the dome of St. Paul's Cathedral, then rummage through our civilization's attic at the British Museum.",
				uk, y2017, city, museums);
		london = cityRepo.save(london);
		City nazare = new City("Nazar\u00E9", "nazare640.jpg", 
				"Overlooking Nazaré and Sítio",
				"Nazaré falls somewhere between a real-life, narrow-laned fishing village and a busy resort with a beach littered with frolicking families. You'll be greeted by the energetic applause of the surf, widows with rooms to rent, and big plates of percebes (barnacles). Relax in the Portuguese sun in a land of cork groves, eucalyptus trees, ladies in petticoats, and men who stow cigarettes and fishhooks in their stocking caps. Wander the back streets for a fine look at Portuguese family-in-the-street life: Laundry flaps in the wind, kids play soccer, and fish sizzle over tiny curbside hibachis. While the town doesn’t have any real \"sights,\" a few worthy stops lay within easy day-trip distance: Batalha (for its monastery), the pilgrimage site at Fátima, and Alcobaça, home to Portugal's largest church (and saddest romance).",
				portugal, y2017, town, beach);
		nazare = cityRepo.save(nazare);
		City paris = new City("Paris", "paris640.jpg", 
				"Notre-Dame Cathedral and the Seine River", 
				"Paris — the City of Light — has been a beacon of culture for centuries. As a world capital of art, fashion, food, literature, and ideas, it stands as a symbol of all the fine things human civilization can offer. Paris offers sweeping boulevards, chatty crêpe stands, chic boutiques, and world-class art galleries. Sip decaf with deconstructionists at a sidewalk café, then step into an Impressionist painting in a tree-lined park. Climb Notre-Dame and rub shoulders with a gargoyle. Cruise the Seine, zip to the top of the Eiffel Tower, and saunter down Avenue des Champs-Elysées. Master the Louvre and Orsay museums. Save some after-dark energy for one of the world's most romantic cities.",
				france, y2014, city, museums);
		paris = cityRepo.save(paris);
		
		Comment comment1 = new Comment("One of my favorite cities.", "Bob", london);
		comment1 = commentRepo.save(comment1);
		Comment comment2 = new Comment("I love seeing theater productions on the West End.", "Jane", london);
		comment2 = commentRepo.save(comment2);
	}

	
	
}
