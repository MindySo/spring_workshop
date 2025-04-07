package di_02_의존성개념;

public class Test {
	public static void main(String[] args) {
		// 프로그래머가 데스크톱에 대한 의존성을 가지고 있다.
		Desktop desktop = new Desktop();
		Programmer p = new Programmer(desktop);
		p.coding();
		
		
	}
}
