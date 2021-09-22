package unsw.dungeon;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * The door entity, which can block user's movement if it
 * is not open. Will be able to open if the player has a
 * key who has same id with this door
 * @author Haowei Lou
 */
public class Door extends Entity {
    private int id;
    private boolean open;
    private ImageView view;
    /**
     * Create a Door positioned in square (x,y)
     * @param x
     * @param y
     * @param id
     */
    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.open = false;
    }
    /**
     * check whether if the door is open
     * @return
     */
    public boolean isOpen(){
        return this.open;
    }
    /**
     * When the player try to open the door, it will check
     * if the input entity is player. If yes, it will continue 
     * check if the player has a key with same id with door's id.
     * And open the door in the end. Otherwise, the door will not be opend
     * @param entity
     */
    public void Open(Entity entity){
        if(!(entity instanceof Player)) {return;}
        Player player = (Player) entity;
        if(player.getKey() != null && player.getKey().getId() == this.id){
            this.open = true;
            player.removekey();
        } else {
            return;
        }
        if(this.view != null){
            Image opendoor = new Image((new File("images/open_door.png")).toURI().toString());
            this.view.setImage(opendoor);
        }
    }
    /**
     * Change the view of this door, open or unopen
     * @param view
     */
    public void setView(ImageView view){
        this.view = view;
    }
    /**
     * Return the id of door
     * @return
     */
    public int getId(){
        return id;
    }
}