package unsw.dungeon;
/**
 * Exit entity potion, the player will
 * exit the game if he arrive the square
 * contaisn this entity and fulfiled all goal
 * in the dungeon
 * @author Haowei Lou
 */
public class Exit extends Entity{
    /**
     * Create a Exit positioned in square (x,y)
     * @param x
     * @param y
     */
    public Exit(int x, int y) {
        super(x, y);
    }
}