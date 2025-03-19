package co.edu.uniquindio.project.projectapp;

import co.edu.uniquindio.project.projectapp.viewController.ProjectViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import co.edu.uniquindio.project.projectapp.factory.ModelFactory;

public class App extends Application {
    private Stage primaryStage;
    @SuppressWarnings("exports")
    public static ModelFactory modelFactory;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Compañia");
        openPrincipalView();
    }

    public void openPrincipalView() {
        try {
            invoke();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("projectMenu.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            ProjectViewController projectViewController = loader.getController();
            projectViewController.setApp(this);


            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    private void invoke(){
        App.modelFactory = ModelFactory.getInstance();
    }
}