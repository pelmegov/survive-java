package ru.pelmegov.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.physics.box2d.Body
import ru.pelmegov.game.Direction.*
import ru.pelmegov.game.player.Player

import ru.pelmegov.graphic.animation.PlayerAnimation.PLAYER_DEFAULT_SPEED

class PlayerKeyboard {

    fun getDirectionKeyPressed(player: Player) {
        var horizontalForce = 0f
        var verticalForce = 0f

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            horizontalForce -= 1
            player.direction = LEFT
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            horizontalForce += 1
            player.direction = RIGHT
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            verticalForce += 1
            player.direction = UP
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            verticalForce -= 1
            player.direction = DOWN
        }

        player.body.setLinearVelocity(
                horizontalForce * PLAYER_DEFAULT_SPEED,
                verticalForce * PLAYER_DEFAULT_SPEED
        )
    }
}
