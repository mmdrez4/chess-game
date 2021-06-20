package sample.Model;//import Pieces.Piece;

import sample.Model.Pieces.Piece;

public class Move {
    private User player;
    private Position start;
    private Position end;

    public Move(User player, Piece movedPiece, int startRow, int startColumn, int endRow, int endColumn, Piece killedPiece) {
        this.player = player;
        this.start = new Position(movedPiece, startRow, startColumn);
        this.end = new Position(killedPiece, endRow, endColumn);
    }

    @Override
    public String toString() {
        return start.getPiece().showPiece() + " " + start.getRow() + ","
                + start.getColumn() + " to " + end.getRow() + "," +
                end.getColumn();
    }

    public User getPlayer() {
        return player;
    }

    public Position getStart() {
        return start;
    }

    public Position getEnd() {
        return end;
    }
}
