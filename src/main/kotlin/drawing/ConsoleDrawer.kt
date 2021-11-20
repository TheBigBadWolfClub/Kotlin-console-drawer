package drawing

object ConsoleDrawer {

    val points: MutableMap<Pair<Int, Int>, Char> = mutableMapOf()
    var canvasMaxSize: Int = 100
    var rowLimit: Int = 0
    var colLimit: Int = 0

    fun isCanvasDefined() = rowLimit > 0 && colLimit > 0

    fun draw() {
        (0 .. rowLimit).asSequence()
            .forEach { line ->
                run {
                    (0 .. colLimit).asSequence()
                        .forEach { col -> print(points.getOrDefault(Pair(col, line), ' ')) }
                    println()
                }
            }
        println()
    }
}
