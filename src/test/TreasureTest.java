package test;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Treasure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TreasureTest {
    @Test
    public void testTreasureCollectable(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Treasure treasure = new Treasure(0, 1);
        dungeon.addEntity(treasure);
        dungeon.setPlayer(player);
        assertEquals(true, dungeon.getEntities().contains(treasure));     
        player.moveDown();
        //since the treasure potion has been colleceted, it should no appear in the dungeon entities list
        assertEquals(false, dungeon.getEntities().contains(treasure)); 
    }
}