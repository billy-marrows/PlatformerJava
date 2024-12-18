import platformer.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Player player=new Player(1, 2, 1); // Создание игрока
            Enemy enemy = new Enemy(2, 2);
            List<Enemy> enemies = new ArrayList<>();
            enemies.add(enemy);
            Position pos = new Position(3, 10);
            Platform platform = new Platform(pos);
            List<Platform> platforms = new ArrayList<>();
            platforms.add(platform);
            String name = "LevelName";

            // Работа с массивом объектов
            Item[] arritems = new Item[]{
                new Item(1, 3, 1),
                new Item(4, 5, 0),
                new Item(7, 8, 0)
            };
            List<Item> items = new ArrayList<>();
            for (Item item : arritems) {
                items.add(item);
            }

            Level level1 = new Level (name, player, enemies, platforms, items),
            	  level = new Level();
            
            int menu = 0;

            while (true) {
                System.out.println("Меню:\n1) Играть\n2) Ввести новый уровень с клавиатуры\n3) Выход");
                menu = scanner.nextInt();

                if (menu == 1) {
                	level=level1;
                    if (level.getPlayer().getHealth() == 0) {
                        level.getPlayer().addHealth(1);
                    }
                    int moveResult = 0;
                    while (moveResult != 27) {
                        if (moveResult == -1) {
                            System.out.println("Вы получили урон!");
                        }
                        if (level.getwin()) break;
                        if (level.getPlayer().getHealth()==0) break;
                        level.renderGrid();
                        moveResult = scanner.nextInt(); // Заменяем _getch() на ввод с клавиатуры
                        level.movePlayer(moveResult);
                        if (level.getPlayer().getHealth() == 0) {
                            System.out.println("Игра окончена!");
                            break;
                        }
                    }
                } else if (menu == 2) {
                    level1.writeLevel();
                } else if (menu == 3) {
                    break;
                }
            }
            scanner.close();
        }
    }
