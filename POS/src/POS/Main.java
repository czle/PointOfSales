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
		System.out.println("<�ش��ϴ� �޴��� �������ּ���>");
		System.out.println("1. ��      ǰ      ��      ��");
		System.out.println("2. ��  ��  ��  ��  ǰ  ��  ��");
		System.out.println("3. ��   ǰ   ��   ��   ��  ��");
		System.out.println("4. �� ǰ �� �� �� �� �� �� ��");
		System.out.println("5. �� �� �� �� Ȯ �� �� �� ��");
		System.out.println("6. ��     ��       ��      ��");
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
				System.out.println("�̿����ּż� �����մϴ�.");
				try {
					for (int i = 3; i > 0; i--) {
						System.out.println(i);
						Thread.sleep(1000);
					}
				} catch (Exception e) {
				}
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(choice);

			default:
				System.out.println("�Է��� �߸��Ǿ����ϴ�.");
				System.out.println("�ٽ� �������� �̵��մϴ�.");
				Main.main(null);
				break;

			}
		}
	}

}
