package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.Image;
import sample.Model.Board;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
        Image image;
        if(isWhite){
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/white_Rook.png"));
        }
        else{
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/Black_Rook.png"));
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
        if (deltaColumn == 0 || deltaRow == 0) {
            int displacement = (deltaRow != 0) ? deltaRow : deltaColumn;
            return isNotThereObstacle(startRow, startColumn, endRow, displacement, board);
        }
        return false;
    }

    public static boolean isNotThereObstacle(int startRow, int startColumn, int endRow, int displacement, Board board) {
        String displaced = "column";
        if (startRow != endRow)
            displaced = "row";
        int orientation = (displacement > 0) ? 1 : -1;
        for (int i = 1; i < Math.abs(displacement); i++) {
            if (displaced.equals("row")) {
                if (board.getSpot(startRow + orientation * i, startColumn).getPiece() != null) {
                    return false;
                }
            } else{
                if (board.getSpot(startRow , startColumn + orientation * i).getPiece() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String showPiece() {
        String piece = "R";
        if (super.isWhite()) {
            piece += "w";
        } else {
            piece += "b";
        }
        return piece;
    }

}
