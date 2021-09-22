package unsw.dungeon;

/**
 * the strategy which builds up the most peaceful enemy
 * @author Yun Li
 */
public class EasyStrategy extends StrategyParent implements Strategy{
    /**
     * constructor for the easystrategy
     * @param enemy the enemy which is going to use this strategy
     * @param dungeon the dungeon contains the enemy
     */
    public EasyStrategy(Enemy enemy, Dungeon dungeon){
        super(enemy, dungeon);
    }
    
    /**
     * return the time that enemy refresh its location
     */
    @Override
    public int moving_speed(){
        return 900;
    }
    /**
     * the moving strategy for easy level is random
     */
    @Override
    public void moving() {
        movingRandom();
    }

    
}