package test;

import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoalTest {
    @Test
    public void test_treasure(){
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Treasure treasure = new Treasure(2,3);
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject trea = new JSONObject();
        JSONObject condition = new JSONObject();
        trea.put("goal", "treasure");
        arr.put(trea);
        json.put("goal", "AND");
        json.put("subgoals", arr);
        condition.put("goal-condition", json);
        Subgoal subgoal = new Subgoal(json, dungeon);
        Goal goal = new Goal(subgoal);
        dungeon.addEntity(treasure);
        dungeon.setPlayer(player);
        dungeon.setGoal(goal);
        assertEquals(false, subgoal.pass());

        assertEquals(false, subgoal.pass());

        player.moveDown();
        assertEquals(true, subgoal.pass());
        
    }

    @Test
    public void test_enemy(){
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Sword sword = new Sword(2,3);
        Enemy enemy = new Enemy(dungeon,2,4);
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject tmp = new JSONObject();
        JSONObject condition = new JSONObject();
        tmp.put("goal", "enemies");
        arr.put(tmp);
        json.put("goal", "AND");
        json.put("subgoals", arr);
        condition.put("goal-condition", json);
        Subgoal subgoal = new Subgoal(json, dungeon);
        Goal goal = new Goal(subgoal);
        dungeon.addEntity(enemy);
        dungeon.addEntity(sword);
        dungeon.setPlayer(player);
        dungeon.setGoal(goal);
        assertEquals(false, subgoal.pass());

        player.moveDown();
        player.moveDown();
        assertEquals(true, subgoal.pass()); 
    }

    @Test
    public void test_Boulder(){
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Boulder boulder = new Boulder(dungeon, 2,3);
        Switch sw = new Switch(dungeon,2,4);
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject tmp = new JSONObject();
        JSONObject condition = new JSONObject();
        tmp.put("goal", "boulders");
        arr.put(tmp);
        json.put("goal", "AND");
        json.put("subgoals", arr);
        condition.put("goal-condition", json);
        Subgoal subgoal = new Subgoal(json, dungeon);
        Goal goal = new Goal(subgoal);
        dungeon.addEntity(sw);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        dungeon.setGoal(goal);
        assertEquals(false, subgoal.pass());

        player.moveDown();
        assertEquals(true, subgoal.pass()); 
    }
    @Test
    public void test_Exit(){
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Exit exit = new Exit(2,3);
        JSONObject json = new JSONObject();
        JSONArray arr = new JSONArray();
        JSONObject tmp = new JSONObject();
        JSONObject condition = new JSONObject();
        tmp.put("goal", "exit");
        arr.put(tmp);
        json.put("goal", "AND");
        json.put("subgoals", arr);
        condition.put("goal-condition", json);
        Subgoal subgoal = new Subgoal(json, dungeon);
        Goal goal = new Goal(subgoal);
        dungeon.addEntity(exit);
        dungeon.setPlayer(player);
        dungeon.setGoal(goal);
        assertEquals(false, subgoal.pass());

        player.moveDown();
        assertEquals(true, subgoal.pass()); 
    }
    @Test
    public void test_Or(){
        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Treasure treasure = new Treasure(2,3);
        Exit exit = new Exit(2,4);
        JSONObject json = new JSONObject();
        JSONArray arr1 = new JSONArray();
        JSONArray arr2 = new JSONArray();
        JSONObject tmp = new JSONObject();
        JSONObject condition = new JSONObject();
        JSONObject subjson1 = new JSONObject();
        JSONObject subjson2 = new JSONObject();
        JSONObject subjson3 = new JSONObject();
        subjson1.put("goal", "treasure");
        subjson2.put("goal", "enemies");
        arr2.put(subjson1);
        arr2.put(subjson2);
        subjson3.put("goal","OR");
        subjson3.put("subgoals",arr2);
        tmp.put("goal", "exit");
        arr1.put(tmp);
        arr1.put(subjson3);
        json.put("goal", "AND");
        json.put("subgoals", arr1);
        condition.put("goal-condition", json);
        Subgoal subgoal = new Subgoal(json, dungeon);
        Goal goal = new Goal(subgoal);

        dungeon.addEntity(exit);
        dungeon.addEntity(treasure);
        dungeon.setPlayer(player);
        dungeon.setGoal(goal);
        assertEquals(false, subgoal.pass());

        player.moveDown();
        assertEquals(false, subgoal.pass());
        player.moveDown();
        assertEquals(true, subgoal.pass()); 
    }
}