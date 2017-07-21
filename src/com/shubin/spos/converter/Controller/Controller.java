package com.shubin.spos.converter.Controller;

import com.shubin.spos.converter.Model.Waypoint;
import com.shubin.spos.converter.utils.Parser;
import com.shubin.spos.converter.utils.SPOSXMLWriter;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class Controller {

    private Stage stage;
    private List<Waypoint> waypoints;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void actionUpload(ActionEvent actionEvent) {
        FileChooser fileChooser = createOpenFileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            waypoints = Parser.parseWaypoints(file);
        }
    }

    public void actionSave(ActionEvent actionEvent) {
        FileChooser fileChooser = createSaveFileChooser();
        File file = fileChooser.showSaveDialog(stage);
        SPOSXMLWriter writer = new SPOSXMLWriter();
        writer.write(waypoints, file);
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
}
