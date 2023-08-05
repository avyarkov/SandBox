import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class SunriseAndSunset {
    public static void main(String[] args) throws IOException {
        double latitude = 55.752, longitude = 37.616; // Moscow
        int zoneOffset = 3; // Moscow

        String url = "https://api.sunrise-sunset.org/json?lat=%f&lng=%f&formatted=0".formatted(latitude, longitude);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);
        String responseJsonString = EntityUtils.toString(response.getEntity());

        JSONObject responseJson = new JSONObject(responseJsonString);
        JSONObject resultsJson = responseJson.getJSONObject("results");
        Instant sunriseTime = Instant.parse(resultsJson.getString("sunrise"));
        Instant sunsetTime = Instant.parse(resultsJson.getString("sunset"));

        OffsetDateTime sunriseTimeCorrected = sunriseTime.atOffset(ZoneOffset.ofHours(zoneOffset));
        OffsetDateTime sunsetTimeCorrected = sunsetTime.atOffset(ZoneOffset.ofHours(zoneOffset));
        System.out.println("sunrise: " + sunriseTimeCorrected.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("sunset: " + sunsetTimeCorrected.format(DateTimeFormatter.ISO_LOCAL_TIME));

        httpClient.close();
    }
}
