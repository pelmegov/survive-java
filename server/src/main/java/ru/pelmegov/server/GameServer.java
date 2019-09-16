package ru.pelmegov.server;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import ru.pelmegov.game.Direction;
import ru.pelmegov.network.GameRequest;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameServer {

    public static final int BUFFERS_SIZE = 1000000;
    public static final int TCP_PORT = 54555;
    public static final int UDP_PORT = 54777;

    private static Set<GameRequest> REQUESTS = new HashSet<>();
    private static Set<Integer> REMOVED_PLAYERS = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Server server = new Server(BUFFERS_SIZE, BUFFERS_SIZE);
        registerClasses(server);

        server.start();
        server.bind(TCP_PORT, UDP_PORT);
        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof GameRequest) {
                    REQUESTS.remove(object);
                    GameRequest object1 = (GameRequest) object;
                    REMOVED_PLAYERS.addAll(object1.getDeletedUsers());
                    REQUESTS.removeIf(request -> REMOVED_PLAYERS.contains(request.getId()));
                    object1.getDeletedUsers().clear();
                    object1.getDeletedUsers().addAll(REMOVED_PLAYERS);
                    REQUESTS.add(object1);
                    server.sendToAllTCP(REQUESTS);
                }
            }
        });
    }

    private static void registerClasses(Server server) {
        Kryo kryo = server.getKryo();
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
