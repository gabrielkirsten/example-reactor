import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import reactor.core.publisher.Flux
import reactor.core.scheduler.Scheduler
import reactor.core.scheduler.Schedulers

class Main

val scheduler: Scheduler = Schedulers.single()

fun main() {
    val publisher:Flux<Int> = getDataFromDatabase()

    publisher.subscribe()

    tenSecondsFunction()
}

private fun getDataFromDatabase(): Flux<Int> {
    return Flux.fromStream {
        (1..10).map {
            runBlocking { delay(500) }
            println("processing $it")
            it
        }.stream()
    }.subscribeOn(scheduler)
}

private fun tenSecondsFunction() =
    repeat((1..10).count()) {
        println("waiting...")
        runBlocking { delay(1000) }
    }
