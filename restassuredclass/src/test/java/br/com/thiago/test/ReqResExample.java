package br.com.thiago.test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.thiago.servicos.Servicos;
import br.com.thiago.test.entidate.PessoaRequest;
import br.com.thiago.test.entidate.PessoaResponse;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ReqResExample {

	@Before
	public void configuraApi() {
		baseURI = 	"https://reqres.in/";
	}

	@Test
	public void methodGet() {
			given()
		.when()
			.get(Servicos.Users_ID.getValor(), 2)
		.then().contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExample.json"))
			.log().all();
				
	}
	
	@Test
	public void methodGet2() {
			given()
		.when()
			.get(Servicos.Users_ID.getValor(), 23)
		.then().contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/thiagoExample.json"))
			.log().all();
				
	}
	
	@Test
	public void methodPost() {
		PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");
		
		basePath= "/api/users";		
		PessoaResponse as = given()
			.contentType("application/json")
		.body(pessoaRequest)
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED)
			.extract().response().as(PessoaResponse.class);
		
		Assert.assertNotNull(as);
		Assert.assertNotNull(as.getId());
		Assert.assertEquals(pessoaRequest.getName(), as.getName());
		Assert.assertEquals(pessoaRequest.getJob(), as.getJob());
	}

	@Test
	public void methodPost2() {
		PessoaRequest pessoaRequest = new PessoaRequest("thiago","QA");
		
		basePath= "/api/users";		
		String as =
				given()
			.contentType("application/json")
		.body(pessoaRequest)
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED).log().all()
			.and().extract().response().path("nome");
		
		System.out.println(as);
		Assert.assertNotNull(as);
	}
	
	@Test
	public void methodGetAll() {
		
		basePath= "/api/users?page=2";	
		
		 given()
		.when()
			.get(Servicos.getUsers_PAGE.getValor(), 2)
		.then().contentType(ContentType.JSON)
			.statusCode(HttpStatus.SC_OK)
			.and()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schemas/usuarios.json"))
			.log().all();
	}
	
	@Test
	public void methodPostCreate() {
	PessoaRequest pessoaRequest = new PessoaRequest("Danilo","DEV");
		
		basePath= "/api/users";		
		PessoaResponse as = given()
			.contentType("application/json")
		.body(pessoaRequest)
		.when()
			.post("/")
		.then()
			.statusCode(HttpStatus.SC_CREATED)
			.extract().response().as(PessoaResponse.class);
		
		Assert.assertNotNull(as);
		Assert.assertNotNull(as.getId());
		Assert.assertEquals(pessoaRequest.getName(), as.getName());
		Assert.assertEquals(pessoaRequest.getJob(), as.getJob());
	}	

	@Test
	public void methodPut() {
	PessoaRequest pessoaRequest = new PessoaRequest("morpheus 123","zion resident 123");
			
		PessoaResponse as = given()
			.contentType(ContentType.JSON)
		.body(pessoaRequest)
		.when()
			.put(Servicos.Users_ID.getValor(), 2)
		.then()
		.assertThat()
			.statusCode(HttpStatus.SC_OK).log().all()
			.extract().response().as(PessoaResponse.class);
		
		Assert.assertNotNull(as);
		Assert.assertEquals(pessoaRequest.getName(), as.getName());
		Assert.assertEquals(pessoaRequest.getJob(), as.getJob());
	}
	
	
	@Test
	public void methodPatch() {
	PessoaRequest pessoaRequest = new PessoaRequest("morpheus","zion resident");
				
		PessoaResponse as = given()
			.contentType(ContentType.JSON)
		.body(pessoaRequest)
		.when()
			.patch(Servicos.Users_ID.getValor(), 2)
		.then()
			.statusCode(HttpStatus.SC_OK).log().all()
			.extract().response().as(PessoaResponse.class);
		
		Assert.assertNotNull(as);
		Assert.assertNotNull(as.getId());
		Assert.assertEquals(pessoaRequest.getName(), as.getName());
		Assert.assertEquals(pessoaRequest.getJob(), as.getJob());
		Assert.assertNotNull(as.getUpdatedAt());
	}
	
	@Test
	public void methodDelete() {
			
		 given()
			.contentType(ContentType.JSON)
		.when()
			.delete(Servicos.Users_ID.getValor(), 2)
		.then()
			.assertThat()
			.statusCode(HttpStatus.SC_CREATED);		
	}
	
}
