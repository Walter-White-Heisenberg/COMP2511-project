package unsw.dungeon;

import java.time.LocalDateTime;

import javafx.scene.image.ImageView;

/**
 * The player entity
 * @author Haowei Lou
 *
 */
public class Player extends Movable {

    private Key key;
    private Integer treasure;
    private Integer sword;
    private LocalDateTime invincibility;
    private ImageView view;
    private int countMove;
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.treasure = 0;
        this.sword = 0;
        this.invincibility = LocalDateTime.now();
    }
    /**
     * Set the ImageView of the player entity
     * @param view
     */
    public void setView(ImageView view){
        this.view = view;
    }
    /**
     * Return the ImageView of this entity
     * @return
     */
    public ImageView view(){
        return view;
    }
    /**
     * Set the player to be dead and terminate current game
     */
    public void dead(){
        Death dead = new Death();
        System.out.println(dead.toString());
    }
    /**
     * Remove key from play's hand, set the key attribute to be null
     */
    public void removekey(){
        this.key = null;
    }
    /**
     * Collect the entity that lay on the dungeon, if it is a collectable item.
     * Collectable item include <Key, Treasure, Sword, Invincibility>. The player's 
     * corresponding special ability will be successfully updated once a collection
     * is fulfiled 
     * @param entity
     */
    public void collect(Entity entity){
        if (entity instanceof Key && this.key == null){
            Key key = (Key) entity;
            this.key = key;
            System.out.println("player has collected key: " + key.getId());
            dungeon.removeEntity(entity);
            key.remove();
        } else if (entity instanceof Treasure) {
            Treasure treasure = (Treasure) entity;
            this.treasure += 1;
            System.out.println("player has collected 1 treasure, total treasure: " + this.treasure.toString());
            dungeon.removeEntity(entity);
            treasure.remove();
        } else if (entity instanceof Sword) {
            Sword sword = (Sword) entity;
            this.sword = 5;
            System.out.println("player has collected 1 sword, remain hits: " + this.sword);
            dungeon.removeEntity(entity);
            sword.remove();
        } else if (entity instanceof Invincibility) {
            Invincibility invincibility = (Invincibility) entity;
            this.invincibility = LocalDateTime.now().plusSeconds(32);
            System.out.println("player has collected 1 invincibility potion and it will end in " + this.invincibility.toString());
            dungeon.removeEntity(entity);
            invincibility.remove();
        }
    }
    /**
     * Return whether player's invincibility is in effect at current time.
     * @return
     */
    public boolean isInvincible(){
        return LocalDateTime.now().isBefore(this.invincibility);
    }
    /**
     * Return the key in player's hand, return null if there is no key
     * @return
     */
    public Key getKey(){
        return this.key;
    }
    /**
     * Check if the player has a sword in hand
     * @return
     */
    public boolean hasSword(){
        return this.sword>0;
    }
    /**
     * Perform hit from player to an enemy, and decrease the number of remain hits
     * of the player's sword in hand
     */
    public void hit(){
        this.sword -= 1;
        System.out.println("Killed an enemy, remain " + this.sword + " hits");
    }

    public Integer getTreasure(){
        return this.treasure;
    }

    public Integer getSword(){
        return this.sword;
    }

    public void addMove(){
        this.countMove += 1;
    }

    public int getCountMove(){
        return this.countMove;
    }
}
