package chapter.novelties.records;

public record Point(int x, int y) {
    public Point {
       if (x<0 || y<0) throw new IllegalArgumentException("bad");
    }
    public Point() {
        this(0);
    }
    public Point(int x) {
        this(x,0);
    }
    public int x() {
        return this.x;
    }
    public double distanceTo(Point p) {
        int dx = x-p.x;
        int dy = y-p.y;
        return Math.sqrt(dx*dx+dy*dy);
    }
    public static double distanceBetween(Point p1,Point p2) {
        return p1.distanceTo(p2);
    }
}

