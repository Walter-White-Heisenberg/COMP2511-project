package unsw.dungeon;
/**
 * The Treasure entity, inherited from Collectable
 * class. It can be collected by player, add  one 
 * quantity of treasure in player's bag and disappear.
 * @author Haowei Lou
 */
public class Treasure extends Collectable{
    /**
     * Create a Treasure positioned in square (x,y)
     * @param x
     * @param y
     */
    public Treasure(int x, int y) {
        super(x, y);
    }
}