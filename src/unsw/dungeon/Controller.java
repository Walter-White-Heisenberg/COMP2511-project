package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


public class Controller {
    /**
     * change fxml and load json map into the application
     * @param fxmlFileName the fxml file that will be converted into 
     * @param jsonFileName the convertion for dungeon map (if there is only fxml acced, this will be null)
     * @param ActionFile the ActionFile that get from button handle
     */
    public void changeScence(String fxmlFileName, String jsonFileName, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
        if(fxmlFileName.equals("DungeonView.fxml")){
            DungeonControllerLoader dungeonLoader = new DungeonControllerLoader(jsonFileName);
            DungeonController controller = dungeonLoader.loadController();
            loader.setController(controller);
        }else if (fxmlFileName.equals("LevelScene.fxml")) {
            LevelSceneController controller = new LevelSceneController();
            loader.setController(controller);
        }else if (fxmlFileName.equals("StartScene.fxml")) {
            StartSceneController controller = new StartSceneController();
            loader.setController(controller);
        }else if (fxmlFileName.equals("help.fxml")) {
            helpController controller = new helpController();
            loader.setController(controller);
        }
        
        Parent view2 = loader.load();
        Scene scene2 = new Scene(view2);
        if(fxmlFileName.equals("DungeonView.fxml")){
            view2.requestFocus();
        }
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();       
    }
    
}