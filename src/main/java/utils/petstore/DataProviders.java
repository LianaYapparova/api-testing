package utils.petstore;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "statusDataProvider")
    public Object[][] dpMethod() {
        return new Object [][] { {"available"}, {"pending"},{"sold"}};
    }

}
