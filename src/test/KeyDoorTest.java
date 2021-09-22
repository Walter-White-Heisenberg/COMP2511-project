package test;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Door;
import unsw.dungeon.Key;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class KeyDoorTest {
    @Test
    public void testKeyCollectable(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Key key = new Key(0, 1, 1);
        dungeon.addEntity(key);
        dungeon.setPlayer(player);    
        player.moveDown();
        //since the invincibility potion has been colleceted, it should no appear in the dungeon entities list
        assertEquals(false, dungeon.getEntities().contains(key)); 
        //check the player has the key
        assertEquals(key, player.getKey());
    }
    @Test
    public void testOneKeyToOneDoor(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Key key = new Key(0, 1, 1);
        //door with id 1, which can be opened by key1
        Door door1 = new Door(1, 0, 1);
        //door with id 1, which cannot be opened by key1
        Door door2 = new Door(1, 1, 2);
        dungeon.addEntity(key);
        dungeon.addEntity(door1);
        dungeon.addEntity(door2);   
        dungeon.setPlayer(player); 
        //player collect the key 1, position(0,1)
        player.moveDown();
        //try to open door 2 at (1,1),player shall not move and door 2 is not open
        player.moveRight();
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
        assertEquals(false, door2.isOpen());
        //check the player has the key
        assertEquals(key, player.getKey());
        //position 0,0
        player.moveUp();
        player.moveRight();
        //the player should opened the door 1, and at position 1,0
        assertEquals(1, player.getX());
        assertEquals(0, player.getY());
        assertEquals(true, door1.isOpen());
        //the player has no key now
        assertEquals(null, player.getKey());
    }
}