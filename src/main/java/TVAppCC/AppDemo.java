package TVAppCC;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by PZON_SM on 05.01.2021.
 **/
public class AppDemo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("View/MainWindow.fxml"));
        stage.setTitle("travelWeatherApp by PZon");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/phiSmall2.png")));

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false);
        stage.sizeToScene();
    }
}
