import platformer.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                new Win(1, 3),
                new Item(4, 5),
                new Item(7, 8)
            };
            List<Item> items = new ArrayList<>();
            for (Item item : arritems) {
                items.add(item);
            }

            Level levels[][] = new Level[5][5], 
            	  level=new Level();
            levels[0][0]=new Level(name, player, enemies, platforms, items);
            Enemy enemyarr[]=new Enemy[5];
            for (int i=1;i<25;i++) {             //случайная генерация уровней 2-25
            	name="levelname";                                    
            	name+=String.valueOf(i);        
            	player=new Player(rand.rtwenty(),rand.rtwenty(),1);                              
            	enemies.clear();
            	for(int j=0;j<rand.rfive();j++) {
            		enemyarr[j]=new Enemy(rand.rtwenty(),rand.rtwenty());
            	}
            	enemies.addAll(Arrays.asList(enemyarr));
            	platforms.clear();
            	platforms.add(new Platform(rand.rtwenty(),rand.rtwenty()));
            	int itemType;
            	items.clear();
            	do {
            		itemType=(int)rand.binrand();
            		items.add(new Item(rand.rtwenty(),rand.rtwenty()));
            	}while (itemType!=1);
            	levels[i/5][i%5]=new Level(name,player,enemies,platforms,items);  
            }
            int menu = 0;

            while (true) {
                System.out.println("Меню:\n1) Играть\n2) Ввести новый уровень с клавиатуры\n3) Выход");
                menu = scanner.nextInt();
                int levelnumber;
                if (menu == 1) {
                	do{
                		System.out.println("Какой уровень хотите сыграть? (ваш написанный уровень сохраняется в ячейке 1)");
                		System.out.println("Доступны уровни от 1 до 25");
                		levelnumber=scanner.nextInt();
                	}while((levelnumber<1)||(levelnumber>25));
            		level=levels[(levelnumber-1)/5][(levelnumber-1)%5];
            		level.getItems().sort(null);
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
