package drawing

object Factories {
    fun validateInstance(shape: ShapeEnum): IValidation {
        val instanceClass = mapOf(
            ShapeEnum.CANVAS to CanvasValidation(),
            ShapeEnum.LINE to LineValidation(),
            ShapeEnum.RECTANGLE to RectangleValidation(),
            ShapeEnum.NO_SHAPE to InvalidInput()
        )
        return requireNotNull(instanceClass[shape])
    }

    fun getPointsCalculator(shape: ShapeEnum): IPointCalculator {
        val instanceClass = mapOf(
            ShapeEnum.CANVAS to Canvas(),
            ShapeEnum.LINE to Line(),
            ShapeEnum.RECTANGLE to Rectangle()
        )
        return requireNotNull(instanceClass[shape])
    }
}
