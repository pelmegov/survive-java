package ru.pelmegov.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import ru.pelmegov.game.model.ammunition.Bullet;
import ru.pelmegov.game.model.player.Player;

public class PlayerContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Object firstBody = contact.getFixtureA().getBody().getUserData();
        Object secondBody = contact.getFixtureB().getBody().getUserData();

        if (firstBody.getClass().equals(Player.class) && secondBody.getClass().equals(Bullet.class)) {
            Player player = (Player) firstBody;
            Bullet bullet = (Bullet) secondBody;
            if (!GameContext.getPlayerBullets(player.getId()).contains(bullet)) {
                player.damage(Bullet.BULLET_DAMAGE);
                bullet.markToDelete();
            }
        }

    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
