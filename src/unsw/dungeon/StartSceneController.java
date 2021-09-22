package unsw.dungeon;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.event.*;

import java.io.File;
import java.io.IOException;

/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class StartSceneController extends Controller{   
    @FXML
    private Button play;

    @FXML
    private Button exit;

    @FXML
    private Button help;

    @FXML
    private Button load;

    @FXML
    private ImageView background;
    /**
     * the load the level choosing scence after the play button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handlePlay(ActionEvent event) throws IOException{
        changeScence("LevelScene.fxml", null, event);
    }
    /**
     * the load the help scene map after the help button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleHelp(ActionEvent event) throws IOException{
        changeScence("help.fxml", null, event);
    }
    /**
     * the load the loaded map after the load button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleLoad(ActionEvent event) throws IOException{
        changeScence("DungeonView.fxml", "output.json", event);
    }
    /**
     * the load the easy map after the easy button is pressed
     * @param event the event come from the handler
     * @throws IOException to deal with failed opening of fxml file
     */
    @FXML
    public void handleExit(ActionEvent event){
        System.exit(1);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/background.jpg")).toURI().toString());
        BackgroundFill  fill = new BackgroundFill(Color.valueOf("#9e7c3d"), CornerRadii.EMPTY, Insets.EMPTY);
        Background buttonbackground = new Background(fill);
        play.setBackground(buttonbackground);
        exit.setBackground(buttonbackground);
        load.setBackground(buttonbackground);
        help.setBackground(buttonbackground);
        background.setImage(ground);
    }
}