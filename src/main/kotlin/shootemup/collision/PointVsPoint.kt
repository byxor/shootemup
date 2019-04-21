package shootemup.collision

import shootemup.collision.PointCollider
import shootemup.geometry.*

fun isColliding(c1: PointCollider, c2: PointCollider): Boolean = when {
    c1.position.size < c2.position.size -> higherDimensionsAreZero(c1.position, c2.position)
    c1.position.size > c2.position.size -> higherDimensionsAreZero(c2.position, c1.position)
    else -> c1.position == c2.position
}

private fun higherDimensionsAreZero(smallerVector: Vector, largerVector: Vector): Boolean {
    val uncheckedRange = IntRange(smallerVector.size, largerVector.size - 1)

    val uncheckedComponents = largerVector.slice(uncheckedRange)

    uncheckedComponents.forEach { component ->
        if (component != 0) {
            return false
        }
    }

    return true
}
