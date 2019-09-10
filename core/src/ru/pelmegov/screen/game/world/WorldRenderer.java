package ru.pelmegov.screen.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.PlayerKeyboardInputProcessor;
import ru.pelmegov.game.model.ammunition.Bullet;
import ru.pelmegov.game.model.player.Player;
import ru.pelmegov.graphic.sprite.SpriteContainer;

import static ru.pelmegov.game.model.ammunition.BulletHolder.getBullets;
import static ru.pelmegov.graphic.sprite.SpriteName.*;
import static ru.pelmegov.util.Constant.TILE_SIZE_PIXELS;

public class WorldRenderer implements Disposable {

    private SpriteBatch batch;

    public WorldRenderer() {
        initialize();
    }

    @Override
    public void dispose() {
    }

    private void initialize() {
        initializeSprites();
        initializeBatches();
        initializeInputProcessor();
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

        Player currentPlayer = new Player();
        GameContext.currentPlayer = currentPlayer;
        GameContext.addPlayer(currentPlayer);
    }

    private void initializeAmmunitionSprites() {
        Texture texture = new Texture("graphic/weapon/m4-bullet.png");
        Sprite sprite = new Sprite(texture);
        SpriteContainer.getInstance().addSprite(BULLET, sprite);
    }

    private void initializeBatches() {
        batch = new SpriteBatch();
    }

    private void initializeInputProcessor() {
        Gdx.input.setInputProcessor(new PlayerKeyboardInputProcessor(GameContext.currentPlayer));
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(GameContext.worldCamera.combined);

        batch.begin();
        renderGround();
        renderPlayers();
        renderBullets();
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
        Player currentPlayer = GameContext.currentPlayer;
        currentPlayer.update();
        batch.draw(currentPlayer.getSprite(), currentPlayer.getBody().getPosition().x, currentPlayer.getBody().getPosition().y);

        for (Player player : GameContext.getAllPlayers()) {
            if (player.getId().equals(currentPlayer.getId())) {
                continue;
            }
            player.update();
            batch.draw(player.getSprite(), player.getBody().getPosition().x, player.getBody().getPosition().y);
        }
    }

    private void renderBullets() {
        for (Bullet bullet : getBullets()) {
            Vector2 current = bullet.update();
            batch.draw(SpriteContainer.getInstance().getSprite(BULLET), current.x, current.y);
        }
    }

    private void renderWorld() {
        GameContext.world.step(1 / 60f, 6, 5);

        // for debugging box2d
        // GameContext.b2dr.render(GameContext.world, GameContext.worldCamera.combined);
    }

    private void renderCamera() {
        Player player = GameContext.currentPlayer;
        Vector2 playerMovement = new Vector2(player.getBody().getPosition().x, player.getBody().getPosition().y);

        GameContext.worldCamera.position.set(playerMovement, 0);
        GameContext.worldCamera.update();
    }

}