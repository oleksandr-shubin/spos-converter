package com.shubin.spos.converter.Controller;

import com.shubin.spos.converter.Model.Route;
import com.shubin.spos.converter.utils.Parser;
import com.shubin.spos.converter.utils.SPOSXMLWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private Label statusLabel;
    @FXML
    private Button saveButton;

    private Stage stage;
    private Stage aboutStage;
    private Route route;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void actionUpload(ActionEvent actionEvent) {
        FileChooser fileChooser = createOpenFileChooser();
        File file = fileChooser.showOpenDialog(stage);

        if (file == null) {
            return;
        }

        route = Parser.parseRoute(file);
        printSuccessStatus("Route \"" + route.getName() + "\" successfully converted. You may now save it");
        saveButton.setManaged(true);
    }

    public void actionSave(ActionEvent actionEvent) {
        FileChooser fileChooser = createSaveFileChooser();
        File file = fileChooser.showSaveDialog(stage);

        if (file == null) {
            return;
        }

        SPOSXMLWriter writer = new SPOSXMLWriter();
        writer.write(route, file);
    }

    private FileChooser createOpenFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open JRC CSV route file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*csv*"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );
        return fileChooser;
    }

    private FileChooser createSaveFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as SPOS route file");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        return fileChooser;
    }

    private void printSuccessStatus(String text) {
        statusLabel.setText(text);
        statusLabel.setManaged(true);
        statusLabel.setStyle("-fx-text-fill: green");
    }

    public void actionShowAbout(ActionEvent actionEvent) {
        try {
            if (aboutStage == null) {
                aboutStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../View/about.fxml"));
                aboutStage.setTitle("About");
//                aboutStage.setMinHeight(320);
//                aboutStage.setMinWidth(320);
                aboutStage.setResizable(false);
                aboutStage.setScene(new Scene(root));
                aboutStage.initModality(Modality.WINDOW_MODAL);
                if (this.stage != null) {
                    aboutStage.initOwner(this.stage);
                } else {
                    return;
                }
            }

            aboutStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
