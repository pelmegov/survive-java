package ru.pelmegov.screen.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import ru.pelmegov.game.Direction;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.player.Player;
import ru.pelmegov.graphic.sprite.SpriteContainer;

import java.util.Iterator;

import static ru.pelmegov.game.player.Player.PLAYER_HEIGHT;
import static ru.pelmegov.game.player.Player.PLAYER_WIDTH;
import static ru.pelmegov.graphic.sprite.SpriteName.GRASS_1;
import static ru.pelmegov.graphic.sprite.SpriteName.PLAYER_1;
import static ru.pelmegov.util.Constant.TILE_SIZE_PIXELS;

public class WorldRenderer implements Disposable {

    private SpriteBatch batch;
    private GameContext gameContext;

    public WorldRenderer(GameContext gameContext) {
        this.gameContext = gameContext;
        initialize();
    }

    @Override
    public void dispose() {
    }

    private void initialize() {
        initializeSprites();
        initializeBatches();
    }

    private void initializeSprites() {
        initializeGroundSprites();
        initializePlayerSprites();
    }

    private void initializeGroundSprites() {
        Texture texture = new Texture("graphic/texture/ground/grass1.jpg");
        Sprite sprite = new Sprite(texture);
        SpriteContainer.getInstance().addSprite(GRASS_1, sprite);
    }

    private void initializePlayerSprites() {
        Texture texture = new Texture("graphic/player/player.png");
        Sprite sprite = new Sprite(texture);
        SpriteContainer.getInstance().addSprite(PLAYER_1, sprite);

        gameContext.setCurrentPlayer(new Player(gameContext.getWorld(), sprite));
    }

    private void initializeBatches() {
        batch = new SpriteBatch();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gameContext.getWorldCamera().combined);

        batch.begin();

        renderGround();
        renderPlayers();
        renderWorld();
        renderCamera();

        batch.end();
    }

    private void renderGround() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                Sprite sprite = SpriteContainer.getInstance().getSprite(GRASS_1);
                sprite.setPosition(i * TILE_SIZE_PIXELS, j * TILE_SIZE_PIXELS);
                sprite.draw(batch);
            }
        }
    }

    private void renderPlayers() {
        Player currentPlayer = gameContext.getCurrentPlayer();
        Direction direction = currentPlayer.getPlayerKeyboard().getDirectionKeyPressed();
        Sprite currentPlayerSprite = currentPlayer.draw(direction);

        float x = currentPlayer.getBody().getPosition().x;
        float y = currentPlayer.getBody().getPosition().y;
        currentPlayerSprite.setPosition(x - PLAYER_WIDTH, y - PLAYER_HEIGHT);

        Vector2 playerMovement = new Vector2(x, y);

        // send movements by tcp
        gameContext.getGameClient().send(currentPlayer.getId(), playerMovement, direction);

        currentPlayerSprite.draw(batch);
        gameContext.getWorldCamera().position.set(playerMovement, 0);

        // render another players on map
        Iterator<Player> iterator = gameContext.getAllPlayers().iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();

            // current player already drew
            if (player.getId() == currentPlayer.getId()) {
                continue;
            }

            System.err.println("Draw player with ID = " + player.getId() + " on position = [" + player.getBody().getPosition().x + ", " + player.getBody().getPosition().y + "]");

            Sprite playerSprite = player.draw(null);
            playerSprite.draw(batch);
        }
    }

    private void renderWorld() {
        gameContext.getWorld().step(1f / 60f, 5, 8);
    }

    private void renderCamera() {
        gameContext.getWorldCamera().update();
    }

}