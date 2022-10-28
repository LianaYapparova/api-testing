package utils.petstore.services;

import com.github.javafaker.Faker;
import pogos.petstore.store.Order;

public class CreateOrder {
    private static Faker faker = new Faker();
    public static Order getOrder() {
        int id = Integer.parseInt(faker.number().digits(2));
        int idPet = Integer.parseInt(faker.number().digits(2));
        int quantity = Integer.parseInt(faker.number().digits(2));
        return Order.builder().id(id).quantity(quantity).complete(true).petId(idPet).build();
    }
}
