package popechess.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Circle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import popechess.engine.Board;
import popechess.engine.Piece;
import popechess.engine.Position;
import popechess.engine.Tile;
import popechess.util.Utils;

import static popechess.engine.Piece.BLACK_BISHOP;
import static popechess.engine.Piece.BLACK_KING;
import static popechess.engine.Piece.BLACK_KNIGHT;
import static popechess.engine.Piece.BLACK_PAWN;
import static popechess.engine.Piece.BLACK_QUEEN;
import static popechess.engine.Piece.BLACK_ROOK;
import static popechess.engine.Piece.EMPTY;
import static popechess.engine.Piece.WHITE_BISHOP;
import static popechess.engine.Piece.WHITE_KING;
import static popechess.engine.Piece.WHITE_KNIGHT;
import static popechess.engine.Piece.WHITE_PAWN;
import static popechess.engine.Piece.WHITE_QUEEN;
import static popechess.engine.Piece.WHITE_ROOK;

public class Main extends Game {
	public boolean isCheckmate;
	//	public boolean pawnBeingPromoted;
	List<Position> possiblePositions;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	public Board board;
	Position positionOfPieceBeingMoved;

	int width;
	int height;
	float backgroundLength;
	float squareLength;
	float horizontalOffset;
	float verticalOffset;

	Texture popeTexture;
	Sprite popeSprite;


	Utils utils;
	public boolean popeBeingMoved;
	public boolean isWhiteTurn;
	public boolean whitePawnBeingPromoted;
	public boolean blackPawnBeingPromoted;
	public Position positionOfPawnBeingPromoted;


	Prefs prefs;

