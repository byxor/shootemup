package shootemup

class Hitbox(private val occupiedSpace: Dimensions) {

    init { checkNumberOfDimensions() }

    fun contains(point: Vector): Boolean {
        occupiedSpace.zip(point).forEach({(range, magnitude) ->
            if (!range.contains(magnitude))
                return false
        })
        return true
    }

    private fun checkNumberOfDimensions() {
        if (occupiedSpace.size <= 0)
            throw IllegalArgumentException("Hitbox cannot be 0-dimensional")
    }
}
