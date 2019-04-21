package shootemup.collision

import kotlin.math.abs

fun isColliding(c1: PointCollider, c2: CircleCollider): Boolean {
    // https://www.mathsisfun.com/algebra/distance-2-points.html
    //
    // We don't need to calculate a square root to compare distances.
    // It's faster to square the other number before comparing.

    val distanceSquared = (c2.position zip c1.position)
            .map { (a, b) -> (b - a).squared }
            .reduce { sum, component ->  sum + component }

    return distanceSquared < c2.radius.squared
}

fun isColliding(c1: CircleCollider, c2: PointCollider): Boolean {
    return isColliding(c2, c1)
}

val Int.squared get() = this * this
