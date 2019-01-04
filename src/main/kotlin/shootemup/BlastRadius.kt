package shootemup

import shootemup.*
import kotlin.math.*

data class BlastRadius(private val position: Vector,
                       private val radius: Int) {

    init { checkRadius() }

    fun contains(point: Vector): Boolean {
        return abs(point.get(0) - position.get(0)) < radius
    }

    private fun checkRadius() {
        if (radius < 0)
            throw IllegalArgumentException("Radius must be positive")
    }
}
