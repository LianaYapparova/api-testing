package utils.petstore.services;

import io.restassured.specification.RequestSpecification;
import utils.UtilsMethod;

public abstract class RestService {
    protected final static String BASE_URL = UtilsMethod.getUrl("url.gateway");
    protected RequestSpecification REQ_SPEC;
}
