package di_02_객체생성의존성제거;

public class Test {
	public static void main(String[] args) {
		// 프로그래머가 데스크톱에 대한 의존성을 가지고 있다.
		Programmer p = new Programmer();
		p.coding();
		
		
	}
}
