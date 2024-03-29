package ru.pelmegov.network;

import com.badlogic.gdx.math.Vector2;
import ru.pelmegov.game.Direction;
import ru.pelmegov.game.GameContext;

import java.util.List;
import java.util.Objects;

public class GameRequest {

    private int id;
    private Vector2 playerMovement;
    private Direction direction;
    private final List<Integer> deletedUsers;

    public GameRequest() {
        this.deletedUsers = GameContext.deletedPlayers;
    }

    public GameRequest(int id, Vector2 playerMovement, Direction direction) {
        this.id = id;
        this.playerMovement = playerMovement;
        this.direction = direction;
        this.deletedUsers = GameContext.deletedPlayers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector2 getPlayerMovement() {
        return playerMovement;
    }

    public void setPlayerMovement(Vector2 playerMovement) {
        this.playerMovement = playerMovement;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameRequest that = (GameRequest) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public List<Integer> getDeletedUsers() {
        return deletedUsers;
    }
}