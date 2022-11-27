package poofinal.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

//teste
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("mainMenu.fxml")));
        File file = new File("src/main/resources/files/img/cobaltoIcon.png");
        InputStream inputStream = new FileInputStream(file);
        Image image = new Image(inputStream);
        stage.getIcons().add(image);
        stage.setTitle("Gerenciador de Horas Complementares");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}