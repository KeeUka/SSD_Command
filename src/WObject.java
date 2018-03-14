public abstract class WObject {

    private int x;
    private int y;

    private int dx;
    private int dy;

    public WObject(int x, int y) {
        this.x = x;
        this.y = y;

        this.dx = this.dy = 0;
    }

    public void turnNorth() {
        dx = 0;
        dy = -1;
    }

    public void turnSouth() {
        dx = 0;
        dy = 1;
    }

    public void turnWest() {
        dx = -1;
        dy = 0;
    }

    public void turnEast() {
        dx = 1;
        dy = 0;
    }

    public void move() {
        this.x += dx;
        this.y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hit(WObject wObj) {
        return x == wObj.x && y == wObj.y;
    }
}
