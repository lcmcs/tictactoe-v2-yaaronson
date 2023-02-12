package edu.touro.cs.mcon364;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    T3_Model game = new T3_Model();

    @Test
    void getWinnerRow() {
        game.makeMove(0,0);
        game.makeMove(2,0);
        game.makeMove(0,1);
        game.makeMove(2,2);
        game.makeMove(0,2);
        Assertions.assertTrue(game.isGameOver());
        Assertions.assertEquals(game.getWinner(), T3_Model.PlayerValue.X);
    }
    @Test
    void getWinnerColumn() {
        game.makeMove(0,0);
        game.makeMove(0,1);
        game.makeMove(1,0);
        game.makeMove(0,2);
        game.makeMove(2,0);
        Assertions.assertTrue(game.isGameOver());
        Assertions.assertEquals(game.getWinner(), T3_Model.PlayerValue.X);
    }
    @Test
    void getWinnerDiagonal1() {
        game.makeMove(0,0);
        game.makeMove(0,1);
        game.makeMove(1,1);
        game.makeMove(0,2);
        game.makeMove(2,2);
        Assertions.assertTrue(game.isGameOver());
        Assertions.assertEquals(game.getWinner(), T3_Model.PlayerValue.X);
    }
    @Test
    void getWinnerDiagonal2() {
        game.makeMove(0,2);
        game.makeMove(0,1);
        game.makeMove(1,1);
        game.makeMove(0,0);
        game.makeMove(2,0);
        Assertions.assertTrue(game.isGameOver());
        Assertions.assertEquals(game.getWinner(), T3_Model.PlayerValue.X);
    }
    @Test
    void isGameOver() {
        game.makeMove(0,0);
        game.makeMove(0,1);
        game.makeMove(0,2);
        game.makeMove(1,1);
        game.makeMove(1,0);
        game.makeMove(1,2);
        game.makeMove(2,1);
        game.makeMove(2,0);
        game.makeMove(2,2);
        Assertions.assertTrue(game.isGameOver());
    }

    @Test
    void getCurrentPlayer() {
        Assertions.assertEquals(T3_Model.PlayerValue.X,game.getCurrentPlayer());
        game.makeMove(0,2);
        Assertions.assertEquals(T3_Model.PlayerValue.O,game.getCurrentPlayer());
    }
    @Test
    void computerTurn() {
    game.computerTurn();
    assertEquals("O",game.getBoxes(0,0));

    }
    @Test
    void getBoxes() {
        game.makeMove(0,0);
        assertEquals("X",game.getBoxes(0,0));
    }
}