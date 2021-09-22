package test;
import unsw.dungeon.Player;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Sword;
import unsw.dungeon.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class SwordTest {
    @Test
    public void testCollectable(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Sword sword = new Sword(0, 1);
        dungeon.addEntity(sword);
        dungeon.setPlayer(player);    
        player.moveDown();
        //since the sword has been colleceted, it should no appear in the dungeon entities list
        assertEquals(false, dungeon.getEntities().contains(sword)); 
        //check the player has a sword in his hand
        assertEquals(true, player.hasSword()); 

    }
    @Test
    public void testKillEnemy(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Enemy enemy = new Enemy(dungeon, 1, 1);
        Sword sword = new Sword(0, 1);
        dungeon.addEntity(sword);
        dungeon.setPlayer(player);    
        dungeon.addEntity(enemy);
        //collect sword
        player.moveDown();
        //go to position 1,1 to kill enemy, hence there is no enemy on the ground
        player.moveRight();
        assertEquals(false, dungeon.enemyExsit());
    }
    @Test
    public void testBrokenAfterKill5Enemy(){
        //initialise the map
        int x = 0;
        int y = 0;
        Dungeon dungeon = new Dungeon(3, 3);
        Player player = new Player(dungeon, x, y);
        Enemy enemy1 = new Enemy(dungeon, 1, 1);
        Enemy enemy2 = new Enemy(dungeon, 1, 1);
        Enemy enemy3 = new Enemy(dungeon, 1, 1);
        Enemy enemy4 = new Enemy(dungeon, 1, 1);
        Enemy enemy5 = new Enemy(dungeon, 1, 1);
        Sword sword = new Sword(0, 1);
        dungeon.addEntity(sword);
        dungeon.setPlayer(player);    
        dungeon.addEntity(enemy1);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy3);
        dungeon.addEntity(enemy4);
        dungeon.addEntity(enemy5);
        //collect sword
        player.moveDown();
        //go to position 1,1 to kill all enemies, hence there is no enemy on the ground
        player.moveRight();
        assertEquals(false, dungeon.enemyExsit());
        //no sword in hand
        assertEquals(false, player.hasSword());
    }
}