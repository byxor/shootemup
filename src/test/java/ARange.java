import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;

import shootemup.geometry.Range;


public class ARange {

    @Test
    public void doesNotContainValuesTooLow() {
	asList(
	    new E1(new Range(0, 5),      asList(-1,  -2,  -3,  -99)),
	    new E1(new Range(10, 20),    asList(9,   8,   7,   6)),
	    new E1(new Range(200, 1000), asList(199, 198, 100, 0))
        ).stream().forEach(entry -> doesNotContainValues(entry.range, entry.values));
    }

    @Test
    public void doesNotContainValuesTooHigh() {
	asList(
	    new E1(new Range(0, 5),     asList(6, 7, 8, 100)),
	    new E1(new Range(10, 20),   asList(21, 22, 999)),
	    new E1(new Range(-50, -40), asList(-39, -38, -37))
	).stream().forEach(entry -> doesNotContainValues(entry.range, entry.values));
    }

    @Test
    public void containsTheLowerBoundary() {
	arbitraryRanges().stream().forEach(range -> assertTrue(range.contains(range._lower)));
    }

    @Test
    public void containsTheUpperBoundary() {
	arbitraryRanges().stream().forEach(range -> assertTrue(range.contains(range._upper)));
    }

    @Test
    public void containsEverythingInBetween() {
    	for (Range range : arbitraryRanges())
    	    for (int i = range._lower + 1; i < range._upper - 1; i++)
    		assertTrue(range.contains(i));
    }

    private void doesNotContainValues(Range range, List<Integer> values) {
	values.stream().forEach(value -> assertFalse(range.contains(value)));
    }

    private void containsValues(Range range, List<Integer> values) {
	values.stream().forEach(value -> assertTrue(range.contains(value)));
    }

    private List<Range> arbitraryRanges() {
	final int ARBITRARY_COVERAGE = 100;
	List<Range> ranges = new ArrayList<>();
	for (int lower = -ARBITRARY_COVERAGE; lower < ARBITRARY_COVERAGE; lower++)
	    for (int upper = lower + 1; upper < lower + ARBITRARY_COVERAGE; upper++)
		ranges.add(new Range(lower, upper));
	return ranges;
    }

}

class E1 {
    public final Range range;
    public final List<Integer> values;

    public E1(Range range, List<Integer> values) {
        this.range = range;
	this.values = values;
    }
}
