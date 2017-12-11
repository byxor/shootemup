import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;
import java.util.ArrayList;

import shootemup.Hitbox;
import shootemup.geometry.Point;


public class AHitbox {

    @Test
    public void checksIfTheXValueOfThePointIsWithinRange() {
	for (Point point : arbitraryPoints()) {
	    cleanAllState();
	    assertFalse(mockHorizontalRange.calledWith(point.x));
	    hitbox.contains(point);
	    assertTrue(mockHorizontalRange.calledWith(point.x));
	}
    }

    @Test
    public void checksIfTheYValueOfThePointIsWithinRange() {
    	for (Point point : arbitraryPoints()) {
	    cleanAllState();
    	    assertFalse(mockVerticalRange.calledWith(point.y));
    	    hitbox.contains(point);
    	    assertTrue(mockVerticalRange.calledWith(point.y));
    	}
    }

    @Test
    public void doesNotContainPointIfAllRangeChecksFail() {
	mockHorizontalRange = new MockRange(false);
	mockVerticalRange = new MockRange(false);
	hitbox = new Hitbox(mockHorizontalRange, mockVerticalRange);
	for (Point point : arbitraryPoints())
	    assertFalse(hitbox.contains(point));
    }

    @Test
    public void doesNotContainPointIfHorizontalRangeCheckFails() {
	mockHorizontalRange = new MockRange(false);
	mockVerticalRange = new MockRange(true);
	hitbox = new Hitbox(mockHorizontalRange, mockVerticalRange);
	for (Point point : arbitraryPoints())
	    assertFalse(hitbox.contains(point));
    }

    @Test
    public void doesNotContainPointIfVerticalRangeCheckFails() {
	mockHorizontalRange = new MockRange(true);
	mockVerticalRange = new MockRange(false);
	hitbox = new Hitbox(mockHorizontalRange, mockVerticalRange);
	for (Point point : arbitraryPoints())
	    assertFalse(hitbox.contains(point));
    }

    @Test
    public void containsPointIfAllRangeChecksPass() {
	mockHorizontalRange = new MockRange(true);
	mockVerticalRange = new MockRange(true);
	hitbox = new Hitbox(mockHorizontalRange, mockVerticalRange);
	for (Point point : arbitraryPoints())
	    assertTrue(hitbox.contains(point));
    }

    private List<Point> arbitraryPoints() {
	final int ARBITRARY_COVERAGE = 200;
	List<Point> points = new ArrayList<>();
	for (int x = -ARBITRARY_COVERAGE; x < ARBITRARY_COVERAGE; x++)
	    for (int y = -ARBITRARY_COVERAGE; y < ARBITRARY_COVERAGE; y++)
		points.add(new Point(x, y));
	return points;
    }

    private void cleanAllState() {
	mockHorizontalRange = new MockRange();
	mockVerticalRange = new MockRange();
	hitbox = new Hitbox(mockHorizontalRange, mockVerticalRange);
    }

    private MockRange mockHorizontalRange;
    private MockRange mockVerticalRange;
    private Hitbox hitbox;
}
