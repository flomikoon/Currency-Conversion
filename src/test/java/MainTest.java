import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void APIwork() {
        try {
            Main.sendRequest("EUR");
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void ResponseTest(){
        JSONObject response = null;
        try {
            JSONObject exchangeRates  = Main.sendRequest("EUR");
            response = exchangeRates.getJSONObject("response");

        } catch (JSONException e) {
            fail();
        }
        assertNotNull(response);
    }

    @Test
    public void RatesTest(){
        double rates = 0.0;
        try {
            JSONObject exchangeRates  = Main.sendRequest("EUR");
            rates = exchangeRates.getJSONObject("response").getJSONObject("rates").getDouble("RUB");

        } catch (JSONException e) {
            fail();
        }
        assertTrue(rates > 0.0);
    }

    @Test
    public void ConvertTest(){
        double value = 0.0;
        try {
            JSONObject exchangeRates  = Main.sendRequest("EUR");
            value = Main.getValue(exchangeRates , "100" , "RUB");

        } catch (JSONException e) {
            fail();
        }
        assertTrue(value > 0.0);
    }

    @Test
    public void CorrectConvertTest(){
        double value = 0.0;
        double valueTwo = 0.0;

        try {
            JSONObject exchangeRates  = Main.sendRequest("EUR");
            value = Main.getValue(exchangeRates , "100" , "RUB");
            exchangeRates  = Main.sendRequest("RUB");
            valueTwo = Main.getValue(exchangeRates , String.valueOf(value), "EUR");
        } catch (JSONException e) {
            fail();
        }

        assertEquals(100 , valueTwo , 0.1);
    }
}
