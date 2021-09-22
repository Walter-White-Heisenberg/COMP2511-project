package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
/**
 * the class for enemy which contains the construction and the movement of enemy, and collision with the player
 * @author frank
 */

public class Enemy extends Movable{
    private ImageView view;
    private boolean dead;
    public Enemy(Dungeon dungeon, int x, int y){
        super(dungeon, x, y);
        this.dead = false;
    }

    private Strategy strategy;
    /**
     * the enemy is dead after running this function
     */
    public void dead(){
        this.dungeon.removeEntity(this);
        this.dead = true;
        if(this.view != null){
            this.view.toBack();
        }
    }
    /**
     * set the image view
     * @param view set the image view of the enemy
     */
    public void setView(ImageView view){
        this.view = view;
    }

    /**
     * set the image view
     * @param view set the image view of the enemy
     */
    public void setdead(){
        this.dead = true;
    }

    /**
     * set the strategy
     * @param strategy set the strategy of the enemy
     */
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    
    /**
     * move the enemy a bloakc per second in the java fx game playing 
     */
    public void move() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(dead){timer.cancel();}
                    strategy.moving();
                });
            }
        }, 1000, 400);
    }
    /**
     * getter of the view
     * @return the view of this enemy
     */
    public ImageView view(){
        return this.view;
    }
    /**
     * return is enemy dead
     * @return
     */
    public boolean isDead(){
        return dead;
    }

    /**
     * set the strategy
     * @param strategy set the strategy of the enemy
     */
    public Strategy getStrategy(){
        return strategy;
    }
}