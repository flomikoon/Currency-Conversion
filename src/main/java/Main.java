import java.util.Objects;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
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

public class Main {
    public static final String ACCESS_KEY = "5920be24b974d1cc7f15f9079a4dd417";
    public static final String BASE_URL = "https://api.currencyscoop.com/v1/";
    public static final String ENDPOINT = "latest";

    static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static JSONObject sendRequest(String string) {
        HttpGet get = new HttpGet(BASE_URL + ENDPOINT + "?api_key=" + ACCESS_KEY + "&base=" + string);

        JSONObject exchangeRates = null;

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();

            exchangeRates = new JSONObject(EntityUtils.toString(entity));
            response.close();
        } catch (IOException | ParseException | JSONException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }

    public static double getValue(JSONObject exchangeRates , String string , String currencyout) throws JSONException {
        return Double.parseDouble(string) * exchangeRates.getJSONObject("response")
                .getJSONObject("rates").getDouble(currencyout);
    }

    public static void main(String[] args) throws IOException, JSONException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Enter the currency you want to convert: ");
            String currency = reader.readLine();
            System.out.print("Enter the currency you want to convert to: ");
            String currencyout = reader.readLine();
            System.out.print("Enter a number: ");
            String string = reader.readLine();
            if(Objects.equals(string, "stop")){
                break;
            }
            JSONObject exchangeRates = sendRequest(currency);

            System.out.println(Double.parseDouble(string) +  " " + string + " in " + currencyout + ": " + getValue(exchangeRates , string , currencyout));
        }
        httpClient.close();
    }
}
