package test;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Sword;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;
import unsw.dungeon.Invincibility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class PlayerTest {
    @Test
    public void testPlayerAdd(){
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        dungeon.setPlayer(player);
        assertEquals(x, dungeon.getPlayer().getX());
        assertEquals(y, dungeon.getPlayer().getY());
    }

    @Test
    public void testPlayerMove(){
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        dungeon.setPlayer(player);
        //because the player has no place to move, it should stay in same position
        player.moveUp();
        assertEquals(x, dungeon.getPlayer().getX());
        assertEquals(y, dungeon.getPlayer().getY());
        //player have move down, so y + 1
        player.moveDown();
        assertEquals(x, dungeon.getPlayer().getX());
        assertEquals(y+1, dungeon.getPlayer().getY());
        //player have move right, so x + 1
        player.moveRight();
        assertEquals(x+1, dungeon.getPlayer().getX());
        assertEquals(y+1, dungeon.getPlayer().getY());
        //player have move up, so y = y + 1 - 1 = y
        player.moveUp();
        assertEquals(x+1, dungeon.getPlayer().getX());
        assertEquals(y, dungeon.getPlayer().getY());
        //player have move up, so x = x + 1 - 1 = x, back to same position
        player.moveLeft();
        assertEquals(x, dungeon.getPlayer().getX());
        assertEquals(y, dungeon.getPlayer().getY());
    }

    @Test
    public void testPlayerCollect(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Key key = new Key(0, 1, 1);
        Sword sword = new Sword(1, 1);
        Invincibility invincibility = new Invincibility(1, 0);
        Treasure treasure = new Treasure(2, 0);
        dungeon.addEntity(key);
        dungeon.addEntity(sword);
        dungeon.addEntity(invincibility);
        dungeon.addEntity(treasure);
        dungeon.setPlayer(player);        
        //player move down, position = 0,1, which should collect the key and the getkey will return same key
        player.moveDown();
        assertEquals(key, player.getKey());
        //player move right, position = 1,1, which should collect the sword and has sword in hand
        player.moveRight();
        assertEquals(true, player.hasSword());
        //player move up, position = 1,0, which should collect the invincibility potion and be invincible
        player.moveUp();
        assertEquals(true, player.isInvincible());
        //player move right, position = 2,0, which should collect the treasure and have 1 treasure in hand which means no treasure
        //in the map is remain
        player.moveRight();
        assertEquals(false, dungeon.treasureExist());
    }
    @Test
    public void testPlayerBlock(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Wall wall = new Wall(0, 1);
        dungeon.addEntity(wall);
        dungeon.setPlayer(player);        
        //player move down, position = 0,1, which will not be proccessed since the path is blocked by a wall,
        //and it will stay in 0,0
        player.moveDown();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
    }
    @Test
    //test the player will be able to push a boulder, assume nothing block the way of boulder
    public void testPlayerPushBoulder(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Boulder boulder = new Boulder(dungeon, 0, 1);
        dungeon.addEntity(boulder);
        dungeon.setPlayer(player);        
        //player move down, position = 0,1, which will push the boulder to the down square which is 0,2
        player.moveDown();
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
        assertEquals(0, boulder.getX());
        assertEquals(2, boulder.getY());
    }
    @Test
    //test the player will be able to push a boulder, assume nothing block the way of boulder
    public void testOpenDoor(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Key key = new Key(1, 0, 1);
        Door door = new Door(0, 1, 1);
        dungeon.addEntity(key);
        dungeon.addEntity(door);
        dungeon.setPlayer(player);        
        //player move down, position = 0,1, which will try to pass the unopened door
        //it shall not pass and stay in same square
        player.moveDown();
        assertEquals(0, player.getX());
        assertEquals(0, player.getY());
        //player move right to collect the key and should be able to open the door for now
        player.moveRight();
        //player move left back to same position
        player.moveLeft();
        //player move down,open the door, he shall on the same square with door for now
        player.moveDown();
        assertEquals(0, player.getX());
        assertEquals(1, player.getY());
        //door should be opened
        assertEquals(true, door.isOpen());
    }
}