import kotlinx.coroutines.*
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

fun main() = runBlocking {
    val time1 = System.currentTimeMillis()
    val numbers = (1..10).toList()

    val results = numbers.asFlow()
        .map { number ->
            async(Dispatchers.Default) {
                println("call:$number")
                performHeavyComputation(number)
            }
        }
        .toList()
        .map {
            println("response ")
            it.await()
        }
    val time2=System.currentTimeMillis()
    println("Time takse is : ${time2-time1}")

    println("Results: $results")
}

suspend fun performHeavyComputation(number: Int): String {
    delay(1000) // Simulating a heavy computation
    return "Result for $number"
}
