package POS;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SaveGoods {
	int choice2, menuCho;
	static int cnt = 0;;
	String Menu, WCode, Time;
	String[] MenuSplit = null;
	static Saved[] Saved = new Saved[20];
	Scanner sc = new Scanner(System.in);
	Main Ma = new Main();
	static boolean go2 = true;
	Connection conn = null; // DB����� ����(����)�� ���� ��ü
	PreparedStatement pstm = null; // SQL ���� ��Ÿ���� ��ü
	ResultSet rs = null; // �������� �����Ϳ� ���� ��ȯ���� ���� ��ü

	public void choice() throws IOException, SQLException {

		System.out.println("---------��ǰ�� ����� �ּ���-----------");
		System.out.println("***��Ϲ��) �ڵ� : �̸� : ���� : ����***");
		System.out.println("-----------------------------------------");
		System.out.println("�ڷΰ��� ���Ͻø� '�ڷ�' �� �Է����ּ���");
		System.out.println("-----------------------------------------");
		Menu = sc.next();
		if (Menu.equals("�ڷ�"))
			Main.main(null);
		MenuSplit = Menu.split(":");

		try {
			if (true) {

				int Amount = Integer.parseInt(MenuSplit[3]);
				Saved[cnt] = new Saved(MenuSplit[0], MenuSplit[1], MenuSplit[2], Amount);
				++cnt;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("�߸��� �Է��Դϴ�.");
			System.out.println("�ٽ� �Է����ּ���.");
			return;

		}
		System.out.println("------------��ϵǾ����ϴ�-------------");
		System.out.println();
		System.out.println("�߰��� ����Ͻðڽ��ϱ�?");
		System.out.println("1. ��          2. �ƴϿ�");
		choice2 = sc.nextInt();

		if (choice2 == 1) {

		} else if (choice2 == 2) {
			System.out.println("���θ޴��� ���ư��ϴ�.");
			System.out.println();
			Main.main(null);

		} else {
			System.out.println("�߸��� �����Դϴ�.");
			System.out.println("���θ޴��� ���ư��ϴ�.");
			System.out.println();
			Main.main(null);
		}
	}

	public void MenuList() throws IOException, SQLException {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("MM" + "�� " + "dd" + "�� " + "hh" + "�� " + "mm" + "��");
		Time = dayTime.format(new Date(time));
		if (cnt == 0) {
			System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
			System.out.println("��ǰ��� �� �ٽ� �Է����ּ���");
			Main.main(null);

		} else {
			System.out.println(Time + "������ ��� ��ǰ �Դϴ�.");
			try {
				for (int i = 0; i < 3; i++) {
					Thread.sleep(400);
					System.out.println("*");
				}
			} catch (Exception e) {

			}

			for (int i = 0; i < cnt; i++) {
				System.out.print(i + 1 + "��° ��ǰ : ");
				Saved[i].show();

			}

			Main.main(null);
		}
	}

}
