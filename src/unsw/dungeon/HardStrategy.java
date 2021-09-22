package unsw.dungeon;

import java.util.ArrayList;
import java.util.Collections;

/**
 * the strategy which builds up the most aggresive enemy
 * @author Yun Li
 */
public class HardStrategy extends StrategyParent implements Strategy {

    int[][] fake_dungeon;
    /**
     * the private class I create to fake the priority queue,
     * which is why I overwrite the compareTo
     */
    private class node implements Comparable<node>{
        int x;
        int y;
        int cur_length;
        int distance;
        public node (int x, int y, int length, int distance) {
            this.x = x;
            this.y = y;
            this.cur_length = length;
            this.distance = distance;
        }

        @Override
        public int compareTo(node other) {
            if(this.distance > other.distance){
                return 1;
            } else if (this.distance < other.distance) {
                return -1;
            }
            return 0;
        } 
    }
    /**
     * the constructor for the strategy. initialize a 2d array to fake the dungeon during BFS
     * @param enemy the enemy which is going to use this strategy
     * @param dungeon the dungeon contains the enemy
     */
    public HardStrategy(Enemy enemy, Dungeon dungeon){
        super(enemy, dungeon);
        fake_dungeon = new int[dungeon.getWidth()][dungeon.getHeight()];
        initial();
        
    }
    /**
     * mark all the movable point to 1000
     * mark all the immovable point to -1
     */
    public void initial(){
        for(int i = 0; i < dungeon.getWidth(); i++){
            for(int j = 0; j < dungeon.getHeight(); j++){
                fake_dungeon[i][j] = is_available(i, j);
            }
        }
    }
    /**
     * return corresponding value according to if the enemy is movable
     * @param x the x coord of the point in thd dungeon
     * @param y the y coord of the point in thd dungeon
     * @return the 1000 or -1 depends on if the point is movable
     */
    public int is_available(int x, int y){
        for(Entity entity : this.dungeon.getEntity(x, y)){
            if(entity instanceof Wall){
                return -1;
            } else if(entity instanceof Door){
                Door door = (Door) entity;
                if(door.isOpen()){
                    return 1000;
                }
                return -1;
            } else if(entity instanceof Boulder){
                return -1;
            } else if(entity instanceof Enemy){
                return -1;
            } else if(entity instanceof Exit){
                return -1;
            }
        }
        return 1000;
    }
    /**
     * construct the queue node and add node to the fake queue if the construction is successful
     * @param x the x cood in the 2d array
     * @param y the y cood in the 2d array
     * @param nl the 'diy' queue for purpose of BFS
     */
    public void add_node(int x, int y, ArrayList<node> nl){
        int length = shortestPath(x, y);
        node m = new node(x, y, length, length);
        if(m.cur_length != -1){
            nl.add(m);
        }
    }
    /**
     * return the time that enemy refresh its location
     */
    @Override
    public int moving_speed(){
        return 400;
    }

    /**
     * when the player's without invincible potion, move the enemy towards to player arrcording to the shortest path
     * step 1: find out the length of the shortest for different case in enemy movement: up down, right, left
     * step 2: find out which is the shortest adn move in that direction. 
     *         If there are no possible path, use medium strategy
     * if player's holding the invincible potion, follow the medium strategy
     * 
     */
    @Override
    public void moving(){
        if(dungeon.getPlayer().isInvincible()){
            MediumStrategy ms = new MediumStrategy(enemy, dungeon);
            ms.moving();
            return;
        }
        ArrayList<node> nl = new ArrayList<node>();
        if(enemy.checkBlock("Up")){
            add_node(enemy.getX(), enemy.getY() - 1, nl);
        }
        if(enemy.checkBlock("Down")){
            initial();
            add_node(enemy.getX(), enemy.getY() + 1, nl);
        }
        if(enemy.checkBlock("Right")){
            initial();
            add_node(enemy.getX() + 1, enemy.getY(), nl);
        }
        if(enemy.checkBlock("Left")){
            initial();
            add_node(enemy.getX() - 1, enemy.getY(), nl);
        }
        if(nl.size() == 0) {
            MediumStrategy ms = new MediumStrategy(enemy, dungeon);
            ms.moving();
            return;
        }
        Collections.sort(nl);
        node n = nl.remove(0);
        if(n.x == enemy.getX() - 1) {
            enemy.moveLeft();
        }if(n.x == enemy.getX() + 1) {
            enemy.moveRight();
        }
        if(n.y == enemy.getY() - 1) {
            enemy.moveUp();
        }if(n.y == enemy.getY() + 1) {
            enemy.moveDown();
        }
    }
    
    /**
     * find the length of the shortest path from origin to the location of the player
     * @param x the x cood of the origin
     * @param y the y cood of teh origin
     * @return the length of the shortest path
     */
    public int shortestPath(int x, int y){
        ArrayList<node> nl = new ArrayList<node>();
        int final_x = dungeon.getPlayer().getX();
        int final_y = dungeon.getPlayer().getY();
        addpoint(x, y, 0 ,nl);
        int i = 0;
        while(nl.size() != 0 && i < 400){
            Collections.sort(nl);
            node n = nl.remove(0);
            if(n.x == final_x && n.y == final_y) {
                return n.cur_length;
            }
            if(fake_dungeon[n.x][n.y] > n.cur_length){
                fake_dungeon[n.x][n.y] = n.cur_length;
                addpoint(n.x - 1, n.y, n.cur_length + 1 ,nl);
                addpoint(n.x + 1, n.y, n.cur_length + 1 ,nl);
                addpoint(n.x, n.y + 1, n.cur_length + 1 ,nl);
                addpoint(n.x, n.y - 1, n.cur_length + 1 ,nl);
            }  
            i++;
        }
        return -1;
    }
    /**
     * calculate the square of the distance between two point on dungeon
     * @param x x coord of the first point
     * @param y y coord of the first point
     * @param x_ x coord of the second point
     * @param y_ y coord of the ssecond point
     * @return
     */
    public int calculate_distance(int x, int y, int x_, int y_){
        return (x-x_)*(x-x_) + (y-y_)*(y-y_);
    }

    /**
     * if the poing is qualified (movable and in the range of dungeon), added it to the priority queue
     * @param x the x coord of the point which will be added to the queue
     * @param y the y coord of thh point which will be added to the queue
     * @param cur_length the cur_length it takes to achieve this point from the source
     * @param nl the list containing the existing point
     */
    public void addpoint(int x, int y, int cur_length, ArrayList<node> nl){
        if(x < 0 || y < 0 || x >= dungeon.getWidth() || y >= dungeon.getHeight()) {
            return;
        }
        if(fake_dungeon[x][y] != -1) {
            int final_x = dungeon.getPlayer().getX();
            int final_y = dungeon.getPlayer().getY();
            node t = new node(x, y, cur_length, calculate_distance(x, y, final_x, final_y));
            nl.add(t);
        }  
    }

}
