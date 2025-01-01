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

            Level levels[] = new Level[5],  //Полноценная работа с массивом объектов
            	  level=new Level();
            levels[0]=new Level(name, player, enemies, platforms, items);
            for (int i=1;i<5;i++) {             //случайная генерация уровней 2-5
            	name="levelname";                                      //работа со строками
            	name+=String.valueOf(i);        
            	player=new Player(rand.rtwenty(),rand.rtwenty(),1);                               //использование вспомогательного класса
            	enemies.clear();
            	enemies.add(new Enemy(rand.rtwenty(),rand.rtwenty()));
            	platforms.clear();
            	platforms.add(new Platform(rand.rtwenty(),rand.rtwenty()));
            	int itemType;
            	items.clear();
            	do {
            		itemType=(int)rand.binrand();
            		items.add(new Item(rand.rtwenty(),rand.rtwenty(),itemType));
            	}while (itemType!=1);
            	levels[i]=new Level(name,player,enemies,platforms,items);  
            }
            int menu = 0;

            while (true) {
                System.out.println("Меню:\n1) Играть\n2) Ввести новый уровень с клавиатуры\n3) Выход");
                menu = scanner.nextInt();
                int levelnumber;
                if (menu == 1) {
                	do{
                		System.out.println("Какой уровень хотите сыграть? (ваш написанный уровень сохраняется в ячейке 1)");
                		System.out.println("1  2  3  4  5");
                		levelnumber=scanner.nextInt();
                	}while((levelnumber<1)||(levelnumber>5));
            		level=new Level(levels[levelnumber-1]);
                    if (level.getPlayer().getHealth() == 0) {
                        level.getPlayer().addHealth(1);
                    }
                    int moveResult = 0;
                    System.out.println("Управление:");
                    System.out.println("8 - вверх, 4 - влево, 6 - вправо, 2 - вниз");
                    System.out.println("27 - выход в меню");
                    while (moveResult != 27) {
                        if (moveResult == -1) {
                            System.out.println("Вы получили урон!");
                        }
                        if (level.getwin()) { 
                        	System.out.println("Уровень пройден! Пройди другой уровень или напиши свой.");
                        	System.out.println("Пройденных уровней: "+Level.getCompletedTimes());
                        	break;}
                        if (level.getPlayer().getHealth()==0) break;
                        System.out.println(level.getName());
                        level.renderGrid();
                        moveResult = scanner.nextInt(); // Заменяем _getch() на ввод с клавиатуры
                        level.movePlayer(moveResult);
                        if (level.getPlayer().getHealth() == 0) {
                            System.out.println("Игра окончена!");
                            break;
                        }
                    }
                } else if (menu == 2) {
                    level.writeLevel();
                } else if (menu == 3) {
                    break;
                }
            }
            scanner.close();
        }
    }
