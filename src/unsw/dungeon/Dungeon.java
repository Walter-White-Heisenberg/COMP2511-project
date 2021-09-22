/**
 *
 */
package unsw.dungeon;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private ArrayList<Entity> entities;
    private ArrayList<Switch> switchs;
    private ArrayList<Boulder> boulders;
    private Player player;
    private JSONObject jsongoal;
    private Goal goal;
    private DungeonController dungeonController;
    private LocalDateTime start;
    private int killenemy;

    /**
     * constructor of the dungeon
     * @param width the width of the dungeon
     * @param height the height of the dungeon
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.switchs = new ArrayList<>();
        this.boulders = new ArrayList<>();
        this.start = LocalDateTime.now();
        this.player = null;
        this.goal = null;
    }
    /**
     * checking if any treasure exists (made for checking the goal)
     * @return return is there any treasure exists in the dungeon
     */
    public boolean treasureExist(){
        for(Entity e : entities) {
            if(e instanceof Treasure) {return true;}
        }
        return false;
    }
    /**
     * checking if the player is on exit entity
     * @return return true if the player is on entity
     */
    public boolean exitPass(){
        int x = player.getX();
        int y = player.getY();
        for(Entity entity: getEntity(x, y)){
            if(entity instanceof Exit){return true;}
        }
        return false;
    }

    /**
     * according to input level set the strategy that enemy follows in the game for movement
     * @param level the level which will determine what strategy enemy is using when moving
     */
    public void set_level(String level){
        for(Entity entity : entities) {
            if(entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                Strategy s;
                if(level.equals("easy")) {
                    s = new EasyStrategy(enemy, this);
                } else if (level.equals("hard")) {
                    s = new HardStrategy(enemy, this);
                } else {
                    s = new MediumStrategy(enemy, this);
                }
                enemy.setStrategy(s);
            }
        }
    } 
    
    /**
     * checking if any enemy exists (made for checking the goal)
     * @return return is there any enemy exists in the dungeon
     */
    public boolean enemyExsit(){
        for(Entity entity : entities) {
            if(entity instanceof Enemy) {
                Enemy enemy = (Enemy) entity;
                if(!enemy.isDead()){return true;}
            }
        }
        return false;
    }
    /**
     * check if all the boulder is pressed
     * @return the if all the boulder is pressed by the boulder
     */
    public boolean boulderExsit(){
        for(Switch plate: this.switchs){
            if(plate.is_pressed() == false){
                return false;
            }
        }
        return true;
    }
    /**
     * getter of the width of the dungeon
     * @return the width of the dungeon
     */
    public int getWidth() {
        return width;
    }
    /**
     * getter of the height of the dungeon
     * @return the height of the dungeon
     */
    public int getHeight() {
        return height;
    }
    /**
     * getter of the player of the dungeon
     * @return the player of the dungeon
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * set the player as teh true player in the game
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * set the input goal as the goal of this map
     * @param goal the goal who is gonna be set of this graph
     */
    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    /**
     * update two things: the first thing is to check if player picked up any entity
     * the second thing is to check if the game is finished
     */
    public void update() {
        int x = player.getX();
        int y = player.getY();
        ArrayList<Entity> entities = getEntity(x, y);
        for(Entity entity:entities){
            if(entity instanceof Collectable){
                player.collect(entity);
            } else if (entity instanceof Enemy){
                collide(player, entity);
            }
        }
        if(this.dungeonController != null){
            this.dungeonController.update();
        }

        if(goal != null && goal.pass()){
            killAllEnemy();
            if(this.dungeonController != null){
                this.dungeonController.finish(getMark());
            }
        }
    }
    /**
     * remove all the enemy from dungeon
     */
    public void killAllEnemy(){
        for(Entity entity:entities){
            if (entity instanceof Enemy){
                Enemy enemy = (Enemy) entity;
                enemy.setdead();
            }
        }
    }

    /**
     * add the input entity into the graph
     * @param entity the entity which will be added into this graph
     */
    public void addEntity(Entity entity) {
        if(entity instanceof Switch){
            Switch plate = (Switch) entity;
            this.switchs.add(plate);
        }
        if(entity instanceof Boulder){
            Boulder boulder = (Boulder) entity;
            this.boulders.add(boulder);
        }
        if(entity instanceof Enemy){
            Enemy bad_guy = (Enemy) entity;
            bad_guy.move();
        }
        entities.add(entity);
    }
    /**
     * find the entity inside a given block
     * @param x the x coordinate of the block
     * @param y the y coordinate of the block
     * @return a list entity inside a block
     */
    public ArrayList<Entity> getEntity(int x, int y){
        ArrayList<Entity> output = new ArrayList<Entity>();
        for(int i = 0; i < this.entities.size(); i++){
            Entity entity = this.entities.get(i);
            if(entity.getX() == x && entity.getY() == y){
                output.add(entity);
            }
        }
        return output;
    }
    /**
     * teh getter of all the entity of this dungeon
     * @return the list of entity in this dungeon
     */
    public ArrayList<Entity> getEntities(){
        return this.entities;
    }
    /**
     * remove a entity from the dungeon
     * @param entity the entity gonna be removed from the dungeon
     */
    public void removeEntity(Entity entity){
        this.entities.remove(entity);
    }
    /**
     * dealing with player collding with enemy
     * @param player the player in this dungeon
     * @param entity the entity which collid with player
     */    
    private void collide(Player player, Entity entity){
        Enemy enemy = (Enemy) entity;
        if(player.isInvincible()){
            enemy.dead();
            this.killenemy+=1;
        } else if(player.hasSword()){
            player.hit();
            enemy.dead();
            this.killenemy+=1;
        } else {
            if(this.dungeonController != null){
                this.dungeonController.dead();
            }
            enemy.dead();
        }
    }
    /**
     * the getter of the boulder
     * @return the list of boulder in dungeon
     */
    public ArrayList<Boulder> getBoulders(){
        return this.boulders;
    }
    /**
     * convert an entity from dungeon to Json object
     * for the purpose convert an entity from dungeon to Json object
     */
    public JSONObject toJson(Entity entity){
        JSONObject output = new JSONObject();
        String type = "";
        int id = -1;
        if(entity instanceof Player){
            type = "player";
        } else if(entity instanceof Wall){
            type = "wall";
        } else if(entity instanceof Door){
            type = "door";
            Door door = (Door) entity;
            id = door.getId();
        } else if(entity instanceof Key){
            type = "key";
            Key key = (Key) entity;
            id = key.getId();
        } else if(entity instanceof Exit){
            type = "exit";
        } else if(entity instanceof Treasure){
            type = "treasure";
        } else if(entity instanceof Sword){
            type = "sword";
        } else if(entity instanceof Invincibility){
            type = "invincibility";
        } else if(entity instanceof Boulder){
            type = "boulder";
        } else if(entity instanceof Portal){
            type = "portal";
            Portal portal = (Portal) entity;
            id = portal.getId();
        } else if(entity instanceof Switch){
            type = "switch";
        } else if(entity instanceof Enemy){
            type = "enemy";
        } 
        output.put("x", entity.getX());
        output.put("y", entity.getY());
        output.put("type", type);
        if(id != -1){output.put("id", id);}
        return output;
    }
    public void setJsongoal(JSONObject goal){
        this.jsongoal = goal;
    }
    /**
     * Save current status
     */
    public void Save(){
        JSONObject output = new JSONObject();
        output.put("width", getWidth());
        output.put("height", getHeight());
        JSONArray outentities = new JSONArray();
        for(Entity entity:entities){
            outentities.put(this.toJson(entity));
        }
        output.put("entities", outentities);
        output.put("goal-condition", this.jsongoal);
        try(FileWriter file = new FileWriter("dungeons/output.json")){
            file.write(output.toString(1));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * build the connection between controller and the containing dungeon
     * @param controller the controller for the map
     */
    public void setController(DungeonController controller){
        this.dungeonController = controller;
    }

    /**
     * the mark calculated by time, treasure, enemy killed and player's move
     * @return the mark calculated by algorithm
     */
    public int getMark(){
        LocalDateTime now = LocalDateTime.now();
        int timetake = (int) Duration.between(this.start,now).toSeconds();
        return (int)6000/timetake + this.player.getTreasure()*100 + this.killenemy*200 - player.getCountMove();
    }
   
    /**
     * get the current progress for the game to refresh front end 
     * @return the current goal which doesn't fulfilled
     */
    public String getProgress(){
        return this.goal.getString();
    }
}
