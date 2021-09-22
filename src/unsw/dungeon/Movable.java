package unsw.dungeon;
/**
 * Moveable entity, inherited from Entity class. 
 * Which include <Player, enemy and Portal>. Entity
 * in this class will be able to move in dungeons
 * @author Haowei Lou
 */
public class Movable extends Entity {
    protected Dungeon dungeon;
    /**
     * Create a Movable item positioned in square (x,y)
     * @param x
     * @param y
     */
    public Movable(Dungeon dungeon, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
    }
    /**
     * Check whether the position this movable entity is going to
     * move is bolcked by other entities, ie. Wall, Boulder, etc
     * @param position
     * @return
     */
    protected boolean checkBlock(String position){
        int x = this.getX();
        int y = this.getY();
        boolean output = true;
        if(position == "Up"){y = y -  1;}
        if(position == "Down"){y = y + 1;}
        if(position == "Left"){x = x - 1;}
        if(position == "Right"){x = x + 1;}
        for(Entity entity:this.dungeon.getEntity(x, y)){
            if(entity instanceof Wall){
                output = output && false;
            } else if(entity instanceof Door){
                Door door = (Door) entity;
                door.Open(this);
                output = output && door.isOpen();
            } else if(entity instanceof Boulder){
                Boulder boulder = (Boulder)entity;
                if(!boulder.push(this, position)){
                    output = output && false;
                }
            } else if(entity instanceof Enemy && this instanceof Player){
                output = output&&true;
            } else if(entity instanceof Exit && this instanceof Enemy){
                output = output&&false;
            } else if(entity instanceof Enemy){
                output = output&&false;
            } else if(entity instanceof Portal && this instanceof Boulder == false){
                Portal portal = (Portal) entity;
                portal.move(this);
                return false;
            }
        }
        if(this instanceof Player && output){
            Player player = (Player) this;
            player.addMove();
        }
        return output;
    }
    /**
     * Move the current entity to square in up square
     */
    public void moveUp() {
        if (getY() > 0 && checkBlock("Up")){
            y().set(getY() - 1);
        }
        dungeon.update();
    }
    /**
     * Move the current entity to square in down square
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1 && checkBlock("Down")){
            y().set(getY() + 1);
        }
        dungeon.update();
    }
    /**
     * Move the current entity to square in left square
     */
    public void moveLeft() {
        if (getX() > 0 && checkBlock("Left")){
            x().set(getX() - 1);
        }
        dungeon.update();

    }
    /**
     * Move the current entity to square in right square
     */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1 && checkBlock("Right")){
            x().set(getX() + 1);
        }        
        dungeon.update();
    }
}