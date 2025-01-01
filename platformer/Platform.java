package platformer;
public class Platform {
    private Position position;

    public Platform() {}

    public Platform(int x, int y) {
        this.position = new Position(x, y);
    }

    public Platform(Position position) {
        this.position = position;
    }

    public Position getPos() {
        return this.position;
    }

    public void outPlatform() {
        System.out.print("Информация о платформе: ");
        this.position.outPos();
        System.out.println();
    }

    public void writePlatform() {
        this.position.writePos();
        System.out.println("Платформа готова!");
    }
}

