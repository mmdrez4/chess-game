package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.Image;
import sample.Model.Board;

public class Queen extends Piece {

    public Queen(boolean isWhite) {
        super(isWhite);
        Image image;
        if(isWhite){
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/white_Queen.png"));
        }
        else{
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/Black_Queen.png"));
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
        if ((Math.abs(deltaRow) == Math.abs(deltaColumn)) || (deltaColumn == 0 || deltaRow == 0)) {
            return isNotThereObstacle(startRow, startColumn, endRow, deltaRow, deltaColumn, board);
        }
        return false;
    }

    public boolean isNotThereObstacle(int startRow, int startColumn, int endRow, int deltaRow, int deltaColumn, Board board) {
        if (deltaRow == 0 || deltaColumn == 0) {
            int displacement = (deltaRow != 0) ? deltaRow : deltaColumn;
            return Rook.isNotThereObstacle(startRow, startColumn, endRow, displacement, board);
        } else {
            int rowOrientation = (deltaRow > 0) ? 1 : -1;
            int columnOrientation = (deltaColumn > 0) ? 1 : -1;
            return Bishop.isNotThereObstacle(startRow, startColumn, deltaRow, rowOrientation, columnOrientation, board);
        }
    }

    @Override
    public String showPiece() {
        String piece = "Q";
        if (super.isWhite()) {
            piece += "w";
        } else {
            piece += "b";
        }
        return piece;
    }

}
