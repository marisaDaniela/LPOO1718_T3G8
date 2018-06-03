# LPOO1718_T3G8

Final Project for the class Oriented Objects Programming Language of MIEIC at FEUP 

## Authors

* Marisa Daniela Quintal Oliveira, up201308594, up201308594@fe.up.pt

## Title:  Super Mario

This project is a clone of the original platform game Super Mario Bros. It is built in Java using the LibGDX game development library.


### Setup Instructions:

* Download this Repository on this branch and unzip it
* Open android studio and do Import project (Eclipse ADT,Gradle, etc).
* Then select folder unzipped
* Then click OK

---

### Development documentation:

#### UML diagrams

##### Package Diagram

##### Class Diagram

#### Design Patterns used

* Singleton: classes Play and SuperMario have only one instance, and provide a global point of access to it. 
* State: the Mario's behavior is governed by states. Besides, characters can have multiple states that limits their actions (ex: JUMPING, STANDING...) to be executed in any moment of the game. 
* Observer: Are implemented observers to detect input from user and detection of collisions between characters in the game.
* Stategy: Different kind of characters (Enemies and Mario) execute different actions (JUMPING, STANDING...). Each character can execute them with different algorithms. 

#### Major Difficulties 

Doing this kind of game takes more time than expected. Our major difficulty was to set up all the software required to make the game. After that, the worst part was starting the implementation. When the start was over, it became easier and more fascinating.

#### Lessons Learned

* Making this kind of game is quite interesting and calls for more to achieve better results.
* Doing this kind of game takes more time than expected.


#### Overall Time Spent Developing

* In overall i've spent about 100 hours developping this projects: 
- 21 hours on class.
- About 80 hours outside with major distribution in the last 3 weeks.

#### Work Distribution Amongst Team Members

* Although we had settled some work distribution between us in the beginning, it unfortunately didn't work.
* In the end, the project was mainly done by Marisa (about 95% of the work) and Leonor only helped a bit with the initial sprites.
---

### User manual

* When you start the game this will appear:

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/begin.png)

* Then Mario can eat a mushroom and grow...

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/mushroom.png)

* Mario can run, and jump...

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/jump.png)

* But danger is in every corner. If Mario touches an enemy, he becomes smaller:

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/smaller.png)

* And if he touches another enemy, he dies!

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/die.png)

* Oh no, game over...

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/gameOver.png)

* But let's start again, and now Mario will achieve the end of the game! 

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/finalRelease/images/end.png)

