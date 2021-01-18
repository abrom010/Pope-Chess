package popechess.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import popechess.engine.Board;
import popechess.engine.Piece;

public class Main extends Game {
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	Board board;
	Piece pieceBeingCarried;

	int width;
	int height;
	float backgroundLength;
	float squareLength;
	float horizontalOffset;
	float verticalOffset;

	Texture popeTexture;
	Texture whiteRookTexture;
	Texture whiteKnightTexture;
	Texture whiteBishopTexture;
	Texture whiteQueenTexture;
	Texture whiteKingTexture;
	Texture whitePawnTexture;
	Texture blackRookTexture;
	Texture blackKnightTexture;
	Texture blackBishopTexture;
	Texture blackQueenTexture;
	Texture blackKingTexture;
	Texture blackPawnTexture;

	Sprite popeSprite;
	Sprite whiteRookSprite;
	Sprite whiteKnightSprite;
	Sprite whiteBishopSprite;
	Sprite whiteQueenSprite;
	Sprite whiteKingSprite;
	Sprite whitePawnSprite;
	Sprite blackRookSprite;
	Sprite blackKnightSprite;
	Sprite blackBishopSprite;
	Sprite blackQueenSprite;
	Sprite blackKingSprite;
	Sprite blackPawnSprite;

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		board = new Board();
		pieceBeingCarried = null;

		setScreen(new MenuScreen(this));

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		backgroundLength = Math.min(width, height);
		squareLength = backgroundLength/8;
		if(width>height) {
			horizontalOffset = (width-height)/2;
			verticalOffset = 0;
		} else if(height>width) {
			horizontalOffset = 0;
			verticalOffset = (height-width)/2;
		} else {
			horizontalOffset = 0;
			verticalOffset = 0;
		}

		// textures
		popeTexture = new Texture(Gdx.files.internal("pope.png"));
		whiteRookTexture = new Texture(Gdx.files.internal("whiterook.png"));
		whiteKnightTexture = new Texture(Gdx.files.internal("whiteknight.png"));
		whiteBishopTexture = new Texture(Gdx.files.internal("whitebishop.png"));
		whiteQueenTexture = new Texture(Gdx.files.internal("whitequeen.png"));
		whiteKingTexture = new Texture(Gdx.files.internal("whiteking.png"));
		whitePawnTexture = new Texture(Gdx.files.internal("whitepawn.png"));
		blackRookTexture = new Texture(Gdx.files.internal("blackrook.png"));
		blackKnightTexture = new Texture(Gdx.files.internal("blackknight.png"));
		blackBishopTexture = new Texture(Gdx.files.internal("blackbishop.png"));
		blackQueenTexture = new Texture(Gdx.files.internal("blackqueen.png"));
		blackKingTexture = new Texture(Gdx.files.internal("blackking.png"));
		blackPawnTexture = new Texture(Gdx.files.internal("blackpawn.png"));

		// sprites
		popeSprite = new Sprite(popeTexture);
		popeSprite.setOriginCenter();
		popeSprite.setSize(squareLength, squareLength);
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		shapeRenderer.dispose();
		popeTexture.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	void drawBoard() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.BROWN);
		for(int j=0; j<8; j++) {
			shapeRenderer = alternateColor(shapeRenderer, Color.BROWN, Color.TAN);
			for(int i=0; i<8; i++) {
				shapeRenderer = alternateColor(shapeRenderer, Color.BROWN, Color.TAN);
				shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
			}
		}
		shapeRenderer.end();
	}

	void drawPieces() {
		spriteBatch.begin();

		popeSprite.setPosition(horizontalOffset+(this.backgroundLength/2)-squareLength/2,verticalOffset+(this.backgroundLength/2)-squareLength/2);
		popeSprite.draw(spriteBatch);

		float padding = squareLength*.1f;
		for(int j=0; j<board.state.length; j++) {
			for(int i=0; i<board.state[0].length; i++) {
				Texture pieceTexture = getTextureFromPiece(board.state[j][i]);
				if(pieceTexture == null || pieceTexture == popeTexture) continue;
				Sprite pieceSprite = new Sprite(pieceTexture);
				pieceSprite.setOriginCenter();
				pieceSprite.setSize(squareLength*.8f, squareLength*.8f);
				pieceSprite.setPosition(horizontalOffset+padding+i*squareLength, verticalOffset+padding+j*squareLength);
				pieceSprite.draw(spriteBatch);
			}
		}
		spriteBatch.end();
	}

	ShapeRenderer alternateColor(ShapeRenderer shapeRenderer, Color color1, Color color2) {
		if(shapeRenderer.getColor().equals(color1)) {
			shapeRenderer.setColor(color2);
		} else {
			shapeRenderer.setColor(color1);
		}
		return shapeRenderer;
	}
	
	Texture getTextureFromPiece(Piece piece) {
		switch(piece) {
			case POPE:
				return popeTexture;
			case WHITE_ROOK:
				return whiteRookTexture;
			case WHITE_KNIGHT:
				return whiteKnightTexture;
			case WHITE_BISHOP:
				return whiteBishopTexture;
			case WHITE_QUEEN:
				return whiteQueenTexture;
			case WHITE_KING:
				return whiteKingTexture;
			case WHITE_PAWN:
				return whitePawnTexture;
			case BLACK_ROOK:
				return blackRookTexture;
			case BLACK_KNIGHT:
				return blackKnightTexture;
			case BLACK_BISHOP:
				return blackBishopTexture;
			case BLACK_QUEEN:
				return blackQueenTexture;
			case BLACK_KING:
				return blackKingTexture;
			case BLACK_PAWN:
				return blackPawnTexture;
			default:
				return null;
		}
	}

	public Piece getPieceFromCoordinates(int x, int y) {
		float boardStartX = horizontalOffset;
		float boardEndX = horizontalOffset+backgroundLength;
		float boardStartY = verticalOffset;
		float boardEndY = verticalOffset+backgroundLength;

		if(x>boardStartX && x<boardEndX && y>boardStartY && y<boardEndY) {
			// extract piece from coordinates
			float x2 = (x - boardStartX);
			float y2 = (boardEndY - y);

			int i =((int)x2 / (int)squareLength);
			int j = ((int)y2 / (int)squareLength);

			return board.state[j][i];
		}
		return null;
	}
}
