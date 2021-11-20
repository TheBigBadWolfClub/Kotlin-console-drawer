package drawing

class Canvas : IPointCalculator {
    companion object {
         const val hChar: Char = '-'
         const val vChar: Char =  '|'
    }
    override fun process(shape: Shape): Map<Pair<Int, Int>, Char> {
        return genericRectangle(shape, hChar, vChar)
    }
}

class Line : IPointCalculator {
    companion object {
        const val char = 'x'
    }
    override fun process(shape: Shape): Map<Pair<Int, Int>, Char> {
        return LineHelper(shape.p1.first, shape.p1.second, shape.p2.first, shape.p2.second, char)
            .getPoints()
            .toMap()
    }
}

class Rectangle: IPointCalculator {
    companion object {
        const val char = 'x'
    }
    override fun process(shape: Shape): Map<Pair<Int, Int>, Char> {
        return genericRectangle(shape, char, char)
    }

}

interface IPointCalculator {
    fun process(shape: Shape): Map<Pair<Int, Int>, Char>

    // ups, some magic number's in code
    fun genericRectangle(shape: Shape, xChar: Char, yChar: Char): Map<Pair<Int, Int>, Char> {
        return sequenceOf(
            LineHelper(shape.p1.first, shape.p1.second, shape.p2.first, shape.p1.second, xChar).getPoints(),
            LineHelper(shape.p2.first, shape.p1.second + 1, shape.p2.first, shape.p2.second - 1, yChar).getPoints(),
            LineHelper(shape.p1.first, shape.p2.second, shape.p2.first, shape.p2.second, xChar).getPoints(),
            LineHelper(shape.p1.first, shape.p1.second + 1, shape.p1.first, shape.p2.second - 1, yChar).getPoints()
        ).flatten().toMap()
    }
}
