package shootemup

data class Projectile(var position: Vector,
                      private val velocity: Vector) {

    init { checkNumberOfDimensions() }

    fun travel(): Projectile {
        val newPosition = (position zip velocity).map({ (p, v) -> p + v })
        return copy(newPosition)
    }

    private fun checkNumberOfDimensions() {
        if (position.size != velocity.size) {
            val message = "Position and Velocity must have the same number of dimensions"
            throw IllegalArgumentException(message)
        }
    }
}
