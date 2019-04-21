package shootemup.collision

import kotlin.math.abs

fun isColliding(point: PointCollider, circle: CircleCollider): Boolean {
    // https://www.mathsisfun.com/algebra/distance-2-points.html
    //
    // We don't need to calculate a square root to compare distances.
    // It's faster to square the other number before comparing.

    val distanceSquared = (circle.position zip point.position)
            .map { (a, b) -> (b - a).squared }
            .reduce { sum, component ->  sum + component }

    return distanceSquared < circle.radius.squared
}

fun isColliding(point: CircleCollider, circle: PointCollider): Boolean {
    return isColliding(circle, point)
}

val Int.squared get() = this * this
