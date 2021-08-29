# TicTacToeAPI
This is a spring boot rest API for TicTacToe 

## Rules

The rules are described below :

- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
	- One player has three in a row, horizontally, vertically or diagonally
	- All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.


## How to install 

- clone this maven project 
- its a Java 8 project
- if in IDE, ensure lombok settings are enabled 
- goto cmd/terminal/IDE and run mvn clean install
- navigate to target folder and execute java -jar tictactoe-0.0.1-SNAPSHOT.jar


## End Points 
# POST: localhost:8080/newgame
- this will create a new game and return a unique gameId - 

# POST: localhost:8080/playgame
-inputs 
-    "gameId": <gameId>,
-    "player" : "X", // this should be either X or O , to mention which player make the move 
-    "position" : "8" // position value is valid between 0 - 9. this denotes the position of tictactoe board . please refer the image below for position values 

![Alt text](tic-tac-toe-board.png?raw=true "Tic Tac Toe")


## GET: localhost:8080/status/{gameId}
- provide the gameId in the URL to fetch the complete details of the game.


## Swagger UI 
http://localhost:8080/swagger-ui.html

