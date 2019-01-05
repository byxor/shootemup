package shootemup

import kotlin.math.*

sealed class Collider {
    abstract fun isTouching(point: Vector): Boolean
}

class Hitbox(private val occupiedSpace: Dimensions) : Collider() {

    init { checkNumberOfDimensions() }

    override fun isTouching(point: Vector): Boolean {
        (occupiedSpace zip point).forEach {(range, magnitude) ->
            if (!range.contains(magnitude))
                return false
        }
        return true
    }

    private fun checkNumberOfDimensions() {
        if (occupiedSpace.size <= 0)
            throw IllegalArgumentException("Hitbox cannot be 0-dimensional")
    }
}

class BlastRadius(private val position: Vector,
                  private val radius: Int) : Collider() {

    init { checkRadius() }

    override fun isTouching(point: Vector): Boolean {
        // https://www.mathsisfun.com/algebra/distance-2-points.html
        val distanceSquared = (position zip point)
                .map { (a, b) -> (b - a).squared }
                .reduce { sum, component ->  sum + component }
        return distanceSquared < radius.squared
    }

    private fun checkRadius() {
        if (radius < 0)
            throw IllegalArgumentException("Radius must be positive")
    }
}

fun Int.pow(n: Int) = toDouble().pow(n).toInt()
val Int.squared get() = pow(2)

