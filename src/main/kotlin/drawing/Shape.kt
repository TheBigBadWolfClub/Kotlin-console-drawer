package drawing

data class Shape(private val input: String) {

    val shape: ShapeEnum
        get() = calculateShape()

    val p1: Pair<Int, Int>
        get() = calculateP1()

    val p2: Pair<Int, Int>
        get() = calculateP2()


    private fun calculateP1(): Pair<Int, Int> {
        if(shape == ShapeEnum.CANVAS) return Pair(0,0)
        return Pair(
            input.splitToSequence(' ').elementAt(1).toInt(),
            input.splitToSequence(' ').elementAt(2).toInt()
        )
    }

    private fun calculateP2(): Pair<Int, Int> {
        if(shape == ShapeEnum.CANVAS) {
            // ups, some magic number's in code
            val width = input.splitToSequence(' ').elementAt(1).toInt() + 1
            val height = input.splitToSequence(' ').elementAt(2).toInt() + 1
            return Pair(width, height)

        }
        return Pair(
            input.splitToSequence(' ').elementAt(3).toInt(),
            input.splitToSequence(' ').elementAt(4).toInt()
        )
    }

    private fun calculateShape(): ShapeEnum {
        val shapeChar = input.splitToSequence(' ').elementAt(0)[0]
        return ShapeEnum[shapeChar] ?: ShapeEnum.NO_SHAPE
    }
}
