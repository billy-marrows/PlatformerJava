package platformer;
import java.util.Scanner;
public class Item implements Comparable<Item>,Cloneable {
    protected Position position;


    public Item() {} // Конструктор по умолчанию

    public Item(int x, int y) {
        this.position = new Position(x, y);
    }

    public Item(Position position) {
        this.position = position;

    }

    public Position getPos() { // Команды для доступа к членам класса
        return this.position;
    }

    public int getType() {
        return 0;
    }

    public void outItem() { // Вывод информации о предмете
        System.out.print("Информация о предмете: ");
        this.position.outPos();
        System.out.printf("Тип: %d\n", this.getType());
    }

    public void writeItem() { // Создание предмета путём ввода из консоли
        this.position.writePos();
    }
    public int compareTo(Item item) {
    	if(this.getPos().getX()==item.getPos().getX()) return 0;
    	else if(this.getPos().getX()>item.getPos().getX())return 1;
    	else return -1;
    }
    public Item clone() throws CloneNotSupportedException {
        Item clonedItem = (Item) super.clone();
        clonedItem.position = new Position(this.position);
        return clonedItem;
    }

}

