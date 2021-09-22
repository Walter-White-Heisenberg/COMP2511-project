package unsw.dungeon;
/**
 * The key entity, inherited from collectable class.
 * Every key has a id, whihc is used to open the corresponding
 * door with same id.
 * @author Haowei Lou
 */
public class Key extends Collectable{
    private int id;
    /**
     * Create a Door positioned in square (x,y) with Id
     * @param x
     * @param y
     * @param id
     */
    public Key(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }
    /**
     * Return the id of this key
     * @return
     */
    public int getId(){
        return id;
    }
}