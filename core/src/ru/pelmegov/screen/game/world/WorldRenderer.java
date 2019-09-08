package ru.pelmegov.screen.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.player.Player;
import ru.pelmegov.graphic.sprite.SpriteContainer;

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
        renderPlayer();
        renderWorld();
        renderCamera();
        batch.end();
    }

    private void renderPlayer() {
        Player currentPlayer = gameContext.getCurrentPlayer();

        Sprite currentPlayerSprite = currentPlayer.prepareSprite();
        currentPlayerSprite.setPosition(
                currentPlayer.getBody().getPosition().x - PLAYER_WIDTH,
                currentPlayer.getBody().getPosition().y - PLAYER_HEIGHT);
        currentPlayerSprite.draw(batch);

        for (Player player : gameContext.getAllPlayers()) {
            if (player.equals(currentPlayer)) continue;

            Sprite playerSprite = player.prepareSprite();
            playerSprite.setPosition(
                    player.getBody().getPosition().x,
                    player.getBody().getPosition().y);

            System.out.println("Setting position for player with id = " + player.getId()
                    + ", position = [" + (player.getBody().getPosition().x) + ", "
                    + (player.getBody().getPosition().y) + "]");

            playerSprite.draw(batch);
        }
    }

    private void renderWorld() {
        gameContext.getWorld().step(1 / 60f, 6, 5);
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

    private void renderCamera() {
        Player player = gameContext.getCurrentPlayer();
        Vector2 playerMovement = new Vector2(player.getBody().getPosition().x, player.getBody().getPosition().y);

        gameContext.getWorldCamera().position.set(playerMovement, 0);
        gameContext.getWorldCamera().update();
    }

}