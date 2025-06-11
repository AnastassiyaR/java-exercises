package ee.taltech.iti0202.factory;

import ee.taltech.iti0202.polygon.Hexagon;
import ee.taltech.iti0202.polygon.Pentagon;
import ee.taltech.iti0202.polygon.Polygon;
import ee.taltech.iti0202.polygon.Square;
import ee.taltech.iti0202.polygon.Triangle;


public class PolygonFactory {

    private static final int THREE_SIDES = 3;
    private static final int FOUR_SIDES = 4;
    private static final int FIVE_SIDES = 5;
    private static final int SIX_SIDES = 6;

    /**
     * Factory makes a new Polygon with given amount of sides.
     * @param numberOfSides number of sides on the polygon
     * @return new Polygon class with correct number of sides ( numberOfSides = 4 -> new Square() )
     */
    public static Polygon getPolygon(int numberOfSides) {

        return switch (numberOfSides) {
            case THREE_SIDES -> new Triangle();
            case FOUR_SIDES -> new Square();
            case FIVE_SIDES -> new Pentagon();
            case SIX_SIDES -> new Hexagon();
            default -> throw new IllegalArgumentException();
        };
    }
}
