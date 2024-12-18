package platformer;
import java.util.Scanner;

public class Enemy {
    private Position position;
    private int health;

    public Enemy() {}

    public Enemy(int x, int y) {
        this.position = new Position(x, y);
        this.health = 1;
    }

    public Enemy(Position position) {
        this.position = position;
        this.health = 1;
    }

    public Position getPos() {
        return this.position;
    }

    public int getHealth() {
        return this.health;
    }

    public void outEnemy() {
        System.out.print("Информация о враге:  ");
        this.position.outPos();
        System.out.printf("Здоровье: %d  ", this.health);
    }

    public void takeDamage() {
        this.health--;
    }

    public void writeEnemy() {
        this.position.writePos();
        System.out.print("Введите здоровье: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка ввода.");
            scanner.next(); // Очистка некорректного ввода
        }
        this.health = scanner.nextInt();
        System.out.println("Новый враг готов!");
    }
}
