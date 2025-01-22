package platformer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;



public class Level {
	public static final int UP = 2;
	public static final int LEFT =4;
	public static final int RIGHT =6;
	public static final int DOWN = 8;
    private String name;
    private Player player;
    private List<Enemy> enemies;
    private List<Platform> platforms;
    private List<Item> items;
    private boolean win;
    private char[][] grid;
    private static int completedTimes =0;

    public Level() {
        this.enemies = new ArrayList<>();
        this.platforms = new ArrayList<>();
        this.items = new ArrayList<>();
        this.grid = new char[20][20];
    }

    public Level(String name, Player player, List<Enemy> enemies, List<Platform> platforms, List<Item> items) {
        this.name = name;
        this.player = player;
        this.enemies = enemies;
        this.platforms = platforms;
        this.items = items;
        this.win = false;
        this.grid = new char[20][20];
    }

    public Level(Level level) {
		this.name= level.name;
		this.player=level.player;
		this.enemies=level.enemies;
		this.platforms = level.platforms;
		this.items=level.items;
		this.win=false;
		this.grid = new char[20][20];
	}

	public void outLevel() { // Вывод информации об уровне
        System.out.println("Информация об уровне:");
        System.out.printf("  Название: %s\n", name);
        this.player.outPlayer();
        for (Enemy enemy : this.enemies) {
            System.out.print("  ");
            enemy.outEnemy();
        }
        for (Platform platform : this.platforms) {
            System.out.print("  ");
            platform.outPlatform();
        }
        for (Item item : this.items) {
            System.out.print("  ");
            item.outItem();
        }
        if (this.win) {
            System.out.println("  Уровень пройден.\n");
        } else {
            System.out.println("  Уровень не пройден.\n");
        }
    }

    public void pickupItem(int x, int y) {
        Item pickedUpItem = null;
        for (Item item : this.items) {
            if (item.getPos().getX() == x && item.getPos().getY() == y) {
                pickedUpItem = item;
                this.items.remove(item);
                break;
            }
        }
        if (pickedUpItem != null) {
            switch (pickedUpItem.getType()) {
                case 0:
                    System.out.println("Подобран предмет на здоровье.");
                    this.player.addHealth(1);
                    break;
                case 1:
                    System.out.println("Вы победили!");
                    completedTimes++;
                    this.win = true;
                    break;
            }
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public void addPlatform(Platform platform) {
        this.platforms.add(platform);
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void deleteEnemy(int x, int y) {
        for (Enemy enemy : this.enemies) {
            if (enemy.getPos().getX() == x && enemy.getPos().getY() == y) {
                this.enemies.remove(enemy);
                break;
            }
        }
    }
    public boolean getwin() {
    	return this.win;
    }
    public String getName() {
        return this.name;
    }

    public Player getPlayer() {
        return this.player;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public List<Enemy> getEnemies() {
        return this.enemies;
    }

    public List<Platform> getPlatforms() {
        return this.platforms;
    }

    public boolean isWin() {
        return this.win;
    }

    public void writeLevel() { // Создание уровня путём ввода из консоли
        Scanner scanner = new Scanner(System.in);
        this.player.writePlayer();
        System.out.print("Введите название уровня: ");
        this.name = scanner.nextLine();
        int choice = 1;
        System.out.println("Создание предметов:");
        while (choice != 0) {
            Item it = new Item();
            it.writeItem();
            this.items.add(it);
            System.out.print("Добавить ещё предметов? 0 = нет, 1 = да: ");
            choice = scanner.nextInt();
        }

        choice = 1;
        System.out.println("Создание врагов:");
        while (choice != 0) {
            Enemy en = new Enemy();
            en.writeEnemy();
            this.enemies.add(en);
            System.out.print("Добавить ещё врагов? 0 = нет, 1 = да: ");
            choice = scanner.nextInt();
        }

        choice = 1;
        System.out.println("Создание платформ:");
        while (choice != 0) {
            Platform pl = new Platform();
            pl.writePlatform();
            this.platforms.add(pl);
            System.out.print("Добавить ещё платформ? 0 = нет, 1 = да: ");
            choice = scanner.nextInt();
        }
        scanner.close();
    }
    public void renderGrid() { // Отрисовка сетки
        char[][] playGrid = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                playGrid[j][i] = '*'; // Заполнение сетки символами '*'
            }
        }

        for (Enemy enemy : this.enemies) {
            if (enemy.getHealth() != 0) {
                playGrid[enemy.getPos().getX()][enemy.getPos().getY()] = 'E';
            }
        }
        for (Platform platform : this.platforms) {
            playGrid[platform.getPos().getX()][platform.getPos().getY()] = 'X';
        }
        for (Item item : this.items) {
            if (item.getType() == 1) {
                playGrid[item.getPos().getX()][item.getPos().getY()] = 'W';
            } else {
                playGrid[item.getPos().getX()][item.getPos().getY()] = 'I';
            }
        }
        playGrid[this.getPlayer().getPos().getX()][this.getPlayer().getPos().getY()] = 'P';

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                System.out.print(playGrid[j][i]);
                this.grid[j][i] = playGrid[j][i];
            }
            System.out.println();
        }
    }

