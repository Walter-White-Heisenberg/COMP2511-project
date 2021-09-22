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

public class helpController extends Controller{
    @FXML
    private Button back;

    @FXML
    private ImageView background;

    @FXML
    private ImageView portalim;

    @FXML
    private ImageView enemyim;

    @FXML
    private ImageView exitim;

    @FXML
    private ImageView switchim;

    @FXML
    private ImageView boulderim;

    @FXML
    private ImageView treasureim;

    @FXML
    private ImageView swordim;

    @FXML
    private ImageView potionim;

    @FXML
    private ImageView doorim;

    public helpController(){

    }

    /**
     * set the image for different images and the background for the helpscene
     */
    @FXML
    public void initialize() {
        Image doorImage = new Image((new File("images/closed_door.png")).toURI().toString());
        doorim.setImage(doorImage);
        Image exitImage = new Image((new File("images/exit.png")).toURI().toString());
        exitim.setImage(exitImage);
        Image treasureImage = new Image((new File("images/gold_pile.png")).toURI().toString());
        treasureim.setImage(treasureImage);
        Image swordImage = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        swordim.setImage(swordImage);
        Image invincibilityImage = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        potionim.setImage(invincibilityImage);
        Image boulderImage = new Image((new File("images/boulder.png")).toURI().toString());
        boulderim.setImage(boulderImage);
        Image portalImage = new Image((new File("images/portal.png")).toURI().toString());
        portalim.setImage(portalImage);
        Image switchImage = new Image((new File("images/pressure_plate.png")).toURI().toString());
        switchim.setImage(switchImage);
        Image enemyImage = new Image((new File("images/deep_elf_master_archer.png")).toURI().toString());
        enemyim.setImage(enemyImage);
        Image ground = new Image((new File("images/background.png")).toURI().toString());
        BackgroundFill fill = new BackgroundFill(Color.valueOf("#9e7c3d"), CornerRadii.EMPTY, Insets.EMPTY);
        Background buttonbackground = new Background(fill);
        back.setBackground(buttonbackground);
        background.setImage(ground);
    }    

    /**
     * handle the case that "back" button is pressed
     * @param event the event get by buton handler
     * @throws IOException to cover the probability that failed to open fxml
     */
    @FXML
    public void handleBack(ActionEvent event) throws IOException{
        changeScence("StartScene.fxml", null, event);
    }
}