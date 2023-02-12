package edu.touro.cs.mcon364;

import java.io.Serializable;

class T3_Model implements Serializable
{
    public enum PlayerValue {X, O, NONE}
    private PlayerValue currentPlayer = PlayerValue.X;

    private PlayerValue board[][] = new PlayerValue[3][3];

    public void makeMove(int row, int column){
        board[row][column] = getCurrentPlayer();
        if (isGameOver()){
            currentPlayer = PlayerValue.NONE;
            return; //stops the game
        }
        else if (currentPlayer == PlayerValue.X){ //switching turns
            currentPlayer = PlayerValue.O;
        }
        else if (currentPlayer == PlayerValue.O){
            currentPlayer = PlayerValue.X;
        }
        if (currentTurn == GameType.COMPUTER){
            computerTurn();
        }

    }
    public PlayerValue getCurrentPlayer(){

        return currentPlayer;
    }
    public PlayerValue getWinner(){
        for (int i = 0; i < 3; i++) {
            PlayerValue one = checkRow(i);
            if (one != PlayerValue.NONE){
                return one;
            }
        }
        for (int i = 0; i < 3; i++) {
            PlayerValue two = checkColumn(i);
            if (two != PlayerValue.NONE){
                return two;
            }
        }
        if (board[0][0] == board[1][1] && board[2][2] == board[1][1] && board[0][0] != null){
            return board[0][0];
        }
        if (board[0][2] == board[1][1] && board[2][0] == board[1][1] && board[1][1] != null){
            return board[2][0];
        }
        return PlayerValue.NONE;
    }
    private PlayerValue checkRow(int row){
        if (board[row][0] == board[row][1] && board[row][1] == board[row][2] && board[row][0] != null){
            return board[row][0];
        }
        return PlayerValue.NONE;
    }
    private PlayerValue checkColumn(int column){
        if (board[0][column] == board[1][column] && board[1][column] == board[2][column] && board[0][column] != null){
            return board[1][column];
        }
        return PlayerValue.NONE;
    }
    public boolean isGameOver() {
        if (getWinner() != PlayerValue.NONE){
            return true;
        }
        for (int i =0; i < 3; i++){
            for (int j =0; j<3; j++){
                if (board[i][j] == null || board[i][j] == PlayerValue.NONE){
                    return false;
                }
            }
        }
        return true;
    }

    public enum GameType {HUMAN, COMPUTER}
    private GameType currentTurn = GameType.HUMAN;

    public GameType currentTurn(){
        return currentTurn;
    }
    public void startGame( GameType gametype) {
        currentTurn = gametype;
    }


    public void computerTurn() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    board[i][j] = PlayerValue.O; //computer is O
                    currentPlayer = PlayerValue.X; //switches turns
                    return;
                }
            }
        }
    }

    public String getBoxes(int x, int i){
        if (board[x][i] == PlayerValue.X){
            return "X";
        }
        if (board[x][i] == PlayerValue.O){
            return "O";
        }
        if (board[x][i] == PlayerValue.NONE){
            return " ";
        }
        return null;
    }
}