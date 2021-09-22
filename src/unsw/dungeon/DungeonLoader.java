package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    private String level;
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
        if(filename.equals("easy.json")){
            level = "easy";
        } else if(filename.equals("hard.json")){
            level = "hard";
        } else {
            level = "medium";
        }
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        Component goals;
        if(jsonGoals.has("subgoals")){
            goals = new Subgoal(jsonGoals,dungeon);
        } else {
            goals = new GoalLeaf(jsonGoals.getString("goal"),dungeon);
        }
        Goal goal = new Goal(goals);
        dungeon.setGoal(goal);
        dungeon.setJsongoal(jsonGoals);
        dungeon.set_level(level);
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "door":
            int door_id = json.getInt("id");
            Door door = new Door(x, y, door_id);
            onLoad(door);
            entity = door;
            break;
        case "key":
            int key_id = json.getInt("id");
            Key key = new Key(x, y, key_id);
            onLoad(key);
            entity = key;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "invincibility":
            Invincibility invincibility = new Invincibility(x, y);
            onLoad(invincibility);
            entity = invincibility;
            break;
        case "boulder":
            Boulder boulder = new Boulder(dungeon, x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "portal":
            int portal_id = json.getInt("id");
            Portal portal = new Portal(dungeon,x, y, portal_id);
            onLoad(portal);
            entity = portal;
            break; 
        case "switch":
            Switch plate = new Switch(dungeon, x, y);
            onLoad(plate);
            entity = plate;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            onLoad(enemy);
            entity = enemy;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Player player);

    public abstract void onLoad(Wall wall);

    public abstract void onLoad(Door door);

    public abstract void onLoad(Key key);

    public abstract void onLoad(Exit exit);

    public abstract void onLoad(Treasure treasure);

    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Invincibility invincibility);

    public abstract void onLoad(Boulder boulder);

    public abstract void onLoad(Portal portal);

    public abstract void onLoad(Switch plate);

    public abstract void onLoad(Enemy enemy);


}
