/**
 * Created by PZON_SM on 05.01.2021.
 **/module TVAppCC {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.web;
    requires org.json;

    opens TVAppCC;
    opens TVAppCC.Controller;
    opens TVAppCC.View;
    opens TVAppCC.Model;
}