package shootemup

import kotlin.math.*

fun isColliding(c1: PointCollider, c2: PointCollider): Boolean {
    fun higherDimensionsAreZero(smaller: Vector, larger: Vector): Boolean {
        val lower = smaller.size
        val upper = larger.size - 1
        val range = IntRange(lower, upper)
        larger.slice(range).forEach {
            component -> if (component != 0) return false
        }
        return true
    }

    return when {
        c1.position.size < c2.position.size -> higherDimensionsAreZero(c1.position, c2.position)
        c1.position.size > c2.position.size -> higherDimensionsAreZero(c2.position, c1.position)
        else -> c1.position == c2.position
    }
}

fun isColliding(c1: PointCollider, c2: CircleCollider): Boolean {
    // https://www.mathsisfun.com/algebra/distance-2-points.html
    // Square root isn't required for distance comparison.
    val distanceSquared = (c2.position zip c1.position)
            .map { (a, b) -> (b - a).squared }
            .reduce { sum, component ->  sum + component }
    return distanceSquared < c2.radius.squared
}

fun isColliding(c1: CircleCollider, c2: PointCollider): Boolean {
    return isColliding(c2, c1)
}

fun isColliding(c1: CircleCollider, c2: CircleCollider): Boolean {
    return true
}


// class BoxCollider(private val occupiedSpace: Dimensions) : Collider() {

//     init { checkNumberOfDimensions() }

//     override fun isTouching(point: Vector): Boolean {
//         (occupiedSpace zip point).forEach {(range, magnitude) ->
//             if (!range.contains(magnitude))
//                 return false
//         }
//         return true
//     }

//     private fun checkNumberOfDimensions() {
//         if (occupiedSpace.size <= 0)
//             throw IllegalArgumentException("BoxCollider cannot be 0-dimensional")
//     }
// }

val Int.squared get() = this * this
