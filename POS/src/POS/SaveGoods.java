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
	Connection conn = null; // DB연결된 상태(세션)을 담은 객체
	PreparedStatement pstm = null; // SQL 문을 나타내는 객체
	ResultSet rs = null; // 쿼리문을 날린것에 대한 반환값을 담을 객체

	public void choice() throws IOException, SQLException {

		System.out.println("---------상품을 등록해 주세요-----------");
		System.out.println("***등록방식) 코드 : 이름 : 가격 : 수량***");
		System.out.println("-----------------------------------------");
		System.out.println("뒤로가기 원하시면 '뒤로' 를 입력해주세요");
		System.out.println("-----------------------------------------");
		Menu = sc.next();
		if (Menu.equals("뒤로"))
			Main.main(null);
		MenuSplit = Menu.split(":");

		try {
			if (true) {

				int Amount = Integer.parseInt(MenuSplit[3]);
				Saved[cnt] = new Saved(MenuSplit[0], MenuSplit[1], MenuSplit[2], Amount);
				++cnt;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("잘못된 입력입니다.");
			System.out.println("다시 입력해주세요.");
			return;

		}
		System.out.println("------------등록되었습니다-------------");
		System.out.println();
		System.out.println("추가로 등록하시겠습니까?");
		System.out.println("1. 예          2. 아니오");
		choice2 = sc.nextInt();

		if (choice2 == 1) {

		} else if (choice2 == 2) {
			System.out.println("메인메뉴로 돌아갑니다.");
			System.out.println();
			Main.main(null);

		} else {
			System.out.println("잘못된 선택입니다.");
			System.out.println("메인메뉴로 돌아갑니다.");
			System.out.println();
			Main.main(null);
		}
	}

	public void MenuList() throws IOException, SQLException {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("MM" + "월 " + "dd" + "일 " + "hh" + "시 " + "mm" + "분");
		Time = dayTime.format(new Date(time));
		if (cnt == 0) {
			System.out.println("등록된 상품이 없습니다.");
			System.out.println("상품등록 후 다시 입력해주세요");
			Main.main(null);

		} else {
			System.out.println(Time + "까지의 등록 상품 입니다.");
			try {
				for (int i = 0; i < 3; i++) {
					Thread.sleep(400);
					System.out.println("*");
				}
			} catch (Exception e) {

			}

			for (int i = 0; i < cnt; i++) {
				System.out.print(i + 1 + "번째 상품 : ");
				Saved[i].show();

			}

			Main.main(null);
		}
	}

}
