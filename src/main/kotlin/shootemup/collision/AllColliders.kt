package shootemup.collision

import shootemup.geometry.Vector

sealed class Collider {}

data class PointCollider(val position: Vector) : Collider() {
    init {
        if (position.size <= 0)
            throw IllegalArgumentException("PointCollider cannot be 0-dimensional")
    }
}

data class CircleCollider(val position: Vector,
                          val radius: Int) : Collider() {
    init {
        if (radius < 0)
            throw IllegalArgumentException("Radius must be positive")
    }
}
