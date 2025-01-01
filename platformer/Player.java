package platformer;
import java.util.Scanner;
public class Player {
    private Position position;
    private int health;

    public Player() {} // Конструктор по умолчанию

    public Player(int x, int y, int health) {
        this.position = new Position(x, y);
        this.health = health;
    }

    public Player(Position position, int health) {
        this.position = position;
        this.health = health;
    }

    public Position getPos() { // Функция доступа к позиции
        return this.position;
    }

    public int getHealth() {
        if (this.health < 1) {
            System.out.println("Игрок погиб!");
        }
        return this.health;
    }

    public void outPlayer() { // Вывод информации об игроке
        System.out.print("Информация об игроке:   ");
        this.position.outPos();
        System.out.printf("Здоровье: %d  ", this.health);
    }

    public void addHealth(int d) {
        this.health += d;
    }

    public void movePlayer(int b) {
        switch (b) {
            case 2: // UP
                this.position.move(0, 1);
                break;
            case 4: // LEFT
                this.position.move(-1, 0);
                break;
            case 8: // DOWN
                this.position.move(0, -1);
                break;
            case 6: // RIGHT
                this.position.move(1, 0);
                break;
        }
        System.out.printf("Позиция игрока: %d %d\n", this.getPos().getX(), this.getPos().getY());
    }

    public void writePlayer() { // Создание игрока через ввод
        System.out.println("Создание игрока:");
        this.position.writePos();
        System.out.print("Введите здоровье: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка ввода.");
            scanner.next(); // Очистка некорректного ввода
        }
        this.health = scanner.nextInt();
        System.out.println("Игрок готов!");
        scanner.close();
    }
}

