package shootemup.collision

import kotlin.math.*

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
    val distanceBetweenCircles = abs(c1.position.get(0) - c2.position.get(0))
    val sumOfRadii = c1.radius + c2.radius
    return distanceBetweenCircles < sumOfRadii
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
