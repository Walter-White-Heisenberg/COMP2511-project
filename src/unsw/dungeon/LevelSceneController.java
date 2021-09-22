package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class LevelSceneController extends Controller{

    @FXML
    private ImageView background;

    @FXML
    private Button easy;

    @FXML
    private Button hard;

    @FXML
    private Button medium;

    @FXML
    private Button back;

    public LevelSceneController(){
        
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/background.jpg")).toURI().toString());
        BackgroundFill  fill = new BackgroundFill(Color.valueOf("#9e7c3d"), CornerRadii.EMPTY, Insets.EMPTY);
        Background buttonbackground = new Background(fill);
        easy.setBackground(buttonbackground);
        medium.setBackground(buttonbackground);
        hard.setBackground(buttonbackground);
        back.setBackground(buttonbackground);
        background.setImage(ground);
    }
    /**
     * the load the easy map after the easy button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleEasy(ActionEvent event) throws IOException {
        load_level("easy.json", event);
    }
    /**
     * the load the medium map after the medium button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleMedium(ActionEvent event) throws IOException {
        load_level("medium.json", event);
    }
    /**
     * the load the hard map after the hard button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleHard(ActionEvent event) throws IOException {
        load_level("hard.json", event);
    }
    /**
     * the load the starting menu after the back button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        changeScence("StartScene.fxml", null, event);
    }
    /**
     * load json from different level in to the game
     * @param name_of_json shows which map to be used for a specific level
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    public void load_level(String name_of_json, ActionEvent event) throws IOException {
        changeScence("DungeonView.fxml", name_of_json, event);
    }
}
