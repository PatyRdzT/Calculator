module com.calculator.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.calculator to javafx.fxml;
    exports com.calculator;
}