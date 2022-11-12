package tests.petstore;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pogos.petstore.pets.Pet;
import pogos.petstore.ResponseBody;
import utils.petstore.DataProviders;
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

  @Test()
  public void getPetById() {
    Pet pet = petService.createPetResponse().as(Pet.class);
    Response response = petService.getPetResponse(pet);
    response.print();
    assertThat(response.getStatusCode()).isEqualTo(200);
    assertThat(response.as(Pet.class)).extracting(Pet::getId).isEqualTo(pet.getId());
  }

  @Test(dataProvider = "statusDataProvider", dataProviderClass = DataProviders.class)
  public void getPetsByStatus(String status) {
    Response response = petService.getPetsByStatusResponse(status);
    response.print();
    assertThat(response.getStatusCode()).isEqualTo(200);
    List<Pet> pets = response.then().extract().jsonPath().getList("$", Pet.class);
    assertThat(pets).extracting(Pet::getStatus).contains(status);
  }

  @Test
  public void postPetUploadImage() {
    Pet pet = petService.createPetResponse().as(Pet.class);
    Response response = petService.postUploadImageResponse(pet);
    response.print();
    assertThat(response.getStatusCode()).isEqualTo(200);
    assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).asString().contains("test.jpg");
  }

  @Test
  public void putPet() {
    Pet pet = petService.createPetResponse().as(Pet.class);
    Response response = petService.putPetResponse(pet);
    response.print();
    assertThat(response.getStatusCode()).isEqualTo(200);
    assertThat(response.as(Pet.class)).isEqualTo(pet).extracting(Pet::getName).isEqualTo("NewName");

  }

  @Test
  public void deletePet() {
    Pet pet = petService.createPetResponse().as(Pet.class);
    Response response = petService.deletePetResponse(pet);
    response.print();
    assertThat(response.getStatusCode()).isEqualTo(200);
    assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo(String.valueOf(pet.getId()));
  }
}

