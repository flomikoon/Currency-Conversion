import java.util.Objects;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static final String ACCESS_KEY = "5920be24b974d1cc7f15f9079a4dd417";
    public static final String BASE_URL = "https://api.currencyscoop.com/v1/";
    public static final String ENDPOINT = "latest";

    static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void sendRequest(int i) {
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?api_key=" + ACCESS_KEY);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));

            System.out.println(i + " USD" + " in EUR : " + i * exchangeRates.getJSONObject("response")
                    .getJSONObject("rates").getDouble("EUR"));
            response.close();
        } catch (IOException | ParseException | JSONException e) {
            e.printStackTrace();
        }
    }

    // sendLiveRequest() function is executed
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter usd: ");
            String string = reader.readLine();
            if(Objects.equals(string, "stop")){
                break;
            }
            sendRequest(Integer.parseInt(string));
        }
        httpClient.close();
    }
}
