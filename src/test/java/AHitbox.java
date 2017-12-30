import static org.junit.Assert.*;
import org.junit.*;

import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.stream.IntStream;
import java.util.concurrent.ThreadLocalRandom;

import shootemup.Hitbox;
import shootemup.geometry.IRange;
import shootemup.geometry.Vector;


public class AHitbox {

    @Test(expected = IllegalArgumentException.class)
    public void mustSpanAtLeastOneDimension() {
	new Hitbox(asList());
    }

    @Test
    public void doesNotContainAPointIfADimensionCheckFails() {
	manyNumbersOfDimensions().forEach(numberOfDimensions -> {
	    mockDimensions = nonPerfectMockDimensions(numberOfDimensions);
            point = randomPoint(numberOfDimensions);
            hitbox = new Hitbox(mockDimensions);
	    assertFalse(hitbox.contains(point));
	});
    }

    @Test
    public void containsAPointIfAllDimensionChecksPass() {
	manyNumbersOfDimensions().forEach(numberOfDimensions -> {
	    mockDimensions = perfectMockDimensions(numberOfDimensions);
            point = randomPoint(numberOfDimensions);
            hitbox = new Hitbox(mockDimensions);
            assertTrue(hitbox.contains(point));
	});
    }

    private IntStream manyNumbersOfDimensions() {
	return IntStream.range(1, 20);
    }

    private List<IRange> nonPerfectMockDimensions(int amount) {
	List<IRange> mockDimensions = new ArrayList<>();
	ThreadLocalRandom random = ThreadLocalRandom.current();

	int correctAmount = amount == 1 ? 0 : random.nextInt(amount - 1);
	
	IntStream.range(0, correctAmount)
	    .forEach($ -> mockDimensions.add(new MockRange(true)));

	int incorrectAmount = amount - correctAmount;
	IntStream.range(0, incorrectAmount)
	    .forEach($ -> mockDimensions.add(new MockRange(false)));

	return mockDimensions;
    }

    private List<IRange> perfectMockDimensions(int amount) {
	List<IRange> mockDimensions = new ArrayList<>();
        IntStream.range(0, amount)
            .forEach($ -> mockDimensions.add(new MockRange(true)));
        return mockDimensions;
    }

    private List<IRange> mockDimensions(int amount) {
	List<IRange> mockDimensions = new ArrayList<>();
	IntStream.range(0, amount).forEach($ -> mockDimensions.add(new MockRange()) );
	return mockDimensions;
    }

    private Vector randomPoint(int numberOfDimensions) {
	ThreadLocalRandom random = ThreadLocalRandom.current();
	List<Integer> elements = IntStream
	    .range(0, numberOfDimensions)
	    .map($ -> random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE))
	    .boxed()
	    .collect(Collectors.toList());
	return new Vector(elements);
    }
    
    private Hitbox hitbox;
    private List<IRange> mockDimensions;
    private Vector point;
}
