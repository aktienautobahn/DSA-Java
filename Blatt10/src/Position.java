public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // copy constructor
    public Position(Position that) {
        this(that.x, that.y);
    }

    /**
     * Displace the position by the specified values.
     *
     * @param xd Displacement in x-direction
     * @param yd Displacement in y-direction
     */
    public void displace(int xd, int yd) {
        x += xd;
        y += yd;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        /* TODO */
        if (o == this) return true;
        else if (o != null && this.getClass() == o.getClass()) {
            Position other = (Position)o;
            return this.x == other.x && this.y == other.y;
        } else {
            return false;
        }

    }


    @Override
    public int hashCode() {
        return 20 * y + x;
    }
}

