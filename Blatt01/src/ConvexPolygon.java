
public class ConvexPolygon extends Polygon {

    // Parameterless constructor
    public ConvexPolygon() {
        this.vertices = new Vector2D[0];
    }

    // Constructor with vertices array
    public ConvexPolygon(Vector2D[] vertices) {
//        this.vertices = Arrays.copyOf(vertices, vertices.length);  // Deep copy of the array
        this.vertices = vertices; // Shallow copy

    }

    // Copy constructor
    public ConvexPolygon(ConvexPolygon other) {
//        this.vertices = new Vector2D[other.vertices.length];
//        for (int i = 0; i < other.vertices.length; i++) {
//            this.vertices[i] = new Vector2D(other.vertices[i]);  // Deep copy each vertex
//        }
        this.vertices = other.vertices;
    }

    public static Polygon[] somePolygons() {
        // array of polygons with size of 4
        Polygon[] testPolygons;
        testPolygons = new Polygon[4];

        // triangle
        Vector2D a = new Vector2D(0, 0);
        Vector2D b = new Vector2D(10, 0);
        Vector2D c =  new Vector2D(5, 5);
        testPolygons[0] = new Triangle(a,b,c);

        // tetragon
        Vector2D e = new Vector2D(0, 0);
        Vector2D f = new Vector2D(10, -5);
        Vector2D g =  new Vector2D(12, 2);
        Vector2D h = new Vector2D(3, 17);
        testPolygons[1] = new Tetragon(e, f, g, h);

        // pentagon with radius 1

        testPolygons[2] = new RegularPolygon(5, 1); // N=5, radius=1;

        // hexagon with radius 1
        testPolygons[3] = new RegularPolygon(6, 1); // N=5, radius=1;

        return testPolygons;
    }
    public static double totalArea(Polygon[] polygons) {
        double totalAreaSum = 0;
        for (Polygon singlePolygon : polygons) {
            totalAreaSum += singlePolygon.area();
        }


        return totalAreaSum;
    }

    private double distance(Vector2D firstPoint, Vector2D secondPoint) {
        return Math.sqrt(Math.pow(secondPoint.getX() - firstPoint.getX(), 2) + Math.pow(secondPoint.getY() - firstPoint.getY(), 2));
    }
    @Override
    public double perimeter(){
        if (this.vertices.length < 3) return 0;

        double perimeterValue = 0;

        // iterate over all sides calculating distance , then sum all together
        Vector2D previous = vertices[vertices.length - 1]; // Starting with the last vertex
        for (Vector2D current : this.vertices) {
            perimeterValue += distance(previous, current);
            previous = current;
        }
        return perimeterValue;

    }



    @Override
    public double area() {
        double totalArea = 0;
        int n = vertices.length;
        if (n < 3) return 0; // not a polygon

        Vector2D referencePoint = vertices[0];
        // Iterate over all sets of adjacent vertices along with the reference point
        for (int i = 1; i < n - 1; i++) {
            Triangle triangle = new Triangle(referencePoint, vertices[i], vertices[i + 1]);
            totalArea += triangle.area();
        }
        return totalArea;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ConvexPolygon([");
        for (Vector2D vertice : this.vertices) {
            sb.append(vertice).append(", ");
        }
        // Remove the last comma and space if the list is not empty
        if (this.vertices.length > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("])");
        return sb.toString();
    }

    public static void main(String[] args) {
        Polygon[] polygons = somePolygons();  // get the array of polygons

        for (Polygon polygon : polygons) { // iterate through the array of polygons
            if (polygon != null) {  // check if the polygon exists in the array
                System.out.printf("The area of the polygon with %s vertices is: %1f \n", polygon.vertices.length, polygon.area());
                System.out.println(polygon);
            }
        }
    }
}
