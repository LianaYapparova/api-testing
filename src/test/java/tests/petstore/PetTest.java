package tests.petstore;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pogos.petstore.pets.Pet;
import pogos.petstore.ResponseBody;
import utils.petstore.services.DataProviders;
import utils.petstore.services.PetService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PetTest {
    private PetService petService;

    @BeforeClass
    public void prepareService() {
        petService = new PetService();
    }

    @Test
    public void postPet() {
        Response response = petService.createPetResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(Pet.class)).isEqualTo(petService.getPet());
    }
    @Test(dependsOnMethods = "postPet")
    public void getPetById() {
        Response response = petService.getPetResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(Pet.class)).extracting(Pet::getId).isEqualTo(petService.getPet().getId());
    }
    @Test(dataProvider = "statusDataProvider", dataProviderClass = DataProviders.class)
    public void getPetsByStatus(String status) {
        Response response = petService.getPetsByStatusResponse(status);
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        List<Pet> pets = response.then().extract().jsonPath().getList("$", Pet.class);
        assertThat(pets).extracting(Pet::getStatus).contains(status);
    }
    @Test(dependsOnMethods = "postPet")
    public void postPetUploadImage() {
        Response response = petService.postUploadImageResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).asString().contains("test.jpg");
    }
    @Test(dependsOnMethods = "postPet")
    public void putPet() {
        Response response = petService.putPetResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(Pet.class)).isEqualTo(petService.getPet()).extracting(Pet::getName).isEqualTo("NewName");

    }
    @Test(dependsOnMethods = "getPetById")
    public void deletePet() {
        Response response = petService.deletePetResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        String actual = String.valueOf(petService.getPet().getId());
       assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo(String.valueOf(petService.getPet().getId()));
    }
}

