# Ultimate Tic-Tac-Toe Game

**Rules**

1. Each turn, you mark one of the small squares.
2. When you get three in a row on a small board, you've won that board.
3. To win the game, you need to win three small boards in a row.
4. You don't get to pick which of the nine boards to play on. That's determine by your opponent's previous move. Whichever square he picks, that's the board
   you must play in next.
5. If your opponent sends you to a board that's already been won, you must play in that board in there is a free square.
6. If your opponent sends you to a board that's full, you can play anywhere on the board.


##Running the Application

The main entry point of the Application is `com.ultimatetictactoe.UltimateTicTacToe`.

To run the program, first build the application. (Note: please use the gradle wrapper).
```
./gradlew clean build
```

And then run the whole application.
```
./gradlew run
```
