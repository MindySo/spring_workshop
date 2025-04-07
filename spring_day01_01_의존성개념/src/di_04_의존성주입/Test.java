package di_04_의존성주입;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		// 프로그래머가 데스크톱에 대한 의존성을 가지고 있다.
		Desktop desktop = new Desktop();
		Laptop laptop = new Laptop();
		Programmer p = new Programmer(laptop);
		p.coding();
		
		Programmer p2 = new Programmer();
		p2.setComputer(desktop);
		p2.coding();
		
		Scanner sc = new Scanner(System.in);
		Programmer p3 = new Programmer();
		
		// 실제로 서비스를 배포 실행한 것과 같은 상태를 만들기 위해서
		while(true) {
			p3.setComputer(ComputerFactory.getcomputer(sc.next()));
			p3.coding();
		}
	}
}
