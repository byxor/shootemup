import org.junit.Assert.*
import org.junit.*
import shootemup.geometry.*

class ARange {

    @Test
    fun containsValuesAtTheLowerBoundary() {
        manyRanges().forEach({ assertTrue(it.contains(it._lower)) })
    }

    @Test
    fun doesNotContainValuesAtTheUpperBoundary() {
        manyRanges().forEach({ assertFalse(it.contains(it._upper)) })
    }

    @Test
    fun containsEveryValueInBetween() {
        manyRanges().forEach({ range ->
            range.innerValues.forEach({
                assertTrue(range.contains(it))
            })
        })
    }

    @Test
    fun doesNotContainValuesTooLow() {
        listOf(
            T(Range(0, 5),      listOf(-1, -2, -3, -99)),
            T(Range(10, 20),    listOf(9, 8, 7, 6)),
            T(Range(200, 1000), listOf(199, 198, 100, 0))
        ).forEach(::doesNotContainValues)
    }

    @Test
    fun doesNotContainValuesTooHigh() {
        listOf(
            T(Range(0, 5),     listOf(6, 7, 8, 100)),
            T(Range(10, 20),   listOf(21, 22, 999)),
            T(Range(-50, -40), listOf(-39, -38, -37))
        ).forEach(::doesNotContainValues)
    }

    private fun manyRanges(): List<Range> {
        val numbers = IntRange(0, 99)
        return numbers.map({ i -> numbers.map({ j -> Range(i, i + j + 1) }) })[0]
    }

    private fun doesNotContainValues(t: T) {
        t.values.forEach({ value -> assertFalse(t.range.contains(value)) })
    }
}

val Range.innerValues get() = IntRange(_lower, _upper - 2)

internal data class T(val range: Range, val values: List<Int>)

