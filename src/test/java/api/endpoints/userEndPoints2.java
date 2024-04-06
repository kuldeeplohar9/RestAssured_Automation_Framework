package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userEndPoints2 {
	static ResourceBundle getURL()

	{

		ResourceBundle routes = ResourceBundle.getBundle("Routes");// load Propties file
		return routes;
	}

	public static Response createUser(user payload) {

		String post_url = getURL().getString("post_url");
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload).when()
				.post(post_url);
		return response;
	}

	public static Response GetUser(String username) {
		String get_url = getURL().getString("get_url");
		Response response = given().accept(ContentType.JSON).pathParam("username", username).when().get(get_url);
		return response;
	}

	public static Response putUser(String username, user payload) {
		String put_url = getURL().getString("put_url");
		Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON).body(payload)
				.pathParam("username", username).when().put(put_url);
		return response;
	}

	public static Response deleteUser(String username) {
		String det_url = getURL().getString("det_url");
		Response response = given().accept(ContentType.JSON).pathParam("username", username).when().delete(det_url);
		return response;
	}

}
