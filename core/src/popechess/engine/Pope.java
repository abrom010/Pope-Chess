package popechess.engine;

public class Pope {
    // position should be in a 7x7 array
    Position position;
    boolean justMoved;

    public Pope() {
        position = new Position(3, 3);
    }
    
    public void moveUp() {
        position.j++;
    }

    public void moveDown() {
        position.j--;
    }

    public void moveLeft() {
        position.i--;
    }

    public void moveRight() {
        position.i++;
    }
}
