import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import java.sql.Timestamp;
import java.util.Date;
public class Lab3 {

	private static final String NS = "http://org.semwebprogramming/chapter2/people#";
	private static final String INPUT_RDF = "Monterey.rdf";
	private static final String SPARQL_QUERY = "SELECT *\r\n" + "WHERE\r\n" + " { \r\n"
			+ "        <http://urn.monterey.org/incidents#incident1621> ?p1 ?o1 \r\n"
			+ "         OPTIONAL {             \r\n" + "                ?o1 ?p2 ?o2 . \r\n"
			+ "FILTER (?p1 != <http://www.w3.org/2001/vcard-rdf/3.0#ORG>)\r\n"
			+ "                      OPTIONAL {  ?o2 ?p3 ?o3 .} \r\n" + "                 }\r\n" + " }";
	private static final String OUTPUT_XML = "Lab3_2_axn180041.xml";

	public static void main(String[] args) {

		Lab3 lab3 = new Lab3();

		try {
			System.out.println("Beginning Lab3 Assignment.... ");
			System.out.println("\nProgram Started..");
			lab3.lab3_part2();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nProgram Completed.");
	}

	private void lab3_part2() {
		
		Model model = loadFileAndGetModel();
		System.out.println("\nBeginning to execute the below query: " + "\n" + SPARQL_QUERY);
		Query query = QueryFactory.create(SPARQL_QUERY);

		try {
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			System.out.println("Query execution complete, collecting the results.");
			ResultSet results = qe.execSelect();
			OutputStream out = new FileOutputStream(OUTPUT_XML);
			ResultSetFormatter.outputAsXML(out, results);
			System.out.println("\nResults stored in: " + OUTPUT_XML);
		} catch (Exception e) {
			System.out.println("Error during program execution: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private Model loadFileAndGetModel()
	{
		Timestamp start = getTimestamp();
		Model model =ModelFactory.createDefaultModel();
		try {
		InputStream sampleFoafInstance = FileManager.get().open(INPUT_RDF);
		model.read(sampleFoafInstance, NS);
		Timestamp end = getTimestamp();
		System.out.println("\nDatafile " + INPUT_RDF + " loaded successfully, took: " + getDiffInSeconds(start, end) + " sec.");
		}
		catch(Exception e)
		{
			System.out.println("Error when loading datafile: " + INPUT_RDF + ", Error: " + e.getMessage());
		}
		return model;
	}
	public static int getDiffInSeconds(Timestamp start, Timestamp end) throws Exception {
		long ms = end.getTime() - start.getTime();
		int seconds = (int) ms / 1000;
		return seconds;
	}
	
	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}
}
