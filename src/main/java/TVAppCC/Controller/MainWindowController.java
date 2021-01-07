package TVAppCC.Controller;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import TVAppCC.Model.ImgManager;
import TVAppCC.Model.WeatherManager;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by PZON_SM on 05.01.2021.
 **/
public class MainWindowController implements Initializable {
    String setCityOne, setCityTwo;
    WeatherManager weatherManager;

    public MainWindowController() {
        this.setCityOne = "Krakow".toUpperCase();
        this.setCityTwo = "Zakopane".toUpperCase();
    }

    @FXML
    private Pane citiesWindow;

    @FXML
    private Label cityOne;

    @FXML
    private Label cityTwo;

    @FXML
    private Label today;

    @FXML
    private Pane weatherWindow;

    @FXML
    private ImageView vericalLone;

    @FXML
    private ImageView imgOne;

    @FXML
    private ImageView imgTwo;

    @FXML
    private Label tempOne;

    @FXML
    private Label tempTwo;

    @FXML
    private Label windSpeedOne;

    @FXML
    private Label cloudinessOne;

    @FXML
    private Label pressureOne;

    @FXML
    private Label humidityOne;

    @FXML
    private Label windSpeedTwo;

    @FXML
    private Label cloudinessTwo;

    @FXML
    private Label pressureTwo;

    @FXML
    private Label humidityTwo;

    @FXML
    private ImageView img11;

    @FXML
    private Label temp11;

    @FXML
    private Label temp12;

    @FXML
    private ImageView img12;

    @FXML
    private ImageView img13;

    @FXML
    private Label temp13;

    @FXML
    private ImageView img21;

    @FXML
    private Label temp21;

    @FXML
    private ImageView img22;

    @FXML
    private Label temp22;

    @FXML
    private ImageView img23;

    @FXML
    private Label temp23;

    @FXML
    private Pane searchWindow;

    @FXML
    private Button checkButton;

    @FXML
    private Button findButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cityOneInput;

    @FXML
    private TextField cityTwoInput;

    @FXML
    private Label errors;

    @FXML
    private Label desc1;

    @FXML
    private Label desc2;

    @FXML
    private Label day11;

    @FXML
    private Label day12;

    @FXML
    private Label day13;

    @FXML
    private Label day21;

    @FXML
    private Label day22;

    @FXML
    private Label day23;

