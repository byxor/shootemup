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
        manyNumbersOfDimensions().forEach({ numberOfDimensions->
            mockDimensions = nonPerfectMockDimensions(numberOfDimensions)
            point = randomPoint(numberOfDimensions)
            hitbox = Hitbox(mockDimensions)
            assertFalse(hitbox.contains(point))
        })
    }

    @Test
    fun containsAPointIfAllDimensionChecksPass() {
        manyNumbersOfDimensions().forEach({ numberOfDimensions->
            mockDimensions = perfectMockDimensions(numberOfDimensions)
            point = randomPoint(numberOfDimensions)
            hitbox = Hitbox(mockDimensions)
            assertTrue(hitbox.contains(point))
        })
    }

    private fun manyNumbersOfDimensions(): IntRange {
        return IntRange(1, 20)
    }

    private fun nonPerfectMockDimensions(amount: Int): List<IRange> {
        val mockDimensions = mutableListOf<IRange>()
        val random = ThreadLocalRandom.current()

        val correctAmount = if (amount == 1) 0 else random.nextInt(amount - 1)
        IntRange(0, correctAmount - 1)
            .forEach({ _ -> mockDimensions.add(MockRange(true)) })

        val incorrectAmount = amount - correctAmount
        IntRange(0, incorrectAmount - 1)
            .forEach({ _ -> mockDimensions.add(MockRange(false)) })

        return mockDimensions
    }

    private fun perfectMockDimensions(amount: Int): List<IRange> {
        val mockDimensions = mutableListOf<IRange>()
        IntRange(0, amount - 1)
            .forEach({ _ -> mockDimensions.add(MockRange(true)) })
        return mockDimensions
    }

    private fun mockDimensions(amount:Int):List<IRange> {
        val mockDimensions = mutableListOf<IRange>()
        IntRange(0, amount - 1)
	    .forEach({ _ -> mockDimensions.add(MockRange()) })
        return mockDimensions
    }

    private fun randomPoint(numberOfDimensions:Int):Vector {
        val random = ThreadLocalRandom.current()
        val elements = IntRange(0, numberOfDimensions - 1)
            .map({ _ -> random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE) })
        return Vector(elements)
    }

    private lateinit var hitbox:Hitbox
    private lateinit var mockDimensions:List<IRange>
    private lateinit var point:Vector
}
