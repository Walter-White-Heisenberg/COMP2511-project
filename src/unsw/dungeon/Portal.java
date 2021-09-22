package unsw.dungeon;
import java.util.ArrayList;

/**
 * the class of the portal contains the construction, transportation nad the matching of the portals
 * @author yun
 */
public class Portal extends Entity{
    private Dungeon dungeon;
    private int id; 
    private int destination_x;
    private int destination_y;
    /**
     * the constructor of the portal
     * @param dungeon the dungeon which is set to be the dungeon contains this portal
     * @param x the x coordinate of the portal
     * @param y the y coordinate of the portal
     * @param id the id of the portal
     */
    public Portal(Dungeon dungeon, int x, int y, int id){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        match_another_portal();
    }
    /**
     * match only one portal with same id and return the result
     * @return  if the match is successful
     */
    public boolean match_another_portal() {
        ArrayList<Entity> entities = (ArrayList<Entity>) dungeon.getEntities();
        for(Entity e : entities){
            if(e instanceof Portal) {
                Portal another = (Portal) e;
                if (another.getId() == this.id && another != this) {
                    int x = another.getX();
                    int y = another.getY();
                    another.setDestination_x(this.getX());
                    another.setDestination_y(this.getY());
                    setDestination_x(x);
                    setDestination_y(y);
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * the getter of the getDestination_x
     * @return the x coordinate desination of portal
     */
    public int getDestination_x() {
        return destination_x;
    }
    /**
     * the setter of the x coordinate of the destination of this portal
     * @param destination_x the input destination_y gonna be set as the x destination of this portal
     */
    public void setDestination_x(int destination_x) {
        this.destination_x = destination_x;
    }
    /**
     * the getter of the getDestination_y
     * @return the y coordinate desination of portal
     */
    public int getDestination_y() {
        return destination_y;
    }
    /**
     * the setter of the y coordinate of the destination of this portal
     * @param destination_y the input destination_y gonna be set as the y destination of this portal
     */
    public void setDestination_y(int destination_y) {
        this.destination_y = destination_y;
    }
    /**
     * getter of portal id
     * @return the id of this portal
     */
    public int getId() {
        return id;
    }
    /**
     * move the entity from portal A to Portal B
     * @param entity the entity gonna be trasported
     */ 
    public void move(Entity entity){
        entity.y().set(destination_y);
        entity.x().set(destination_x);
    }
}