package shootemup.geometry

import shootemup.geometry.*;

data class Projectile(var position: Vector,
                      private val velocity: Vector) {

    init { checkNumberOfDimensions() }

    fun travel(): Projectile {
        val newPosition = this.position
                .zip(this.velocity)
                .map({(posElement, velElement) -> posElement + velElement })
        return this.copy(newPosition)
    }

    private fun checkNumberOfDimensions() {
        if (position.size != velocity.size)
            throw IllegalArgumentException(
                "Position and Velocity must have the same number of dimensions"
            )
    }
}

