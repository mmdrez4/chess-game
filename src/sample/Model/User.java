package sample.Model;

public class User implements Comparable<User> {
    private String username;
    private String password;
    private boolean isWhite;
    private int undoNumber;
    private int points;
    private int wins;
    private int draws;
    private int losses;

    private boolean isTurn;

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
        this.isTurn = false;
        this.points = 0;
        this.wins = 0;
        this.draws = 0;
        this.losses = 0;
    }

    public void addPoints(int point) {
        this.points += point;
    }

    public int getPoints() {
        return points;
    }

    public int getWins() {
        return wins;
    }

    public void addWins() {
        this.wins++;
    }

    public int getDraws() {
        return draws;
    }

    public void addDraws() {
        this.draws++;
    }

    public int getLosses() {
        return losses;
    }

    public void addLosses() {
        this.losses++;
    }

    public static String getColor(User player) {
        if (player.isWhite) {
            return "white";
        } else {
            return "black";
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean canUndo() {
        return undoNumber > 0;
    }

    public void decreaseUndoNumber() {
        this.undoNumber--;
    }

    public void setUndoNumber(int undoNumber) {
        this.undoNumber = undoNumber;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public int getUndoNumber() {
        return undoNumber;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    @Override
    public int compareTo(User user) {
        if (user.points != this.points) {
            return user.points - this.points;
        } else if (user.wins != this.wins) {
            return user.wins - this.wins;
        } else if (user.draws != this.draws) {
            return user.draws - this.draws;
        } else if (this.losses != user.losses) {
            return this.losses - user.losses;
        } else {
            return this.username.compareTo(user.username);
        }
    }

}
