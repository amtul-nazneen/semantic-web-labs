package lab2.app.main;
import org.apache.jena.tdb.TDBFactory;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.RDF;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Lab2 {

	private static final String NS = "http://utdallas/semclass#";
	private static final String TYPE_RDFXML = "RDF/XML";
	private static final String TYPE_NTRIPLE = "N-TRIPLE";
	private static final String TYPE_N3 = "N3";
	private static final String OUTPUT_RDFXML = "Lab2p3_axn180041.rdf";
	private static final String OUTPUT_NTRIPLE = "Lab2p3_axn180041.ntp";
	private static final String OUTPUT_N3 = "Lab2p3_axn180041.n3";
	
	public static void main(String[] args) {
		Logger.getRootLogger().setLevel(Level.OFF);
		Lab2 lab2 = new Lab2();
		
		Dataset dataset = TDBFactory.createDataset("Dataset1");
		dataset.begin(ReadWrite.WRITE);
		
		try {
			System.out.println("Beginning Lab2 Assignment.... ");
			lab2.lab2_part3();
			dataset.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dataset.end();
		}
		System.out.println("Completed Lab2. \nFiles generated with name Lab2p3_axn180041.* in project folder");
	}

	public void lab2_part3() throws IOException {
		
		Model model = ModelFactory.createDefaultModel();
		Property property_authored = model.createProperty(NS + "Authored");
		Property property_madeToMovie = model.createProperty(NS + "MadeToMovie");
		Property property_madeToRadio = model.createProperty(NS + "MadeToRadio");
		Property property_directedBy = model.createProperty(NS + "DirectedBy");
		Property property_writtenBy = model.createProperty(NS + "WrittenBy");

		Resource resource_author_wells = model.createResource(NS + "Author_H.G_Wells")
				.addProperty(RDF.type, model.createResource(TYPE.PERSON))
				.addProperty(VCARD.FN, "Wells")
				.addProperty(VCARD.Given, "H.G");

		Resource resource_book_warOfWorlds = model.createResource(NS + "Book_WarOfWorlds")
				.addProperty(RDF.type, model.createResource(TYPE.BOOK))
				.addProperty(model.createProperty(NS + "Title"), "The War of the Worlds")
				.addProperty(model.createProperty(NS + "Year"), "1990");

		Resource resource_book_timeMachine = model.createResource(NS + "Book_TimeMachine")
				.addProperty(RDF.type, model.createResource(TYPE.BOOK))
				.addProperty(model.createProperty(NS + "Title"), "The Time Machine")
				.addProperty(model.createProperty(NS + "Year"), "2000");

		Resource resource_warOfWorlds_movie1 = model.createResource(NS + "Book_WarOfWorlds_Movie1")
				.addProperty(RDF.type, model.createResource(TYPE.MOVIE)).addProperty(DC.title, "The War of the Worlds")
				.addProperty(DC.language, "French");
		Resource resource_warOfWorlds_movie1_director = model.createResource(NS + "Book_WarOfWorlds_Movie1_Director")
				.addProperty(RDF.type, model.createResource(TYPE.PERSON)).addProperty(VCARD.NAME, "David Koepp")
				.addProperty(VCARD.Country, "United States");

		Resource resource_warOfWorlds_movie2 = model.createResource(NS + "Book_WarOfWorlds_Movie2")
				.addProperty(RDF.type, model.createResource(TYPE.MOVIE)).addProperty(DC.title, "The War of the Worlds")
				.addProperty(DC.language, "English");
		Resource resource_warOfWorlds_movie2_director = model.createResource(NS + "Book_WarOfWorlds_Movie2_Director")
				.addProperty(RDF.type, model.createResource(TYPE.PERSON)).addProperty(VCARD.NAME, "Steven Spielberg")
				.addProperty(VCARD.Country, "United States");

		Resource resource_warOfWorlds_radio1 = model.createResource(NS + "Book_WarOfWorlds_Radio1")
				.addProperty(RDF.type, model.createResource(TYPE.RADIO)).addProperty(DC.title, "The War of the Worlds")
				.addProperty(DC.publisher, "Warner Brothers");
		Resource resource_warOfWorlds_radio1_writer = model.createResource(NS + "Book_WarOfWorlds_Radio1_Writer")
				.addProperty(RDF.type, model.createResource(TYPE.PERSON)).addProperty(VCARD.NAME, "Josh Friedman")
				.addProperty(VCARD.Country, "United Kingdom");

		Resource resource_timeMachine_movie1 = model.createResource(NS + "Book_TimeMachine_Movie1")
				.addProperty(RDF.type, model.createResource(TYPE.MOVIE)).addProperty(DC.title, "The Time Machine")
				.addProperty(DC.language, "French");
		Resource resource_timeMachine_movie1_director = model.createResource(NS + "Book_TimeMachine_Movie1_Director")
				.addProperty(RDF.type, model.createResource(TYPE.PERSON)).addProperty(VCARD.NAME, "David Koepp")
				.addProperty(VCARD.Country, "United States");

		Resource resource_timeMachine_movie2 = model.createResource(NS + "Book_TimeMachine_Movie2")
				.addProperty(RDF.type, model.createResource(TYPE.MOVIE)).addProperty(DC.title, "The Time Machine")
				.addProperty(DC.language, "Korean");
		Resource resource_timeMachine_movie2_director = model.createResource(NS + "Book_TimeMachine_Movie2_Director")
				.addProperty(RDF.type, model.createResource(TYPE.PERSON)).addProperty(VCARD.NAME, "David Koepp")
				.addProperty(VCARD.Country, "United States");

		Statement stmt = model.createStatement(resource_author_wells, property_authored, resource_book_warOfWorlds);
		model.add(stmt);
		stmt = model.createStatement(resource_author_wells, property_authored, resource_book_timeMachine);
		model.add(stmt);

		stmt = model.createStatement(resource_book_warOfWorlds, property_madeToMovie, resource_warOfWorlds_movie1);
		model.add(stmt);
		stmt = model.createStatement(resource_book_warOfWorlds, property_madeToMovie, resource_warOfWorlds_movie2);
		model.add(stmt);
		stmt = model.createStatement(resource_book_warOfWorlds, property_madeToRadio, resource_warOfWorlds_radio1);
		model.add(stmt);

		stmt = model.createStatement(resource_book_timeMachine, property_madeToMovie, resource_timeMachine_movie1);
		model.add(stmt);
		stmt = model.createStatement(resource_book_timeMachine, property_madeToMovie, resource_timeMachine_movie2);
		model.add(stmt);

		stmt = model.createStatement(resource_warOfWorlds_movie1, property_directedBy,
				resource_warOfWorlds_movie1_director);
		model.add(stmt);
		stmt = model.createStatement(resource_warOfWorlds_movie2, property_directedBy,
				resource_warOfWorlds_movie2_director);
		model.add(stmt);
		stmt = model.createStatement(resource_warOfWorlds_radio1, property_writtenBy,
				resource_warOfWorlds_radio1_writer);
		model.add(stmt);

		stmt = model.createStatement(resource_timeMachine_movie1, property_directedBy,
				resource_timeMachine_movie1_director);
		model.add(stmt);
		stmt = model.createStatement(resource_timeMachine_movie2, property_directedBy,
				resource_timeMachine_movie2_director);
		model.add(stmt);

		//model.write(System.out);
		model.write(new FileWriter(OUTPUT_RDFXML), TYPE_RDFXML);
		model.write(new FileWriter(OUTPUT_NTRIPLE), TYPE_NTRIPLE);
		model.write(new FileWriter(OUTPUT_N3), TYPE_N3);
	}

}
