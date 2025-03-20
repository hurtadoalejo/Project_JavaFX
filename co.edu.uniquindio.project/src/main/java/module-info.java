module co.edu.uniquindio.poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.desktop;

    opens co.edu.uniquindio.project.projectapp to javafx.fxml;
    exports co.edu.uniquindio.project.projectapp;
    exports co.edu.uniquindio.project.projectapp.viewController;
    opens co.edu.uniquindio.project.projectapp.viewController to javafx.fxml;
}