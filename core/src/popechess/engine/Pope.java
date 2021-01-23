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

    public Position getPosition() {
        return position;
    }
    
    public void moveUp() {
        if(position.j-1<0) return;
        position.j--;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveDown() {
        if(position.j+1>6) return;
        position.j++;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveLeft() {
        if(position.i-1<0) return;
        position.i--;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveRight() {
        if(position.i+1>6) return;
        position.i++;
        this.protectedTiles = calculateProtectedTiles();
    }

    public List<Position> calculateProtectedTiles() {
        List<Position> tiles = new ArrayList<>();
        tiles.add(new Position(this.position.i, this.position.j));
        tiles.add(new Position(this.position.i + 1, this.position.j));
        tiles.add(new Position(this.position.i, this.position.j + 1));
        tiles.add(new Position(this.position.i + 1, this.position.j + 1));
        return tiles;
    }

    public List<Position> getProtectedTiles() {
        return protectedTiles;
    }

    public boolean isTileProtected(Position position) {
        for( Position p : this.protectedTiles) {
            if(p.i == position.i && p.j == position.j) return true;
        }
        return false;
    }
}
