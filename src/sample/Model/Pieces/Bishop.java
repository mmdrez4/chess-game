package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.Image;
import sample.Model.Board;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
        Image image;
        if (isWhite) {
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/white_Bishop.png"));
        } else {
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/Black_Bishop.png"));
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
        int deltaRow = endRow - startRow;
        int deltaColumn = endColumn - startColumn;
        int rowOrientation = (deltaRow > 0) ? 1 : -1;
        int columnOrientation = (deltaColumn > 0) ? 1 : -1;

        if (Math.abs(deltaRow) == Math.abs(deltaColumn)) {
            return isNotThereObstacle(startRow, startColumn, deltaRow, rowOrientation, columnOrientation, board);
        }
        return false;
    }

    public static boolean isNotThereObstacle(int startRow, int startColumn, int deltaRow, int rowOrientation, int columnOrientation, Board board) {
        for (int i = 1; i < Math.abs(deltaRow); i++) {
            if (board.getSpot(startRow + rowOrientation * i, startColumn + columnOrientation * i).getPiece() != null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String showPiece() {
        String piece = "B";
        if (super.isWhite()) {
            piece += "w";
        } else {
            piece += "b";
        }
        return piece;
    }

}

