package drawing

// CodeReview: constructor parameters should be private
// CodeReview: LineHelper can be an object class
// CodeReview: to many args in constructor
class LineHelper constructor(val x1: Int, val y1: Int, val x2: Int, val y2: Int, var char: Char) {
    fun getPoints(): Sequence<Pair<Pair<Int, Int>, Char>> {
        return getRange().asSequence().map { position -> buildPoint(position) }
    }

    private fun isLineHorizontal(): Boolean = y1 == y2
    private fun getLinePosition(): Int = if (isLineHorizontal()) y1 else x1
    private fun getRange(): IntRange = if (isLineHorizontal()) (x1..x2) else (y1..y2)

    private fun buildPoint(position: Int): Pair<Pair<Int, Int>, Char> =
        if (isLineHorizontal()) Pair(Pair(position, getLinePosition()), char)
        else Pair(Pair(getLinePosition(), position), char)
}
