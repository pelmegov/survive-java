package ru.pelmegov.screen.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.ammunition.Bullet;
import ru.pelmegov.game.player.Player;
import ru.pelmegov.graphic.sprite.SpriteContainer;

import static ru.pelmegov.game.ammunition.BulletHolder.getBullets;
import static ru.pelmegov.graphic.sprite.SpriteName.*;
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
        initializeAmmunitionSprites();
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

        Player currentPlayer = new Player(gameContext.getWorld(), sprite);
        gameContext.setCurrentPlayer(currentPlayer);
        gameContext.addPlayer(currentPlayer);
    }

    private void initializeAmmunitionSprites() {
        Texture texture = new Texture("graphic/weapon/m4-bullet.png");
        Sprite sprite = new Sprite(texture);
        SpriteContainer.getInstance().addSprite(BULLET, sprite);
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
        renderBullets();
        renderWorld();
        renderCamera();
        batch.end();
    }

    private void renderBullets() {
        for (Bullet bullet : getBullets(gameContext)) {
            Vector2 current = bullet.update();
            batch.draw(SpriteContainer.getInstance().getSprite(BULLET), current.x, current.y);
        }
    }

    private void renderPlayers() {
        gameContext.getCurrentPlayer().prepareCurrentUserSprite().draw(batch);

        for (Player player : gameContext.getAllPlayers()) {
            if (player.getId() == gameContext.getCurrentPlayer().getId()) {
                continue;
            }
            player.prepareSprite().draw(batch);
        }
    }

    private void renderWorld() {
        gameContext.getWorld().step(1 / 60f, 6, 5);

        // for debugging box2d
        // gameContext.getB2dr().render(gameContext.getWorld(), gameContext.getWorldCamera().combined);
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