	@Override
	public void create() {
		positionOfPawnBeingPromoted = null;
		whitePawnBeingPromoted = false;
		blackPawnBeingPromoted = false;

		Gdx.input.setCatchBackKey(true);
		prefs = new Prefs();

		popeBeingMoved = false;
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		board = new Board();
		positionOfPieceBeingMoved = null;
		possiblePositions = null;
		isWhiteTurn = true;
		isCheckmate = false;

		setScreen(new MenuScreen(this));

		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		backgroundLength = width;//Math.min(width, height);
		squareLength = backgroundLength/8;

//		if(width>height) {
//			horizontalOffset = (width-height)/2;
//			verticalOffset = 0;
//		} else if(height>width) {
//			horizontalOffset = 0;
//			verticalOffset = (height-width)/2;
//		} else {
//			horizontalOffset = 0;
//			verticalOffset = 0;
//		}
		horizontalOffset = 0;
		verticalOffset = (height-width)/2;

		utils = new Utils();

		// pope
		popeTexture = new Texture("pope.png");
		popeSprite = new Sprite(popeTexture);
		popeSprite.setOriginCenter();
		popeSprite.setSize(squareLength*1.4f, squareLength*1.4f);

	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		shapeRenderer.dispose();
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
			shapeRenderer = utils.alternateColor(shapeRenderer, Color.BROWN, Color.TAN);
			for(int i=0; i<8; i++) {
				shapeRenderer = utils.alternateColor(shapeRenderer, Color.BROWN, Color.TAN);
				shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
			}
		}
		shapeRenderer.end();
	}

	void drawPopeHighlights() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setColor(new Color(1,1,1,0.25f));
		for(int j=0; j<8; j++) {
			for (int i = 0; i < 8; i++) {
				for(Position p : board.pope.getProtectedTiles()) {
					if(p.i == i && p.j == j) {
						shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
					}
				}
			}
		}
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.BLACK);
		// 3
		Gdx.gl.glLineWidth(squareLength/40);
		for(int j=0; j<8; j++) {
			for (int i = 0; i < 8; i++) {
				for(Position p : board.pope.getProtectedTiles()) {
					if(p.i == i && p.j == j) {
						shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
					}
				}
			}
		}
		shapeRenderer.end();
	}
	
	void drawHighlights() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//Gdx.gl.glEnable(GL30.GL_BLEND);
		//Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);

		// drawing highlights (now not translucent)
		shapeRenderer.setColor(new Color(1, 1, 0, 0.3f));
		for(int j=0; j<8; j++) {
			for(int i=0; i<8; i++) {
				if(positionOfPieceBeingMoved == null) continue;
				for(Position p : possiblePositions) {
					if(p.i == i && p.j == j) {
						if(board.getPieceAtPosition(new Position(i, j)) != Piece.EMPTY) {
							shapeRenderer.setColor(new Color(1, 0, 0, 0.3f));
						}
						shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
						shapeRenderer.setColor(new Color(1, 1, 0, 0.3f));
					}
				}
			}
		}
		// drawing borders
		shapeRenderer.end();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.BLACK);
		Gdx.gl.glLineWidth(squareLength/40);
		for(int j=0; j<8; j++) {
			for(int i=0; i<8; i++) {
				if(positionOfPieceBeingMoved == null) continue;
				for(Position p : possiblePositions) {
					if(p.i == i && p.j == j) {
						shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
					}
				}
			}
		}
		shapeRenderer.end();
		//Gdx.gl.glDisable(GL30.GL_BLEND);
	}

	void drawPieces() {
		spriteBatch.begin();
		float padding = squareLength*.1f;
		for(int j=0; j<board.state.length; j++) {
			for(int i=0; i<board.state[0].length; i++) {
				Position position = new Position(i,j);
				Tile tile = board.getTileAtPosition(position);
				Texture pieceTexture = utils.getTextureFromPiece(tile.getPiece());
				if(pieceTexture == null) continue;
				Sprite pieceSprite = new Sprite(pieceTexture);
				pieceSprite.setOriginCenter();
				pieceSprite.setSize(squareLength*.8f, squareLength*.8f);
				pieceSprite.setPosition(horizontalOffset+padding+i*squareLength, verticalOffset+padding+j*squareLength);
				pieceSprite.draw(spriteBatch);
			}
		}
		Position popePosition = board.pope.getPosition();
		popeSprite.setPosition(horizontalOffset+(popePosition.i*squareLength+squareLength)-squareLength*1.4f/1.95f,verticalOffset+(popePosition.j*squareLength+squareLength)-squareLength*1.4f/2);
		popeSprite.draw(spriteBatch);
		spriteBatch.end();
	}

	void drawIndicator() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		if(isWhiteTurn) {
			shapeRenderer.setColor(Color.WHITE);
		} else {
			shapeRenderer.setColor(Color.BLACK);
		}
		shapeRenderer.rect(width-squareLength*.8f-squareLength*.1f,0+squareLength*.1f,squareLength*.8f,squareLength*.8f);
		shapeRenderer.rect(width-squareLength*.8f-squareLength*.1f,height-squareLength*.8f-squareLength*.1f, squareLength*.8f, squareLength*.8f);
		shapeRenderer.end();
	}

	void drawCapturedPieces() {
		spriteBatch.begin();
		float pieceLength = .4f * squareLength;
		float padding = .05f * pieceLength;

		// draw black pieces
		float blackHeight = verticalOffset-pieceLength-padding;

		for(int i=0; i<board.capturedBlackPieces.size(); i++) {
			Texture pieceTexture = utils.getTextureFromPiece(board.capturedBlackPieces.get(i));
			Sprite pieceSprite = new Sprite(pieceTexture);
			pieceSprite.setOriginCenter();
			pieceSprite.setSize(pieceLength, pieceLength);
			pieceSprite.setPosition(i*pieceLength+padding,blackHeight-padding);
			pieceSprite.draw(spriteBatch);
		}

		// draw white pieces
		float whiteHeight = verticalOffset+squareLength*8+padding;
		for(int i=0; i<board.capturedWhitePieces.size(); i++) {
			Texture pieceTexture = utils.getTextureFromPiece(board.capturedWhitePieces.get(i));
			Sprite pieceSprite = new Sprite(pieceTexture);
			pieceSprite.setOriginCenter();
			pieceSprite.setSize(pieceLength, pieceLength);
			pieceSprite.setPosition(i*pieceLength+padding,whiteHeight+padding);
			pieceSprite.draw(spriteBatch);
		}

		spriteBatch.end();
	}

	void drawCheckmateBanner() {
		float y = this.verticalOffset+squareLength*3;
		y -= squareLength*.2f;
		float tabHeight = squareLength*2.4f;
		float tabWidth = this.width;
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.WHITE);
		shapeRenderer.rect(0, y, tabWidth, tabHeight);
		shapeRenderer.end();
		BitmapFont font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		font.getData().setScale(10);
		GlyphLayout layout = new GlyphLayout();
		layout.setText(font, "Checkmate!");
		this.spriteBatch.begin();
		font.draw(this.spriteBatch, "Checkmate!", Gdx.graphics.getWidth()/2.0f-layout.width/2.0f, Gdx.graphics.getHeight()/2.0f+layout.height/2.0f);
		this.spriteBatch.end();
	}

	void drawPawnPromotion(boolean white) {
		float y = this.verticalOffset+squareLength*3;
		float tabHeight = squareLength*2;
		float tabWidth = this.width;

		List<Sprite> whiteSprites = Arrays.asList(
				new Sprite(utils.getTextureFromPiece(WHITE_QUEEN)), new Sprite(utils.getTextureFromPiece(WHITE_ROOK)),
				new Sprite(utils.getTextureFromPiece(WHITE_BISHOP)), new Sprite(utils.getTextureFromPiece(WHITE_KNIGHT))
		);
		List<Sprite> blackSprites = Arrays.asList(
				new Sprite(utils.getTextureFromPiece(BLACK_QUEEN)), new Sprite(utils.getTextureFromPiece(BLACK_ROOK)),
				new Sprite(utils.getTextureFromPiece(BLACK_BISHOP)), new Sprite(utils.getTextureFromPiece(BLACK_KNIGHT))
		);

		float pieceLength = 1.8f * squareLength;

		for(int i=0; i<whiteSprites.size(); i++) {
			Sprite sprite = whiteSprites.get(i);
			sprite.setSize(pieceLength,pieceLength);
			sprite.setPosition(i*tabHeight,y);
		}
		for(int i=0; i<blackSprites.size(); i++) {
			Sprite sprite = blackSprites.get(i);
			sprite.setSize(pieceLength,pieceLength);
			sprite.setPosition(i*tabHeight,y);
		}

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.LIGHT_GRAY);
		shapeRenderer.rect(0, y, tabWidth, tabHeight);
		shapeRenderer.end();
		
		spriteBatch.begin();
		if(white) {
			for(Sprite s : whiteSprites) {
				s.draw(spriteBatch);
			}
		} else {
			for(Sprite s : blackSprites) {
				s.draw(spriteBatch);
			}
		}
		spriteBatch.end();
	}

	public Position getPositionFromCoordinates(float x, float y) {
		float boardStartX = horizontalOffset;
		float boardEndX = horizontalOffset+backgroundLength;
		float boardStartY = verticalOffset;
		float boardEndY = verticalOffset+backgroundLength;

		if(x>boardStartX && x<boardEndX && y>boardStartY && y<boardEndY) {
			float x2 = (x - boardStartX);
			float y2 = (boardEndY - y);

			int i =((int)x2 / (int)squareLength);
			int j = ((int)y2 / (int)squareLength);

			return new Position(i, j);
		}
		return null;
	}

	public void drawRedDots() {
		float x = popeSprite.getX() + (popeSprite.getWidth()/2);
		float y = popeSprite.getY() + (popeSprite.getHeight()/2);

		// 20
		float radius = squareLength/5;

		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(Color.RED);
		if(board.pope.canMoveLeft()) {
			shapeRenderer.circle(x-squareLength,y,radius);
		}
		if(board.pope.canMoveRight()) {
			shapeRenderer.circle(x+squareLength,y,radius);
		}
		if(board.pope.canMoveDown()) {
			shapeRenderer.circle(x,y+squareLength,radius);
		}
		if(board.pope.canMoveUp()) {
			shapeRenderer.circle(x,y-squareLength,radius);
		}

		shapeRenderer.end();
	}

	public void setPopeBeingMoved(boolean b) {
		popeBeingMoved = b;
	}

	public boolean getPopeBeingMoved() {
		return popeBeingMoved;
	}

	public boolean clickedOnPope(float x, float y) {
		float popeX = popeSprite.getX() + popeSprite.getWidth()/2;
		float popeY = getPopeSpriteY() + popeSprite.getWidth()/5;
		float radius = (popeSprite.getWidth() - popeSprite.getWidth()*.5f)/2;
		Circle circle = new Circle(popeX,popeY,radius);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.circle(popeX,popeY,radius);
		shapeRenderer.end();
		return circle.contains(x,y);
//		float popeX = popeSprite.getX() + popeSprite.getWidth()*.2f;
//		float popeY = getPopeSpriteY() - popeSprite.getHeight()*.2f;
//		float popeWidth = popeSprite.getWidth() - popeSprite.getWidth()*.5f;
//		float popeHeight = popeSprite.getHeight();
//		return x > popeX && x < popeX+popeWidth && y > popeY && y < popeY+popeHeight;
	}

	public void drawHitbox() {
		float popeX = popeSprite.getX() + popeSprite.getWidth()/2;
		float popeY = getPopeSpriteY() + popeSprite.getWidth()/5;
		float radius = (popeSprite.getWidth() - popeSprite.getWidth()*.5f)/2;
		Circle circle = new Circle(popeX,popeY,radius);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.circle(popeX,popeY,radius);
		shapeRenderer.end();
	}

	public float getPopeSpriteY() {
		return height - popeSprite.getY() - squareLength;
	}
}
