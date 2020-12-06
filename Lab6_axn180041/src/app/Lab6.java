package app;
import java.io.FileWriter;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.DC;
import org.apache.jena.vocabulary.RDF;

public class Lab6 {
	private static final String OUTPUT = "Lab6_axn180041.n3";

	public static void main(String[] args) {

		Lab6 lab6 = new Lab6();
		try {
			System.out.println("Beginning Lab6 Assignment.... ");
			lab6.lab3_part_C();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("\nProgram Completed.");
	}
	
	private void lab3_part_C() {
		System.out.println("\n>>>>>>>>>>> Begin of: lab3_part_C");
		System.out.println("\nBeginning to execute the SPARQL Query");
		Query query = QueryFactory.create(Query1.SPARQL_QUERY);
		Model model = ModelFactory.createDefaultModel();
		try {
			QueryExecution qe = QueryExecutionFactory.create(query, model);
			System.out.println("Query execution complete, collecting the results.");
			ResultSet results = qe.execSelect();
			int totalResults = 0;
			while (results.hasNext()) {
				QuerySolution qs = results.nextSolution();
				System.out.println("Result:"+qs.toString());
				totalResults++;
			}
			System.out.println("Number of results: " + totalResults);
			System.out.println("End of First Listing");
			System.out.println("\n>>>>>>>>>>> End of: lab3_part_C");

			/*PART D*/
			System.out.println("\n>>>>>>>>>>> Begin of: lab3_part_D");
			qe = QueryExecutionFactory.create(query, model);
			results = qe.execSelect();
			totalResults = 0;
			Model sameModel = ModelFactory.createDefaultModel();
			while (results.hasNext()) {
				QuerySolution qs = results.nextSolution();
				String s1 = qs.get("s1").toString();
				String s1URI = s1.split("person2018/")[1];
				String s2 = qs.get("s2").toString();
				String s2URI = s2.split("person2019/")[1];
				String o1 = qs.get("o1").toString();
				if (s1URI.equalsIgnoreCase(s2URI)) {
					totalResults++;
					Resource assertResource = sameModel.createResource("assertingResource"+String.valueOf(totalResults))
							.addProperty(RDF.type, "<http://www.w3.org/1999/02/22-rdf-syntax-ns#Statement>")
							.addProperty(RDF.subject, s1)
							.addProperty(RDF.predicate, "owl:sameas")
							.addProperty(RDF.object, s2)
							.addProperty(DC.creator, "axn180041");
				}
			}
			System.out.println("Matching URI: " + totalResults);
			sameModel.write(new FileWriter(OUTPUT), "N3");
			/*PART E-1*/
			System.out.println("\n>>>>>>>>>>> Begin of: lab3_part_E_part_1");
			ResIterator resIter = sameModel.listSubjectsWithProperty(RDF.predicate, "owl:sameas");
			while(resIter.hasNext())
			{
				Resource r = resIter.nextResource();
				System.out.println("Resource Subject: " + r.getProperty(RDF.subject).getObject());
				System.out.println("Resource Object: " + r.getProperty(RDF.object).getObject());
			}
			
			/*PART E-2*/
			System.out.println("\n>>>>>>>>>>> Begin of: lab3_part_E_part_2");
			 resIter = sameModel.listSubjectsWithProperty(RDF.predicate, "owl:sameas");
			 int allPapercount = 0;
			while(resIter.hasNext())
			{
				Resource r = resIter.nextResource();
				String input1 =r.getProperty(RDF.subject).getObject().toString();
				input1 = "<"+input1+">";
				String input2 = r.getProperty(RDF.object).getObject().toString();
				input2 = "<"+input2+">";
				String sparqlQuery2 = Query2.QUERY_2A + input1 + Query2.QUERY_2B + input1 + Query2.QUERY_2C + input1 + 
						Query2.QUERY_2D + input2 + Query2.QUERY_2E + input2 +Query2.QUERY_2F +input2 +Query2.QUERY_2G;
				Model modelTemp = ModelFactory.createDefaultModel();
				qe = QueryExecutionFactory.create(sparqlQuery2, modelTemp);
				results = qe.execSelect();
				int papersByCreator =0;
				while (results.hasNext()) {
					QuerySolution qs = results.nextSolution();
					String name = qs.get("name1").toString();
					String paper = qs.get("paper1").toString();
					papersByCreator++;
					System.out.println("Name: "+ name + ", Paper"+papersByCreator+": " + paper);
				}
				allPapercount+=papersByCreator;
			}
			System.out.println("All Papers Count: " + allPapercount);
			System.out.println("End of Second Listing");
			/*PART F*/
			System.out.println("\n>>>>>>>>>>> Begin of: lab3_part_F");
			System.out.println("PartF");
			 resIter = sameModel.listSubjectsWithProperty(RDF.predicate, "owl:sameas");
			  allPapercount = 0;
			while(resIter.hasNext())
			{
				Resource r = resIter.nextResource();
				String input1 =r.getProperty(RDF.subject).getObject().toString();
				input1 = "<"+input1+">";
				String input2 = r.getProperty(RDF.object).getObject().toString();
				input2 = "<"+input2+">";
				String sparqlQuery2 = Query2.QUERY_2A + input1 + Query2.QUERY_2B + input1 + Query2.QUERY_2C + input1 + 
						Query2.QUERY_2D + input2 + Query2.QUERY_2E + input2 +Query2.QUERY_2F +input2 +Query2.QUERY_2G;
				Model modelTemp = ModelFactory.createDefaultModel();
				qe = QueryExecutionFactory.create(sparqlQuery2, modelTemp);
				results = qe.execSelect();
				int papersByCreator =0;
				while (results.hasNext()) {
					QuerySolution qs = results.nextSolution();
					String name = qs.get("name1").toString();
					String paper = qs.get("paper1").toString();
					papersByCreator++;
					System.out.println("Name: "+ name + ", Paper"+papersByCreator+": " + paper);
				}
				allPapercount+=papersByCreator;
			}
			System.out.println("All Papers Count: " + allPapercount);
			System.out.println("End of Third Listing");
			
		} catch (Exception e) {
			System.out.println("Error during program execution: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
}
