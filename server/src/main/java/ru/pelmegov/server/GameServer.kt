package ru.pelmegov.server

import com.badlogic.gdx.math.Vector2
import com.esotericsoftware.kryonet.Connection
import com.esotericsoftware.kryonet.Listener
import com.esotericsoftware.kryonet.Server
import ru.pelmegov.game.Direction
import ru.pelmegov.network.GameRequest

import java.io.IOException
import java.util.HashSet
import java.util.concurrent.CopyOnWriteArrayList

object GameServer {
    const val BUFFERS_SIZE = 1000000
    const val TCP_PORT = 54555
    const val UDP_PORT = 54777

    private val REQUESTS = HashSet<GameRequest>()
    private val REMOVED_PLAYERS = HashSet<Int>()

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val server = Server(BUFFERS_SIZE, BUFFERS_SIZE)
        registerClasses(server)

        server.start()
        server.bind(TCP_PORT, UDP_PORT)
        server.addListener(object : Listener() {
            override fun received(connection: Connection, obj: Any) {
                if (obj is GameRequest) {
                    REQUESTS.remove(obj)
                    val copy = obj
                    REMOVED_PLAYERS.addAll(copy.deletedUsers)
                    REQUESTS.removeIf { request -> REMOVED_PLAYERS.contains(request.id) }
                    copy.deletedUsers.clear()
                    copy.deletedUsers.addAll(REMOVED_PLAYERS)
                    REQUESTS.add(copy)
                    server.sendToAllTCP(REQUESTS)
                }
            }
        })
    }

    private fun registerClasses(server: Server) {
        server.kryo.apply {
            register(Set::class.java)
            register(HashSet::class.java)
            register(Any::class.java)
            register(GameRequest::class.java)
            register(Direction::class.java)
            register(Vector2::class.java)
            register(CopyOnWriteArrayList::class.java)
            register(List::class.java)
        }
    }
}
