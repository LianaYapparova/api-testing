package utils.petstore.services;

import com.github.javafaker.Faker;
import com.github.javafaker.IdNumber;
import pogos.petstore.users.User;

public class CreateUser {
    private static Faker faker = new Faker();

    public static User getUser() {
        int id = Integer.parseInt(faker.number().digits(4));
        String name = faker.name().name();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().cellPhone();
        return User.builder().id(id).username(name).firstName(firstName)
                .lastName(lastName).email(email).password(password).phone(phone).build();
    }
}
