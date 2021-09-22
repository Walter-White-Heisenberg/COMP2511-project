USER STORY FOR COMP2511 20T2

GROUP: DARANK

GROUP MEMBER: Haowei Lou(z5258575) Yun Li(z5231701)

**Pre explanation and assumptions:**

Story points: (5 levels)

1: no algorithm + less than 10 line of code

2: might be algorithm used with 1-2 method but might be with lots of case

3: algorithm used with 2-3 functions

4. complex algorithm used with 3-4 functions with more than 1 case

5: complex algorithm used with 4-5 function with more than 3 cases

Priorities: (given that the start code is able to run already, the first priority of the software is turning program into a game instead of making a program)

1. The essential part of the game, the program can run without it but can&#39;t be defined as a game
2. The important part of the game, the program can still work as a game without them but the game is relatively boring.
3. The non-essential part of the game, the game can still work as a OK game but more challenging and interesting with them.

**Theme:**

For transportation in the game:



**Epic:** The player can access to next level after they fulfill the goal of this level.

**User Story:**

As a player I can have a goal for each level so I can know exactly what I should do to pass this level and gain confidence after I achieved this goal.

**Acceptance Criteria:**

1. The goal should be clear and easy to understand
2. The system should notify me while I achieve a goal so that I don&#39;t need to work on it anymore
3. The text colour for an unsolved goal should be white, and a finished goal should be green

**Story points: 1 (telling the player the condition to pass the level instead of implements)**

**Priority: 2**    



**User Story:**

As a player, I can go to the higher level by a portal, so that I have a way to complete the game.

**Acceptance Criteria:**

1. There are two doors in each floor except the first floor and the last floor
2. In the first floor, there is only a portal which accesses second floor
3. In the last floor, there is only a portal to accesses the second last floor
4. Else If a player goes in a portal in level n, he will suddenly appear in front of the portal from level n + 1.
5. Portal is reusable, a player can go back to the lower floor by the portal he used before.
6. Portals can be accessed in any direction.
7. The player is the only object that can pass the portal.

**Story points: 2**    

**Priority:  1**    



**User Story:**

As a player, I can only access the portal when each floor switch is pressed by a boulder, so that the game is tougher and more challenging.

**Acceptance Criteria:**

1. The portal will only appear when each switch is pressed by a boulder.
2. When the portal is not being shown, nothing will happen when the player is on the location where portal would shown up

**Story points: 2**    

**Priority: 3**    



**Epic:** The player can move in direction which doesn&#39;t contains inaccessible block.



**User Story:**

As a player, I cannot access the square where the wall is on, so that the game is more challenging.

**Acceptance Criteria:**

1. The player can&#39;t access to the block where the wall is on.
2. The enemy also can&#39;t access to the block where the wall is on.

**Story points:1**    

**Priority: 2**    



**User Story:**

As a player, I can move in the game so that I can access to the place I want.

**Acceptance Criteria:**

1. I want to move up, down, left and right into the adjacent square.
2. If a path is blocked by a wall, I can&#39;t pass beyond this point, so I have to find another way or path
3. If a path is blocked by a door, I should be able to open the door when I have a key, or the block is inaccessible.
4. If a path is blocked by a boulder, I might be able to push this boulder to its adjacent square unless the square has been blocked by another boulder, door, and wall.

**Story points: 1 (the implementation already contained in start-code)**

**Priority:  1**    



**Theme:**

For player-item interaction:



**Epic:** The player can carry multiple different items, but there is a limit for each item to carry.



**User Story:**

As a player, I want to have a way to enhance my equipment/skills/ability while playing so that I can defeat more powerful enemies at the higher level.

**Acceptance Criteria:**

1. While a user reaches a square that contains a sword, he will have a sword with 5 remaining capable hits in hand and will be able to kill the enemy.
2. While a user reaches a square that contains an Invincibility potion, he will have a 32s duration of invincible time, that he will kill any enemy who collides with him.

**Story points: 3**    

**Priority: 3**    

**Epic:** If the player can use potion on himself and use right key to open right door, and he can push the boulder in some condition.



**User Story:**

As a player, I can push the boulder if the adjacent square of boulder is empty, so that there will be mor e difficulty for me which brings me the challenge.

**Acceptance Criteria:**

1. You can only push the boulder when there are no wall 2 behind the square boulder. Otherwise, the boulder is not pushable in this direction
2. You cannot access the square when a boulder is on it.

**Story points: 2**    

**Priority: 3**    



**User Story:**

As a player, I can collect treasure so that I can achieve a higher score with a stronger sense of achievement.

**Acceptance Criteria:**

1.when the player goes into the block that contains the treasure, the treasure disappears and the score of the player will go up.

2. Treasure is accumulative for the game play.

**Story points:1**    

**Priority: 3**    



**User Story:**

As a player, I want multiple ways to solve each problem &amp; task so that my gaming experience is more interesting and challenging.

**Acceptance Criteria:**

