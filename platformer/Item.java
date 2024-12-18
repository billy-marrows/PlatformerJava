package platformer;
import java.util.Scanner;
public class Item {
    private Position position;
    private int type;

    public Item() {} // Конструктор по умолчанию

    public Item(int x, int y, int type) {
        this.position = new Position(x, y);
        this.type = type;
    }

    public Item(Position position, int type) {
        this.position = position;
        this.type = type;
    }

    public Position getPos() { // Команды для доступа к членам класса
        return this.position;
    }

    public int getType() {
        return this.type;
    }

    public void outItem() { // Вывод информации о предмете
        System.out.print("Информация о предмете: ");
        this.position.outPos();
        System.out.printf("Тип: %d\n", this.type);
    }

    public void writeItem() { // Создание предмета путём ввода из консоли
        this.position.writePos();
        System.out.print("Введите тип предмета: ");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка ввода.");
            scanner.next(); // Очистка некорректного ввода
        }
        this.type = scanner.nextInt();
    }
}

