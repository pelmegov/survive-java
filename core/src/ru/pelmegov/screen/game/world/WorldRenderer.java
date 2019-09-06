package ru.pelmegov.screen.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Disposable;
import ru.pelmegov.game.Direction;
import ru.pelmegov.game.PlayerKeyboard;
import ru.pelmegov.graphic.animation.PlayerAnimation;
import ru.pelmegov.graphic.sprite.SpriteContainer;
import ru.pelmegov.screen.game.GameProcessScreen;

import static ru.pelmegov.graphic.sprite.SpriteName.GRASS_1;
import static ru.pelmegov.graphic.sprite.SpriteName.PLAYER_1;
import static ru.pelmegov.util.Constant.TILE_SIZE_PIXELS;

public class WorldRenderer implements Disposable {

    private SpriteBatch batch;
    private GameProcessScreen gameProcessScreen;
    private PlayerAnimation playerAnimation;
    private PlayerKeyboard playerKeyboard;
    private Body player;

    public WorldRenderer(GameProcessScreen gameProcessScreen) {
        this.gameProcessScreen = gameProcessScreen;

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

        playerAnimation = new PlayerAnimation(texture);
        player = playerAnimation.createPlayer(gameProcessScreen.world);
        playerKeyboard = new PlayerKeyboard();
    }

    private void initializeBatches() {
        batch = new SpriteBatch();
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(gameProcessScreen.worldCamera.combined);

        batch.begin();

        renderGround();
        renderPlayer();

        batch.end();
    }

    private void renderPlayer() {
        Direction direction = playerKeyboard.getDirectionKeyPressed(player);
        TextureRegion playerTextureByDirection = playerAnimation.getTexture(direction);
        batch.draw(playerTextureByDirection, player.getPosition().x - 32, player.getPosition().y - 24);

        gameProcessScreen.world.step(1 / 60f, 6, 2);

        Vector3 position = gameProcessScreen.worldCamera.position;
        position.x = player.getPosition().x;
        position.y = player.getPosition().y;
        gameProcessScreen.worldCamera.position.set(position);

        gameProcessScreen.worldCamera.update();
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

}