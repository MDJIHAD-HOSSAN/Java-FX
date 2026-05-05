module bd.edu.seu.javafxapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens bd.edu.seu.javafxapplication to javafx.fxml;
    exports bd.edu.seu.javafxapplication;
}