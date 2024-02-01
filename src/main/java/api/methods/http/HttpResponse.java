package api.methods.http;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

public class HttpResponse {

    @Getter @Setter
    private Response response;
}
