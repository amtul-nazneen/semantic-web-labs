package lab1.app.main;

import java.io.FileWriter;
import java.io.InputStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VCARD;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Lab1p4 {

	private static final String DEFAULT_NAMESPACE = "http://org.semwebprogramming/chapter2/people#";
	private static final String DATASET = "FOAFDataset";
	private static final String RDF_FILE = "axn180041_FOAFFriends.rdf";
	private static final String NAMED_MODEL = "myrdf";
	private static final String OUTPUT_RDFXML = "Lab1p4_axn180041.xml";
	private static final String OUTPUT_NTRIPLE = "Lab1p4_axn180041.ntp";
	private static final String OUTPUT_N3 = "Lab1p4_axn180041.n3";
	private static final String TYPE_RDFXML = "RDF/XML";
	private static final String TYPE_NTRIPLE = "N-TRIPLE";
	private static final String TYPE_N3 = "N3";
	private static Logger log = Logger.getLogger(Lab1p4.class.getName());

	@SuppressWarnings("deprecation")
	public static void lab1_part4() {
		Logger.getRootLogger().setLevel(Level.OFF);
		log.debug("Beginning Lab1p4....");
		Dataset dataset = TDBFactory.createDataset(DATASET);

		dataset.begin(ReadWrite.WRITE);
		try {
			InputStream sampleFoafInstance = FileManager.get().open(RDF_FILE);
			dataset.getNamedModel(NAMED_MODEL).read(sampleFoafInstance, DEFAULT_NAMESPACE);
			dataset.commit();
		} catch (Exception e) {
			log.error("Error when populating TDB Dataset " + e.getMessage());
			e.printStackTrace();
		} finally {
			dataset.end();
		}
		log.debug("Populating FOAF Data completed....");

		dataset.begin(ReadWrite.READ);
		try {

			dataset.getNamedModel(NAMED_MODEL).write(new FileWriter(OUTPUT_RDFXML), TYPE_RDFXML);
			dataset.getNamedModel(NAMED_MODEL).write(new FileWriter(OUTPUT_NTRIPLE), TYPE_NTRIPLE);
			dataset.getNamedModel(NAMED_MODEL).write(new FileWriter(OUTPUT_N3), TYPE_N3);
		} catch (Exception e) {
			log.error("Error when reading TDB Dataset " + e.getMessage());
			e.printStackTrace();
		} finally {
			dataset.end();
		}
		log.debug("Reading FOAF Data completed....");
	}

	public static void lab1_part2() {

		String vCardURI = "http://utdallas/someuri";

		Model model = ModelFactory.createDefaultModel();
		Resource vCardResource = model.createResource(vCardURI).addProperty(VCARD.FN, "Dr. May Westford")
				.addProperty(VCARD.TITLE, "North America Division Vice President")
				.addProperty(VCARD.ORG, "Consolidated Semantics, U.S.").addProperty(VCARD.BDAY, "March 28, 1965")
				.addProperty(VCARD.EMAIL, "May.Westford@consolidatedsem.com")
				.addProperty(RDF.type, "http://utdallas/semclass");

		/*System.out.println("Printing the vCard Model below: ");
		System.out.println(" *** ***** **** **** ***** ");
		model.write(System.out);
		System.out.println(" *** ***** **** **** ***** ");*/
		try {
			model.write(new FileWriter("Lab1p2_axn180041.xml"), TYPE_RDFXML);
			model.write(new FileWriter("Lab1p2_axn180041.ntp"), TYPE_NTRIPLE);
			model.write(new FileWriter("Lab1p2_axn180041.n3"), TYPE_N3);
		} catch (Exception e) {
			log.error("Error when writing model " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Beginning Lab1 Assignment.... ");
		System.out.println("\nBeginning Lab1-Part2...");
		lab1_part2();
		System.out.println("Completed Lab1-Part2. Files generated with name Lab1p2_axn180041.* in project folder");
		System.out.println("\nBeginning Lab1-Part4...");
		lab1_part4();
		System.out.println("Completed Lab1-Part4. Files generated with name Lab1p4_axn180041.* in project folder");
		System.out.println("\nLab1 Assignment Completed");
	}

}
