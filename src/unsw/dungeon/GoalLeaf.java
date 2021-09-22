package unsw.dungeon;
/**
 * the class of goalLeaf contains the construction and judging of the basic goal 
 * @author yun
 */
public class GoalLeaf implements Component{
    private String goal;
    private Dungeon dungeon;
    /**
     * the constructor of the 
     * @param goal the lowest level of this goal
     * @param dungeon the dungeon contains the GoalLeaf
     */
    public GoalLeaf(String goal,Dungeon dungeon){
        this.goal = goal;
        this.dungeon = dungeon;
    }
    /**
     * return if this goalLeaf is fullfilled in this dungeon
     */
    @Override
    public boolean pass() {
        return goal_fullfiled();
    }
    /**
     * check if the goal fullfilled
     * @return if the goal is fullfilled
     */
    public boolean goal_fullfiled(){
        if (goal.equals("treasure")){
            if(dungeon.treasureExist()){return false;}
            System.out.println("pass treasure");
            return true;
        } else if (goal.equals("enemies")){
            if(dungeon.enemyExsit()){return false;}
            System.out.println("pass enemy");
            return true;
        } else if (goal.equals("exit")){
            if(!dungeon.exitPass()){return false;}
            System.out.println("pass exit");
            return true;
        } else if (goal.equals("boulders")){
            if(!dungeon.boulderExsit()){return false;}
            System.out.println("pass boulder");
            return true;
        } else {
            return false;
        }
    }

    /**
     * get the current progress for the sake of front end
     */
    @Override
    public String getString(){
        if(this.goal_fullfiled()){
            return "";
        } else {
            if(this.goal.equals("exit")){
                return "Go to exit";
            }else if(this.goal.equals("boulders")){
                return "Press all boulders on switch";
            }else if(this.goal.equals("enemies")){
                return "Kill all enemies!";
            }else{
                return "Collect all tresures!";
            }
        }
    }
}