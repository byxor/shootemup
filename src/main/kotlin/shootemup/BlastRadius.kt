package shootemup

import shootemup.*
import kotlin.math.*

data class BlastRadius(private val position: Vector,
                       private val radius: Int) {

    init {
        checkRadius()
    }

    fun contains(point: Vector): Boolean {
        // https://www.mathsisfun.com/algebra/distance-2-points.html
        return (position zip point)
                .map({ (a, b) -> (b - a).squared() })
                .reduce({ sum, component ->  sum + component })
                .squareRoot() < radius
    }

    private fun checkRadius() {
        if (radius < 0)
            throw IllegalArgumentException("Radius must be positive")
    }
}

fun Int.squared() = toDouble().pow(2).toInt()
fun Int.squareRoot() = sqrt(toDouble())
