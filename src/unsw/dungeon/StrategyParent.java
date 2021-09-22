package unsw.dungeon;
import java.util.Random;


public class StrategyParent {
    public Enemy enemy;
    public Dungeon dungeon;
    /**
     * 
     * @param enemy the enemy which will use the strategy
     * @param dungeon the dungon contains the enemy
     */
    public StrategyParent(Enemy enemy, Dungeon dungeon){
        this.enemy = enemy;
        this.dungeon = dungeon;
    }

    /**
     * moving randomly according to the random int, don't move if there is no possible movement
     */
    public void movingRandom(){
        Random rand =  new Random();
        if(!(enemy.checkBlock("Left") || enemy.checkBlock("Right") || enemy.checkBlock("Up") || enemy.checkBlock("Down"))){
            return;
        }
        int randomNum = (rand.nextInt(101) * rand.nextInt(98)) % 4;
        if (randomNum == 0 && enemy.checkBlock("Left")) {
            enemy.moveLeft();
        }else if (randomNum == 1 && enemy.checkBlock("Right")) {
            enemy.moveRight();
        }else if (randomNum == 2 && enemy.checkBlock("Up")) {
            enemy.moveUp();
        }else if(enemy.checkBlock("Down")){
            enemy.moveDown();
        }else{
            movingRandom();
        }
        
    }
}