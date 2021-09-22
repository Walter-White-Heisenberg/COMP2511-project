package unsw.dungeon;

/**
 * the class of goal contains the setting and updating of the goal of the dungeon
 * @author yun
 */
public class Goal implements Observer{
    /*private final String general_m = "To pass this level you need to ";
    private final String treasure_m = "Collect all the Treasure in this floor.";
    private final String enemy_m = "Eilminite all the Enemy in this floor.";
    private final String boulder_m = "All the floor switch needs to be pressed by a boulder.";    
    */
    private Component goal;
    /**
     * the constructor of the goal 
     * @param goal the goal which is gonan be set as this goal
     */
    public Goal(Component goal){
        this.goal = goal;
    }
    
    /**
     * update if the goal passed, and return true
     */
    @Override
    public boolean pass(){
        return this.goal.pass();
    }

    /**
     * get progress of whole goal
     */
    @Override
    public String getString(){
        return this.goal.getString();
    }
}