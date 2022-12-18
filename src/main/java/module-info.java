module com.example.sevenwondersv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.sevenwondersv2 to javafx.fxml;
    exports com.example.sevenwondersv2;
}