package ru.pelmegov.network;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ru.pelmegov.game.Direction;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.model.player.Player;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClient {

    public static final int BUFFERS_SIZE = 1000000;
    public static final String HOST = "127.0.0.1";
    public static final int TCP_PORT = 54555;
    public static final int UDP_PORT = 54777;

    private Client client;

    public GameClient() {
        init();
    }

    public void init() {
        client = new Client(BUFFERS_SIZE, BUFFERS_SIZE);
        registerClasses();
        client.start();
        try {
            client.connect(Integer.MAX_VALUE, HOST, TCP_PORT, UDP_PORT);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Set) {
                    Set<GameRequest> gameRequests = (Set<GameRequest>) object;

                    for (GameRequest response : gameRequests) {
                        Optional<Player> playerExists =
                                GameContext.getAllPlayers().stream().filter(p -> p.getId() == response.getId()).findAny();

                        if (playerExists.isPresent()) {
                            Player player = playerExists.get();
                            GameContext.deletedPlayers.addAll(response.getDeletedUsers());
                            if (GameContext.deletedPlayers.contains(player.getId())) {
                                continue;
                            }
                            player.getBody().setTransform(response.getPlayerMovement(), player.getBody().getAngle());
                            player.setDirection(response.getDirection());
                            continue;
                        }

                        Player player = new Player(response.getId(), response.getPlayerMovement());
                        GameContext.addPlayer(player);
                    }
                }
            }
        });
    }

    public void send(int id, Vector2 playerMovement, Direction direction) {
        client.sendTCP(new GameRequest(id, playerMovement, direction));
    }

    private void registerClasses() {
        Kryo kryo = client.getKryo();
        kryo.register(Set.class);
        kryo.register(HashSet.class);
        kryo.register(Object.class);
        kryo.register(GameRequest.class);
        kryo.register(Direction.class);
        kryo.register(Vector2.class);
        kryo.register(CopyOnWriteArrayList.class);
        kryo.register(List.class);
    }

}