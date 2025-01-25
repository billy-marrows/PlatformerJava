package platformer;

public class Win extends Item {
	 public Win() {} // Конструктор по умолчанию

	    public Win(int x, int y) {
	        this.position = new Position(x, y);
	    }

	    public Win(Position position) {
	        this.position = position;
	    }
	    public Win(Position position,int i) {
	    	super(position);
	    }
	public int getType() {
		System.out.println("Вызов метода базового класса внутри "+ super.getType());
		return 1;
	}
	public int getType(int a) {
		System.out.println("Этот метод ничего не вызывает!");
		return 1;
	}
}
