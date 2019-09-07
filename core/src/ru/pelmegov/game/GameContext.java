package ru.pelmegov.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import ru.pelmegov.game.player.Player;
import ru.pelmegov.network.GameClient;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class GameContext {

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera worldCamera;
    private GameClient gameClient;

    private Player currentPlayer;
    private Set<Player> players = new CopyOnWriteArraySet<>();

    public void addPlayer(Player player) {
        players.remove(player);
        players.add(player);
    }

    public Set<Player> getAllPlayers() {
        return players;
    }


    // getters and setters

    public OrthographicCamera getWorldCamera() {
        return worldCamera;
    }

    public void setWorldCamera(OrthographicCamera worldCamera) {
        this.worldCamera = worldCamera;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Box2DDebugRenderer getB2dr() {
        return b2dr;
    }

    public void setB2dr(Box2DDebugRenderer b2dr) {
        this.b2dr = b2dr;
    }

    public GameClient getGameClient() {
        return gameClient;
    }

    public void setGameClient(GameClient gameClient) {
        this.gameClient = gameClient;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
