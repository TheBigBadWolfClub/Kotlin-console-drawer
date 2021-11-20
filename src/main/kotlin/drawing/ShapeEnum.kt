package drawing

enum class ShapeEnum(val char: Char) {
    CANVAS('C'),
    LINE('L'),
    RECTANGLE('R'),
    NO_SHAPE(';');

    companion object {
        private val map = values().associateBy(ShapeEnum::char)
        operator fun get(value: Char): ShapeEnum? = map[value]
    }
}
