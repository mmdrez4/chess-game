package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.Image;
import sample.Model.Board;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
        Image image;
        if(isWhite){
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/white_Pawn.png"));
        }
        else{
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/Black_Pawn.png"));
        }
        imageView.setImage(image);
        imageView.fitHeightProperty();
        imageView.fitWidthProperty();
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
    }

    @Override
    public boolean isMovementValid(int startRow, int startColumn, int endRow, int endColumn, Board board) {
        if (!(super.isMovementValid(startRow, startColumn, endRow, endColumn, board)))
            return false;
        if (!(licensedArea(startRow, endRow)))
            return false;

        int deltaRow = Math.abs(endRow - startRow);
        int deltaColumn = Math.abs(endColumn - startColumn);

        if (isFirstTime(startRow)) {
            if (canGoStraight(startRow, startColumn, board) && (deltaRow == 1 || deltaRow == 2) && deltaColumn == 0) {
                return true;
            } else {
                return canKillDiagonal(deltaRow, deltaColumn, endRow, endColumn, board);
            }
        } else {
            if (canGoStraight(startRow, startColumn, board) && deltaRow == 1 && deltaColumn == 0) {
                return true;
            } else {
                return canKillDiagonal(deltaRow, deltaColumn, endRow, endColumn, board);
            }
        }
    }

    public boolean isFirstTime(int startRow){
        if (super.isWhite()) {
            return startRow == 2;
        } else {
            return startRow == 7;
        }
    }

    public boolean licensedArea(int startRow, int endRow) {
        if (super.isWhite()) {
            return endRow > 2 && endRow > startRow;
        } else {
            return endRow < 7 && endRow < startRow;
        }
    }

    public boolean canGoStraight(int startRow, int startColumn, Board board) {
        if (super.isWhite())
            return (board.getSpot(startRow + 1, startColumn).getPiece() == null);
        else {
            return (board.getSpot(startRow - 1, startColumn).getPiece() == null);
        }
    }


    public boolean canKillDiagonal(int deltaRow, int deltaColumn, int endRow, int endColumn, Board board) {
        if (board.getSpot(endRow, endColumn).getPiece() == null) return false;
        if (super.isWhite()) {
            return !board.getSpot(endRow, endColumn).getPiece().isWhite() && (deltaRow == 1) && (deltaColumn == 1);
        } else {
            return board.getSpot(endRow, endColumn).getPiece().isWhite() && (deltaRow == 1) && (deltaColumn == 1);
        }
    }

    @Override
    public String showPiece() {
        String piece = "P";
        if (super.isWhite()) {
            piece += "w";
        } else {
            piece += "b";
        }
        return piece;
    }


}
