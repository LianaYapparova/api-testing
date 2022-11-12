package utils.petstore;

import com.github.javafaker.Faker;
import pogos.petstore.pets.Category;
import pogos.petstore.pets.Pet;

public class CreatePet {
    private final static Faker faker = new Faker();
    public static Pet getPet(){
        long id = Long.parseLong(faker.number().digits(2));
        String name = faker.dog().name();
        Category category = getCategory();
        return new Pet.PetBuilder().id(id).name(name).category(category).status("available").build();

    }

    private static Category getCategory() {
        String name =  faker.dog().breed();
        int id = Integer.parseInt(faker.number().digits(4));
        return Category.builder().id(id).name(name).build();
    }
}
