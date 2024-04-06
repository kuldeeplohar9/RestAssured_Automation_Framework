package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints {
	public static Response createUser(user payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload).when()
				.post(Routes.post_url);
		return response;
	}

	public static Response GetUser(String username) {
		Response response = given().accept(ContentType.JSON).pathParam("username", username).when().get(Routes.get_url);
		return response;
	}

	public static Response putUser(String username, user payload) {
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(Routes.put_url);
		return response;
	}

	public static Response deleteUser(String username) {
		Response response = given().accept(ContentType.JSON).pathParam("username", username).when()
				.delete(Routes.det_url);
		return response;
	}

}
