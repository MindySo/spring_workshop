package di_03_타입의존성제거;

public class Programmer {
	private String name;
	private int age;
	
	// 오늘의 주된 관심사
	// 프로그래머는 PC가 필요하다
	private Computer computer;
	
//	public Programmer() {
//		// 프로그래머 고용 -> 새 컴퓨터 하나 사줘
//		this.desktop = new Desktop();
//	}
	
	// 생성자 이용하여 의존성 주입
	public Programmer(Computer computer) {
		// 가지고 있는 데스크탑이 있다면 그거 쥐어주면서 고용
		this.computer = computer;
	}
	
	public void coding() {
		System.out.println(computer.getInfo() + "으로 개발 고고");
	}
}
