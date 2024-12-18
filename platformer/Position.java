package platformer;
import java.util.Scanner;
public class Position {
    private int x;
    private int y;

    public Position() {}

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void outPos() {
        System.out.printf("Позиция: %d %d  ", this.x, this.y);
    }

    public void writePos() {
        System.out.print("Введите координаты: ");
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        while (!flag) {
            if (scanner.hasNextInt()) {
                this.x = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    this.y = scanner.nextInt();
                    if (this.x >= 0 && this.x < 19 && this.y >= 0 && this.y < 20) {
                        flag = true;
                    } else {
                        System.out.println("Ошибка ввода. Координаты должны быть в пределах.");
                    }
                } else {
                    System.out.println("Ошибка ввода.");
                    scanner.next(); // очистка некорректного ввода
                }
            } else {
                System.out.println("Ошибка ввода.");
                scanner.next(); // очистка некорректного ввода
            }
        }
    }
}
