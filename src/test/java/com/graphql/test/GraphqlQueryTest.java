package com.graphql.test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.graphql.pojo.GQLquery;
import com.graphql.pojo.QueryVariable;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GraphqlQueryTest {
	
	
	@Test(enabled=false)
	public void getAllFilmName()
	{
		RestAssured.baseURI="https://swapi-graphql.netlify.app";
		String Query="{\"query\":\"\\n\\n\\n{\\n  allFilms\\n  {\\n    films{\\n      title\\n      director\\n      \\n        }\\n      }\\n    }\\n\\n\\n\\n\",\"variables\":null}";
		given().log().all().contentType("application/json").body(Query)
		.when().log().all().post("/.netlify/functions/index").then().log().body().assertThat().statusCode(200)
		.and().body("data.allFilms.films[0].title",equalTo("A New Hope"))
		.assertThat().statusLine("HTTP/1.1 200 OK");
			
		
	}
	
	@DataProvider
	public Object[][] getQueryData(){
		return new Object[][] {{"8"},
			};
	}
	
	@Test(dataProvider="getQueryData",enabled=false)
	public void getDataUsingAuth(String limit)
	{
		RestAssured.baseURI="https://hasura.io";
		String Query="{\"query\":\"{\\n  online_users(limit:"+limit+") {\\n    last_seen\\n    user {\\n      id\\n      name\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		given().log().all().contentType("application/json").header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxMWEzYmY0ZDRhZTJkMDA2OTZkMmE4NCJ9LCJuaWNrbmFtZSI6InZhcnNoYSIsIm5hbWUiOiJ2YXJzaGFAZ3JhcGhxbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvODg1NmQxYTFmYzVhMjIyZjRlNmE4MzdmOTg0NmQzODE_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZ2YS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0wOC0xNlQxMDoyMDozOC4zNTBaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjExYTNiZjRkNGFlMmQwMDY5NmQyYTg0IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2MjkxMDkyNDAsImV4cCI6MTYyOTE0NTI0MCwiYXRfaGFzaCI6Imp6cGNNd0gwb213d1VjTGkteGhMZkEiLCJub25jZSI6IlZiTDRibGs5TFRuWWdLa3hpd3Z3SG4yRUtUOE5yd2ZDIn0.mcsFJ_tiIR4Xk2fqfqJQa4unthZx_SBkw1nspP7HMPewW95bXF5iSaLpdLPgJsivOVv-KNOFJ2j43rkQtSsOIbh4It-_Dgbm8-i2d_nixzhgzFQErjQdzwMHpVBT9XCR5gccv0_YDdqPuXHa96lEae5vD-nW4LAMk-iIbubBWEvkw555ayQTMtLCaW8H43MsfXD4YGjRqlZplWJmfncLNzEgLn7noerbBiVubMnAYaenZ2AuOHnZeBuRylXv_nzM11W2LqkYKNN73Ipkr7u6z6UyUWMPtYRctl6esWS9dLJRai8Bo5-7gvorjBPzCBKs7KM9oftmPUF3i_pxanlr7Q")
		.body(Query)
		.when().log().all().post("learn/graphql").then().log().body().assertThat().statusCode(200)
		.and()
		.assertThat().statusLine("HTTP/1.1 200 OK");
			
		
	}
	
	@Test(dataProvider="getQueryData",enabled=false)
	public void getUserDataWithPojo(String limit)
	{
		RestAssured.baseURI="https://hasura.io";
		GQLquery query=new GQLquery();
		query.setQuery("query($limit:Int!){\n"
				+ "  users(limit:$limit) {\n"
				+ "    name\n"
				+ "    todos {\n"
				+ "      id\n"
				+ "      title\n"
				+ "    }\n"
				+ "  }\n"
				+ "}\n"
				+ "");
		QueryVariable qv=new QueryVariable();
		qv.setLimit(5);
		query.setVariables(qv);
		given().log().body(true).contentType(ContentType.JSON)
		.header("Authorization","Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik9FWTJSVGM1UlVOR05qSXhSRUV5TURJNFFUWXdNekZETWtReU1EQXdSVUV4UVVRM05EazFNQSJ9.eyJodHRwczovL2hhc3VyYS5pby9qd3QvY2xhaW1zIjp7IngtaGFzdXJhLWRlZmF1bHQtcm9sZSI6InVzZXIiLCJ4LWhhc3VyYS1hbGxvd2VkLXJvbGVzIjpbInVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6ImF1dGgwfDYxMWEzYmY0ZDRhZTJkMDA2OTZkMmE4NCJ9LCJuaWNrbmFtZSI6InZhcnNoYSIsIm5hbWUiOiJ2YXJzaGFAZ3JhcGhxbC5jb20iLCJwaWN0dXJlIjoiaHR0cHM6Ly9zLmdyYXZhdGFyLmNvbS9hdmF0YXIvODg1NmQxYTFmYzVhMjIyZjRlNmE4MzdmOTg0NmQzODE_cz00ODAmcj1wZyZkPWh0dHBzJTNBJTJGJTJGY2RuLmF1dGgwLmNvbSUyRmF2YXRhcnMlMkZ2YS5wbmciLCJ1cGRhdGVkX2F0IjoiMjAyMS0wOC0xN1QxMjoyODo0MC43OTNaIiwiaXNzIjoiaHR0cHM6Ly9ncmFwaHFsLXR1dG9yaWFscy5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NjExYTNiZjRkNGFlMmQwMDY5NmQyYTg0IiwiYXVkIjoiUDM4cW5GbzFsRkFRSnJ6a3VuLS13RXpxbGpWTkdjV1ciLCJpYXQiOjE2MjkyMDMzMjMsImV4cCI6MTYyOTIzOTMyMywiYXRfaGFzaCI6Imt5LXVwUEw0MktQd1NUc2NBdkhCQnciLCJub25jZSI6IkdSeU0wWEE3Rk8yQVBpMGZvaUg4SGpYYlJweUc3UTk0In0.AzLj4rOk09n6NgkvW5PtU82LbJzI_ERQgWukeZqt7pfQ_2QWWxHVsDIdML0VGtrEvTtudllmyRz3shylJbF6iGEfC4UxVrBY9b2rCBHUT5XZ2X974Ksnxv5V4SrPmkoy8OQ3SrwZpUFLYE8yB5-bBLSxlWyZxNuxSATgMhFuEhvbxhpy51YdqZqFk9WJ6kQTufKjsHr0Gbvl90xyd5RNiRrAZh6QztoHyfyWXdj2WONr9VB1sRvG0yzKriL31h-ykXvtTIBhqXE9zaPNPdH96o-gibheneb3lZfxdewWYVlPoug8t4glkw1UIRIy7tK6pxF6KpWJkmazuANNQ3T6CQ")
		.body(query).when().log().all().post("learn/graphql").then()
		.log().body().assertThat().statusCode(200);
		//.and().body("data.users[0].name", equalTo("tui.glen"));
	}
	
	@Test(enabled=false)
	public void TestMutationWithoutPojo()
	{
		RestAssured.baseURI="https://www.predic8.de/fruit-shop-graphql?";
		//GQLquery query=new GQLquery();
		String Query="{\"query\":\"mutation {\\n  updateCategory(id: 1, name: \\\"Juicy Fruits\\\", products: [7]) {\\n    name\\n    products {\\n      name\\n    }\\n  }\\n}\\n\",\"variables\":null}";
		given().log().all().contentType("application/json").body(Query)
		.when().log().all().post().then().log().all().assertThat().statusLine("HTTP/1.1 200 OK");
	}

	@Test(dataProvider="getMutationData",enabled=true)
	public void TestMutationWithVariable(int id, String Name)
	{
		RestAssured.baseURI="https://www.predic8.de/fruit-shop-graphql?";
		
		String Query="{\"query\":\"mutation ($id:Int!, $name:String!,$products:[Int!]){\\n  updateCategory(id:$id, name:$name,products: $products)  {\\n    name\\n    products {\\n      name\\n    }\\n  }\\n}\\n\",\"variables\":{\"id\":"+id+",\"name\":\""+Name+"\",\"products\":[2,7]}}";
				
		given().log().all().contentType("application/json").body(Query)
		.when().log().all().post().then().log().all().assertThat().statusLine("HTTP/1.1 200 OK");
	}
	
	@DataProvider
	public Object[][] getMutationData(){
		return new Object[][] {{7,"CherryRed"},
			};
	}
	
	@Test(dataProvider="getQueryData",enabled=false)
	public void testGraphqlfromFile(String id)
	{
		RestAssured.baseURI="https://www.predic8.de/fruit-shop-graphql?";
		String query="{\"query\":\"query($id:String!){\\n  products(id:$id) {\\n    name\\n    price\\n    category {\\n      name\\n    }\\n    vendor {\\n      name\\n      id\\n    }\\n  }\\n}\\n\","
				+ "\"variables\":{\"id\":"+id+"}}";
		//String jsonString=graphqlToJson(query);
//		String query="{\"query\":\"query($id:String!){\n"
//				+ "  products(id:$id) {\n"
//				+ "    name\n"
//				+ "    price\n"
//				+ "    category {\n"
//				+ "      name\n"
//				+ "    }\n"
//				+ "    vendor {\n"
//				+ "      name\n"
//				+ "      id\n"
//				+ "    }\n"
//				+ "  }\n"
//				+ "}\n"
//				+ "\",\"variables\":{\"id\":\"8\"}}";
//		String jsonstring=graphqlToJson(query);
		
		given().log().all().contentType("application/json").body(query)
		.when().log().all().post().then().log().all().assertThat().statusLine("HTTP/1.1 200 OK");
		
		
	}
	
	private static String graphqlToJson(String payload){
        JSONObject json = new JSONObject();
        json.put("query",payload);
        return  json.toString();
    }
}
