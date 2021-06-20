package sample.Model.Pieces;//package Pieces;

import javafx.scene.image.ImageView;
import sample.Model.Board;

public class Piece {

    private boolean isWhite;
    private boolean isKilled;
    protected ImageView imageView = new ImageView();

    public Piece(boolean isWhite) {
        this.isKilled = false;
        this.isWhite = isWhite;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public static boolean isInsideArea(int row, int column) {
        return row <= 8 && row >= 1 && column <= 8 && column >= 1;
    }

    public static boolean isNotTheSamePosition(int startRow, int startColumn, int endRow, int endColumn) {
        return startRow != endRow || startColumn != endColumn;
    }

    public boolean isMovementValid(int startRow, int startColumn, int endRow, int endColumn, Board board){
        return (isInsideArea(endRow, endColumn)) && isNotTheSamePosition(startRow, startColumn, endRow, endColumn);
    }

    public void setKilled() {
        isKilled = true;
    }

    public String showPiece(){
        String piece = "X";
        if (this.isWhite()){
            piece += "w";
        }else {
            piece += "b";
        }
        return piece;
    }


    public String getType(){
        return "Pieces.Piece";
    }




}