1. To kill an enemy, I should either kill it with a sword or I have an Invincibility potion.
2. Given that the goal of this level is already achieved, when a boulder is pushed onto a floor switch, the portal should appear regardless of which boulder it is.

**Story points: 2**    

**Priority:  2**    



**Theme:**

For player-enemy interaction:



**Epic:** The player can kill the enemy when he&#39;s carrying sword or using potion.



**User Story:**

As a player, I want to kill or battle with enemies so I can beat them and be more attractive for me.

**Acceptance Criteria:**

1. A player can kill an enemy if he collides with enemy and one of following is true:

  1. He has a sword on his hand and the sword has remaining capable hits.
  2. He has invincibility potion in effect and uses it.
1. If the invincibility potion is in effect, and the enemy collides with the player, the enemy should be killed and the remaining capable hits of the sword in the user&#39;s hand should not change.
2. When the invincibility potion is still in effect, and the player takes another potion,

the new potion&#39;s effect will cover the old potion. (the time will refresh)

**Story points: 2**    

**Priority: 3**    



**User Story:**

As a player, I can kill the enemy by using sword, so that I can play game with the sense of achievement, and the gameplay will be more fun.

**Acceptance Criteria:**

1. The player can carry at most one sword at a time.
2. The sword is only capable of 5 killings, then disappears after that.
3. The sword only works for the enemy.
4. The player can only carry one sword regardless of the usage of the current sword at the same time. If the user is carrying the sword, he can&#39;t pick up any other sword at that moment.

**Story points:2**    

**Priority: 3**    



**Theme:**

For the player-game interaction:



**Epic:** The player can win in certain condition, and the player can lose in certain condition, and the game experience is fun.



**User Story: **

As a player, I want to have a score or rank system after I finish playing this game so that I can know how good I did when I was playing compared to others or previous time.

**Acceptance Criteria:**

1. The player should be able to collect the treasure on floor and the amount of money in wallet should also be increased

**Story points: 3 (the algorithm to calculate the final score might be complex)**

**Priority: 2**    



**User Story: **

As a player, I want to have a score or rank system after I finish playing this game so that I can know how good I did when I was playing compared to others or previous time.

**Acceptance Criteria:**

1. The player should be able to collect the treasure on floor and the amount of money in wallet should also be increased

**Story points: 3 (the algorithm to calculate the final score might be complex)**

**Priority: 2**    

**User Story:**

As a player, I want to have some surprise, extra scene or unexpected experience so that this game becomes interesting and engaging.

**Acceptance Criteria:**

1. For each time the player dies, the message for die should be different and interesting, the format is as follow &quot;You have been killed by <NAME>, because<REASON>&quot;. Eg _You have been killed by pikachu, because it is so cute._

**Story points: 2 (use the random number generate by current time to print different message)**

**Priority: 3**    

**User Story:**

As a player, I want every target, level and rewards to be achievable otherwise I will be frustrated because I can&#39;t achieve these goals.

**Acceptance Criteria:**

1. Every door should have a corresponding key to open it
2. The number of treasure requirements should not be greater than the amount of treasure that can be found so far.

**Story points: 2 (for testing ???)**

**Priority: 2**    

**User Story:**

As a player, I want to have a way to save my current gaming progress so that I can still play this game after some unexpected technical issues(system is dead) happened or I want to stop playing for now and playing in future.

**Acceptance Criteria:**

1. The system should save the player&#39;s current status in json file format while user exit program or clicks the save button. Include the user&#39;s position, number of treasures he owns and whether he has a sword if yes, remaining capable hits.
2. Next time a user launches games, if he chooses to load the saved progress system will open the corresponding json file.

**Story points: 4 (conversion for json)**

**Priority: 2**    

**User Story:**

As a player, I want to have a goal/gate for each level so I can access the next level after I achieve all tasks in the current level.

**Acceptance Criteria:**

1. While the user reaches the portal on each level, he will automatically be transported to the next page/level.

**Story points:2**    

**Priority: 1**    

**User Story:**

As a player, I want my enemies to constantly become more powerful so that I can have a sense of confidence when I beat a more powerful enemy.

**Acceptance Criteria:**

1. There are three levels of enemies, easy, medium and hard.
2. The easy enemies should move randomly, and move 1 square in 1 second.
3. The medium enemies should move toward the player, but not in minimal path, and moves 1 square in 0.5 second
4. The hard enemies should move toward the player in minimal path, and it will run away from the player if the player is invincible. It will move 1 square in 0.25 second.

**Story points: 5 (the moving pattern of enemy is hard to implement)**

**Priority: 3**    

**User Story:**

As a player, I can finish the puzzle by passing the exit so that I have a way to finish the game.

**Acceptance Criteria:**

1. When the player goes into the block contains exit, the game will end and the ending scene designed by the programmer with congratulation will show up.

      2.   Exit will only show up in the last level. And the portal won&#39;t show up.

**Story points:1**    

**Priority: 1**    
