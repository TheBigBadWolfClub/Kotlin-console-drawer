package drawing

import kotlin.system.exitProcess

class InputReader {
    fun readInput(): String  {
        val input = inputTrim(readLine() ?: "")
        testExit(input)
        return input
    }

    private fun testExit(input: String) {
        if (input.isNotEmpty() && input[0] == 'Q') {
            println("See you...")
            exitProcess(0)
        }
    }

    private fun inputTrim(input: String): String = input
        .trim()
        .replace("\\s+".toRegex(), " ")
        .toUpperCase()
}
