package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;
import org.junit.jupiter.api.Test;

public class EnemyTest {
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
        Player player = new Player(dungeon, 0 ,0);
        dungeon.setPlayer(player);
        Enemy enemy = new Enemy(dungeon, x, y);
        dungeon.addEntity(enemy);
        //because the player has no place to move, it should stay in same position
        enemy.moveUp();
        assertEquals(x, enemy.getX());
        assertEquals(y, enemy.getY());
        //player have move down, so y + 1
        enemy.moveDown();
        assertEquals(x, enemy.getX());
        assertEquals(y+1, enemy.getY());
        //player have move right, so x + 1
        enemy.moveRight();
        assertEquals(x+1, enemy.getX());
        assertEquals(y+1, enemy.getY());
        //player have move up, so y = y + 1 - 1 = y
        enemy.moveUp();
        assertEquals(x+1, enemy.getX());
        assertEquals(y, enemy.getY());
        //player have move up, so x = x + 1 - 1 = x, back to same position
        enemy.moveLeft();
        assertEquals(x, enemy.getX());
        assertEquals(y, enemy.getY());
    }

    @Test
    public void test_push_right_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Enemy enemy = new Enemy(dungeon, 1, 1);
        Player player = new Player(dungeon, 0 ,0);
        dungeon.setPlayer(player);
        Boulder boulder = new Boulder(dungeon, 2, 1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(enemy);
        enemy.moveRight();
        assertEquals(1, enemy.getX());
        assertEquals(1, enemy.getY());
    }

    @Test
    public void test_push_left_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Boulder boulder = new Boulder(dungeon, 1,1);
        Player player = new Player(dungeon, 0 ,0);
        dungeon.setPlayer(player);
        Enemy enemy = new Enemy(dungeon ,2 ,1);
        dungeon.addEntity(boulder);
        dungeon.addEntity(enemy);
        enemy.moveLeft();
        assertEquals(2, enemy.getX());
        assertEquals(1, enemy.getY());
    }
    
    @Test
    public void test_push_up_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Enemy enemy = new Enemy(dungeon, 1, 1);
        Player player = new Player(dungeon, 0 ,0);
        dungeon.setPlayer(player);
        Door door = new Door(1, 0, 3);
        dungeon.addEntity(door);
        dungeon.addEntity(enemy);
        enemy.moveUp();
        assertEquals(1, enemy.getX());
        assertEquals(1, enemy.getY());
    }

    @Test
    public void test_push_down_no(){
        Dungeon dungeon = new Dungeon(3, 3);
        Enemy enemy = new Enemy(dungeon, 1, 0);
        Player player = new Player(dungeon, 0 ,0);
        dungeon.setPlayer(player);
        Wall wall = new Wall(1,1);
        dungeon.addEntity(wall);
        dungeon.addEntity(enemy);
        enemy.moveDown();
        assertEquals(1, enemy.getX());
        assertEquals(0, enemy.getY());
    }

    @Test
    public void Enemy_move_auto(){
        //initialise the map

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Enemy enemy1 = new Enemy(dungeon, 0, 2);
        Enemy enemy2 = new Enemy(dungeon, 4, 2);
        Enemy enemy3 = new Enemy(dungeon, 2, 0);
        Enemy enemy4 = new Enemy(dungeon, 2, 4);
        dungeon.setPlayer(player);    
        dungeon.addEntity(enemy1);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy3);
        dungeon.addEntity(enemy4);
        dungeon.set_level("");
        enemy1.getStrategy().moving();
        enemy2.getStrategy().moving();
        enemy3.getStrategy().moving();
        enemy4.getStrategy().moving();

        assertEquals(1, enemy1.getX());
        assertEquals(2, enemy1.getY());
        assertEquals(3, enemy2.getX());
        assertEquals(2, enemy2.getY());
        assertEquals(2, enemy3.getX());
        assertEquals(1, enemy3.getY());
        assertEquals(2, enemy4.getX());
        assertEquals(3, enemy4.getY());
    }

    @Test
    public void Enemy_move_auto_anti(){
        //initialise the map

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Invincibility inv = new Invincibility(2, 3);
        Enemy enemy1 = new Enemy(dungeon, 1, 2);
        Enemy enemy2 = new Enemy(dungeon, 3, 2);
        Enemy enemy3 = new Enemy(dungeon, 2, 1);
        Enemy enemy4 = new Enemy(dungeon, 2, 3);
        dungeon.setPlayer(player);    
        dungeon.addEntity(enemy1);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy3);
        dungeon.addEntity(inv);
        
        player.moveDown();
        player.moveUp();

        dungeon.addEntity(enemy4);
        dungeon.set_level("");

        enemy1.getStrategy().moving();
        enemy2.getStrategy().moving();
        enemy3.getStrategy().moving();
        enemy4.getStrategy().moving();

        assertEquals(0, enemy1.getX());
        assertEquals(2, enemy1.getY());
        assertEquals(4, enemy2.getX());
        assertEquals(2, enemy2.getY());
        assertEquals(2, enemy3.getX());
        assertEquals(0, enemy3.getY());
        assertEquals(2, enemy4.getX());
        assertEquals(4, enemy4.getY());
    }

    @Test
    public void Enemy_move_and_killed(){
        //initialise the map

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Sword sword = new Sword(2, 3);
        Enemy enemy1 = new Enemy(dungeon, 1, 2);

        dungeon.setPlayer(player);    
        dungeon.addEntity(enemy1);

        dungeon.addEntity(sword);
        
        player.moveDown();
        player.moveUp();

        dungeon.set_level("");

        enemy1.getStrategy().moving();

        assertEquals(true, enemy1.isDead());

    }

    @Test
    public void Enemy_move_by_itself() throws InterruptedException {
        //initialise the map

        Dungeon dungeon = new Dungeon(5, 5);
        Player player = new Player(dungeon, 2, 2);
        Sword sword = new Sword(2, 3);
        Enemy enemy1 = new Enemy(dungeon, 1, 2);

        dungeon.setPlayer(player);    
        dungeon.addEntity(enemy1);

        dungeon.addEntity(sword);
        
        player.moveDown();
        player.moveUp();
        dungeon.set_level("");


        enemy1.move();


        player.moveDown();
        //System.out.println(enemy1.getX()+" , "+enemy1.getY());
        //assertEquals(2, enemy1.getX());
        //assertEquals(2, enemy1.getY());
        //assertEquals(true, enemy1.get_is_dead());

    }

    @Test
    public void test_inv_duration() throws InterruptedException {
        Dungeon dungeon = new Dungeon(1, 6);
        Player player = new Player(dungeon, 0, 0);
        Enemy enemy1 = new Enemy(dungeon, 0, 2);
        Enemy enemy2 = new Enemy(dungeon, 0, 4);
        Invincibility inv = new Invincibility(0, 1);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy1);
        dungeon.addEntity(inv);
        dungeon.setPlayer(player); 
        //move down to get inv
        player.moveDown();
        //move to kill the enemy
        player.moveDown();
        assertEquals(true, enemy1.isDead());
        dungeon.set_level("");

        //enemy should move away from player
        enemy2.getStrategy().moving();

        assertEquals(0, enemy2.getX());
        assertEquals(5, enemy2.getY());
        Thread.sleep(33000);
        //move towards to enemy after potion stop working
        enemy2.getStrategy().moving();
        assertEquals(0, enemy2.getX());
        assertEquals(4, enemy2.getY());
    }

    @Test
    public void test_moving_random(){
        Dungeon dungeon = new Dungeon(7, 7);
        Player player = new Player(dungeon, 3, 3);
        dungeon.setPlayer(player);
        Wall wallA = new Wall(0,2);
        Wall wallB = new Wall(0,4);
        Wall wallC = new Wall(1,2);
        Wall wallD = new Wall(1,4);
        Wall wallE = new Wall(2,2);
        Wall wallF = new Wall(2,4);
        Wall wallG = new Wall(3,2);
        Wall wallH = new Wall(3,4);
        Wall wallI = new Wall(4,2);
        Wall wallJ = new Wall(4,4);
        Wall wallK = new Wall(4,2);
        Wall wallL = new Wall(4,4);
        Wall wallM = new Wall(5,2);
        Wall wallN = new Wall(5,4);
        Wall wallO = new Wall(5,2);
        Wall wallP = new Wall(5,4);
        Wall wallQ = new Wall(6,2);
        Wall wallR = new Wall(6,4);
        Wall wallS = new Wall(2,0);
        Wall wallT = new Wall(4,0);
        Wall wallU = new Wall(2,1);
        Wall wallV = new Wall(4,1);
        Wall wallW = new Wall(2,3);
        Wall wallX = new Wall(4,3);
        Wall wallY = new Wall(2,5);
        Wall wallZ = new Wall(4,5);
        Wall wall1 = new Wall(2,6);
        Wall wall0 = new Wall(4,6);
        dungeon.addEntity(wallA);
        dungeon.addEntity(wallB);
        dungeon.addEntity(wallC);
        dungeon.addEntity(wallD);
        dungeon.addEntity(wallE);
        dungeon.addEntity(wallF);
        dungeon.addEntity(wallG);
        dungeon.addEntity(wallH);
        dungeon.addEntity(wallI);
        dungeon.addEntity(wallJ);
        dungeon.addEntity(wallK);
        dungeon.addEntity(wallL);
        dungeon.addEntity(wallM);
        dungeon.addEntity(wallN);
        dungeon.addEntity(wallO);
        dungeon.addEntity(wallP);
        dungeon.addEntity(wallQ);
        dungeon.addEntity(wallR);
        dungeon.addEntity(wallS);
        dungeon.addEntity(wallT);
        dungeon.addEntity(wallU);
        dungeon.addEntity(wallV);
        dungeon.addEntity(wallW);
        dungeon.addEntity(wallX);
        dungeon.addEntity(wallY);
        dungeon.addEntity(wallZ);
        dungeon.addEntity(wall1);
        dungeon.addEntity(wall0);

        Enemy enemy1 = new Enemy(dungeon, 3, 1);
        Enemy enemy2 = new Enemy(dungeon, 3, 5);
        Enemy enemy3 = new Enemy(dungeon, 1, 3);
        Enemy enemy4 = new Enemy(dungeon, 5, 3);
        dungeon.addEntity(enemy2);
        dungeon.addEntity(enemy1);
        dungeon.addEntity(enemy3);
        dungeon.addEntity(enemy4);
        dungeon.set_level("");

        //enemy should move away from player cause its the only choice
        enemy2.getStrategy().moving();
        enemy1.getStrategy().moving();
        enemy3.getStrategy().moving();
        enemy4.getStrategy().moving();
        assertEquals(3, enemy2.getX());
        assertEquals(6, enemy2.getY());
        assertEquals(3, enemy1.getX());
        assertEquals(0, enemy1.getY());
        assertEquals(0, enemy3.getX());
        assertEquals(3, enemy3.getY());
        assertEquals(6, enemy4.getX());
        assertEquals(3, enemy4.getY());
        dungeon.removeEntity(wallW);
        Invincibility invincibility = new Invincibility(2,3);
        dungeon.addEntity(invincibility);
        player.moveLeft();
        player.moveRight();
        enemy3.moveRight();
        Wall wall2 = new Wall(0, 3);
        dungeon.addEntity(wall2);
        enemy3.getStrategy().moving();
        assertEquals(2, enemy3.getX());
        assertEquals(3, enemy3.getY());

    }
/*
 0123456
0  * *
1  *e*
2*******
3 e*p*e
4*******
5  *e*
6  * *
*/
}