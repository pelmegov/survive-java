package ru.pelmegov.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.PlayerContactListener;
import ru.pelmegov.game.PlayerKeyboardInputProcessor;
import ru.pelmegov.game.model.player.Player;
import ru.pelmegov.network.GameClient;
import ru.pelmegov.screen.AbstractScreen;
import ru.pelmegov.screen.game.world.WorldRenderer;

public class GameProcessScreen extends AbstractScreen {

    private WorldRenderer worldRenderer;
    private float delta = 0;

    @Override
    public void show() {
        initializeWorld2dBox();
        initializeWorldRenderer();
        initializeCamera();
        initializeInputManagers();
        initializeContactListeners();
        initializeNetwork();
    }

    @Override
    public void render(float delta) {
        if (GameContext.deletedPlayers.contains(GameContext.currentPlayer.getId())) {
            System.err.println("SORRY, YOU DIED...");
            Gdx.app.exit();
        }

        worldRenderer.render();
        sendPlayerInfo(delta);
        GameContext.clean();
    }

    private void sendPlayerInfo(float delta) {
        this.delta += delta;
        this.sendPlayerMovement();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }

    private void initializeWorld2dBox() {
        GameContext.world = new World(new Vector2(0f, 0f), false);
        GameContext.b2dr = new Box2DDebugRenderer();
    }

    private void initializeWorldRenderer() {
        worldRenderer = new WorldRenderer();
    }

    private void initializeCamera() {
        OrthographicCamera worldCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        worldCamera.lookAt(0, 0, 0);
        worldCamera.zoom = 1f;
        worldCamera.update();

        GameContext.worldCamera = worldCamera;
    }

    private void initializeInputManagers() {
        Gdx.input.setInputProcessor(new PlayerKeyboardInputProcessor());
    }

    private void initializeContactListeners() {
        GameContext.world.setContactListener(new PlayerContactListener());
    }

    private void initializeNetwork() {
        GameContext.gameClient = new GameClient();
    }

    private void sendPlayerMovement() {
        Player player = GameContext.currentPlayer;
        Vector2 playerMovement = new Vector2(player.getBody().getPosition().x, player.getBody().getPosition().y);
        // send through api
        GameContext.gameClient.send(player.getId(), playerMovement, player.getDirection());
    }

}
