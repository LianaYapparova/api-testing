package utils.petstore.services;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "statusDataProvider")
    public Object[][] dpMethod() {
        return new Object [][] { {"available"}, {"pending"},{"sold"}};
    }

}
