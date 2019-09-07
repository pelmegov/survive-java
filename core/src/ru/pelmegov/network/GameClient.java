package ru.pelmegov.network;

import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import ru.pelmegov.game.Direction;
import ru.pelmegov.game.GameContext;
import ru.pelmegov.game.player.Player;
import ru.pelmegov.graphic.sprite.SpriteContainer;
import ru.pelmegov.graphic.sprite.SpriteName;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GameClient {

    private Client client;
    private GameContext gameContext;

    public GameClient(GameContext gameContext) {
        this.gameContext = gameContext;
        init();
    }

    public void init() {
        client = new Client(1000000, 1000000);
        client.start();
        try {
            client.connect(Integer.MAX_VALUE, "127.0.0.1", 54555, 54777);
        } catch (IOException e) {
            throw new IllegalStateException();
        }

        registerClasses();

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                Set<GameRequest> gameRequests = (Set<GameRequest>) object;

                for (GameRequest response : gameRequests) {
                    Player player = new Player(
                            response.getId(),
                            gameContext.getWorld(),
                            SpriteContainer.getInstance().getSprite(SpriteName.PLAYER_1),
                            response.getPlayerMovement(),
                            response.getDirection()
                    );
                    gameContext.addPlayer(player);
                }
            }
        });
    }

    private void registerClasses() {
        Kryo kryo = client.getKryo();
        kryo.register(Set.class);
        kryo.register(HashSet.class);
        kryo.register(Object.class);
        kryo.register(GameRequest.class);
        kryo.register(Direction.class);
        kryo.register(Vector2.class);
    }

    public void send(int id, Vector2 playerMovement, Direction direction) {
        client.sendTCP(new GameRequest(id, playerMovement, direction));
    }
}
