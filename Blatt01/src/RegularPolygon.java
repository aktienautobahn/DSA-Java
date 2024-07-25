// Diese Klasse implementiert nur *zentrierte* reguläre Polygone, also mit midpoint = (0, 0).

public class RegularPolygon extends ConvexPolygon {
    // TODO
    int N;
    private double radius;

    // constructor
    public RegularPolygon(int N, double radius) {
        // TODO
        super();
        this.N = N; // vertices amount
        this.radius = radius; // radius size
        calculateVertices();

    }

    // copy constructor
    public RegularPolygon(RegularPolygon polygon) {
        // TODO
        this.N = polygon.N;
        this.radius = polygon.radius;
        this.vertices = new Vector2D[this.N];
        calculateVertices();


    }

    private void calculateVertices() {
        this.vertices = new Vector2D[this.N];
        for (int i = 0; i < this.N; i++) {
            double angle = 2 * Math.PI * i / N; // angle calculation
            double x = this.radius * Math.cos(angle); // x coordinate calculation
            double y = this.radius * Math.sin(angle); // y coordinate calculation
            this.vertices[i] = new Vector2D(x, y); // assign new Vector2D instance to each position in the array
        }
    }

    public void resize(double newradius) {
        this.setRadius(newradius);
        calculateVertices();
    }

    public double getRadius() {
        return this.radius;
    }

     private void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "RegularPolygon{" +
                "N=" + N +
                ", radius=" + radius +
                '}';
    }

    public static void main(String[] args) {
        RegularPolygon pentagon = new RegularPolygon(5, 1);
        System.out.println("Der Flächeninhalt des " + pentagon + " beträgt " + pentagon.area() + " LE^2.");
//        RegularPolygon otherpentagon = pentagon;      // Dies funktioniert nicht!
        RegularPolygon otherpentagon = new RegularPolygon(pentagon);
        pentagon.resize(10);
        System.out.println("Nach Vergrößerung: " + pentagon + " mit Fläche " + pentagon.area() + " LE^2.");
        System.out.println("Die Kopie: " + otherpentagon + " mit Fläche " + otherpentagon.area() + " LE^2.");
        /*
        Die erwartete Ausgabe ist:
Der Flächeninhalt des RegularPolygon{N=5, radius=1.0} beträgt 2.377641290737883 LE^2.
Nach Vergrößerung: RegularPolygon{N=5, radius=10.0} mit Fläche 237.7641290737884 LE^2.
Die Kopie: RegularPolygon{N=5, radius=1.0} mit Fläche 2.377641290737883 LE^2.
         */
    }
}