    @FXML
    void buttonAction(ActionEvent actionEvent) {
        String city1 = cityOne.getText();
        String city2 = cityTwo.getText();

        if(actionEvent.getSource() == findButton){
            cityOneInput.setText("");
            cityTwoInput.setText("");
            bottomPaneSettings(true);
            cityOneInput.requestFocus();
        }else if(actionEvent.getSource() == checkButton){
            setPressed();
        }else if(actionEvent.getSource() == cancelButton){
            cityOneInput.setText(city1);
            cityTwoInput.setText(city2);
            bottomPaneSettings(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cityOneInput.setText(setCityOne);
        cityTwoInput.setText(setCityTwo);
        cityOneInput.setDisable(true);
        cityTwoInput.setDisable(true);
        checkButton.setVisible(false);
        cancelButton.setVisible(false);
        errors.setText("");
        weatherManager = new WeatherManager(setCityOne, setCityTwo);

        try {
            displayWeather();
            displayThreeDaysWeather();
        }catch (Exception e){
            showError("Something went wrong. Try again.");
            resetSettings();
            findButton.setDisable(true);
            cityOneInput.setText("");
            cityTwoInput.setText("");
        }

        cityTwoInput.setOnKeyPressed(e->{
            if(e.getCode() == KeyCode.ENTER){
                setPressed();
            }
        });
    }

    private void setPressed() {
        if(cityOneInput.getText().equals("") || cityTwoInput.getText().equals("")){
            showError("Cities can't be empty");
            return;
        }else{
            try{
                errors.setText("");
                this.setCityOne = cityOneInput.getText().trim();
                this.setCityTwo = cityTwoInput.getText().trim();
                cityOneInput.setText(this.setCityOne.toUpperCase());
                cityTwoInput.setText(this.setCityTwo.toUpperCase());
                weatherManager = new WeatherManager(setCityOne, setCityTwo);
                displayWeather();
                displayThreeDaysWeather();
                bottomPaneSettings(false);
            }catch (Exception e){
                showError("No location found. Try again");
                resetSettings();
            }
        }
    }

    private void bottomPaneSettings(boolean b) {
        cityOneInput.setDisable(!b);
        cityTwoInput.setDisable(!b);
        findButton.setVisible(!b);
        checkButton.setVisible(b);
        cancelButton.setVisible(b);
    }

    private void resetSettings() {
        bottomPaneSettings(false);
        today.setText("");
        desc1.setText("");
        desc2.setText("");
        imgOne.setImage(null);
        img11.setImage(null);
        img12.setImage(null);
        img13.setImage(null);
        imgTwo.setImage(null);
        img21.setImage(null);
        img22.setImage(null);
        img23.setImage(null);
        tempOne.setText(" °C");
        tempTwo.setText(" °C");
        temp11.setText(" °C");
        temp12.setText(" °C");
        temp13.setText(" °C");
        temp21.setText(" °C");
        temp22.setText(" °C");
        temp23.setText(" °C");
        windSpeedOne.setText("");
        windSpeedTwo.setText("");
        cloudinessOne.setText("");
        cloudinessTwo.setText("");
        pressureOne.setText("");
        pressureTwo.setText("");
        humidityOne.setText("");
        humidityTwo.setText("");
    }

    private void showError(String txt) {
        errors.setText(txt);
        if(cityOneInput.getText().equals("")){ cityOneInput.requestFocus();}
        else if(cityTwoInput.getText().equals("")) {cityTwoInput.requestFocus();}

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), errors);
        fadeIn.setToValue(1);
        fadeIn.setFromValue(0);
        fadeIn.play();

        fadeIn.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(event2 -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), errors);
                fadeOut.setToValue(0);
                fadeOut.setFromValue(1);
                fadeOut.play();
            });
        });
    }

    private void displayWeather() {
        today.setText(getDayName(0));

        weatherManager.getWeatherData(cityOneInput.getText());
        cityOne.setText(weatherManager.getCityOne().toUpperCase());
        tempOne.setText(weatherManager.getTempOne().toString()+" °C");
        pressureOne.setText(weatherManager.getPressure()+" hpa");
        humidityOne.setText(weatherManager.getHumidity()+" %");
        windSpeedOne.setText(weatherManager.getWindSpeed()+" m/s");
        cloudinessOne.setText(weatherManager.getCloudiness()+ "%");
        desc1.setText(weatherManager.getDescription());
        imgOne.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getIcon()))));

        weatherManager.getWeatherData(cityTwoInput.getText());
        cityTwo.setText(weatherManager.getCityTwo().toUpperCase());
        tempTwo.setText(weatherManager.getTempOne().toString()+" °C");
        pressureTwo.setText(weatherManager.getPressure()+" hpa");
        humidityTwo.setText(weatherManager.getHumidity()+" %");
        windSpeedTwo.setText(weatherManager.getWindSpeed()+" m/s");
        cloudinessTwo.setText(weatherManager.getCloudiness()+ "%");
        desc2.setText(weatherManager.getDescription());
        imgTwo.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getIcon()))));
    }

    private void displayThreeDaysWeather(){
        day11.setText(getDayName(1));
        day21.setText(getDayName(1));
        day12.setText(getDayName(2));
        day22.setText(getDayName(2));
        day13.setText(getDayName(3));
        day23.setText(getDayName(3));

        weatherManager.getThreeDaysForecast(cityOneInput.getText());
        temp11.setText(weatherManager.getTemp11().toString()+" °C");
        temp12.setText(weatherManager.getTemp12().toString()+" °C");
        temp13.setText(weatherManager.getTemp13().toString()+" °C");
        img11.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getImg11()))));
        img12.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getImg12()))));
        img13.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getImg13()))));

        weatherManager.getThreeDaysForecast(cityTwoInput.getText());
        temp21.setText(weatherManager.getTemp11().toString()+" °C");
        temp22.setText(weatherManager.getTemp12().toString()+" °C");
        temp23.setText(weatherManager.getTemp13().toString()+" °C");
        img21.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getImg11()))));
        img22.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getImg12()))));
        img23.setImage(new Image(getClass().getResourceAsStream(ImgManager.setImg(weatherManager.getImg13()))));

    }

    private String getDayName(int i){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,i);
        return simpleDateFormat.format(calendar.getTime());
    }

}
