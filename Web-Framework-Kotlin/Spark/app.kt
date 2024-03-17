import com.google.gson.Gson
import spark.Route
import spark.Spark.get
import spark.Spark.post
import spark.Spark.put
import spark.Spark.delete
import spark.Spark.halt
import spark.Spark.port
import spark.Spark.stop

data class Message(var id: Int, var content: String)

fun main() {
    val messages = mutableListOf(
        Message(1, "Hello, World!"),
        Message(2, "Kotlin and Spark make a great pair!")
    )
    val gson = Gson()

    port(4567)

    get("/messages", Route { _, res ->
        res.type("application/json")
        gson.toJson(messages)
    })

    get("/messages/:id", Route { req, res ->
        res.type("application/json")
        messages.find { it.id == req.params(":id").toInt() }
            ?.let { gson.toJson(it) }
            ?: halt(404, "Message not found")
    })

    post("/messages", Route { req, _ ->
        val message = gson.fromJson(req.body(), Message::class.java)
        messages.add(message)
        "Message added with ID ${message.id}"
    })

    put("/messages/:id", Route { req, _ ->
        val id = req.params(":id").toInt()
        val newContent = gson.fromJson(req.body(), Message::class.java).content
        messages.find { it.id == id }?.let {
            it.content = newContent
            "Message with ID $id updated"
        } ?: halt(404, "Message not found")
    })

    delete("/messages/:id", Route { req, _ ->
        val id = req.params(":id").toInt()
        if (messages.removeIf { it.id == id }) {
            "Message with ID $id deleted"
        } else {
            halt(404, "Message not found")
        }
    })

    get("/stop", Route { _, _ ->
        stop()
        "Server stopped"
    })
}
