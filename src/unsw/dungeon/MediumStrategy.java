package unsw.dungeon;

public class MediumStrategy extends StrategyParent implements Strategy{
    /**
     * 
     * @param enemy the enemy which is going to use the strategy
     * @param dungeon the dungeon which contains the enemy
     */
    public MediumStrategy(Enemy enemy, Dungeon dungeon){
        super(enemy, dungeon);
    }
    
    /**
     * return the time that enemy refresh its location
     */
    @Override
    public int moving_speed(){
        return 700;
    }

    /**
     * move the enemy towards to player when there's no obstacle or move randomly when there no clear path
     */
    @Override
    public void moving(){
        Player player = dungeon.getPlayer();
        if(player.isInvincible()){
            if(player.getX() > enemy.getX() && enemy.checkBlock("Left")){
                enemy.moveLeft();
            }else if(player.getX() < enemy.getX() && enemy.checkBlock("Right")){
                enemy.moveRight();
            }else if(player.getY() < enemy.getY() && enemy.checkBlock("Down")){
                enemy.moveDown();
            }else if(player.getY() > enemy.getY() && enemy.checkBlock("Up")){
                enemy.moveUp();
            }else {
                movingRandom();
            }
        }else {
            if(player.getX() < enemy.getX() && enemy.checkBlock("Left")){
                enemy.moveLeft();
            }else if(player.getX() > enemy.getX() && enemy.checkBlock("Right")){
                enemy.moveRight();
            }else if(player.getY() > enemy.getY() && enemy.checkBlock("Down")){
                enemy.moveDown();
            }else if(player.getY() < enemy.getY() && enemy.checkBlock("Up")){
                enemy.moveUp();
            }else {
                movingRandom();
            }
        }
    }
    
}