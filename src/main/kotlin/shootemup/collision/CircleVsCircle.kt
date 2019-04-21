package shootemup.collision

import kotlin.math.abs

fun isColliding(c1: CircleCollider, c2: CircleCollider): Boolean {
    val distanceBetweenCircles = abs(c1.position.get(0) - c2.position.get(0))
    val sumOfRadii = c1.radius + c2.radius
    return distanceBetweenCircles < sumOfRadii
}
