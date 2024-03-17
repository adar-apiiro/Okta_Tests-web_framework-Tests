
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ApiServerApplication

@RestController
class GreetingController {

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Greeting {
        return Greeting(counter.incrementAndGet(), "Hello, $name")
    }

    companion object {
        val counter = java.util.concurrent.atomic.AtomicLong()
    }
}

data class Greeting(val id: Long, val content: String)

fun main(args: Array<String>) {
    SpringApplication.run(ApiServerApplication::class.java, *args)
}
