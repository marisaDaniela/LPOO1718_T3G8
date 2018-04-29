# LPOO 2017/2018 Turma 03 Grupo 08

 Final Project for the class Oriented Objects Programming Language of MIEIC at FEUP 

## Authors

* Leonor Ribeiro e Sousa Mendes de Freitas, up201207603, up201207603@fe.up.pt
* Marisa Daniela Quintal Oliveira, up201308594, up201308594@fe.up.pt

## Title: Haunted Aunios

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

 * **Knight:** Class for a Knight object, subclass of hero;

 * **Wizard:** Class for a Wizard object, subclass of hero;

 * **Soldier:** Class for a Soldier object, subclass of hero

#### Design of behavioural aspects:
![My image](https://github.com/marisaDaniela/LPOO1718_T3G8/blob/final-project/images/behavioural.png)

### Expected Design Patterns:
a escrever texto

### Main Funcionalities:

* The game will allow to players take a part in single or multiplayer games
* The game will keep scores of a player


### GUI Mockups:

#### Start

#### Instructions

#### Save

#### Game

#### GameOver

---

### Final Expected Tests:

* Test game over
* Test hero and monsters movement
* Test saving scores
* 

