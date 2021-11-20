package drawing

class CanvasValidation : IValidation {
    override fun validate(input: String): Boolean {
        val options = input.splitToSequence(' ')
        return isExpectedNumberOfArgs(options, 3)
                && isValidChar(options.elementAt(0), 'C')
                && (!ConsoleDrawer.isCanvasDefined()).also { if (!it) println("Canvas can only be defined once.") }
                && validateAllInts(options, 3)
                && (options.elementAt(1).toInt() < ConsoleDrawer.canvasMaxSize
                && options.elementAt(2).toInt() < ConsoleDrawer.canvasMaxSize)
            .also { if (!it) println("Canvas Max size is: ${ConsoleDrawer.canvasMaxSize}") }
    }
}

class LineValidation : IValidation {

    // CodeReview: some duplicated code with isValidRectangle
    override fun validate(input: String): Boolean {
        val options = input.splitToSequence(' ')
        return (ConsoleDrawer.isCanvasDefined()).also { if (!it) println("Create canvas first.") }
                && isValidChar(options.elementAt(0), 'L')
                && isCoordinatesInputValid(options)
                && isValidLineOrientation(options)
    }
}


class RectangleValidation : IValidation {
    // CodeReview: some duplicated code with isValidLine
    override fun validate(input: String): Boolean {
        val options = input.splitToSequence(' ')
        return (ConsoleDrawer.isCanvasDefined()).also { if (!it) println("Create canvas first.") }
                && isValidChar(options.elementAt(0), 'R')
                && isCoordinatesInputValid(options)
    }
}

class InvalidInput : IValidation {
    override fun validate(input: String): Boolean {
        return false.also { println("Invalid input, not supported.") }
    }

}


interface IValidation {
    fun validate(input: String): Boolean

    fun isCoordinatesInputValid(options: Sequence<String>): Boolean {
        return isExpectedNumberOfArgs(options, 5)
                && validateAllInts(options, 5)
                && isInOrder(options.elementAt(1).toInt(), options.elementAt(3).toInt())
                && isInOrder(options.elementAt(2).toInt(), options.elementAt(4).toInt())
                && isInsideCanvas(options.elementAt(3).toInt(), ConsoleDrawer.colLimit)
                && isInsideCanvas(options.elementAt(4).toInt(), ConsoleDrawer.rowLimit)
    }

    fun validateAllInts(options: Sequence<String>, size: Int): Boolean {
        return (1 until size).asSequence()
            .filter { !isValidInt(options.elementAt(it)) }
            .none()
    }

    fun isValidLineOrientation(options: Sequence<String>): Boolean {
        return (options.elementAt(1) == options.elementAt(3)
                || options.elementAt(2) == options.elementAt(4))
            .also { if (!it) println("Invalid line orientation") }
    }

    fun isExpectedNumberOfArgs(options: Sequence<String>, expected: Int): Boolean =
        (options.count() == expected)
            .also { if (!it) println("Invalid number of args ${options.count()}, expected $expected") }

    fun isValidChar(option: String, expected: Char) =
        (option.length == 1 && option[0] == expected)
            .also { if (!it) println("Invalid char $option") }

    private fun isInOrder(x1: Int, x2: Int) = (x1 <= x2)
        .also { if (!it) println("Invalid points p1 > p2, $x1 > $x2") }

    private fun isInsideCanvas(x: Int, canvasLimit: Int) = (x < canvasLimit)
        .also { if (!it) println("Invalid outside of canvas, $x > $canvasLimit ") }

    /**
     * made public due to some bug in filter function invoked in [validateIntGroups]
     *
     * if left protected or private, will receive exception
     * =>  java.lang.VerifyError: Bad invokespecial instruction: current class isn't assignable to reference class.
     *
     * reproduced in JVM11 kotlin 1.4.10 (windows 10)
     *
     * if JVM 1.8, this bug does not occur
     *
     */
    fun isValidInt(option: String): Boolean = (option.toIntOrNull() != null && option.toInt() > 0)
        .also { if (!it) println("Invalid coordinate $option") }
}


