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
		FileWriter Fil1 = new FileWriter("orders.txt", true); // �̰� �Ǹų���
		FileWriter Fil2 = new FileWriter("goods.txt", true); // �̰� �ܰ���

		if (cnt == 0) {
			System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
			System.out.println("��ǰ��� �� �ٽ� �Է����ּ���");
			Main.main(null);
		}

		System.out.println("��ǰ �Ǹ��ϱ� �Դϴ�.");
		System.out.println("�ֹ��ڵ� : " + Time);
		System.out.println("��ǰ�ڵ带 �Է��� �ּ���");
		System.out.println("�ڷΰ��� ���Ͻø� '�ڷ�'�� �Է����ּ���");
		WCode = sc.next();

		for (i = 0; i < cnt; i++) {

			if (WCode.equals(Saved2[i].Code)) {
				loop1: while (go3) {
					System.out.println("�ش� ��ǰ�� �����Դϴ�." + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� �̸� : " + Saved2[i].Name + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� ���� : " + Saved2[i].Price + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� ���� : " + Saved2[i].Amount + "\n");
					go1 = true;
					loop2: while (go1) {
						System.out.println("������ �Է����ּ���");
						choAmo = sc.nextInt();

						for (j = 0; j < cnt; j++) {
							AmoArr[j] = choAmo;
						}
						go1 = false;

						System.out.println(choAmo + "�� �����Ͻðڽ��ϱ�?" + "\n");
						System.out.println("1.�����ϱ�  2.�ٽ��Է�" + "\n");

						int amoSel = sc.nextInt();
						if (amoSel == 1) {
							j = i;
							if (Saved2[i].Amount < AmoArr[j]) {
								System.out.println("��� �����մϴ�.");
								System.out.println("�ٽ� �Է����ּ���" + "\n");
								if (Saved2[i].Amount == 0) {
									SalesList.sales();
								}
								continue loop1;

							}
							if (Saved2[i].Amount >= AmoArr[j]) {
								if ((Saved2[i].Amount - choAmo) < 0)
									System.out.println("��� �����մϴ�." + "\n");

								Saved2[i].Amount = Saved2[i].Amount - choAmo;
								Fil2.write(Time + " : " + Saved2[i].Code + " : " + Saved2[i].Name + " : "
										+ Saved2[i].Price + " : " + Saved2[i].Amount + "\n");

								int Price = Integer.parseInt(Saved2[i].Price);
								Fil1.write(Time + " : " + Saved2[i].Code + " : " + Saved2[i].Name + " : "
										+ Saved2[i].Price + " : " + choAmo + " : " + (Price * choAmo) + "\n");
								System.out.println("���Ÿ� �����մϴ�.");
								System.out.println("�����մϴ�.");
								Fil2.close();
								Fil1.close();

							}

						} else if (amoSel == 2) {
							System.out.println("ó������ ���ư��ϴ�." + "\n");
							continue loop1;
						} else {
							System.out.println("�߸��� �����Դϴ�.");
							System.out.println("ó������ ���ư��ϴ�." + "\n");
							SalesList.sales();
						}
						go1 = false; //
					}
					go3 = false;
				}
				Main.main(null);
			}

			if (WCode.equals("�ڷ�")) {
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
			System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
			System.out.println("��ǰ��� �� �ٽ� �Է����ּ���");
			Main.main(null);
		}

		System.out.println("��ǰ ���� �� �����ϱ� �Դϴ�.");
		System.out.println("���ϴ� ��ȣ�� �����ּ���");
		System.out.println("1. ����  2. ����  3. �ڷΰ���");
		int delcho = sc.nextInt();

		if (delcho == 1) {
			System.out.println("��ǰ �����ϱ� �Դϴ�.");
			System.out.println("�����ϰ� ���� ��ǰ�� �ڵ带 �Է����ּ���");
			String chan = sc.next();
			loop2: for (i = 0; i < cnt; i++) {
				if (chan.equals(Saved2[i].Code)) {
					System.out.println("�ش� ��ǰ�� �����Դϴ�." + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� �̸� : " + Saved2[i].Name + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� ���� : " + Saved2[i].Price + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� ���� : " + Saved2[i].Amount + "\n");
					loop1: while (go) {
						System.out.println("����� �����Ͻðڽ��ϱ�?");
						System.out.println("1. �̸�  2. ����  3. ���� 4. �ڷ�");
						int chancho = sc.nextInt();
						if (chancho == 1) {
							System.out.println("������ �̸��� �Է����ּ���");
							String channam = sc.next();
//						Saved2[i].Name.replace(Saved2[i].Name, channam);
							Saved2[i].Name = channam;
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
							System.out.println("�߰��� �����Ͻǰ��� �ֽ��ϱ�?");
							System.out.println("1. ��         2. �ƴϿ�");
							chancho2 = sc.nextInt();
							if (chancho2 == 1) {
								continue loop1;
							} else if (chancho2 == 2) {
								Main.main(null);
							}
						}
						if (chancho == 2) {
							System.out.println("������ ������ �Է����ּ���");
							String chanpri = sc.next();
							Saved2[i].Price = chanpri;
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
							System.out.println("�߰��� �����Ͻǰ��� �ֽ��ϱ�?");
							System.out.println("1. ��         2. �ƴϿ�");
							chancho2 = sc.nextInt();
							if (chancho2 == 1) {
								continue loop1;
							} else if (chancho2 == 2) {
								Main.main(null);
							}

						}
						if (chancho == 3) {
							System.out.println("������ ������ �Է����ּ���");
							int chanamo = sc.nextInt();
							Saved2[i].Amount = chanamo;
							System.out.println("������ �Ϸ�Ǿ����ϴ�.");
							System.out.println("�߰��� �����Ͻǰ��� �ֽ��ϱ�?");
							System.out.println("1. ��         2. �ƴϿ�");
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
							System.out.println("������ �߸��Ǿ����ϴ�.");
							System.out.println("���θ޴��� �̵��մϴ�.");
							Main.main(null);
						}

						go = false;
					}
				}
				if (!chan.equals(Saved2[i].Code)) {
					System.out.println("�ش��ϴ� ��ǰ�� �ڵ尡 �����ϴ�.");
					System.out.println("Ȯ�� �� �ٽ� �Է��� �ּ���" + "\n");
					SalesList.ChanDel();

				}
			}

		}
		if (delcho == 2) {
			System.out.println("�����ϱ� ����Դϴ�.");
			System.out.println("������ ��ǰ�� �ڵ带 �Է����ּ���");
			String delcode = sc.next();
			for (i = 0; i < cnt; i++) {
				if (delcode.equals(Saved2[i].Code)) {
					System.out.println("�ش� ��ǰ�� �����Դϴ�." + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� �̸� : " + Saved2[i].Name + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� ���� : " + Saved2[i].Price + "\n");
					System.out.println(i + 1 + "��° ��ǰ�� ���� : " + Saved2[i].Amount + "\n");
					System.out.println("�����Ͻðڽ��ϱ�?");
					System.out.println("1. ��     2. �ƴϿ�");
					int delremind = sc.nextInt();
					if (delremind == 1) {// sort�� �迭�� ������ �����ұ�? //�þ�ִ� cnt�� �ϳ� ���̱�
						for (i = 0; i < cnt; i++) {
							Saved2[i] = Saved2[i + 1];

						}

						cnt = cnt - 1;
						SaveGoods.cnt = cnt;
						System.out.println("�����Ǿ����ϴ�.");
						Main.main(null);
					}
					if (delremind == 2) {
						System.out.println("ó������ �̵��մϴ�.");
						SalesList.ChanDel();

					} else {
						System.out.println("�߸��Է��Ͽ����ϴ�.");
						System.out.println("ó������ �̵��մϴ�.");
						SalesList.ChanDel();
					}

				} else if (!delcode.equals(Saved2[i].Code)) {
					System.out.println("�ش��ϴ� �ڵ尡 �����ϴ�.");
					System.out.println("�������� �̵��մϴ�.");
					Main.main(null);

				}
			}
		}
		if (delcho == 3) {
			System.out.println("�������� �̵��մϴ�.");
			Main.main(null);
		} else {
			System.out.println("�߸��Է��Ͽ����ϴ�.");
			System.out.println("�������� �̵��մϴ�.");
			Main.main(null);
		}
	}

	public static void basket() throws IOException , SQLException{
		if (SaveGoods.cnt == 0) {
			System.out.println("��ϵ� ��ǰ�� �����ϴ�.");
			System.out.println("���θ޴��� �̵��մϴ�.");
			Main.main(null);
		}
		System.out.println("���ų��� Ȯ���ϱ��Դϴ�.");
		System.out.println("�ش��ϴ� ��ȣ�� �Է����ּ���");
		System.out.println("1. ���ų��� Ȯ�� 2. �ڷΰ���");
		int bascho = sc.nextInt();
		if (bascho == 1) {
			System.out.println("���ų��� Ȯ���ϱ� �Դϴ�.");
			try {
				BufferedReader orderRead = null;
				String s;
				orderRead = new BufferedReader(new FileReader("orders.txt"));
				while ((s = orderRead.readLine()) != null) {
					System.out.println(s);
				}
			} catch (FileNotFoundException e) {

			}
			System.out.println("�������� �̵��մϴ�.");
			Main.main(null);

		}
		if (bascho == 2) {
			System.out.println("�������� �̵��մϴ�.");
			Main.main(null);

		}

		else {
			System.out.println("�Է��� �߸��Ǿ����ϴ�.");
			SalesList.basket();
		}

	}
}
