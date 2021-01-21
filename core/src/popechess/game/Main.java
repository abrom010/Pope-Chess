package popechess.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.List;

import popechess.engine.Board;
import popechess.engine.Piece;
import popechess.engine.Position;
import popechess.engine.Tile;
import popechess.util.Utils;

public class Main extends Game {
	List<Position> possiblePositions;
	SpriteBatch spriteBatch;
	ShapeRenderer shapeRenderer;
	Board board;
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

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		board = new Board();
		positionOfPieceBeingMoved = null;
		possiblePositions = null;

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

		utils = new Utils();

		// pope
		popeTexture = new Texture("pope.png");
		popeSprite = new Sprite(popeTexture);
		popeSprite.setOriginCenter();
		popeSprite.setSize(squareLength*1.2f, squareLength*1.2f);
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
	
	void drawHighlights() {
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		Gdx.gl.glEnable(GL30.GL_BLEND);
		Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setColor(new Color(1, 1, 0, 0.3f));
		for(int j=0; j<8; j++) {
			for(int i=0; i<8; i++) {
				if(positionOfPieceBeingMoved == null) continue;
				boolean contains = false;
				for(Position p : possiblePositions) {
					if(p.i == i && p.j == j) contains = true;
				}
				if(contains) {
					if(board.getPieceAtPosition(new Position(i, j)) != Piece.EMPTY) {
						shapeRenderer.setColor(new Color(1, 0, 0, 0.3f));
					}
					shapeRenderer.rect(horizontalOffset+i*squareLength,verticalOffset+j*squareLength, squareLength, squareLength);
					shapeRenderer.setColor(new Color(1, 1, 0, 0.3f));
				}
			}
		}
		shapeRenderer.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
	}

	void drawPieces() {
		spriteBatch.begin();

		popeSprite.setPosition(horizontalOffset+(this.backgroundLength/2)-squareLength*1.2f/2,verticalOffset+(this.backgroundLength/2)-squareLength*1.2f/2);
		popeSprite.draw(spriteBatch);

		List<Position> popePositions = new ArrayList<>();
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
		spriteBatch.end();
	}

	public Position getPositionFromCoordinates(int x, int y) {
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
}
