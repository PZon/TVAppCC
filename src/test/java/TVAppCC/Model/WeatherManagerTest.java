package TVAppCC.Model;

import TVAppCC.Priv;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by PZON_SM on 29.04.2021.
 **/
class WeatherManagerTest {
    private WeatherManager weatherManager = new WeatherManager("Limanowa", "Kraków");

    @Test
    void constructorReturnCorrectCitiesName(){
        //given
        //when
        //then
        assertThat(weatherManager.cityOne, equalTo("Limanowa"));
        assertThat(weatherManager.cityTwo, equalTo("Kraków"));
    }

    @Test
    void cityNameIsNotNull(){
        //given
        //when
        String cityOneName=weatherManager.getCityOne();
        //then
        assertNotNull(cityOneName);
    }

    @Test
    void createNewWMWithNewParameters(){
        //given
        WeatherManager weatherManager = new WeatherManager("One","Two");
        //when
        String cityTwoName = weatherManager.getCityTwo();
        //then
        assertThat(cityTwoName, equalTo("Two"));
    }

    @Test
    void constructorShouldReturnApiKey(){
        //given
        Priv priv = new Priv();
        //when
        String key = priv.getKey();
        //then
        assertNotNull(key);
    }

    @Test
    void someTest(){
        //given
        //when
        String name = weatherManager.getCityOne();
        //than
        assertThat(name, notNullValue());
        assertThat(name,containsString("iman"));
        assertThat(name.length(), lessThan(10));
    }


}