package popechess.engine;

import java.util.ArrayList;
import java.util.List;

import popechess.game.Main;

public class Pope {
    // position should be in a 7x7 array
    Board board;
    Position position;
    public Position previousPosition;
    List<Position> protectedTiles;

    public Pope(Board board) {
        this.board = board;
        this.position = new Position(3, 3);
        this.protectedTiles = calculateProtectedTiles();
    }

    public Position getPosition() {
        return position;
    }

    // canMoveDown() and canMoveUp() ARE FUNCTIONAL BUT ARE INVERSED AND VERY UNCLEAR/CONFUSING
    public boolean canMoveDown() {
        if(position.j+1>6) return false;
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i, position.j+1);
        return previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }

    public boolean canMoveUp() {
        if(position.j-1<0) return false;
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i, position.j-1);
        return previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }

    public boolean canMoveLeft() {
        if(position.i-1<0) return false;
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i-1, position.j);
        return previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }

    public boolean canMoveRight() {
        if(position.i+1>6) return false;
        if(previousPosition==null) return true;
        Position moveTo = new Position(position.i+1, position.j);
        return previousPosition.i != moveTo.i || previousPosition.j != moveTo.j;
    }
    
    public void moveUp() {
        if(!canMoveUp()) return;
        this.previousPosition = new Position(position.i, position.j);
        position.j--;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveDown() {
        if(!canMoveDown()) return;
        this.previousPosition = new Position(position.i, position.j);
        position.j++;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveLeft() {
        if(!canMoveLeft()) return;
        this.previousPosition = new Position(position.i, position.j);
        position.i--;
        this.protectedTiles = calculateProtectedTiles();
    }

    public void moveRight() {
        if(!canMoveRight()) return;
        this.previousPosition = new Position(position.i, position.j);
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
        if(board.getTileAtPosition(position).isPieceKing()) return false;
        for( Position p : this.protectedTiles) {
            if(p.i == position.i && p.j == position.j) return true;
        }
        return false;
    }
}
