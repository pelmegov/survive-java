package ru.pelmegov.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import ru.pelmegov.game.Direction;
import ru.pelmegov.game.PlayerKeyboard;
import ru.pelmegov.graphic.animation.PlayerAnimation;

import java.util.Objects;
import java.util.Random;

public class Player {

    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 24;

    private int id = new Random().nextInt(Integer.MAX_VALUE);

    private final Body body;
    private final PlayerAnimation playerAnimation;
    private final PlayerKeyboard playerKeyboard;
    private final Direction direction;

    public Player(World world, Sprite playerSprite) {
        this.direction = null;
        this.body = makeBody(world, null);
        this.playerAnimation = new PlayerAnimation(playerSprite);
        this.playerKeyboard = new PlayerKeyboard();
    }

    public Player(Integer id, World world, Sprite playerSprite, Vector2 position, Direction direction) {
        this.id = id;
        this.direction = direction;
        this.body = makeBody(world, position);
        this.playerAnimation = new PlayerAnimation(playerSprite);
        this.playerKeyboard = new PlayerKeyboard();
    }

    public void setBodyTransform(Vector2 position) {
        this.body.setTransform(position, body.getAngle());
    }

    public Sprite prepareSprite() {
        Sprite playerSprite = playerAnimation.getSprite(calculateDirection());
        playerSprite.setOriginBasedPosition(this.body.getPosition().x, this.body.getPosition().y);
        return playerSprite;
    }

    private Body makeBody(World world, Vector2 position) {
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;

        if (position == null) {
            int xPosition = new Random().nextInt(Gdx.graphics.getWidth());
            int yPosition = new Random().nextInt(Gdx.graphics.getHeight());
            def.position.set(xPosition, yPosition);
        } else {
            def.position.set(position);
            System.out.println("Make body positions = [" + position.x + ", " + position.y + "]");
        }


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(PLAYER_WIDTH / 2f, PLAYER_HEIGHT / 2f);

        Body body = world.createBody(def);
        body.createFixture(shape, 1.0f);

        shape.dispose();
        return body;
    }

    private Direction calculateDirection() {
        Direction direction;
        if (this.direction == null) {
            direction = playerKeyboard.getDirectionKeyPressed(this.body);
        } else {
            direction = this.direction;
        }
        return direction;
    }

    public Body getBody() {
        return body;
    }

    public PlayerAnimation getPlayerAnimation() {
        return playerAnimation;
    }

    public PlayerKeyboard getPlayerKeyboard() {
        return playerKeyboard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}