package ru.pelmegov.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ru.pelmegov.game.Direction.*

class PlayerKeyboard {

    fun getDirectionByPressedKey(): Direction? {
        var direction: Direction? = null

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction = LEFT
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction = RIGHT
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction = UP
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction = DOWN
        }

        return direction
    }
}
