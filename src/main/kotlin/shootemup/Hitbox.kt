package shootemup

import shootemup.geometry.*

class Hitbox(private val dimensions: Dimensions) {

    init { checkNumberOf(dimensions) }

    fun contains(point: Vector): Boolean {
        for (i in dimensions.indices) {
            val dimension = dimensions.get(i)
            val element = point.get(i)
            if (!dimension.contains(element))
                return false
        }
        return true
    }

    private fun checkNumberOf(dimensions: Dimensions) {
        if (dimensions.size <= 0)
            throw IllegalArgumentException("Hitbox cannot be 0-dimensional")
    }

}
