import org.junit.Assert.*
import org.junit.*
import java.util.concurrent.ThreadLocalRandom
import shootemup.*
import shootemup.geometry.*

class AHitbox {

    @Test(expected = IllegalArgumentException::class)
    fun mustSpanAtLeastOneDimension() {
        Hitbox(emptyList<IRange>())
    }

    @Test
    fun doesNotContainAPointIfADimensionCheckFails() {
        manyNumbersOfDimensions().forEach({ numOfDimensions ->
            val dimensions = imperfectDimensions(numOfDimensions)
            val point = randomPoint(numOfDimensions)
            val hitbox = Hitbox(dimensions)
            assertFalse(hitbox.contains(point))
        })
    }

    @Test
    fun containsAPointIfAllDimensionChecksPass() {
        manyNumbersOfDimensions().forEach({ numOfDimensions->
            val dimensions = perfectDimensions(numOfDimensions)
            val point = randomPoint(numOfDimensions)
            val hitbox = Hitbox(dimensions)
            assertTrue(hitbox.contains(point))
        })
    }

    private fun manyNumbersOfDimensions(): IntRange {
        return IntRange(1, 20)
    }

    private fun imperfectDimensions(amount: Int): Dimensions {
        val dimensions = mutableListOf<IRange>()
        val random = ThreadLocalRandom.current()

        val correctAmount = if (amount == 1) 0 else random.nextInt(amount - 1)
        val incorrectAmount = amount - correctAmount

        intRange(correctAmount - 1).forEach({ dimensions.add(MockRange(true)) })
        intRange(incorrectAmount - 1).forEach({ dimensions.add(MockRange(false)) })

        return dimensions
    }

    private fun perfectDimensions(amount: Int): Dimensions {
        val dimensions = mutableListOf<IRange>()
        intRange(amount - 1).forEach({ dimensions.add(MockRange(true)) })
        return dimensions
    }

    private fun mockDimensions(amount: Int): Dimensions {
        val dimensions = mutableListOf<IRange>()
        intRange(amount - 1).forEach({ dimensions.add(MockRange()) })
        return dimensions
    }

    private fun randomPoint(numOfDimensions: Int): Vector {
        val random = ThreadLocalRandom.current()
        val magnitudes = IntArray(numOfDimensions) { random.nextInt(Integer.MAX_VALUE) }
        return Vector(magnitudes.asList())
    }

    private fun intRange(upper: Int) = IntRange(0, upper)
}
