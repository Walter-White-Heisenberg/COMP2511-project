package unsw.dungeon;

import java.util.ArrayList;

/**
 * the class of wtich caontians the construction and interaction of switches
 */
public class Switch extends Entity{
    private Dungeon dungeon;
    public Switch(Dungeon dungeon, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
    }
    /**
     * if the switch is pressed
     * @return is the switch being pressed by the boulder
     */
    public boolean is_pressed () {
        ArrayList<Boulder> boulders = dungeon.getBoulders();
        for(Boulder boulder: boulders) {
            if(this.getX() == boulder.getX() && this.getY() == boulder.getY()){
                return true;
            }
        }
        return false;
    }
    
}