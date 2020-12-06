package app;

public class Query2 {

	
	public static final String QUERY_2A =
			"SELECT\r\n" + 
			"  ?s1 ?s2 ?paper1 ?name1\r\n" + 
			"WHERE\r\n" + 
			"  { \r\n" + 
			"     {SERVICE <http://localhost:3030/FedSet1/sparql> \r\n" + 
			"      {\r\n" + 
			"        SELECT ?s1 ?paper1 ?creator1 ?name1\r\n" + 
			"        WHERE {?s1  <http://purl.org/dc/elements/1.1/creator> ";
			
		//	"CHANGE"+
			
	public static final String QUERY_2B =			".\r\n" + 
			"		?s1 <http://www.w3.org/2000/01/rdf-schema#label> ?paper1.\r\n" ;
			
			//"CHANGE"+
			
	public static final String QUERY_2C =			" a <https://w3id.org/scholarlydata/ontology/conference-ontology.owl#Person>.\r\n" ;
			
			//"CHANGE"+
			
	public static final String QUERY_2D =					" <http://www.w3.org/2000/01/rdf-schema#label> ?name1.\r\n" + 
			"      }\r\n" + 
			"                    \r\n" + 
			"    }} union\r\n" + 
			"  \r\n" + 
			"  {SERVICE <http://localhost:3030/FedSet2/sparql> \r\n" + 
			"      {\r\n" + 
			"        SELECT ?s2 ?paper1 ?creator1 ?name1\r\n" + 
			"        WHERE {?s2  <http://purl.org/dc/elements/1.1/creator> ";
			
	//		"CHANGE"
			
public static final String QUERY_2E =		".\r\n" + 
			"		?s2 <http://www.w3.org/2000/01/rdf-schema#label> ?paper1.\r\n" ;
			
			//"CHANGE"+
			
			public static final String QUERY_2F =			" a <https://w3id.org/scholarlydata/ontology/conference-ontology.owl#Person>.\r\n" ;
			
			//"CHANGE "+
			
			public static final String QUERY_2G =			"<http://www.w3.org/2000/01/rdf-schema#label> ?name1.}             \r\n" + 
			"    }}\r\n" + 
			"  }\r\n" + 
			"ORDER BY ?paper1";
			
			
			public static void main(String[] args) {
				String uri ="<https://w3id.org/scholarlydata/person2018/andreas-harth>";
				String uri2 ="<https://w3id.org/scholarlydata/person2019/andreas-harth>";
				String query = QUERY_2A + uri + QUERY_2B + uri + QUERY_2C + uri + 
						QUERY_2D + uri2 + QUERY_2E + uri2 +QUERY_2F +uri2 +QUERY_2G;
				System.out.println(query);
				
			}
}
