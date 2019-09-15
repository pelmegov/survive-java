package ru.pelmegov.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import ru.pelmegov.game.model.ammunition.Bullet;
import ru.pelmegov.game.model.player.Player;
import ru.pelmegov.network.GameClient;
import ru.pelmegov.physic.PhysicalObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameContext {

    public static World world;
    public static Box2DDebugRenderer b2dr;
    public static OrthographicCamera worldCamera;
    public static GameClient gameClient;
    public static Player currentPlayer;

    private static List<Player> players = new CopyOnWriteArrayList<>();
    private static List<Bullet> bullets = new CopyOnWriteArrayList<>();
    private static Map<Integer, List<Bullet>> playerBullets = new HashMap<>();

    public static List<Integer> deletedPlayers = new CopyOnWriteArrayList<>();

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }

    public static void addBullet(Bullet bullet) {
        bullets.add(bullet);

        List<Bullet> bullets = playerBullets.getOrDefault(currentPlayer.getId(), new ArrayList<>());
        bullets.add(bullet);
        playerBullets.put(currentPlayer.getId(), bullets);
    }

    public static List<Bullet> getAllBullets() {
        return new ArrayList<>(bullets);
    }

    public static List<Bullet> getPlayerBullets(Integer playerId) {
        return playerBullets.getOrDefault(playerId, new ArrayList<>());
    }

    public static void clean() {
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            Object data = body.getUserData();
            if (data instanceof PhysicalObject) {
                PhysicalObject physicalObject = (PhysicalObject) data;
                if (physicalObject.needDelete()) {
                    world.destroyBody(body);
                }
            }
        }
    }
}
