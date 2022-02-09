package utils.petstore.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pogos.petstore.users.User;

import java.util.*;

import static io.restassured.RestAssured.given;

public class UsersService extends RestService {
    private final static String BASE_PATH = "/user/";
    private final static String PATH_LOGIN = "/user/login";
    private final static String PATH_LOGOUT = "/user/logout";
    private final static String PATH_CREATE_WITH_ARRAY = "/user/createWithArray";
    private final static String PATH_CREATE_WITH_LIST = "/user/createWithList";
    private User user;


    public UsersService() {
        user = CreateUser.getUser();
        REQ_SPEC = new RequestSpecBuilder().setBaseUri(BASE_URL).setBasePath(BASE_PATH).setContentType(ContentType.JSON).build();
    }

    public User getUser() {
        return user;
    }

    public Response createUser() {
        return given()
                .spec(REQ_SPEC)
                .when()
                .body(user)
                .post();
    }

    public Response createUsersArray() {
        User[] users = {user,CreateUser.getUser(),CreateUser.getUser()};
        return given()
                .spec(REQ_SPEC).basePath(PATH_CREATE_WITH_ARRAY)
                .when()
                .body(users)
                .post();
    }

    public Response createUsersList() {
        List<User> users = Arrays.asList(user,CreateUser.getUser(),CreateUser.getUser());
        return given()
                .spec(REQ_SPEC).basePath(PATH_CREATE_WITH_LIST)
                .when()
                .body(users)
                .post();
    }

    public Response getUserResponse() {

        return given()
                .spec(REQ_SPEC)
                .when()
                .get(user.getUsername());
    }

    public Response getLoginResponse() {
        Map<String, String> params = new HashMap<>();
        params.put("username", "NewName");
        params.put("password", "123132131");
        return given()
                .spec(REQ_SPEC).basePath(PATH_LOGIN)
                .when()
                .params(params)
                .get();
    }

    public Response getLogoutResponse() {
        return given()
                .spec(REQ_SPEC).basePath(PATH_LOGOUT)
                .when()
                .get();
    }

    public Response putUserResponse() {
        user.setFirstName("fistName");
        return given()
                .spec(REQ_SPEC)
                .when()
                .body(user)
                .put(user.getUsername());
    }

    public Response deleteUserResponse() {
        return given()
                .spec(REQ_SPEC)
                .when()
                .delete(user.getUsername());
    }
}
