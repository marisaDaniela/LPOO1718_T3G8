# LPOO 2017/2018 Turma 03 Grupo 08

 Final Project for the class Oriented Objects Programming Language of MIEIC at FEUP 

## Authors

* Leonor Ribeiro e Sousa Mendes de Freitas, up201207603, up201207603@fe.up.pt
* Marisa Daniela Quintal Oliveira, up201308594, up201308594@fe.up.pt

## Title: Aunios

a escrever texto

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

 * **Item:** Class to represents an item of the game;

 * **Scores:** Class to represents the scores of the game;
 
 * **Map:** Class to represent the differents levels of the game;
 
 * **Dialog Box:** Class to represent the story during the game;
 

#### Design of behavioural aspects:
![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/final-project/images/behavioural.png)

### Expected Design Patterns:

* **MVC (Model View Controller):** is the most relevant design pattern, and it is on this principle that the architecture of the program is based. Allows a total isolation between logic and UI components. The View package ensures the UI elements, the package Model stores the data and the functions to manipulate the data and the package Controller receives View statements and commands the Model functions.

* **Singleton:** to ensures that only one instance of a class is created and provide a global access point to the object.
* **State:** the characters may have differents states that limits their actions. Can be done in every moment of the game.

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
