package ru.pelmegov.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import lombok.Data;
import ru.pelmegov.game.player.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameContext {

    private World world;
    private Box2DDebugRenderer b2dr;
    private OrthographicCamera worldCamera;


    private List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getAllPlayers() {
        return players;
    }

}
