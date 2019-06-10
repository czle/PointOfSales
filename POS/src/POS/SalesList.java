package POS;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SalesList {
	static Scanner sc = new Scanner(System.in);

	public static void sales() throws IOException , SQLException{
		String Time, WCode;
		boolean go1 = false, go3 = true;
		int cnt = 0, choAmo = 0;
		int i, j;
		SaveGoods SG = new SaveGoods();
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMddhhmmss");
		Time = dayTime.format(new Date(time));
		cnt = SaveGoods.cnt;
		Saved[] Saved2 = new Saved[20];
		Saved2 = SG.Saved;
		int AmoArr[] = new int[20];
		FileWriter Fil1 = new FileWriter("orders.txt", true); // 이건 판매내역
		FileWriter Fil2 = new FileWriter("goods.txt", true); // 이건 잔고내역

		if (cnt == 0) {
			System.out.println("등록된 상품이 없습니다.");
			System.out.println("상품등록 후 다시 입력해주세요");
			Main.main(null);
		}

		System.out.println("상품 판매하기 입니다.");
		System.out.println("주문코드 : " + Time);
		System.out.println("상품코드를 입력해 주세요");
		System.out.println("뒤로가기 원하시면 '뒤로'를 입력해주세요");
		WCode = sc.next();

		for (i = 0; i < cnt; i++) {

			if (WCode.equals(Saved2[i].Code)) {
				loop1: while (go3) {
					System.out.println("해당 상품의 정보입니다." + "\n");
					System.out.println(i + 1 + "번째 상품의 이름 : " + Saved2[i].Name + "\n");
					System.out.println(i + 1 + "번째 상품의 가격 : " + Saved2[i].Price + "\n");
					System.out.println(i + 1 + "번째 상품의 수량 : " + Saved2[i].Amount + "\n");
					go1 = true;
					loop2: while (go1) {
						System.out.println("수량을 입력해주세요");
						choAmo = sc.nextInt();

						for (j = 0; j < cnt; j++) {
							AmoArr[j] = choAmo;
						}
						go1 = false;

						System.out.println(choAmo + "개 구매하시겠습니까?" + "\n");
						System.out.println("1.구매하기  2.다시입력" + "\n");

						int amoSel = sc.nextInt();
						if (amoSel == 1) {
							j = i;
							if (Saved2[i].Amount < AmoArr[j]) {
								System.out.println("재고가 부족합니다.");
								System.out.println("다시 입력해주세요" + "\n");
								if (Saved2[i].Amount == 0) {
									SalesList.sales();
								}
								continue loop1;

							}
							if (Saved2[i].Amount >= AmoArr[j]) {
								if ((Saved2[i].Amount - choAmo) < 0)
									System.out.println("재고가 부족합니다." + "\n");

								Saved2[i].Amount = Saved2[i].Amount - choAmo;
								Fil2.write(Time + " : " + Saved2[i].Code + " : " + Saved2[i].Name + " : "
										+ Saved2[i].Price + " : " + Saved2[i].Amount + "\n");

								int Price = Integer.parseInt(Saved2[i].Price);
								Fil1.write(Time + " : " + Saved2[i].Code + " : " + Saved2[i].Name + " : "
										+ Saved2[i].Price + " : " + choAmo + " : " + (Price * choAmo) + "\n");
								System.out.println("구매를 진행합니다.");
								System.out.println("감사합니다.");
								Fil2.close();
								Fil1.close();

							}

						} else if (amoSel == 2) {
							System.out.println("처음으로 돌아갑니다." + "\n");
							continue loop1;
						} else {
							System.out.println("잘못된 선택입니다.");
							System.out.println("처음으로 돌아갑니다." + "\n");
							SalesList.sales();
						}
						go1 = false; //
					}
					go3 = false;
				}
				Main.main(null);
			}

			if (WCode.equals("뒤로")) {
				Main.main(null);
			}

		}
	}

	public static void ChanDel() throws IOException , SQLException{
		SaveGoods SG = new SaveGoods();
		int cnt = SaveGoods.cnt;
		Saved[] Saved2 = new Saved[20];
		Saved2 = SG.Saved;
		int i = 0, j;
		int chancho2;
		boolean go = true;
		if (cnt == 0) {
			System.out.println("등록된 상품이 없습니다.");
			System.out.println("상품등록 후 다시 입력해주세요");
			Main.main(null);
		}

		System.out.println("상품 수정 및 삭제하기 입니다.");
		System.out.println("원하는 번호를 눌러주세요");
		System.out.println("1. 수정  2. 삭제  3. 뒤로가기");
		int delcho = sc.nextInt();

		if (delcho == 1) {
			System.out.println("상품 수정하기 입니다.");
			System.out.println("수정하고 싶은 상품의 코드를 입력해주세요");
			String chan = sc.next();
			loop2: for (i = 0; i < cnt; i++) {
				if (chan.equals(Saved2[i].Code)) {
					System.out.println("해당 상품의 정보입니다." + "\n");
					System.out.println(i + 1 + "번째 상품의 이름 : " + Saved2[i].Name + "\n");
					System.out.println(i + 1 + "번째 상품의 가격 : " + Saved2[i].Price + "\n");
					System.out.println(i + 1 + "번째 상품의 수량 : " + Saved2[i].Amount + "\n");
					loop1: while (go) {
						System.out.println("어떤것을 수정하시겠습니까?");
						System.out.println("1. 이름  2. 가격  3. 수량 4. 뒤로");
						int chancho = sc.nextInt();
						if (chancho == 1) {
							System.out.println("수정할 이름을 입력해주세요");
							String channam = sc.next();
//						Saved2[i].Name.replace(Saved2[i].Name, channam);
							Saved2[i].Name = channam;
							System.out.println("변경이 완료되었습니다.");
							System.out.println("추가로 변경하실것이 있습니까?");
							System.out.println("1. 네         2. 아니오");
							chancho2 = sc.nextInt();
							if (chancho2 == 1) {
								continue loop1;
							} else if (chancho2 == 2) {
								Main.main(null);
							}
						}
						if (chancho == 2) {
							System.out.println("수정할 가격을 입력해주세요");
							String chanpri = sc.next();
							Saved2[i].Price = chanpri;
							System.out.println("변경이 완료되었습니다.");
							System.out.println("추가로 변경하실것이 있습니까?");
							System.out.println("1. 네         2. 아니오");
							chancho2 = sc.nextInt();
							if (chancho2 == 1) {
								continue loop1;
							} else if (chancho2 == 2) {
								Main.main(null);
							}

						}
						if (chancho == 3) {
							System.out.println("수정할 수량을 입력해주세요");
							int chanamo = sc.nextInt();
							Saved2[i].Amount = chanamo;
							System.out.println("변경이 완료되었습니다.");
							System.out.println("추가로 변경하실것이 있습니까?");
							System.out.println("1. 네         2. 아니오");
							chancho2 = sc.nextInt();
							if (chancho2 == 1) {
								continue loop1;
							} else if (chancho2 == 2) {
								Main.main(null);
							}
						}
						if (chancho == 4) {
							continue loop2;
						} else {
							System.out.println("선택이 잘못되었습니다.");
							System.out.println("메인메뉴로 이동합니다.");
							Main.main(null);
						}

						go = false;
					}
				}
				if (!chan.equals(Saved2[i].Code)) {
					System.out.println("해당하는 상품의 코드가 없습니다.");
					System.out.println("확인 후 다시 입력해 주세요" + "\n");
					SalesList.ChanDel();

				}
			}

		}
		if (delcho == 2) {
			System.out.println("삭제하기 기능입니다.");
			System.out.println("삭제할 상품의 코드를 입력해주세요");
			String delcode = sc.next();
			for (i = 0; i < cnt; i++) {
				if (delcode.equals(Saved2[i].Code)) {
					System.out.println("해당 상품의 정보입니다." + "\n");
					System.out.println(i + 1 + "번째 상품의 이름 : " + Saved2[i].Name + "\n");
					System.out.println(i + 1 + "번째 상품의 가격 : " + Saved2[i].Price + "\n");
					System.out.println(i + 1 + "번째 상품의 수량 : " + Saved2[i].Amount + "\n");
					System.out.println("삭제하시겠습니까?");
					System.out.println("1. 네     2. 아니오");
					int delremind = sc.nextInt();
					if (delremind == 1) {// sort로 배열을 재정리 가능할까? //늘어나있는 cnt값 하나 줄이기
						for (i = 0; i < cnt; i++) {
							Saved2[i] = Saved2[i + 1];

						}

						cnt = cnt - 1;
						SaveGoods.cnt = cnt;
						System.out.println("삭제되었습니다.");
						Main.main(null);
					}
					if (delremind == 2) {
						System.out.println("처음으로 이동합니다.");
						SalesList.ChanDel();

					} else {
						System.out.println("잘못입력하였습니다.");
						System.out.println("처음으로 이동합니다.");
						SalesList.ChanDel();
					}

				} else if (!delcode.equals(Saved2[i].Code)) {
					System.out.println("해당하는 코드가 없습니다.");
					System.out.println("메인으로 이동합니다.");
					Main.main(null);

				}
			}
		}
		if (delcho == 3) {
			System.out.println("메인으로 이동합니다.");
			Main.main(null);
		} else {
			System.out.println("잘못입력하였습니다.");
			System.out.println("메인으로 이동합니다.");
			Main.main(null);
		}
	}

	public static void basket() throws IOException , SQLException{
		if (SaveGoods.cnt == 0) {
			System.out.println("등록된 상품이 없습니다.");
			System.out.println("메인메뉴로 이동합니다.");
			Main.main(null);
		}
		System.out.println("구매내역 확인하기입니다.");
		System.out.println("해당하는 번호를 입력해주세요");
		System.out.println("1. 구매내역 확인 2. 뒤로가기");
		int bascho = sc.nextInt();
		if (bascho == 1) {
			System.out.println("구매내역 확인하기 입니다.");
			try {
				BufferedReader orderRead = null;
				String s;
				orderRead = new BufferedReader(new FileReader("orders.txt"));
				while ((s = orderRead.readLine()) != null) {
					System.out.println(s);
				}
			} catch (FileNotFoundException e) {

			}
			System.out.println("메인으로 이동합니다.");
			Main.main(null);

		}
		if (bascho == 2) {
			System.out.println("메인으로 이동합니다.");
			Main.main(null);

		}

		else {
			System.out.println("입력이 잘못되었습니다.");
			SalesList.basket();
		}

	}
}
