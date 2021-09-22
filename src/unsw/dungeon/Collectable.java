package unsw.dungeon;

import javafx.scene.image.ImageView;
/**
 * The collectable entity, inherited from Entity class.
 * Player can collect Collevtable entity.
 * @author Haowei Lou
 */
public class Collectable extends Entity {
    ImageView view;
    /**
     * Create a Collectable item positioned in square (x,y)
     * @param x
     * @param y
     */
    public Collectable(int x, int y){
        super(x,y);
    }
    /**
     * Set the ImageView
     * @param view
     */
    public void setView(ImageView view){
        this.view = view;
    }
    /**
     * Remove the imageview from JavaFx user interface by 
     * move current ImageView to an under layer, will imporve
     * this in M3
     */
    public void remove(){
        if(this.view != null){this.view.toBack();}
    }
}