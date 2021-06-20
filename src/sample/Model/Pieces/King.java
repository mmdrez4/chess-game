package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Model.Board;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
        Image image;
        if(isWhite){
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/white_King.png"));
        }
        else{
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/Black_King.png"));
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
        int deltaRow = Math.abs(endRow - startRow);
        int deltaColumn = Math.abs(endColumn - startColumn);
        return deltaRow <= 1 && deltaColumn <= 1;
    }

    @Override
    public String showPiece() {
        String piece = "K";
        if (super.isWhite()) {
            piece += "w";
        } else {
            piece += "b";
        }
        return piece;
    }

    @Override
    public String getType() {
        return "Pieces.King";
    }
}
