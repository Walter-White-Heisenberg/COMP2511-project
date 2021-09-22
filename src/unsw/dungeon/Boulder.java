package unsw.dungeon;
/**
 * the class for boulder which contains the construction and the movement of boulder
 * @author yun
 */
public class Boulder extends Movable{
    /**
     * constructor for the boulder
     * @param dungeon the dungeon contains the boulder
     * @param x the x coordinate of the boulder will be created
     * @param y the y coordinate of the boulder will be created
     */
    public Boulder(Dungeon dungeon, int x, int y){
        super(dungeon, x, y);
    }
    /**
     * push the boulder if the boulder is pushsable and return the result of the pushing
     * @param entity the entity gonna pushed the boulder, only pushable when it comes to player
     * @param direction the direction the player gonna push the boulder
     * @return if the push is successful
     */
    public boolean push(Entity entity, String direction){
        if(entity instanceof Player == false){
            return false;
        } else if(checkBlock(direction)==false){
            return false;
        }
        if(direction == "Up" ){
            this.moveUp();
        }else if(direction == "Down"){
            this.moveDown();
        } else if(direction == "Left"){
            this.moveLeft();
        } else {
            this.moveRight();
        }
        return true;
    }
}