package utils.petstore.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pogos.petstore.pets.Pet;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetService extends RestService {
    private final static String BASE_PATH = "/pet/";
    private final static String PATH_POST_IMAGE = "/pet/%d/uploadImage";
    private final static String PATH_GET_BY_STATUS = "/pet/findByStatus";
    private Pet pet;


    public PetService() {
        pet = CreatePet.getPet();
        REQ_SPEC = new RequestSpecBuilder().setBaseUri(BASE_URL).setBasePath(BASE_PATH).setContentType(ContentType.JSON).build();
    }

    public Response createPetResponse() {
        return given()
                .spec(REQ_SPEC)
                .when()
                .body(pet)
                .post();
    }

    public Response getPetResponse() {
        return given()
                .spec(REQ_SPEC).basePath(BASE_PATH + pet.getId())
                .when()
                .get();
    }

    public Response getPetsByStatusResponse(String status) {
        return given()
                .spec(REQ_SPEC).basePath(PATH_GET_BY_STATUS)
                .when()
                .param("status", status)
                .get();
    }

    public Response postUploadImageResponse() {
        File file = new File("src/test/resources/uploads/test.jpg");
        return given()
                .spec(REQ_SPEC).contentType("multipart/form-data").basePath(String.format(PATH_POST_IMAGE, pet.getId()))
                .when()
                .multiPart(file)
                .post();
    }

    public Response putPetResponse() {
        pet.setName("NewName");
        return given()
                .spec(REQ_SPEC)
                .when()
                .body(pet)
                .post();
    }


    public Response deletePetResponse() {
        return given()
                .spec(REQ_SPEC).basePath(BASE_PATH + pet.getId())
                .when()
                .delete();
    }

    public Pet getPet() {
        return pet;
    }
}
