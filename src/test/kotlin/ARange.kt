import org.junit.Assert.*
import org.junit.*
import shootemup.geometry.*

class ARange {

    @Test
    fun doesNotContainValuesTooLow() {
        listOf(
	    TestEntry(Range(0, 5),      listOf(-1, -2, -3, -99)),
            TestEntry(Range(10, 20),    listOf(9, 8, 7, 6)),
            TestEntry(Range(200, 1000), listOf(199, 198, 100, 0))
        ).forEach({ data -> doesNotContainValues(data.range, data.values) })
    }

    @Test
    fun doesNotContainValuesTooHigh() {
        listOf(
            TestEntry(Range(0, 5),     listOf(6, 7, 8, 100)),
            TestEntry(Range(10, 20),   listOf(21, 22, 999)),
            TestEntry(Range(-50, -40), listOf(-39, -38, -37))
        ).forEach({ data -> doesNotContainValues(data.range, data.values) })
    }

    @Test
    fun containsValuesAtTheLowerBoundary() {
        arbitraryRanges().forEach({ range -> assertTrue(range.contains(range._lower)) })
    }

    @Test
    fun doesNotContainValuesAtTheUpperBoundary() {
        arbitraryRanges().forEach({ range -> assertFalse(range.contains(range._upper)) })
    }

    @Test
    fun containsEveryValueInBetween() {
        arbitraryRanges().forEach({ range ->
            middleValuesOf(range).forEach({ value-> assertTrue(range.contains(value)) }) })
    }

    private fun doesNotContainValues(range:Range, values: List<Int>) {
        values.forEach({ value -> assertFalse(range.contains(value)) })
    }

    private fun containsValues(range:Range, values: List<Int>) {
        values.forEach({ value -> assertTrue(range.contains(value)) })
    }

    private fun arbitraryRanges(): List<Range> {
        val ranges = mutableListOf<Range>()
        arbitraryValues().forEach({ i ->
            arbitraryValues().forEach({ j ->
                val lower = i
                val upper = lower + 1 + j
                ranges.add(Range(lower, upper))
            })
        })
        return ranges
    }

    private fun middleValuesOf(range: Range): IntRange {
        return IntRange(range._lower, range._upper - 2)
    }

    private fun arbitraryValues(): IntRange {
        val ARBITRARY_COVERAGE = 100
        return IntRange(0, ARBITRARY_COVERAGE - 1)
    }

}

internal data class TestEntry(val range: Range, val values: List<Int>)
