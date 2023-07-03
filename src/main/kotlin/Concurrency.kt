import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val time1 = System.currentTimeMillis()
    GlobalScope.launch {
        val task1 = async { performTask1() }
        val task2 = async { performTask2() }
        val task3 = async { performTask2() }
        val task4 = async { performTask2() }
        val task5 = async { performTask2() }
        val task6 = async { performTask2() }

        val result2 = task2.await()
        println("result2 completed")
        val result1 = task1.await()
        println("result1 completed")

        val result3 = task3.await()
        val result4 = task4.await()
        val result5 = task5.await()
        val result6 = task6.await()
        println("Result 1: $result1")
        println("Result 2: $result2")
        val time2 = System.currentTimeMillis();
        println(time2 - time1)
    }

    // Do other work while coroutines are executing

    Thread.sleep(2000) // Wait for coroutines to finish
    print("finish")
}

suspend fun performTask1(): String {
    delay(1000) // Simulating a long-running task
    return "Task 1 completed"
}

suspend fun performTask2(): String {
    delay(1500) // Simulating another long-running task
    return "Task 2 completed"
}
