package unsw.dungeon;
/**
 * The Wall entity, used to block 
 * movable entity's movement in any time.
 * @author Haowei Lou
 */
public class Wall extends Entity {
    /**
     * Create a Wall positioned in square (x,y)
     * @param x
     * @param y
     */
    public Wall(int x, int y) {
        super(x, y);
    }

}
