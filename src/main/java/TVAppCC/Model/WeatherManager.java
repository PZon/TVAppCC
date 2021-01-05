package TVAppCC.Model;

import TVAppCC.Priv;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by PZON_SM on 05.01.2021.
 **/
public class WeatherManager {
    String cityOne,cityTwo;
    String apiKey;
    Integer tempOne,temp11, temp12, temp13;
    String pressure;
    String humidity;
    String windSpeed;
    String cloudiness;
    String description;
    String icon;
    String img11, img12, img13;

    public String getImg11() { return img11; }

    public String getImg12() { return img12; }

    public String getImg13() { return img13; }

    public String getIcon() { return icon; }

    public String getDescription() { return description; }

    public String getCloudiness() {
        return cloudiness;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public Integer getTempOne() {
        return tempOne;
    }

    public Integer getTemp11() {
        return temp11;
    }

    public Integer getTemp12() {
        return temp12;
    }

    public Integer getTemp13() {
        return temp13;
    }

    public String getCityOne() {
        return cityOne;
    }

    public String getCityTwo() {
        return cityTwo;
    }


    public WeatherManager(String cityOne, String cityTwo) {
        Priv priv = new Priv();
        this.cityOne = cityOne;
        this.cityTwo = cityTwo;
        this.apiKey = priv.getKey();
    }


    //Build a String from the read Json file
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    //Reads and returns the JsonObject
    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public void getWeatherData(String city){
        JSONObject json;
        JSONObject jsonData;
        try{
            json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey+"&lang=eng&units=metric");
        }catch (IOException e){
            return;
        }

        jsonData=json.getJSONObject("main");
        this.tempOne = jsonData.getInt("temp");
        this.pressure = jsonData.get("pressure").toString();
        this.humidity = jsonData.get("humidity").toString();

        jsonData=json.getJSONObject("wind");
        this.windSpeed = jsonData.get("speed").toString();

        jsonData=json.getJSONObject("clouds");
        this.cloudiness = jsonData.get("all").toString();

        jsonData=json.getJSONArray("weather").getJSONObject(0);
        this.description=jsonData.get("description").toString();
        this.icon=jsonData.get("icon").toString();
    }


    public void getThreeDaysForecast(String city){
        JSONObject json;
        JSONObject jsonData1, jsonData2, jsonData3, jsonTemp;
        try{
            json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/forecast?q="+city+"&cnt=25&appid="+apiKey+"&lang=eng&units=metric");
        }catch (IOException e){
            return;
        }

        jsonData1=json.getJSONArray("list").getJSONObject(8).getJSONObject("main");
        this.temp11=jsonData1.getInt("temp");
        jsonData1=json.getJSONArray("list").getJSONObject(8).getJSONArray("weather").getJSONObject(0);
        this.img11=jsonData1.get("icon").toString();


        jsonData2 =json.getJSONArray("list").getJSONObject(16).getJSONObject("main");
        this.temp12=jsonData2.getInt("temp");
        jsonData2=json.getJSONArray("list").getJSONObject(16).getJSONArray("weather").getJSONObject(0);
        this.img12=jsonData2.get("icon").toString();

        jsonData3 =json.getJSONArray("list").getJSONObject(24).getJSONObject("main");
        this.temp13=jsonData3.getInt("temp");
        jsonData3=json.getJSONArray("list").getJSONObject(24).getJSONArray("weather").getJSONObject(0);
        this.img13=jsonData3.get("icon").toString();

    }

}
