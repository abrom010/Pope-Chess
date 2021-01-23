package popechess.engine;

import java.util.ArrayList;
import java.util.List;

import static popechess.engine.Piece.EMPTY;
import static popechess.engine.Piece.BLACK_ROOK;
import static popechess.engine.Piece.BLACK_KNIGHT;
import static popechess.engine.Piece.BLACK_BISHOP;
import static popechess.engine.Piece.BLACK_QUEEN;
import static popechess.engine.Piece.BLACK_KING;
import static popechess.engine.Piece.BLACK_PAWN;
import static popechess.engine.Piece.WHITE_ROOK;
import static popechess.engine.Piece.WHITE_KNIGHT;
import static popechess.engine.Piece.WHITE_BISHOP;
import static popechess.engine.Piece.WHITE_QUEEN;
import static popechess.engine.Piece.WHITE_KING;
import static popechess.engine.Piece.WHITE_PAWN;


public class Board {
    public Tile[][] state;
    public Pope pope;

    public Board() {
        this.state = new Tile[][] {
            {new Tile(WHITE_ROOK), new Tile(WHITE_KNIGHT), new Tile(WHITE_BISHOP), new Tile(WHITE_QUEEN), new Tile(WHITE_KING), new Tile(WHITE_BISHOP), new Tile(WHITE_KNIGHT), new Tile(WHITE_ROOK)},
            {new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN), new Tile(WHITE_PAWN)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
            {new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN), new Tile(BLACK_PAWN)},
            {new Tile(BLACK_ROOK), new Tile(BLACK_KNIGHT), new Tile(BLACK_BISHOP), new Tile(BLACK_QUEEN), new Tile(BLACK_KING), new Tile(BLACK_BISHOP), new Tile(BLACK_KNIGHT), new Tile(BLACK_ROOK)}
        };

//        this.state = new Tile[][] {
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(BLACK_ROOK), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)},
//            {new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY), new Tile(EMPTY)}
//        };

        pope = new Pope();
    }

    public Board(Tile[][] state) {
        this.state = state;
        pope = new Pope();
    }

    public Tile getTileAtPosition(Position position) {
        if(position.i < 0 || position.i > 7 || position.j < 0 || position.j > 7) return null;
        return  state[position.j][position.i];
    }

    public Piece getPieceAtPosition(Position position) {
        return getTileAtPosition(position).getPiece();
    }

    public List<Position> getPiecePossiblePositions(Position piecePosition) {
        Piece piece = getPieceAtPosition(piecePosition);
        // test
        Tile tile = getTileAtPosition(piecePosition);
        System.out.println(tile.getPieceName());
        // test
        switch(piece) {
            case WHITE_ROOK:
                return getWhiteRookPossiblePositions(piecePosition);
            case WHITE_KNIGHT:
                return getWhiteKnightPossiblePositions(piecePosition);
            case WHITE_BISHOP:
                return getWhiteBishopPossiblePositions(piecePosition);
            case WHITE_QUEEN:
                return getWhiteQueenPossiblePositions(piecePosition);
            case WHITE_KING:
                return getWhiteKingPossiblePositions(piecePosition);
            case WHITE_PAWN:
                return getWhitePawnPossiblePositions(piecePosition);
            case BLACK_ROOK:
                return getBlackRookPossiblePositions(piecePosition);
            case BLACK_KNIGHT:
                return getBlackKnightPossiblePositions(piecePosition);
            case BLACK_BISHOP:
                return getBlackBishopPossiblePositions(piecePosition);
            case BLACK_QUEEN:
                return getBlackQueenPossiblePositions(piecePosition);
            case BLACK_KING:
                return getBlackKingPossiblePositions(piecePosition);
            case BLACK_PAWN:
                return getBlackPawnPossiblePositions(piecePosition);
            default:
                return new ArrayList<Position>();
        }
    }

    private List<Position> getBlackPawnPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        if(piecePosition.j==0) return null;
        int i1 = piecePosition.i;
        int j1 = piecePosition.j - 1;
        int j2 = j1 - 1;

        Position moveForwardOnce = new Position(i1,j1);
        Piece piece1 = this.getPieceAtPosition(moveForwardOnce);
        if(piece1 == EMPTY) {
            possiblePositions.add(moveForwardOnce);
        }

        if(piecePosition.j == 6) {
            Position moveForwardTwice = new Position(i1,j2);
            Piece piece2 = this.getPieceAtPosition(moveForwardTwice);
            if(piece2 == EMPTY && piece1 == EMPTY) {
                possiblePositions.add(moveForwardTwice);
            }
        }

        int i2 = i1 - 1;
        int i3 = i1 + 1;
        Position attackLeft = new Position(i2, j1);
        if(attackLeft.i>0) {
            Tile tile3 = this.getTileAtPosition(attackLeft);
            Piece piece3 = tile3.getPiece();
            if(piece3 != EMPTY && piece3 != WHITE_KING && tile3.isPieceWhite() && !pope.isTileProtected(attackLeft)) {
                possiblePositions.add(attackLeft);
            }
        }


        Position attackRight = new Position(i3, j1);
        if(attackRight.i<7) {
            Tile tile4 = this.getTileAtPosition(attackRight);
            Piece piece4 = tile4.getPiece();
            if(piece4 != EMPTY && piece4 != WHITE_KING && tile4.isPieceWhite() && !pope.isTileProtected(attackRight)) {
                possiblePositions.add(attackRight);
            }
        }

        return possiblePositions;
    }

    private List<Position> getBlackKingPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        int i = piecePosition.i;
        int j = piecePosition.j;
        int iLeft = i-1;
        int iRight = i+1;
        int jUp = j+1;
        int jDown = j-1;

        if(iLeft >= 0) {
            // move side
            {
                Position position = new Position(iLeft, j);
                Tile tile = getTileAtPosition(position);
                if (tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if (!pope.isTileProtected(position) && tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move up
            if(jUp < 8) {
                Position position = new Position(iLeft,jUp);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move down
            if(jDown >= 0) {
                Position position = new Position(iLeft,jDown);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
        }
        if(iRight < 8) {
            // move side
            {
                Position position = new Position(iRight,j);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move up
            if(jUp < 8) {
                Position position = new Position(i,jUp);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move down
            if(jDown >= 0) {
                Position position = new Position(iRight,jDown);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
        }

        // move up
        if(jUp < 8) {
            Position position = new Position(iRight,jUp);
            Tile tile = getTileAtPosition(position);
            if(tile.isEmpty()) {
                possiblePositions.add(position);
            } else {
                if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                    possiblePositions.add(position);
                }
            }
        }
        // move down
        if(jDown >= 0) {
            Position position = new Position(i,jDown);
            Tile tile = getTileAtPosition(position);
            if(tile.isEmpty()) {
                possiblePositions.add(position);
            } else {
                if(!pope.isTileProtected(position) && tile.isPieceWhite()) {
                    possiblePositions.add(position);
                }
            }
        }
        return possiblePositions;
    }

    private List<Position> getBlackQueenPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        possiblePositions.addAll(getBlackBishopPossiblePositions(piecePosition));
        possiblePositions.addAll(getBlackRookPossiblePositions(piecePosition));
        return possiblePositions;
    }

    private List<Position> getBlackBishopPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();

        // bottom left
        int i = piecePosition.i;
        int j = piecePosition.j;
        while(--i >= 0 && --j >= 0 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i >= 0 && j >= 0 && getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        // bottom right
        i = piecePosition.i;
        j = piecePosition.j;
        while(++i < 8 && --j >= 0 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i < 8 && j >= 0 && getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        // top left
        i = piecePosition.i;
        j = piecePosition.j;
        while(--i >= 0 && ++j < 8 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i >= 0 && j < 8 && getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        // top right
        i = piecePosition.i;
        j = piecePosition.j;
        while(++i < 8 && ++j < 8 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i < 8 && j < 8 && getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        return possiblePositions;
    }

    private List<Position> getBlackKnightPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }

    private List<Position> getBlackRookPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        int i = piecePosition.i;
        while(--i >= 0 && getTileAtPosition(new Position(i,piecePosition.j)).isEmpty()) {
            possiblePositions.add(new Position(i,piecePosition.j));
        }
        if(i >= 0 && getTileAtPosition(new Position(i,piecePosition.j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,piecePosition.j))) {
                possiblePositions.add(new Position(i, piecePosition.j));
            }
        }
        i = piecePosition.i;
        while(++i < 8 && getTileAtPosition(new Position(i,piecePosition.j)).isEmpty()) {
            possiblePositions.add(new Position(i,piecePosition.j));
        }
        if(i < 8 && getTileAtPosition(new Position(i,piecePosition.j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,piecePosition.j))) {
                possiblePositions.add(new Position(i, piecePosition.j));
            }
        }
        int j = piecePosition.j;
        while(--j >= 0 && getTileAtPosition(new Position(piecePosition.i,j)).isEmpty()) {
            possiblePositions.add(new Position(piecePosition.i,j));
        }
        if(j >= 0 && getTileAtPosition(new Position(piecePosition.i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(piecePosition.i,j))) {
                possiblePositions.add(new Position(piecePosition.i,j));
            }
        }
        j = piecePosition.j;
        while(++j < 8 && getTileAtPosition(new Position(piecePosition.i,j)).isEmpty()) {
            possiblePositions.add(new Position(piecePosition.i,j));
        }
        if(j < 8 && getTileAtPosition(new Position(piecePosition.i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(piecePosition.i,j))) {
                possiblePositions.add(new Position(piecePosition.i,j));
            }
        }
        return possiblePositions;
    }

    private List<Position> getWhitePawnPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        if(piecePosition.j==7) return null;
        int i1 = piecePosition.i;
        int j1 = piecePosition.j + 1;
        int j2 = j1 + 1;

        Position moveForwardOnce = new Position(i1,j1);
        Piece piece1 = this.getPieceAtPosition(moveForwardOnce);
        if(piece1 == EMPTY) {
            possiblePositions.add(moveForwardOnce);
        }

        if(piecePosition.j == 1) {
            Position moveForwardTwice = new Position(i1,j2);
            Piece piece2 = this.getPieceAtPosition(moveForwardTwice);
            if(piece2 == EMPTY && piece1 == EMPTY) {
                possiblePositions.add(moveForwardTwice);
            }
        }

        int i2 = i1 - 1;
        int i3 = i1 + 1;
        Position attackLeft = new Position(i2, j1);
        if(attackLeft.i>0) {
            Tile tile3 = this.getTileAtPosition(attackLeft);
            Piece piece3 = tile3.getPiece();
            if (piece3 != EMPTY && piece3 != BLACK_KING && !tile3.isPieceWhite() && !pope.isTileProtected(attackLeft)) {
                possiblePositions.add(attackLeft);
            }
        }


        Position attackRight = new Position(i3, j1);
        if(attackRight.i<7) {
            Tile tile4 = this.getTileAtPosition(attackRight);
            Piece piece4 = tile4.getPiece();
            if(piece4 != EMPTY && piece4 != BLACK_KING && !tile4.isPieceWhite() && !pope.isTileProtected(attackRight)) {
                possiblePositions.add(attackRight);
            }
        }


        return possiblePositions;
    }

    private List<Position> getWhiteKingPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        int i = piecePosition.i;
        int j = piecePosition.j;
        int iLeft = i-1;
        int iRight = i+1;
        int jUp = j+1;
        int jDown = j-1;

        if(iLeft >= 0) {
            // move side
            {
                Position position = new Position(iLeft, j);
                Tile tile = getTileAtPosition(position);
                if (tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if (!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move up
            if(jUp < 8) {
                Position position = new Position(iLeft,jUp);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move down
            if(jDown >= 0) {
                Position position = new Position(iLeft,jDown);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
        }
        if(iRight < 8) {
            // move side
            {
                Position position = new Position(iRight,j);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move up
            if(jUp < 8) {
                Position position = new Position(i,jUp);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
            // move down
            if(jDown >= 0) {
                Position position = new Position(iRight,jDown);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty()) {
                    possiblePositions.add(position);
                } else {
                    if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                        possiblePositions.add(position);
                    }
                }
            }
        }

        // move up
        if(jUp < 8) {
            Position position = new Position(iRight,jUp);
            Tile tile = getTileAtPosition(position);
            if(tile.isEmpty()) {
                possiblePositions.add(position);
            } else {
                if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                    possiblePositions.add(position);
                }
            }
        }
        // move down
        if(jDown >= 0) {
            Position position = new Position(i,jDown);
            Tile tile = getTileAtPosition(position);
            if(tile.isEmpty()) {
                possiblePositions.add(position);
            } else {
                if(!pope.isTileProtected(position) && !tile.isPieceWhite()) {
                    possiblePositions.add(position);
                }
            }
        }
        return possiblePositions;
    }

    private List<Position> getWhiteQueenPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        possiblePositions.addAll(getWhiteBishopPossiblePositions(piecePosition));
        possiblePositions.addAll(getWhiteRookPossiblePositions(piecePosition));
        return possiblePositions;
    }

    private List<Position> getWhiteBishopPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();

        // bottom left
        int i = piecePosition.i;
        int j = piecePosition.j;
        while(--i >= 0 && --j >= 0 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i >= 0 && j >= 0 && !getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        // bottom right
        i = piecePosition.i;
        j = piecePosition.j;
        while(++i < 8 && --j >= 0 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i < 8 && j >= 0 && !getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        // top left
        i = piecePosition.i;
        j = piecePosition.j;
        while(--i >= 0 && ++j < 8 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i >= 0 && j < 8 && !getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        // top right
        i = piecePosition.i;
        j = piecePosition.j;
        while(++i < 8 && ++j < 8 && getTileAtPosition(new Position(i,j)).isEmpty()) {
            possiblePositions.add(new Position(i,j));
        }
        if(i < 8 && j < 8 && !getTileAtPosition(new Position(i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        return possiblePositions;
    }

    private List<Position> getWhiteKnightPossiblePositions(Position piecePosition) {
        return new ArrayList<Position>();
    }


    private List<Position> getWhiteRookPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        int i = piecePosition.i;
        while(--i >= 0 && getTileAtPosition(new Position(i,piecePosition.j)).isEmpty()) {
            possiblePositions.add(new Position(i,piecePosition.j));
        }
        if(i >= 0 && !getTileAtPosition(new Position(i,piecePosition.j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i, piecePosition.j))) {
                possiblePositions.add(new Position(i, piecePosition.j));
            }
        }
        i = piecePosition.i;
        while(++i < 8 && getTileAtPosition(new Position(i,piecePosition.j)).isEmpty()) {
            possiblePositions.add(new Position(i,piecePosition.j));
        }
        if(i < 8 && !getTileAtPosition(new Position(i,piecePosition.j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i, piecePosition.j))) {
                possiblePositions.add(new Position(i, piecePosition.j));
            }
        }
        int j = piecePosition.j;
        while(--j >= 0 && getTileAtPosition(new Position(piecePosition.i,j)).isEmpty()) {
            possiblePositions.add(new Position(piecePosition.i,j));
        }
        if(j >= 0 && !getTileAtPosition(new Position(piecePosition.i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(piecePosition.i,j))) {
                possiblePositions.add(new Position(piecePosition.i,j));
            }
        }
        j = piecePosition.j;
        while(++j < 8 && getTileAtPosition(new Position(piecePosition.i,j)).isEmpty()) {
            possiblePositions.add(new Position(piecePosition.i,j));
        }
        if(j < 8 && !getTileAtPosition(new Position(piecePosition.i,j)).isPieceWhite()) {
            if(!pope.isTileProtected(new Position(piecePosition.i,j))) {
                possiblePositions.add(new Position(piecePosition.i,j));
            }
        }
        return possiblePositions;
    }

    public Board getCopyOfBoard() {
        return new Board(this.state);
    }

    private boolean isNextMoveCheckForBlack() {
        return false;
    }

    private boolean isNextMoveCheckForWhite() {
        return false;
    }
}
