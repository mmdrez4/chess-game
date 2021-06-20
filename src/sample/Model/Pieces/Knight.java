package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.Image;
import sample.Model.Board;

public class Knight extends Piece{

    public Knight(boolean isWhite) {
        super(isWhite);
        Image image;
        if(isWhite){
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/white_Knight.png"));
        }
        else{
            image = new Image(getClass().getResourceAsStream("ChessPiecesImage/Black_Knight.png"));
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
        return deltaRow * deltaColumn == 2;
    }

    @Override
    public String showPiece(){
        String piece = "N";
        if (super.isWhite()){
            piece += "w";
        }else {
            piece += "b";
        }
        return piece;
    }

}