    public int movePlayer(int dest) {
        if (dest == 27) return 27; // Если dest равен 27, возвращаем 27
        int x = this.getPlayer().getPos().getX();
        int y = this.getPlayer().getPos().getY();

        switch (dest) {
            case UP:
                if (y + 1 >= 0 && y + 1 < 20) {
                    if (grid[x][y + 1] == '*') {
                        this.getPlayer().movePlayer(UP);
                    } else if (grid[x][y + 1] == 'I' || grid[x][y + 1] == 'W') {
                        this.getPlayer().movePlayer(UP);
                        this.pickupItem(x, y + 1);
                    } else if (grid[x][y + 1] == 'E') {
                        this.getPlayer().addHealth(-1);
                        this.deleteEnemy(x, y + 1);
                        return -2;
                    }
                }
                break;
            case LEFT:
                if (x - 1 >= 0 && x - 1 < 20) {
                    if (grid[x - 1][y] == '*') {
                        this.getPlayer().movePlayer(LEFT);
                    } else if (grid[x - 1][y] == 'I' || grid[x - 1][y] == 'W') {
                        this.getPlayer().movePlayer(LEFT);
                        this.pickupItem(x - 1, y);
                    } else if (grid[x - 1][y] == 'E') {
                        this.getPlayer().addHealth(-1);
                        this.deleteEnemy(x - 1, y);
                        return -2;
                    }
                }
                break;
            case DOWN:
                if (y - 1 >= 0 && y - 1 < 20) {
                    if (grid[x][y - 1] == '*') {
                        this.getPlayer().movePlayer(DOWN);
                    } else if (grid[x][y - 1] == 'I' || grid[x][y - 1] == 'W') {
                        this.getPlayer().movePlayer(DOWN);
                        this.pickupItem(x, y - 1);
                    } else if (grid[x][y - 1] == 'E') {
                        this.getPlayer().addHealth(-1);
                        this.deleteEnemy(x, y - 1);
                        return -2;
                    }
                }
                break;
            case RIGHT:
                if (x + 1 >=  0 && x + 1 < 20) {
                    if (grid[x + 1][y] == '*') {
                        this.getPlayer().movePlayer(RIGHT);
                    } else if (grid[x + 1][y] == 'I' || grid[x + 1][y] == 'W') {
                        this.getPlayer().movePlayer(RIGHT);
                        this.pickupItem(x + 1, y);
                    } else if (grid[x + 1][y] == 'E') {
                        this.getPlayer().addHealth(-1);
                        this.deleteEnemy(x + 1, y);
                        return -2;
                    }
                }
                break;
        }
        return 0; // Возвращаем 0, если движение не было выполнено
    }
    public static int getCompletedTimes() {
    	return completedTimes;
    }
}

