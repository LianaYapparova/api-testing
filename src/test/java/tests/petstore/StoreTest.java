package tests.petstore;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pogos.petstore.ResponseBody;
import pogos.petstore.store.Order;
import utils.petstore.services.StoreService;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreTest {
    private StoreService storeService;

    @BeforeClass
    public void prepareService() {
        storeService = new StoreService();
    }

    @Test
    public void postStore(){
        Response response = storeService.createOrder();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(Order.class)).isEqualTo(storeService.getOrder());
    }
    @Test(dependsOnMethods = "postStore")
    public void getStoreById(){
        Response response = storeService.getOrderResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(Order.class)).extracting(Order::getId).isEqualTo(storeService.getOrder().getId());
    }
    @Test(dependsOnMethods = "getStoreById")
    public void deleteStoreById(){
        Response response = storeService.deleteOrderResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo(String.valueOf(storeService.getOrder().getId()));
    }
    @Test(dependsOnMethods = "deleteStoreById")
    public void getInventory(){
        Response response = storeService.getInventoryResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

}
