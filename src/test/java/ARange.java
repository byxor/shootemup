import static org.junit.Assert.*;
import org.junit.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.Arrays;
import static java.util.Arrays.asList;
import shootemup.geometry.Range;


public class ARange {

    @Test
    public void doesNotContainValuesTooLow() {
	asList(
	    new TestEntry(new Range(0, 5),      values(-1, -2, -3, -99)),
	    new TestEntry(new Range(10, 20),    values(9, 8, 7, 6)),
	    new TestEntry(new Range(200, 1000), values(199, 198, 100, 0))
        ).stream().forEach(entry -> doesNotContainValues(entry.range, entry.values));
    }

    @Test
    public void doesNotContainValuesTooHigh() {
	asList(
	    new TestEntry(new Range(0, 5),     values(6, 7, 8, 100)),
	    new TestEntry(new Range(10, 20),   values(21, 22, 999)),
	    new TestEntry(new Range(-50, -40), values(-39, -38, -37))
	).stream().forEach(entry -> doesNotContainValues(entry.range, entry.values));
    }

    @Test
    public void containsValuesAtTheLowerBoundary() {
	arbitraryRanges().forEach(range -> assertTrue(range.contains(range._lower)));
    }

    @Test
    public void doesNotContainValuesAtTheUpperBoundary() {
	arbitraryRanges().forEach(range -> assertFalse(range.contains(range._upper)));
    }

    @Test
    public void containsEveryValueInBetween() {
	arbitraryRanges().forEach( range ->
	    middleValuesOf(range).forEach( value ->
	        assertTrue(range.contains(value))));
    }

    private IntStream values(int... values) {
	return Arrays.stream(values);
    }

    private void doesNotContainValues(Range range, IntStream values) {
	values.forEach(value -> assertFalse(range.contains(value)));
    }

    private void containsValues(Range range, IntStream values) {
	values.forEach(value -> assertTrue(range.contains(value)));
    }

    private Stream<Range> arbitraryRanges() {
	List<Range> ranges = new ArrayList<>();
	arbitraryValues().forEach(i ->
	    arbitraryValues().forEach(j -> {
		int lower = i;
                int upper = lower + 1 + j;
		ranges.add(new Range(lower, upper));
	    }));
	return ranges.stream();
    }

    private IntStream middleValuesOf(Range range) {
	return IntStream.range(range._lower, range._upper - 1);
    }
 
    private IntStream arbitraryValues() {
	final int ARBITRARY_COVERAGE = 100;
	return IntStream.range(0, ARBITRARY_COVERAGE);
    }

}

class TestEntry {
    public final Range range;
    public final IntStream values;

    public TestEntry(Range range, IntStream values) {
        this.range = range;
	this.values = values;
    }
}
