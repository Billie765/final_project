package api;

import api.util.Kharkiv;
import api.util.Variables;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.Description;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class APITestSuite {

    String url = Variables.url;
    String path = Variables.path;
    CloseableHttpClient httpClient;
    HttpGet httpGet;
    CloseableHttpResponse response;
    URIBuilder uriBuilder;


    @BeforeSuite
    public void setUp() throws URISyntaxException {
        httpClient = HttpClients.createDefault();
        uriBuilder = new URIBuilder();
        uriBuilder.setScheme(Variables.scheme)
                .setHost(url)
                .setPath(path)
                .addParameter(Variables.query, Kharkiv.city)
                .addParameter(Variables.appId,Variables.key);
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
    @Description("Check status code")
    public void checkResponse() {
        Assert.assertEquals(response
                .getStatusLine().getStatusCode(), 200, "Response code is not 200");
    }

    @Test
    @Description("Check Content Type")
    public void checkContentType() {
        Assert.assertEquals(response
                .getHeaders("Content-Type")[0].getValue(),"application/json; charset=utf-8", "incorrect Content Type");
    }

    @Test
    @Description("Check that coordinates are correct")
    public void checkCoordinates() throws IOException {
        String jsonString = EntityUtils.toString(response.getEntity());
        DocumentContext doc = JsonPath.parse(jsonString);
        Double lon = doc.read("$.coord.lon");
        Assert.assertEquals(lon, Kharkiv.lon, "Incorrect longtitude");
        Integer lat = doc.read("$.coord.lat");
        Assert.assertEquals(lat, Integer.valueOf(Kharkiv.lat), "Incorrect latitude");
    }

    @Test
    @Description("Check country code")
    public void checkCountry() throws IOException {
        String jsonString = EntityUtils.toString(response.getEntity());
        DocumentContext doc = JsonPath.parse(jsonString);
        String country = doc.read("$.sys.country");
        Assert.assertEquals(country, Kharkiv.country, "Incorrect country");
    }

    @Test
    @Description("Check timezone")
    public void checkTimeZone() throws IOException {
        String jsonString = EntityUtils.toString(response.getEntity());
        DocumentContext doc = JsonPath.parse(jsonString);
        int timezone = doc.read("$.timezone");
        Assert.assertEquals(timezone, Kharkiv.timezone, "Incorrect timezone");
    }

    @Test
    @Description("Check city name")
    public void checkCity() throws IOException {
        String jsonString = EntityUtils.toString(response.getEntity());
        DocumentContext doc = JsonPath.parse(jsonString);
        String city = doc.read("$.name");
        Assert.assertEquals(city, Kharkiv.city, "Incorrect city");
    }

    @Test
    @Description("Check city id")
    public void checkCityId() throws IOException {
        String jsonString = EntityUtils.toString(response.getEntity());
        DocumentContext doc = JsonPath.parse(jsonString);
        int id = doc.read("$.id");
        Assert.assertEquals(id, Kharkiv.cityId, "Incorrect city id");
    }

    @Test
    @Description
    public void checkWeatherDescription() throws IOException {
        String jsonString = EntityUtils.toString(response.getEntity());
        DocumentContext doc = JsonPath.parse(jsonString);
        String main = doc.read("$.weather[0].main");
        Assert.assertNotNull(main, "No main message");
        String description = doc.read("$.weather[0].description");
        Assert.assertNotNull(description, "No weather description");
    }
}
