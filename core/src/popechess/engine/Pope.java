package popechess.engine;

import java.util.ArrayList;
import java.util.List;

public class Pope {
    // position should be in a 7x7 array
    Position position;
    boolean justMoved;
    List<Position> protectedTiles;

    public Pope() {
        this.position = new Position(3, 3);
        this.protectedTiles = calculateProtectedTiles();
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

    public List<Position> calculateProtectedTiles() {
        List<Position> tiles = new ArrayList<>();
        tiles.add(new Position(this.position.i, this.position.j));
        tiles.add(new Position(this.position.i + 1, this.position.j));
        tiles.add(new Position(this.position.i, this.position.j + 1));
        tiles.add(new Position(this.position.i + 1, this.position.j + 1));
        return tiles;
    }

    public boolean isTileProtected(Position position) {
        for( Position p : this.protectedTiles) {
            if(p.i == position.i && p.j == position.j) return true;
        }
        return false;
    }
}
