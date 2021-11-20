import drawing.*

fun main(args: Array<String>) {
    val app = App()
    app.setCanvasMaxSize(args)

    printHelp()

    val reader = InputReader()
    loop@ while (true) {
        print("enter command: ")
        val input: String = reader.readInput()
        input.isEmpty() && continue@loop
        (!app.validateInput(input)) && continue@loop
        app.processInputAndDraw(input)
    }
}

class App {

    fun setCanvasMaxSize(args: Array<String>) {
        ConsoleDrawer.canvasMaxSize = args.getOrNull(0)?.toIntOrNull() ?: ConsoleDrawer.canvasMaxSize
    }

    fun validateInput(input: String): Boolean {
        val shape = ShapeEnum[input[0]] ?: ShapeEnum.NO_SHAPE
        return Factories.validateInstance(shape).validate(input)
    }

    fun processInputAndDraw(input: String) {
        val shape = Shape(input)
        val shapeEnum = ShapeEnum[input[0]] ?: ShapeEnum.NO_SHAPE
        val calculator = Factories.getPointsCalculator(shapeEnum)
        ConsoleDrawer.points.putAll(calculator.process(shape))
        if (calculator is Canvas) {
            ConsoleDrawer.rowLimit = shape.p2.second
            ConsoleDrawer.colLimit = shape.p2.first
        }
        ConsoleDrawer.draw()
    }
}


/**
 * examples
 * canvas: C 20 4
 * lineH: L 1 2 6 2
 * lineV: L 6 3 6 4
 * rectangle: R 16 1 20 3
 */
fun printHelp() {
    println("Options: ")
    println("- C w h => create a new canvas of width w and height h.")
    println("- L x1 y1 x2 y2 => create a new line from (x1,y1) to (x2,y2)")
    println("- R x1 y1 x2 y2 => Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2)")
    println("- Q => quit")
    println()
}

