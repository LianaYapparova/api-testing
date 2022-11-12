package utils.petstore.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pogos.petstore.store.Order;
import utils.petstore.CreateOrder;

import static io.restassured.RestAssured.given;

public class StoreService extends RestService {
    private final static String BASE_PATH = "/store/order";
    private final static String PATH_INVENTORY = "/store/inventory";
    private final Order order;


    public StoreService() {
        order = CreateOrder.getOrder();
        REQ_SPEC = new RequestSpecBuilder().setBaseUri(BASE_URL).setBasePath(BASE_PATH).setContentType(ContentType.JSON).build();
    }

    public Response createOrder() {
        System.out.println(order);
        return given()
                .spec(REQ_SPEC)
                .when()
                .body(order)
                .post();
    }

    public Order getOrder() {
        return order;
    }

    public Response getOrderResponse(Order order) {
        return given()
                .spec(REQ_SPEC)
                .when()
                .get("/" + order.getId());
    }

    public Response deleteOrderResponse(Order order) {
        return given()
                .spec(REQ_SPEC)
                .when()
                .delete("/" + order.getId());
    }

    public Response getInventoryResponse() {
        return given()
                .spec(REQ_SPEC).basePath(PATH_INVENTORY)
                .when()
                .get();
    }
}
