package api;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APITestSuite {

    String url = "api.openweathermap.org";
    //String url = "https://google.com";
    String path = "/data/2.5/weather";
    CloseableHttpClient httpClient;
    HttpGet httpGet;
    CloseableHttpResponse response;
    URIBuilder uriBuilder;

    @BeforeSuite
    public void setUp() throws URISyntaxException {
        httpClient = HttpClients.createDefault();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https")
                .setHost(url)
                .setPath(path)
                .addParameter("q","Kharkiv")
                .addParameter("appId","2e51d7e714d629162ef4234958cfd183");
        URI uri = uriBuilder.build();
        httpGet = new HttpGet(uri);
        System.out.println("Before Suite");
    }

    @BeforeMethod
    public void beforeMethod() throws IOException {
        response = httpClient.execute(httpGet);
        System.out.println("Before method");
    }

    @Test
    public void checkResponse() {
        Assert.assertEquals(response
                .getStatusLine().getStatusCode(), 200, "Response code is not 200");
    }

    @Test
    public void checkContentType() {
        Assert.assertEquals(response
                .getHeaders("Content-Type")[0].getValue(),"application/json; charset=utf-8", "incorrect Content Type");
    }
}
