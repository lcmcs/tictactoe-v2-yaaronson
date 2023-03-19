package edu.touro.cs.mcon364;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Objects;

public class T3_Frame extends JFrame {
    class EventHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new T3_Frame();
        }
    }

    private JButton one;
    private JButton save;
    private JButton restore;
    private JLabel statusBar;
    private JCheckBox opponent;
    public  JButton [][] buttons1 = new JButton[3][3];
    private T3_Model ticTacToe = new T3_Model();

    T3_Frame() {
        super("Tic Tac Toe GUI ");
        add(new DrawCanvas(), BorderLayout.CENTER);
        add(statusBar = new JLabel("Status: "), BorderLayout.SOUTH); // status bar

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        opponent = new JCheckBox("Computer Opponent ");
        one = new JButton("Click Here For New Game");
        save = new JButton("Save");
        restore = new JButton("Restore");
        northPanel.add(opponent);
        northPanel.add(one);
        northPanel.add(save);
        save.addActionListener(
                e -> {
                    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("t3.bin"))) {
                        output.writeObject(ticTacToe);
                    } catch (FileNotFoundException ex ) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
        northPanel.add(restore);
        restore.addActionListener(
                e -> {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("t3.bin"))) {
                        ticTacToe = (T3_Model) ois.readObject();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File is not here");
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            buttons1[i][j].setText(ticTacToe.getBoxes(i,j));
                            if (Objects.equals(buttons1[i][j].getText(), "X") || Objects.equals(buttons1[i][j].getText(), "O"))
                            buttons1[i][j].setEnabled(false);
                        }
                    }
                });
        add(northPanel, BorderLayout.NORTH);

        EventHandler events = new EventHandler();
        one.addActionListener(events);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class DrawCanvas extends JPanel {
        class EventHandler implements ActionListener {
            int x;
            int y;

            EventHandler(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();

                if (opponent.isSelected()){
                    ticTacToe.startGame(T3_Model.GameType.COMPUTER);
                } else {ticTacToe.startGame(T3_Model.GameType.HUMAN);}
                ticTacToe.makeMove(x, y);
                getBoard();
                if (ticTacToe.isGameOver()) {
                    statusBar.setText(ticTacToe.getWinner() + " WON!! ");
                } else {
                    statusBar.setText(" it is " + ticTacToe.getCurrentPlayer() + "'s turn");
                }
                if (ticTacToe.isGameOver()) {
                    opponent.setEnabled(true);
                } else {
                    opponent.setEnabled(false);
                }

            }

            public void getBoard(){
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++){
                     if (ticTacToe.getBoxes(i,j) == "X"){ //if in model it is x then make it x here too
                         buttons1[i][j].setText("X");
                         buttons1[i][j].setEnabled(false);
                     }
                     if (ticTacToe.getBoxes(i,j) == "O"){
                            buttons1[i][j].setText("O");
                            buttons1[i][j].setEnabled(false);
                        }
                    }
                }
            }
        }



        DrawCanvas() {
            setBackground(Color.BLUE);
            JButton button;
            setLayout(new GridLayout(3, 3, 9, 9));
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    add(button = new JButton());
                    buttons1[i][j] = button;
                    button.addActionListener(new EventHandler(i, j));
                }
            }
        }
    }
}