package test;
import unsw.dungeon.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class PortalTest {
    @Test
    public void test_match_portal(){
        Dungeon dungeon = new Dungeon(3, 3);
        Portal portal1 = new Portal(dungeon, 0, 0, 1);
        Portal portal3 = new Portal(dungeon, 0, 0, 4);
        dungeon.addEntity(portal1);
        assertEquals(portal1.match_another_portal(), false);
        Portal portal2 = new Portal(dungeon, 2, 2, 1);
        dungeon.addEntity(portal2);
        dungeon.addEntity(portal3);
        assertEquals(portal2.match_another_portal(), true);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        assertEquals(portal1.getDestination_x(), portal2.getX());
        assertEquals(portal2.getDestination_x(), portal1.getX());
        assertEquals(portal1.getDestination_y(), portal2.getY());
        assertEquals(portal2.getDestination_y(), portal1.getY());
    }
    @Test
    public void test_travel(){
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, 0, 1);
        Portal portal1 = new Portal(dungeon, 0, 0, 1);
        Portal portal2 = new Portal(dungeon, 2, 2, 1);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        dungeon.setPlayer(player);
        assertEquals(portal2.match_another_portal(), true);
        player.moveUp();
        assertEquals(2, player.getX());
        assertEquals(2, player.getY());
    }


    @Test
    public void test_boulder_travel(){
        Dungeon dungeon = new Dungeon(3, 3);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        Player player = new Player(dungeon, 0, 2);
        Portal portal1 = new Portal(dungeon, 0, 0, 1);
        Portal portal2 = new Portal(dungeon, 2, 2, 1);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);
        assertEquals(portal2.match_another_portal(), true);
        player.moveUp();
        assertEquals(0, boulder.getX());
        assertEquals(0, boulder.getY());
    }
    @Test
    public void test_Enemy_travel(){
        Dungeon dungeon = new Dungeon(3, 3);
        Enemy enemy = new Enemy(dungeon, 0, 1);
        Player player = new Player(dungeon, 1 ,1);
        dungeon.setPlayer(player);
        Portal portal1 = new Portal(dungeon, 0, 0, 1);
        Portal portal2 = new Portal(dungeon, 2, 2, 1);
        dungeon.addEntity(portal1);
        dungeon.addEntity(portal2);
        dungeon.addEntity(enemy);
        assertEquals(portal2.match_another_portal(), true);
        enemy.moveUp();
        assertEquals(2, enemy.getX());
        assertEquals(2, enemy.getY());
    }

}