package ru.pelmegov.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import ru.pelmegov.game.model.ammunition.Bullet;
import ru.pelmegov.game.model.player.Player;
import ru.pelmegov.network.GameClient;

import java.util.ArrayList;
import java.util.List;

public class GameContext {

    public static World world;
    public static Box2DDebugRenderer b2dr;
    public static OrthographicCamera worldCamera;
    public static GameClient gameClient;
    public static Player currentPlayer;


    private static List<Player> players = new ArrayList<>();
    private static List<Bullet> bullets = new ArrayList<>();

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    public static void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public static List<Bullet> getAllBullets() {
        return new ArrayList<>(bullets);
    }

}
