package unsw.dungeon;

import java.util.ArrayList;
import java.util.Random;

public class Death {
    private ArrayList<String> murder;
    private ArrayList<String> reason;
    public Death(){
        this.murder =  new ArrayList<>();
        this.murder.add("Pikachu");
        this.murder.add("Captain America");
        this.murder.add("Iron Man");
        this.murder.add("Baby");
        this.murder.add("Cat");
        this.murder.add("Turtle");
        this.murder.add("Bus");
        this.murder.add("duck");
        this.murder.add("nerd gorilla");
        this.murder.add("handsome giraffe");
        this.reason =  new ArrayList<>();
        this.reason.add("he is so cute");
        this.reason.add("he sit on your head");
        this.reason.add("he throw rubbish to your");
        this.reason.add("you think he is so ugly");
        this.reason.add("he has virsus that cause you death");
        this.reason.add("he crash your body");
        this.reason.add("he drinks all your water and you are too thirsty");
        this.reason.add("he is so handsome and you forgot breath");
        this.reason.add("he pull you in water");
        this.reason.add("he is immutable");
    }

    /**
     * @return the funny death message ramdomly generated 
     */
    @Override
    public String toString(){
        Random random = new Random();
        int a = (random.nextInt(10) * random.nextInt(10)) % 10;
        int b = (random.nextInt(10) * random.nextInt(10)) % 10;
        return "Your have killed by " + murder.get(a) + " becasue " + reason.get(b);
    }
    
}