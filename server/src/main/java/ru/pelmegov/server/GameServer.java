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
import java.util.Set;

public class GameServer {

    private static Set<GameRequest> REQUESTS = new HashSet<>();

    public static void main(String[] args) throws IOException {
        Server server = new Server(1000000, 1000000);
        server.start();

        server.bind(54555, 54777);

        registerClasses(server);

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof GameRequest) {
                    REQUESTS.remove(object);
                    REQUESTS.add((GameRequest) object);
                    connection.sendTCP(REQUESTS);
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
    }

}
