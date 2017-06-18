package pl.szotaa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Created by Jakub Szota on 10.06.2017.
 *
 * @author Jakub Szota
 * @version 1.0
 *
 *
 *
 */
public class Main extends Application
{
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Captcha Generator");
        this.primaryStage.getIcons().add(new Image("/pl/szotaa/resources/icon.jpg"));
        initRootLayout();
        showCaptchaOverview();
    }

    @Override
    public void stop()
    {
        String path = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.concat("/temp.bmp");
        File file = new File(path);
        file.delete();
        System.out.println("App closing, temp.bmp deleted");
    }

    private void initRootLayout()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        }
        catch (IOException e)
        {
            System.out.println("Exception caught in Main: initRootLayout()");
            e.printStackTrace();
        }
    }

    private void showCaptchaOverview()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/CaptchaOverview.fxml"));
            AnchorPane captchaOverview = loader.load();
            rootLayout.setCenter(captchaOverview);
        }
        catch(IOException e)
        {
            System.out.println("Exception caught in Main: showCaptchaOverview()");
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        System.out.println("\n\n\n\nCaptchaGenerator debug:\n ");
        launch(args);
    }
}
