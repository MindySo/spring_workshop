package di_04_의존성주입;

public class Laptop implements Computer {
	private String CPU;
	private String RAM;
	private String SSD;
	private String power;
	
	// 설정자, 접근자, 생성자
	@Override
	public String getInfo() {
		return "랩탑";
	}
}
