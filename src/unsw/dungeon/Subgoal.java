package unsw.dungeon;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Subgoal implements Component{
    private String type;
    private ArrayList<Component> subgoal = new ArrayList<Component>();
    /**
     * the construcctor of the goal
     * @param json the json file which is gonna converted into goal
     * @param dungeon the dungeon which contains this goal
     */
    public Subgoal(JSONObject json, Dungeon dungeon) {
        this.type = json.getString("goal");
        JSONArray subgoal = json.getJSONArray("subgoals");
        for(int i = 0; i < subgoal.length(); i+=1){
            JSONObject object = subgoal.getJSONObject(i);
            if(object.has("subgoals")){
                Subgoal newgoal = new Subgoal(object, dungeon);
                this.subgoal.add(newgoal);
            } else {
                String goal = object.getString("goal");
                GoalLeaf newgoal = new GoalLeaf(goal, dungeon);
                this.subgoal.add(newgoal);
            }
        }
    }
    /**
     * return if the goal of its belonging dungeon passed
     * @return if the goal of the dungeon passed
     */
    @Override
    public boolean pass() {
        if(getAnd_or().equals("AND")){
            for(Component Sg : subgoal){
                if(!Sg.pass()){
                    return false;
                }
            }
            return true;
        } else {
            for(Component Sg : subgoal){
                if(Sg.pass()){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * return the subgoal's type
     * @return the type of the subgoal
     */
    public String getAnd_or() {
        return type;
    }

    @Override
    public String getString(){
        String output = "";
        for(Component component:this.subgoal){
            output += component.getString() + "   \n";
        }
        return output;
    }
}