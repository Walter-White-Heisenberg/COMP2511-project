package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;


/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController extends Controller{

    @FXML
    private Button back;

    @FXML
    private ImageView sword_img;

    @FXML
    private ImageView gold_img;

    @FXML
    private ImageView potion_img;

    @FXML
    private Text sword;

    @FXML
    private Text treasure;

    @FXML
    private Text invincibility;

    @FXML
    private Text passmsg;

    @FXML
    private Button exit;

    @FXML
    private Button restart;

    @FXML
    private GridPane squares;

    @FXML
    private Rectangle mask;

    @FXML
    private ImageView dead;

    @FXML
    private Text deadmsg;


    private List<ImageView> initialEntities;
    

    private Player player;

    private Dungeon dungeon;

    private String filename;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities,String filename) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
        this.filename = filename;
        dungeon.setController(this);
    }

    @FXML
    public void initialize() {
        Image ground = new Image((new File("images/dirt_0_new.png")).toURI().toString());
        Image sword_Image = new Image((new File("images/greatsword_1_new.png")).toURI().toString());
        Image invic = new Image((new File("images/brilliant_blue_new.png")).toURI().toString());
        Image gold = new Image((new File("images/gold_pile.png")).toURI().toString());

        
        this.sword_img.setImage(sword_Image);
        this.gold_img.setImage(gold);
        this.potion_img.setImage(invic);

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);
        
        this.passmsg.setText(dungeon.getProgress());
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        default:
            break;
        }
    }

    public void update(){
        Player player = dungeon.getPlayer();
        if(player.isInvincible()){
            this.invincibility.setText("Yes");
        } else {
            this.invincibility.setText("No");
        }
        this.treasure.setText(player.getTreasure().toString());
        this.sword.setText(player.getSword().toString());
        this.passmsg.setText(dungeon.getProgress());
    }

    public void dead(){
        this.mask.setFill(javafx.scene.paint.Color.BLACK);
        this.mask.setVisible(true);
        Image deadimg = new Image((new File("images/dead.png")).toURI().toString());
        this.dead.setImage(deadimg);
        this.deadmsg.setText(new Death().toString());
    }

    public void finish(int mark){
        this.mask.setFill(javafx.scene.paint.Color.WHITE);
        this.mask.setVisible(true);
        Image passimg = new Image((new File("images/pass.png")).toURI().toString());
        this.dead.setImage(passimg);
        this.deadmsg.setText("PASS!!!\nMark: "+Integer.toString(mark)+"\nBetter than 96.5%");
    }

    @FXML
    public void handleBack(ActionEvent event) throws IOException {
        dungeon.Save();
        changeScence("LevelScene.fxml", null, event);
    }

    @FXML
    public void handleExit(ActionEvent event) throws IOException {
        dungeon.Save();
        System.exit(0);
    }

    @FXML
    public void handleRestart(ActionEvent event) throws IOException {
        changeScence("DungeonView.fxml", filename, event);
    }

}

