package unsw.dungeon;
/**
 * The Sword entity, inherited from Collectable
 * class. It will give player a sword with 5 remain hits
 * and disappear.
 * @author Haowei Lou
 */
public class Sword extends Collectable {
    /**
     * Create a sword positioned in square (x,y)
     * @param x
     * @param y
     */
    public Sword(int x, int y) {
        super(x, y);
    }
}