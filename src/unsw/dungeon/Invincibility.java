package unsw.dungeon;
/**
 * Invincibility potion, inherited from Collectable
 * class. Once the player collects this entity, he 
 * will have a 32s invinciable duration. After 32s,
 * the player will not be invinciable.
 * @author Haowei Lou
 */
public class Invincibility extends Collectable {
    /**
     * Create a Invincibility positioned in square (x,y)
     * @param x
     * @param y
     */
    public Invincibility(int x, int y) {
        super(x, y);
    }
}