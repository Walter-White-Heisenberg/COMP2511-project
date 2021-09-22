package test;
import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class BoulderTest {
    @Test
    public void test_push_right_yes(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 1);
        Boulder boulder = new Boulder(dungeon, 1,1);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        player.moveRight();
        assertEquals(2, boulder.getX());
        assertEquals(1, boulder.getY());
    }

    @Test
    public void test_push_left_yes(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 2, 1);
        Boulder boulder = new Boulder(dungeon, 1,1);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        player.moveLeft();
        assertEquals(0, boulder.getX());
        assertEquals(1, boulder.getY());
    }

    @Test
    public void test_push_up_yes(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 2);
        Boulder boulder = new Boulder(dungeon, 1,1);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        player.moveUp();
        assertEquals(1, boulder.getX());
        assertEquals(0, boulder.getY());
    }

    @Test
    public void test_push_down_yes(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 0);
        Boulder boulder = new Boulder(dungeon, 1,1);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        player.moveDown();
        assertEquals(1, boulder.getX());
        assertEquals(2, boulder.getY());
    }

    @Test
    public void test_push_right_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 1);
        Boulder boulder1 = new Boulder(dungeon, 1,1);
        Boulder boulder2 = new Boulder(dungeon, 2,1);
        dungeon.addEntity(boulder1);
        dungeon.addEntity(boulder2);
        dungeon.setPlayer(player);
        player.moveRight();
        assertEquals(1, boulder1.getX());
        assertEquals(1, boulder1.getY());
    }

    @Test
    public void test_push_left_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 2, 1);
        Boulder boulder = new Boulder(dungeon, 1,1);
        Enemy enemy = new Enemy(dungeon ,0 ,1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(enemy);
        dungeon.setPlayer(player);
        player.moveLeft();
        assertEquals(1, boulder.getX());
        assertEquals(1, boulder.getY());
    }

    @Test
    public void test_push_up_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 1, 2);
        Boulder boulder = new Boulder(dungeon, 1,1);
        Door door = new Door(1, 0, 3);
        dungeon.addEntity(boulder);
        dungeon.addEntity(door);
        dungeon.addEntity(door);
        dungeon.setPlayer(player);
        player.moveUp();
        assertEquals(1, boulder.getX());
        assertEquals(1, boulder.getY());
    }

    @Test
    public void test_push_down_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 1);
        Boulder boulder = new Boulder(dungeon, 1,1);
        Wall wall = new Wall(1,0);
        dungeon.addEntity(wall);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        player.moveDown();
        assertEquals(1, boulder.getX());
        assertEquals(1, boulder.getY());
    }
}