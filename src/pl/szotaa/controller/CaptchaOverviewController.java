package pl.szotaa.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.szotaa.model.BmpGenerator;

import java.io.File;
import java.util.Optional;

/**
 * Created by kubas on 12.06.2017.
 */
public class CaptchaOverviewController
{
    @FXML
    private Spinner<Integer> numOfDigits;

    @FXML
    private ComboBox<String> blur;

    @FXML
    private TextField captcha;

    @FXML
    private ImageView imageView;

    @FXML
    private Label result;

    @FXML
    private Label welcomeText;

    private String response;

    public CaptchaOverviewController(){}

    @FXML
    private void initialize()
    {
        System.out.println("Successfully loaded fxml file");
    }

   @FXML
    private void handleGenerate()
    {
        String digits = numOfDigits.getValue().toString();
        String blurValueName = blur.getValue();
        int blurValue = 0;
        switch (blurValueName)
        {
            case "None":
                blurValue = 0;
                break;
            case "Low":
            blurValue = 1000;
                break;
            case "Medium":
                blurValue = 3000;
                break;
            case "High":
                blurValue = 5000;
                break;
            case "Unreadable":
                blurValue = 7000;
                break;
        }
        System.out.println("Generating output for " + digits +" digits and " + blurValue + " blurValue (" + blurValueName +")");
        response = BmpGenerator.buildOutput(digits, Integer.toString(blurValue));
        System.out.println("Got response: " + response);
        String path = CaptchaOverviewController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.substring(0, path.lastIndexOf("/"));
        path = path.concat("/temp.bmp");
        System.out.println("Bmp path: " + path);
        File file = new File(path);
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        welcomeText.setVisible(false);
        captcha.setText("");
        result.setText("");
    }

    @FXML
    private void handleSave() //saves bitmap as captchacode_captcha.bmp
    {
        System.out.println("Save");
        String rootpath = CaptchaOverviewController.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        rootpath = rootpath.substring(0, rootpath.lastIndexOf("/"));
        String bmppath = rootpath.concat("/temp.bmp");
        File file = new File(bmppath);
        String newPath = rootpath.concat("/"+ response + "_captcha.bmp");
        System.out.println("New path: " + newPath);
        File newFile = new File(newPath);
        if(newFile.exists())
        {
            System.out.println("File already exist");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Save captcha");
            alert.setHeaderText("File already exist");
            alert.setContentText("Overwrite?");
            Optional<ButtonType> action = alert.showAndWait();
            if(action.get() == ButtonType.OK )
            {
                System.out.println("Saved file at:\n" + rootpath + "\n as: " + response + "_captcha.bmp");
                file.renameTo(new File(response + "_captcha.bmp"));
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setTitle("Save captcha");
                saved.setHeaderText("Saved file at: " + rootpath);
                saved.setContentText("as: " + response + "_captcha.bmp");
                saved.show();
            }
            else
            {
                System.out.println("User aborted saving file");
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setTitle("Save captcha");
                saved.setHeaderText("Saving aborted");
                saved.setContentText("Remove, move or rename " + response + "_captcha.bmp" + " to make saving possible without overwriting");
                saved.show();
            }
        }
        else
        {
            System.out.println("Saved file at:\n" + rootpath + "\n as: " + response + "_captcha.bmp");
            file.renameTo(new File(response + "_captcha.bmp"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save captcha");
            alert.setHeaderText("Saved file at: " + rootpath);
            alert.setContentText("as: " + response + "_captcha.bmp");
            alert.show();
        }
    }

    @FXML
    private void handleTry()
    {
        if(!(response == null))
        {
            if(captcha.getText().equals(response))
                result.setText("✓");
            else
                result.setText("✘");
        }
        else
        {
            System.out.println("User tried captcha before generating it");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Generate Captcha first");
            alert.setHeaderText("Please generate captcha before trying it");
            alert.show();
        }
    }
}
