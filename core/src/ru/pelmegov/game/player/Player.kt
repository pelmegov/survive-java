package ru.pelmegov.game.player

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import ru.pelmegov.game.Direction
import ru.pelmegov.game.PlayerKeyboard
import ru.pelmegov.graphic.animation.PlayerAnimation
import java.util.*

data class Player(val world: World, val playerSprite: Sprite) {

    var id = Random().nextInt(Integer.MAX_VALUE)

    var body: Body = makeBody(world, null)
    var playerAnimation: PlayerAnimation = PlayerAnimation(playerSprite)
    var playerKeyboard: PlayerKeyboard = PlayerKeyboard()
    var direction: Direction? = Direction.DOWN

    constructor(id: Int, world: World, playerSprite: Sprite, position: Vector2, direction: Direction?) : this(world, playerSprite) {
        this.id = id
        this.direction = direction
        this.body = makeBody(world, position)
    }

    fun setBodyTransform(position: Vector2) {
        this.body.setTransform(position, body.angle)
    }

    fun prepareCurrentUserSprite(): Sprite {
        direction = playerKeyboard.getDirectionByPressedKey()
        return prepareSprite()
    }

    fun prepareSprite(): Sprite {
        executeVelocityByDirection(direction)
        val playerSprite = playerAnimation.getSprite(this.direction)
        playerSprite.setOriginBasedPosition(this.body.position.x, this.body.position.y)
        return playerSprite
    }

    private fun executeVelocityByDirection(direction: Direction?) {
        var horizontalForce = 0f
        var verticalForce = 0f

        if (direction == Direction.LEFT) {
            horizontalForce -= 1
        }
        if (direction == Direction.RIGHT) {
            horizontalForce += 1
        }
        if (direction == Direction.UP) {
            verticalForce += 1
        }
        if (direction == Direction.DOWN) {
            verticalForce -= 1
        }

        body.linearVelocity =
                Vector2(horizontalForce * PlayerAnimation.PLAYER_DEFAULT_SPEED, verticalForce * PlayerAnimation.PLAYER_DEFAULT_SPEED)
    }

    private fun makeBody(world: World, position: Vector2?): Body {
        val def = BodyDef()
        def.type = BodyDef.BodyType.DynamicBody
        def.fixedRotation = true

        if (position == null) {
            val xPosition = Random().nextInt(Gdx.graphics.width)
            val yPosition = Random().nextInt(Gdx.graphics.height)
            def.position.set(xPosition.toFloat(), yPosition.toFloat())
        } else {
            def.position.set(position)
        }

        val shape = PolygonShape()
        shape.setAsBox(PLAYER_WIDTH / 2f, PLAYER_HEIGHT / 2f)

        val body = world.createBody(def)
        body.createFixture(shape, 1.0f)

        shape.dispose()
        return body
    }

    companion object {
        const val PLAYER_WIDTH = 32
        const val PLAYER_HEIGHT = 24
    }
}
