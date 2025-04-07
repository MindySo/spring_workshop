package di_02_의존성개념;

public class Programmer {
	private String name;
	private int age;
	
	// 오늘의 주된 관심사
	// 프로그래머는 PC가 필요하다
	private Desktop desktop;
	
//	public Programmer() {
//		// 프로그래머 고용 -> 새 컴퓨터 하나 사줘
//		this.desktop = new Desktop();
//	}
	
	// 객체 생성 의존성 제거
	public Programmer(Desktop desktop) {
		// 가지고 있는 데스크탑이 있다면 그거 쥐어주면서 고용
		this.desktop = desktop;
		
	}
	
	public void coding() {
		System.out.println(desktop.getInfo() + "으로 개발 고고");
	}
}
