package TVAppCC.Controller;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by PZON_SM on 29.04.2021.
 **/
class MainWindowControllerTest {
    private MainWindowController mainWindowController = new MainWindowController();


    @Test
    void shouldReturnCorrectDayName(){
        //given
        // MainWindowController mainWindowController = new MainWindowController();
        //when
        String name = mainWindowController.getDayName(1);
        //then
        assertThat(name, equalTo("Friday"));
    }

    @Test
    void constructorShouldCreateTwoDefaultCities(){
        //given
        //when
        //then
        assertThat(mainWindowController.setCityOne, equalTo("Limanowa".toUpperCase()));
        assertThat(mainWindowController.setCityTwo, equalTo("Kraków".toUpperCase()));
    }

    @Test
    void referenceToOtherObjectShouldNotBeEqual(){
        MainWindowController mainWindowController2 = new MainWindowController();
        assertThat(mainWindowController2, not(sameInstance(mainWindowController)));
    }

}