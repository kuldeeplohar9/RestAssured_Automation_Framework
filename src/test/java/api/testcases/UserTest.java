package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.user;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;// class leevel par object
	user userPayload;

	public static Logger logger;/// first need to create object

	@BeforeClass
	public void generateTestData() {
		faker = new Faker();// object ko memoery allocation
		userPayload = new user();// object ko memoery allocation

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		logger = LogManager.getLogger("RestAssuredAutomationFramework");

	}

	@Test(priority = 1)
	public void Testcreateuser() {
		Response response = userEndPoints.createUser(userPayload);
//response log
		response.then().log().all();
		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Testcreateuser Sucessfully");
	}

	@Test(priority = 2)
	public void TestGetuserdata() {
		Response response = userEndPoints.GetUser(this.userPayload.getUsername());
		// userEndPoints.GetUser(this.userPayload.getUsername());
//response log
		response.then().log().all();
		// validation
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("TestGetuserdata Sucessfully");
	}

	@Test(priority = 3)
	public void testUpdateUser() { // Renamed method to follow naming conventions

		userPayload.setFirstName(faker.name().firstName());
		Response response = userEndPoints.putUser(this.userPayload.getUsername(), userPayload); // Added userPayload as
		// argument
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

		// read update data
		Response responsepostupdate = userEndPoints.GetUser(this.userPayload.getUsername());
		System.out.println("After update");
		responsepostupdate.then().log().all();
		logger.info("testUpdateUser sucessfully");

	}

	@Test(priority = 4)
	public void testDeleteUser() { // Renamed method to follow naming conventions

		Response response = userEndPoints.deleteUser(this.userPayload.getUsername());
		// argument
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("user testDeleteUser sucessfully");

	}
}
