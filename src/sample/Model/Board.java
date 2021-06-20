package sample.Model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.Model.Pieces.*;

public class Board extends Pane {
    private final boolean WHITE = true;
    private final boolean BLACK = false;
    private static Position[][] spots = new Position[9][9];

    public Board() {
        super();
        initializeBoard();
    }

    public void initializeBoard() {
        spots[1][1] = new Position(new Rook(WHITE), 1, 1);
        spots[1][2] = new Position(new Knight(WHITE), 1, 2);
        spots[1][3] = new Position(new Bishop(WHITE), 1, 3);
        spots[1][4] = new Position(new Queen(WHITE), 1, 4);
        spots[1][5] = new Position(new King(WHITE), 1, 5);
        spots[1][6] = new Position(new Bishop(WHITE), 1, 6);
        spots[1][7] = new Position(new Knight(WHITE), 1, 7);
        spots[1][8] = new Position(new Rook(WHITE), 1, 8);
        spots[8][1] = new Position(new Rook(BLACK), 8, 1);
        spots[8][2] = new Position(new Knight(BLACK), 8, 2);
        spots[8][3] = new Position(new Bishop(BLACK), 8, 3);
        spots[8][4] = new Position(new Queen(BLACK), 8, 4);
        spots[8][5] = new Position(new King(BLACK), 8, 5);
        spots[8][6] = new Position(new Bishop(BLACK), 8, 6);
        spots[8][7] = new Position(new Knight(BLACK), 8, 7);
        spots[8][8] = new Position(new Rook(BLACK), 8, 8);

        for (int i = 1; i <= 8; i++) {
            spots[2][i] = new Position(new Pawn(WHITE), 2, i);
            spots[7][i] = new Position(new Pawn(BLACK), 7, i);
        }

        for (int i = 3; i <= 6; i++) {
            for (int j = 1; j <= 8; j++) {
                spots[i][j] = new Position(null, i, j);
            }
        }

    }

    public Position getSpot(int row, int column) {
        return spots[row][column];
    }

    public void setSpotsAfterMoving(int startRow, int startColumn, int endRow, int endColumn, Piece piece) {
        spots[endRow][endColumn].setPiece(spots[startRow][startColumn].getPiece());
        spots[startRow][startColumn].setPiece(piece);
    }

    public boolean isTherePiece(int row, int column) {
        return spots[row][column].getPiece() != null;
    }

    public void killEnemy(int row, int column, Game game) {
        spots[row][column].getPiece().setKilled();
        game.setIsGameOver(spots[row][column].getPiece());
        game.setKilledPieces(row, column, spots[row][column].getPiece());
    }


}
