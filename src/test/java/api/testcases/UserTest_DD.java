package api.testcases;

import org.junit.Assert;
import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.user;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserTest_DD {
	user userPayload;

	@Test(priority = 1, dataProvider = "AllData", dataProviderClass = DataProviders.class)
	public void Testcreateuser(String UserID, String username, String firstName, String lastName, String emailAddress,
			String password, String phone) {

		user userPayload = new user();// object ko memoery allocation

		// userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(username);
		userPayload.setFirstName(firstName);
		userPayload.setLastName(lastName);
		userPayload.setEmail(emailAddress);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);

		Response response = userEndPoints.createUser(userPayload);
//response log
		response.then().log().all();
		// validation
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "UserNamesData", dataProviderClass = DataProviders.class)
	public void testDeleteUser(String username) { // Renamed method to follow naming conventions

		Response response = userEndPoints.deleteUser(username);

		// argument
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}