package di_02_객체생성의존성제거;

public class Programmer {
	private String name;
	private int age;
	
	// 오늘의 주된 관심사
	// 프로그래머는 PC가 필요하다
	private Desktop desktop;
	
	public Programmer() {
		// 프로그래머 고용 -> 컴퓨터 하나 사줘
		this.desktop = new Desktop();
	}
	
	public void coding() {
		System.out.println(desktop.getInfo() + "으로 개발 고고");
	}
}
