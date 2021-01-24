package popechess.engine;

import java.util.ArrayList;
import java.util.List;

public class Pope {
    // position should be in a 7x7 array
    Position position;
    public Position previousPosition;
    public boolean justMoved;
    List<Position> protectedTiles;

    public Pope() {
        this.position = new Position(3, 3);
        this.protectedTiles = calculateProtectedTiles();
    }

    public Position getPosition() {
        return position;
    }

    public boolean canMoveUp() {
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i, position.j+1);
        System.out.println("Previous position: "+previousPosition.toString());
        System.out.println("Requested position: "+moveTo.toString());
        return !justMoved || previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }

    public boolean canMoveDown() {
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i, position.j-1);
        System.out.println("Previous position: "+previousPosition.toString());
        System.out.println("Requested position: "+moveTo.toString());
        return !justMoved || previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }

    public boolean canMoveLeft() {
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i-1, position.j);
        System.out.println("Previous position: "+previousPosition.toString());
        System.out.println("Requested position: "+moveTo.toString());
        return !justMoved || previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }

    public boolean canMoveRight() {
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i+1, position.j);
        System.out.println("Previous position: "+previousPosition.toString());
        System.out.println("Requested position: "+moveTo.toString());
        return !justMoved || previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }
    
    public void moveUp() {
        if(!canMoveUp()) return;
        if(position.j-1<0) return;
        this.previousPosition = new Position(position.i, position.j);
        this.justMoved = true;
        position.j--;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveDown() {
        if(!canMoveDown()) return;
        if(position.j+1>6) return;
        this.previousPosition = new Position(position.i, position.j);
        this.justMoved = true;
        position.j++;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveLeft() {
        if(!canMoveLeft()) return;
        if(position.i-1<0) return;
        this.previousPosition = new Position(position.i, position.j);
        this.justMoved = true;
        position.i--;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveRight() {
        if(!canMoveRight()) return;
        if(position.i+1>6) return;
        this.previousPosition = new Position(position.i, position.j);
        this.justMoved = true;
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
