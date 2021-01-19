package popechess.engine;

public class Move {
    Position startingPosition;
    Position endingPosition;

    public Move(Position startingPosition, Position endingPosition) {
        this.startingPosition = startingPosition;
        this.endingPosition = endingPosition;
    }
}
