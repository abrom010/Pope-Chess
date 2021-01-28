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
    public List<Piece> capturedWhitePieces;
    public List<Piece> capturedBlackPieces;
    public Pope pope;
    public boolean firstMove;
    public boolean whiteKingHasMoved;
    public boolean blackKingHasMoved;

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
        firstMove = true;
        whiteKingHasMoved = false;
        blackKingHasMoved = false;
        capturedWhitePieces = new ArrayList<>();
        capturedBlackPieces = new ArrayList<>();
    }

    public Tile getTileAtPosition(Position position) {
        if(position.i < 0 || position.i > 7 || position.j < 0 || position.j > 7) return null;
        return state[position.j][position.i];
    }

    public Piece getPieceAtPosition(Position position) {
        return getTileAtPosition(position).getPiece();
    }

    public void setPieceAtPosition(Position position, Piece piece) {
        getTileAtPosition(position).setPiece(piece);
    }

    public List<Position> getPiecePossiblePositions(Position piecePosition) {
        Tile originalTile = getTileAtPosition(piecePosition);
        Piece piece = originalTile.getPiece();
        List<Position> possiblePositions;
        switch(piece) {
            case WHITE_ROOK:
                possiblePositions = getWhiteRookPossiblePositions(piecePosition);
                break;
            case WHITE_KNIGHT:
                possiblePositions = getWhiteKnightPossiblePositions(piecePosition);
				break;
            case WHITE_BISHOP:
                possiblePositions = getWhiteBishopPossiblePositions(piecePosition);
				break;
            case WHITE_QUEEN:
                possiblePositions = getWhiteQueenPossiblePositions(piecePosition);
				break;
            case WHITE_KING:
                possiblePositions = getWhiteKingPossiblePositions(piecePosition);
				break;
            case WHITE_PAWN:
                possiblePositions = getWhitePawnPossiblePositions(piecePosition);
				break;
            case BLACK_ROOK:
                possiblePositions = getBlackRookPossiblePositions(piecePosition);
				break;
            case BLACK_KNIGHT:
                possiblePositions = getBlackKnightPossiblePositions(piecePosition);
				break;
            case BLACK_BISHOP:
                possiblePositions = getBlackBishopPossiblePositions(piecePosition);
				break;
            case BLACK_QUEEN:
                possiblePositions = getBlackQueenPossiblePositions(piecePosition);
				break;
            case BLACK_KING:
                possiblePositions = getBlackKingPossiblePositions(piecePosition);
				break;
            case BLACK_PAWN:
                possiblePositions = getBlackPawnPossiblePositions(piecePosition);
				break;
            default:
                possiblePositions = new ArrayList<>();
        }
        if(possiblePositions == null) return possiblePositions;
        List<Position> possiblePositions2 = new ArrayList<>();
        for(Position p : possiblePositions) {
            if(p == null) continue;
            if(p.i < 0 || p.i > 7 || p.j < 0 || p.j >7) continue;
            Tile tile = getTileAtPosition(p);
            if(tile == null) continue;
            if(!(tile.isPieceKing() || (pope.isTileProtected(p) && !tile.isEmpty()))) {
                if(originalTile.isPieceWhite()) {
                    if(!isNextMoveCheckForWhite(piecePosition,p)) {
                        possiblePositions2.add(p);
                    }
                } else {
                    if(!isNextMoveCheckForBlack(piecePosition,p)) {
                        possiblePositions2.add(p);
                    }
                }

            }
        }
        return possiblePositions2;
    }

    public List<Position> getPiecePossiblePositionsRaw(Position piecePosition) {
        Piece piece = getPieceAtPosition(piecePosition);
        List<Position> possiblePositions;
        switch(piece) {
            case WHITE_ROOK:
                possiblePositions = getWhiteRookPossiblePositions(piecePosition);
                break;
            case WHITE_KNIGHT:
                possiblePositions = getWhiteKnightPossiblePositions(piecePosition);
                break;
            case WHITE_BISHOP:
                possiblePositions = getWhiteBishopPossiblePositions(piecePosition);
                break;
            case WHITE_QUEEN:
                possiblePositions = getWhiteQueenPossiblePositions(piecePosition);
                break;
            case WHITE_KING:
                possiblePositions = getWhiteKingPossiblePositions(piecePosition);
                break;
            case WHITE_PAWN:
                possiblePositions = getWhitePawnPossiblePositions(piecePosition);
                break;
            case BLACK_ROOK:
                possiblePositions = getBlackRookPossiblePositions(piecePosition);
                break;
            case BLACK_KNIGHT:
                possiblePositions = getBlackKnightPossiblePositions(piecePosition);
                break;
            case BLACK_BISHOP:
                possiblePositions = getBlackBishopPossiblePositions(piecePosition);
                break;
            case BLACK_QUEEN:
                possiblePositions = getBlackQueenPossiblePositions(piecePosition);
                break;
            case BLACK_KING:
                possiblePositions = getBlackKingPossiblePositions(piecePosition);
                break;
            case BLACK_PAWN:
                possiblePositions = getBlackPawnPossiblePositions(piecePosition);
                break;
            default:
                possiblePositions = new ArrayList<>();
        }

        return possiblePositions;
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
        if(attackLeft.i>=0) {
            Tile tile3 = this.getTileAtPosition(attackLeft);
            Piece piece3 = tile3.getPiece();
            if(piece3 != EMPTY && tile3.isPieceWhite() && !pope.isTileProtected(attackLeft)) {
                possiblePositions.add(attackLeft);
            }
        }


        Position attackRight = new Position(i3, j1);
        if(attackRight.i<8) {
            Tile tile4 = this.getTileAtPosition(attackRight);
            Piece piece4 = tile4.getPiece();
            if(piece4 != EMPTY && tile4.isPieceWhite() && !pope.isTileProtected(attackRight)) {
                possiblePositions.add(attackRight);
            }
        }

        return possiblePositions;
    }

    private List<Position> getBlackKingPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();

        if(!this.blackKingHasMoved && !this.isWhiteKingInCheck()) { // castle
            int i = piecePosition.i;
            int j = piecePosition.j;

            // left
            Tile leftOnce = getTileAtPosition(new Position(i-1, j));
            Tile leftTwice = getTileAtPosition(new Position(i-2,j));
            Tile leftThrice = getTileAtPosition(new Position(i-3,j));
            Tile leftFourTimes = getTileAtPosition(new Position(i-4,j));

            if(leftOnce.isEmpty() && !isPositionUnderWhiteAttack(new Position(i-1, j))) {
                if(leftTwice.isEmpty() && !isPositionUnderWhiteAttack(new Position(i-2,j))) {
                    if(leftThrice.isEmpty() && !isPositionUnderWhiteAttack(new Position(i-3,j))) {
                        if(leftFourTimes.getPiece()==Piece.BLACK_ROOK) {
                            possiblePositions.add(new Position(i-2,j));
                        }
                    }
                }
            }

            // right
            Tile rightOnce = getTileAtPosition(new Position(i+1,j));
            Tile rightTwice = getTileAtPosition(new Position(i+2,j));
            Tile rightThrice = getTileAtPosition(new Position(i+3,j));

            if(rightOnce.isEmpty() && !isPositionUnderWhiteAttack(new Position(i+1,j))) {
                if(rightTwice.isEmpty() && !isPositionUnderWhiteAttack(new Position(i+2,j))) {
                    if(rightThrice.getPiece()==Piece.BLACK_ROOK) {
                        possiblePositions.add(new Position(i+2,j));
                    }
                }
            }
        }

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
        Tile tile1 = getTileAtPosition(new Position(i,j));
        if(i >= 0 && j >= 0 && tile1.isPieceWhite()) {
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
        Tile tile2 = getTileAtPosition(new Position(i,j));
        if(i < 8 && j >= 0 && tile2.isPieceWhite()) {
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
        Tile tile3 = getTileAtPosition(new Position(i,j));
        if(i >= 0 && j < 8 && tile3.isPieceWhite()) {
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
        Tile tile4 = getTileAtPosition(new Position(i,j));
        if(i < 8 && j < 8 && tile4.isPieceWhite()) {
            if(!pope.isTileProtected(new Position(i,j))) {
                possiblePositions.add(new Position(i,j));
            }
        }

        return possiblePositions;
    }

    private List<Position> getBlackKnightPossiblePositions(Position piecePosition) {
        List<Position> possiblePositions = new ArrayList<>();
        int iLeftOnce = piecePosition.i-1;
        int iLeftTwice = piecePosition.i-2;
        int iRightOnce = piecePosition.i+1;
        int iRightTwice = piecePosition.i+2;

        int jUpOnce = piecePosition.j+1;
        int jUpTwice = piecePosition.j+2;
        int jDownOnce = piecePosition.j-1;
        int jDownTwice = piecePosition.j-2;

        if(iLeftOnce >= 0) {
            if(jUpTwice < 8) {
                Position position = new Position(iLeftOnce, jUpTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownTwice >= 0) {
                Position position = new Position(iLeftOnce, jDownTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }
        if(iLeftTwice >= 0) {
            if(jUpOnce < 8) {
                Position position = new Position(iLeftTwice, jUpOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownOnce >= 0) {
                Position position = new Position(iLeftTwice, jDownOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }
        if(iRightOnce < 8) {
            if(jUpTwice < 8) {
                Position position = new Position(iRightOnce, jUpTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownTwice >= 0) {
                Position position = new Position(iRightOnce, jDownTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }
        if(iRightTwice < 8) {
            if(jUpOnce < 8) {
                Position position = new Position(iRightTwice, jUpOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownOnce >= 0) {
                Position position = new Position(iRightTwice, jDownOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }

        return possiblePositions;
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
        if(attackLeft.i>=0) {
            Tile tile3 = this.getTileAtPosition(attackLeft);
            Piece piece3 = tile3.getPiece();
            if (piece3 != EMPTY && piece3 != BLACK_KING && !tile3.isPieceWhite() && !pope.isTileProtected(attackLeft)) {
                possiblePositions.add(attackLeft);
            }
        }


        Position attackRight = new Position(i3, j1);
        if(attackRight.i<8) {
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

        if(!this.whiteKingHasMoved && !this.isWhiteKingInCheck()) { // castle
            int i = piecePosition.i;
            int j = piecePosition.j;

            // left
            Tile leftOnce = getTileAtPosition(new Position(i-1, j));
            Tile leftTwice = getTileAtPosition(new Position(i-2,j));
            Tile leftThrice = getTileAtPosition(new Position(i-3,j));
            Tile leftFourTimes = getTileAtPosition(new Position(i-4,j));

            if(leftOnce.isEmpty() && !isPositionUnderBlackAttack(new Position(i-1, j))) {
                if(leftTwice.isEmpty() && !isPositionUnderBlackAttack(new Position(i-2,j))) {
                    if(leftThrice.isEmpty() && !isPositionUnderBlackAttack(new Position(i-3,j))) {
                        if(leftFourTimes.getPiece()==Piece.WHITE_ROOK) {
                            possiblePositions.add(new Position(i-2,j));
                        }
                    }
                }
            }

            // right
            Tile rightOnce = getTileAtPosition(new Position(i+1,j));
            Tile rightTwice = getTileAtPosition(new Position(i+2,j));
            Tile rightThrice = getTileAtPosition(new Position(i+3,j));

            if(rightOnce.isEmpty() && !isPositionUnderBlackAttack(new Position(i+1,j))) {
                if(rightTwice.isEmpty() && !isPositionUnderBlackAttack(new Position(i+2,j))) {
                    if(rightThrice.getPiece()==Piece.WHITE_ROOK) {
                        possiblePositions.add(new Position(i+2,j));
                    }
                }
            }
        }

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
        List<Position> possiblePositions = new ArrayList<>();
        int iLeftOnce = piecePosition.i-1;
        int iLeftTwice = piecePosition.i-2;
        int iRightOnce = piecePosition.i+1;
        int iRightTwice = piecePosition.i+2;

        int jUpOnce = piecePosition.j+1;
        int jUpTwice = piecePosition.j+2;
        int jDownOnce = piecePosition.j-1;
        int jDownTwice = piecePosition.j-2;

        if(iLeftOnce >= 0) {
            if(jUpTwice < 8) {
                Position position = new Position(iLeftOnce, jUpTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownTwice >= 0) {
                Position position = new Position(iLeftOnce, jDownTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }
        if(iLeftTwice >= 0) {
            if(jUpOnce < 8) {
                Position position = new Position(iLeftTwice, jUpOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownOnce >= 0) {
                Position position = new Position(iLeftTwice, jDownOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }
        if(iRightOnce < 8) {
            if(jUpTwice < 8) {
                Position position = new Position(iRightOnce, jUpTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownTwice >= 0) {
                Position position = new Position(iRightOnce, jDownTwice);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }
        if(iRightTwice < 8) {
            if(jUpOnce < 8) {
                Position position = new Position(iRightTwice, jUpOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
            if(jDownOnce >= 0) {
                Position position = new Position(iRightTwice, jDownOnce);
                Tile tile = getTileAtPosition(position);
                if(tile.isEmpty() || (!tile.isPieceWhite() && !pope.isTileProtected(position))) {
                    possiblePositions.add(position);
                }
            }
        }

        return possiblePositions;
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
        Board newBoard = new Board();

        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                newBoard.setPieceAtPosition(new Position(i,j), this.getPieceAtPosition(new Position(i,j)));
            }
        }

        return newBoard;
    }

    private List<Position> getEveryPositionWithPiece() {
        List<Position> positions = new ArrayList<>();
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                Position position = new Position(i,j);
                if(!getTileAtPosition(position).isEmpty()) {
                    positions.add(position);
                }
            }
        }
        return positions;
    }

    public boolean isBlackKingInCheck() {
        Position kingPosition = new Position(-1,-1);
        for(Position p : getEveryPositionWithPiece()) {
            if(getPieceAtPosition(p)==Piece.BLACK_KING) {
                kingPosition = p;
                break;
            }
        }

        for(Position p : getEveryPositionWithPiece()) {
            Tile tile = getTileAtPosition(p);
            if(getPieceAtPosition(p) != null && !tile.isEmpty() && tile.isPieceWhite() && !tile.isPieceKing()) {
                List<Position> positions = getPiecePossiblePositionsRaw(p);
                if(positions == null) continue;
                for(Position p2 : positions) {
                    if(p2.i == kingPosition.i && p2.j == kingPosition.j) return true;
                }
            }
        }
        return false;
    }

    public boolean isWhiteKingInCheck() {
        Position kingPosition = new Position(-1,-1);
        for(Position p : getEveryPositionWithPiece()) {
            if(getPieceAtPosition(p)==Piece.WHITE_KING) {
                kingPosition = p;
                break;
            }
        }

        for(Position p : getEveryPositionWithPiece()) {
            Tile tile = getTileAtPosition(p);
            if(getPieceAtPosition(p) != null && !tile.isEmpty() && !tile.isPieceWhite() && !tile.isPieceKing()) {
                List<Position> positions = getPiecePossiblePositionsRaw(p);
                if(positions == null) continue;
                for(Position p2 : positions) {
                    if(p2.i == kingPosition.i && p2.j == kingPosition.j) return true;
                }
            }
        }
        return false;
    }

    private boolean isNextMoveCheckForBlack(Position startingPosition, Position endingPosition) {
        Position kingPosition = new Position(-1,-1);
        Board newBoard = getCopyOfBoard();
        for(Position p : newBoard.getEveryPositionWithPiece()) {
            if(newBoard.getPieceAtPosition(p)==Piece.BLACK_KING) {
                kingPosition = p;
                break;
            }
        }

        Tile startingTile = newBoard.getTileAtPosition(startingPosition);
        Piece startingPiece = newBoard.getPieceAtPosition(startingPosition);
        newBoard.setPieceAtPosition(endingPosition, startingPiece);
        newBoard.setPieceAtPosition(startingPosition, EMPTY);
        return newBoard.isBlackKingInCheck();
    }

    private boolean isNextMoveCheckForWhite(Position startingPosition, Position endingPosition) {
        Position kingPosition = new Position(-1,-1);
        Board newBoard = getCopyOfBoard();
        for(Position p : newBoard.getEveryPositionWithPiece()) {
            if(newBoard.getPieceAtPosition(p)==Piece.WHITE_KING) {
                kingPosition = p;
                break;
            }
        }

        Tile startingTile = newBoard.getTileAtPosition(startingPosition);
        Piece startingPiece = newBoard.getPieceAtPosition(startingPosition);
        newBoard.setPieceAtPosition(endingPosition, startingPiece);
        newBoard.setPieceAtPosition(startingPosition, EMPTY);
        return newBoard.isWhiteKingInCheck();
    }

    private boolean isPositionUnderWhiteAttack(Position position) {
        for(Position p : this.getEveryPositionWithPiece()) {
            Tile tile = this.getTileAtPosition(p);
            if(tile.isPieceKing()) continue;
            if(tile.isPieceWhite()) {
                for(Position p2 : getPiecePossiblePositionsRaw(p)) {
                    if(p2.i==position.i&&p2.j==position.j) return true;
                }
            }
        }
        return false;
    }

    private boolean isPositionUnderBlackAttack(Position position) {
        for(Position p : this.getEveryPositionWithPiece()) {
            Tile tile = this.getTileAtPosition(p);
            if(tile.isPieceKing()) continue;
            if(!tile.isPieceWhite()) {
                for(Position p2 : getPiecePossiblePositionsRaw(p)) {
                    if(p2.i==position.i&&p2.j==position.j) return true;
                }
            }
        }
        return false;
    }
}
