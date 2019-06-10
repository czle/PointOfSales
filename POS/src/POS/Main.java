package POS;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, SQLException {
		Scanner sc = new Scanner(System.in);
		SaveGoods SG1 = new SaveGoods();
		SalesList SL = new SalesList();

		System.out.println("------------------------------");
		System.out.println("<해당하는 메뉴를 선택해주세요>");
		System.out.println("1. 상      품      등      록");
		System.out.println("2. 등  록  된  상  품  보  기");
		System.out.println("3. 상   품   판   매   하  기");
		System.out.println("4. 상 품 수 정 및 삭 제 하 기");
		System.out.println("5. 구 매 내 역 확 인 및 출 력");
		System.out.println("6. 종     료       하      기");
		System.out.println("------------------------------");
		int choice = sc.nextInt();

		boolean go = true;
		while (go) {
			switch (choice) {
			case 1:
				SG1.choice();
				break;
			case 2:
				SG1.MenuList();
				break;
			case 3:
				SalesList.sales();
				break;
			case 4:
				SalesList.ChanDel();
				break;
			case 5:
				SalesList.basket();
				break;
			case 6:
				System.out.println("이용해주셔서 감사합니다.");
				try {
					for (int i = 3; i > 0; i--) {
						System.out.println(i);
						Thread.sleep(1000);
					}
				} catch (Exception e) {
				}
				System.out.println("프로그램을 종료합니다.");
				System.exit(choice);

			default:
				System.out.println("입력이 잘못되었습니다.");
				System.out.println("다시 메인으로 이동합니다.");
				Main.main(null);
				break;

			}
		}
	}

}
