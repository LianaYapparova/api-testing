package tests.petstore;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pogos.petstore.ResponseBody;
import pogos.petstore.users.User;
import utils.petstore.services.UsersService;

public class UserTest {
    private UsersService usersService;

    @BeforeClass
    public void prepareService() {
        usersService = new UsersService();
    }

    @Test
    public void postUser(){
        Response response = usersService.createUser();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        int idUser = usersService.getUser().getId();
        assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo(String.valueOf(idUser));
    }

    @Test
    public void postUsersWithArray(){
        Response response = usersService.createUsersArray();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo("ok");

    }
    @Test
    public void postUsersWithList(){
        Response response = usersService.createUsersList();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo("ok");
    }

    @Test
    public void getUser(){
        usersService.createUser();
        Response response = usersService.getUserResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        assertThat(response.as(User.class)).extracting(User::getId).isEqualTo(usersService.getUser().getId());
    }

    @Test
    public void getLogin(){
        Response response = usersService.getLoginResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @Test
    public void putUser(){
        Response response = usersService.putUserResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
        int idUser = usersService.getUser().getId();
        assertThat(response.as(ResponseBody.class)).extracting(ResponseBody::getMessage).isEqualTo(String.valueOf(idUser));

    }

    @Test
    public void deleteUser(){
        usersService.createUser();
        Response response = usersService.deleteUserResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);
    }
    @Test
    public void getLogout(){
        Response response = usersService.getLogoutResponse();
        response.print();
        assertThat(response.getStatusCode()).isEqualTo(200);

    }


}
