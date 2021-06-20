package sample.Model;

import sample.Model.Pieces.Piece;

import java.util.*;

public class Game {
    private Board board;
    private Map<Piece, String> killedPieces;
    private User currentTurn;
    private ArrayList<Move> movesPlayed;
    private User player1;
    private User player2;
    private boolean hasMoved;
    private boolean hasUndoBefore;
    private int limit;
    private boolean isGameOver;
    private User winner;

    public Game(User player1, User player2, int limit, int undoNo) {
        this.player1 = player1;
        this.player1.setWhite(true);
        this.player2 = player2;
        this.player2.setWhite(false);
        this.player1.setTurn(true);
        this.player2.setTurn(false);
        this.player1.setUndoNumber(undoNo);
        this.player2.setUndoNumber(undoNo);
        currentTurn = this.player1;
        this.killedPieces = new LinkedHashMap<>();
        this.movesPlayed = new ArrayList<>();
        board = new Board();
        this.isGameOver = false;
        this.limit = (limit == 0) ? -1 : limit;
    }

    public User getPlayer1() {
        return player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public User forfeit() {
        if (currentTurn.equals(player1)) {
            player1.addPoints(-1);
            player2.addPoints(2);
            player1.addLosses();
            player2.addWins();
            return player2;
        } else {
            player1.addPoints(2);
            player2.addPoints(-1);
            player1.addWins();
            player2.addLosses();
            return player1;
        }
    }

    public void showMovesPlayed() {
        for (Move playedMove : movesPlayed) {
            if (playedMove.getPlayer() == currentTurn) {
                if (playedMove.getEnd().getPiece() == null) {
                    System.out.println(playedMove.toString());
                } else {
                    System.out.println(playedMove.toString() + " destroyed " + playedMove.getEnd().getPiece().showPiece());
                }
            }
        }
    }

    public void showAllMovesPlayed() {
        for (Move playedMove : movesPlayed) {
            if (playedMove.getEnd().getPiece() == null) {
                System.out.println(playedMove.toString());
            } else {
                System.out.println(playedMove.toString() + " destroyed " + playedMove.getEnd().getPiece().showPiece());
            }
        }
    }

    public void undo() {
        int lastIndex = movesPlayed.size() - 1;
        Piece killedPieceInLastMove = movesPlayed.get(lastIndex).getEnd().getPiece();
        this.getBoard().setSpotsAfterMoving(movesPlayed.get(lastIndex).getEnd().getRow(), movesPlayed.get(lastIndex).getEnd().getColumn()
                , movesPlayed.get(lastIndex).getStart().getRow(), movesPlayed.get(lastIndex).getStart().getColumn(), killedPieceInLastMove);
        if (movesPlayed.get(lastIndex).getEnd().getPiece() != null) {
            killedPieces.remove(movesPlayed.get(lastIndex).getEnd().getPiece());
        }
        if (movesPlayed.size() > 0)
            movesPlayed.remove(lastIndex);
        currentTurn.decreaseUndoNumber();
        hasMoved = false;
        hasUndoBefore = true;
    }

    public boolean isHasUndoBefore() {
        return hasUndoBefore;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void setHasUndoBefore(boolean hasUndoBefore) {
        this.hasUndoBefore = hasUndoBefore;
    }

    public void changeTurn() {
        if (this.player1.isTurn()) {
            player1.setTurn(false);
            player2.setTurn(true);
            currentTurn = player2;
        } else {
            player1.setTurn(true);
            player2.setTurn(false);
            currentTurn = player1;
        }
        hasMoved = false;
        hasUndoBefore = false;
    }

    public int getLimit() {
        return limit;
    }

    public void decreaseLimit() {
        this.limit--;
    }

    public void setMovesPlayed(User player, Piece movedPiece, int startRow, int startColumn, int endRow, int endColumn, Piece killedPiece) {
        this.movesPlayed.add(new Move(player, movedPiece, startRow, startColumn, endRow, endColumn, killedPiece));
        hasMoved = true;
        moveInBoard(startRow, startColumn, endRow, endColumn);
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void moveInBoard(int startRow, int startColumn, int endRow, int endColumn) {
        this.board.setSpotsAfterMoving(startRow, startColumn, endRow, endColumn, null);
        hasMoved = true;
    }

    public void setKilledPieces(int row, int column, Piece killedPiece) {
        String spot = Integer.toString(row) + "," + Integer.toString(column);
        killedPieces.put(killedPiece, spot);
    }

    public User getCurrentTurn() {
        return currentTurn;
    }

    public Board getBoard() {
        return board;
    }

    public void setIsGameOver(Piece piece) {
        if (piece.getType().equals("Pieces.King")) {
            isGameOver = true;
            if (piece.isWhite() == player1.isWhite()) {
                winner = player2;
            } else {
                winner = player1;
            }
        }
    }

    public void setIsGameOver(boolean isOver) {
        this.isGameOver = isOver;
    }

    public boolean isGameOver() {
        return isGameOver;
    }


    public User getWinner() {
        return winner;
    }


}
