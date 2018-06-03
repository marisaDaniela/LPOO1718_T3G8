# LPOO 2017/2018 Turma 03 Grupo 08

 Final Project for the class Oriented Objects Programming Language of MIEIC at FEUP 

## Authors

* Leonor Ribeiro e Sousa Mendes de Freitas, up201207603, up201207603@fe.up.pt
* Marisa Daniela Quintal Oliveira, up201308594, up201308594@fe.up.pt

## Title: Aunios

Aunios is a 2D RPG Game in which our hero goes on an adventure, fighting horrible monsters and terrifying enemies, questing for magical objects and facing challenging obstacles in order to save the peaceful village of Aunios from a dreadful fate...

### UML: Package and class diagram:
#### Package Diagram:
![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/final-project/images/packages.png)
#### Class Diagram:
![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/final-project/images/uml.png)

#### Brief Description of each class responsibility:
 * **GameView:** class to represents the visualization of the data that GameModel contains;

 * **GameController:** controller acts on both GameModel and GameView. It controls the data flow into model object and updates the view whenever data changes. It keeps view and model separate;

 * **GameModel:** represents an object carrying data. It can also have logic to update controller if its data changes;

 * **Start:** Class for displaying the screen of the begin of the game;

 * **Instructions:** Class for displaying the instructions of the game;

 * **Save:** Class for displaying the screen to save the game;

 * **Game:** Class for displaying the game;

 * **GameOver:** Class for displaying the end of the game;

 * **GameLoop:** Class for logic of the game;

 * **Hero:** Class where hero methods will be implemented;

 * **Monsters:** Class where monster methods will be implemented;

 * **Item:** Class to represents an entity of the game;

 * **Scores:** Class to represents the scores of the game;
 
 * **Map:** Class to represent the differents levels of the game;
 
 * **Dialog Box:** Class to represent the story during the game;
 

#### Design of behavioural aspects:
![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/final-project/images/behavioural.png)

### Expected Design Patterns:


* **Singleton:** to ensures that only one instance of a class is created and provide a global access point to the object.
* **State:** to evolve from level/map to map; to change sprites accordingly to the motion/action of the character.
* **Strategy:** so that from one main enemy class, we can have enemies with different behaviours.
* **Flyweight:** to separate the shared aspects of a non-player character (appearance, animations,...) from the individual aspects (health, position,...). 

And others to be further studied...
### Main Funcionalities:

* The game will allow to players take a part in single or multiplayer games
* The game will keep scores of a player



### GUI Mockups:

![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/final-project/images/mockup.png)

---

### Final Expected Tests:

* Test hero and monsters movement
* Test saving scores
* Test catching items
* Test shooting a projectile to kill monsters
* Test monster dies with collision with projectile
* Test hero dies
* Test hero wins
* Test achieves goal
* Test hero loses a life in collision with monster
* Test hero gains points when kills a monster
