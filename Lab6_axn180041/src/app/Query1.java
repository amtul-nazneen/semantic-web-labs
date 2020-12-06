package app;

public class Query1 {
	public static final String SPARQL_QUERY = "select distinct ?s1 ?s2 ?o1\r\n" + 
			"where\r\n" + 
			"{\r\n" + 
			"{SERVICE <http://localhost:3030/FedSet1/sparql> \r\n" + 
			"      {\r\n" + 
			"        SELECT ?s1  ?o1\r\n" + 
			"        WHERE {?s1 <http://xmlns.com/foaf/0.1/mbox_sha1sum> ?o1}\r\n" + 
			"    }}.\r\n" + 
			"  \r\n" + 
			"  {SERVICE <http://localhost:3030/FedSet2/sparql> \r\n" + 
			"      {\r\n" + 
			"        SELECT ?s2  ?o1\r\n" + 
			"        WHERE {?s2 <http://xmlns.com/foaf/0.1/mbox_sha1sum> ?o1}\r\n" + 
			"                    \r\n" + 
			"    }}.\r\n" + 
			"{\r\n" + 
			"select  ?o1 (count(*) as ?c)\r\n" + 
			"where\r\n" + 
			"{\r\n" + 
			"SELECT\r\n" + 
			"  ?s1 ?s2 ?o1 \r\n" + 
			"WHERE\r\n" + 
			"  { \r\n" + 
			"     {SERVICE <http://localhost:3030/FedSet1/sparql> \r\n" + 
			"      {\r\n" + 
			"        SELECT ?s1  ?o1\r\n" + 
			"        WHERE {?s1 <http://xmlns.com/foaf/0.1/mbox_sha1sum> ?o1}\r\n" + 
			"                    \r\n" + 
			"    }} union\r\n" + 
			"  \r\n" + 
			"  {SERVICE <http://localhost:3030/FedSet2/sparql> \r\n" + 
			"      {\r\n" + 
			"        SELECT ?s2  ?o1\r\n" + 
			"        WHERE {?s2 <http://xmlns.com/foaf/0.1/mbox_sha1sum> ?o1}                \r\n" + 
			"    }}\r\n" + 
			"  }\r\n" + 
			"}group by (?o1)\r\n" + 
			"  }}\r\n" + 
			"ORDER BY ?o1";
}
