//核心程序
package ex2_book_Borrowing_System;

//import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BookManager {
	final int N = 50;// 宏定义数组元素为50个
	Book[] books = new Book[N];// 建立对象数组
	int n = 0;

	public void initial() {
		books[0] = new Book("数据结构算法", "---------", 0, 1);
		books[1] = new Book("数字电子技术", "---------", 0, 2);
		books[2] = new Book("模拟电子技术", "---------", 0, 3);
		books[3] = new Book("C程序设计", "2020.09.10", 1, 21);
		books[4] = new Book("智能仪器", "---------", 0, 5);
		books[5] = new Book("Matlab2020", "---------", 0, 6);
		n = 6;
	}

	// 归并算法部分一：合并
	public void guibin_merge(int array[][], int L, int M, int R) {
		int i = 0, p = 0, j = 0, k = L;// i是普通循环变量，p是left数组的箭头，j是right数组的箭头，k是原数组的箭头
		int left_size = M - L;// 左边数组的大小
		int right_size = R - M + 1;// 右边数组的大小
		int[][] left = new int[2][left_size];
		int[][] right = new int[2][right_size];
		// 1、左边的数组
		for (i = L; i < M; i++) {
			left[0][i - L] = array[0][i];
			left[1][i - L] = array[1][i];
		}
		// 2、右边的数组
		for (i = M; i <= R; i++) {
			right[0][i - M] = array[0][i];
			right[1][i - M] = array[1][i];
		}
		// 合并到原来的数组中
		while (p < left_size && j < right_size) {// 如果两个数组都没走完
			if (left[0][p] < right[0][j]) {// 小的先存
				array[0][k] = left[0][p];
				array[1][k] = left[1][p];
				p++;
				k++;
			} else {
				array[0][k] = right[0][j];
				array[1][k] = right[1][j];
				j++;
				k++;
			}
		}

		while (p < left_size) {// 右边走完，左边没走完
			array[0][k] = left[0][p];
			array[1][k] = left[1][p];
			p++;
			k++;
		}
		while (j < right_size) {// 左边走完，右边没走完
			array[0][k] = right[0][j];
			array[1][k] = right[1][j];
			j++;
			k++;
		}
	}

	// 归并算法部分二：分而治之，遵循后序遍历(先遍历左子树，然后遍历右子树，最后访问根结点)
	// 中间对半切开，先将左边切到最小，利用递归将左边排完序；之后将右边切到最小，利用递归将右边排完序；最后将左右进行合并。
	public void mergesort(int a[][], int L, int R) {
		if (L == R) {
			return;
		} else {
			int M = (L + R) / 2;
			mergesort(a, L, M);
			mergesort(a, M + 1, R);
			guibin_merge(a, L, M + 1, R);
		}
	}

	public void list() {
		// 为数组分配空间
		int[][] a = new int[2][N];
		int i = 0;
		// length用来记录有效数据长度
		// int length = 0;
		// 遍历对象数组，将借阅次数保存在一维数组a中
		for (i = 0; i < books.length; i++) {
			if (books[i] == null) {
				break;
			} else {
				a[0][i] = books[i].Count;
				a[1][i] = i;
			}
		}
		// 对数组a进行归并排序
		mergesort(a, 0, n - 1);
		// 按借阅次数从大到小进行打印
		System.out.println("(序号)\t(状态)\t\t(名称)\t\t\t(借出日期)\t(借出次数)");
		for (i = n - 1; i >= 0; i--) {
			if (books[a[1][i]].State == 1) {
				System.out.println(n - i + "\t已借出\t\t" + "《" + books[a[1][i]].name + "》\t\t" + books[a[1][i]].Date
						+ "\t" + a[0][i]);
			} else if (books[a[1][i]].State == 0) {
				System.out.println(n - i + "\t可借\t\t" + "《" + books[a[1][i]].name + "》\t\t" + books[a[1][i]].Date + "\t"
						+ a[0][i]);
			} else
				;
		}

	}

	public void search() {
		int i = 0;
		System.out.println("(序号)\t(状态)\t\t(名称)\t\t\t(借出日期)\t(借出次数)");
		for (i = 0; i < books.length; i++) {
			if (books[i] == null) {
				break;
			} else if (books[i].State == 1) {
				System.out.println(
						(i + 1) + "\t已借出\t\t" + "《" + books[i].name + "》\t\t" + books[i].Date + "\t" + books[i].Count);
			} else if (books[i].State == 0) {
				System.out.println(
						(i + 1) + "\t可借\t\t" + "《" + books[i].name + "》\t\t" + books[i].Date + "\t" + books[i].Count);
			} else
				;
		}
		for (i = 0; i < 20; i++)
			System.out.print("-");
		System.out.print("\n");

	}

	public void add() {

		char cycle = 1, confirm = 0;
		while (cycle == 1) {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.println("请输入新增图书名称：");
			try {
				String name = input.next();
				n = n + 1;
				books[n - 1] = new Book(name, "---------", 0, 0);
				System.out.println("——————————>新图书《" + name + "》添加成功！！！\n");
				search();
				System.out.println("请问还需要增加图书么？(y OR press any key to exit)");
				@SuppressWarnings("resource")
				Scanner input1 = new Scanner(System.in);
				confirm = input1.next().charAt(0);
				if (confirm != 'y') {
					cycle = 0;
				} else
					;
			} catch (Exception ex) {
				System.out.println("输入非法，请重新输入！！");
			}

		}
	}

	public void delete() {
		char cycle = 1, confirm = 0;
		int i = 0, deletepoint = -1;
		while (cycle == 1) {
			search();
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.printf("请输入需要删除的图书：");
			String name = input.next();
			for (i = 0; i <= n - 1; i++) {
				if (books[i].name.equals(name)) {
					deletepoint = i;
					break;
				}
			}
			if (deletepoint == -1) {
				System.out.printf("找不到匹配的书籍！！");
			} else {
				for (i = deletepoint; i < n - 1; i++) {
					books[i].setallvalue(books[i + 1].getname(), books[i + 1].getdate(), books[i + 1].getstate(),
							books[i + 1].getcount());
				}
				books[n - 1] = null;
				n = n - 1;
				System.out.println("——————————>删除书籍《" + name + "》成功！！");
				search();
			}
			System.out.println("请问还需要删除图书么？(y OR press any key to exit)");
			@SuppressWarnings("resource")
			Scanner input1 = new Scanner(System.in);
			confirm = input1.next().charAt(0);
			if (confirm != 'y') {
				cycle = 0;
			} else
				;

		}
	}

	public void borrow() {
		char cycle = 1, confirm = 0;
		int i = 0, borrowpoint = -1;
		while (cycle == 1) {
			search();
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.printf("请输入需要借阅的图书：");
			String name = input.next();
			for (i = 0; i <= n - 1; i++) {
				if (books[i].name.equals(name)) {
					borrowpoint = i;
					break;
				}
			}
			if (borrowpoint == -1) {
				System.out.printf("找不到匹配的书籍！！");
			} else {
				@SuppressWarnings("resource")
				Scanner input1 = new Scanner(System.in);
				System.out.printf("请输入借出的日期，年-月-日：");
				String date = input1.next();
				books[borrowpoint].setdate(date);
				books[borrowpoint].setstate(1);
				books[borrowpoint].setcount(books[borrowpoint].getcount() + 1);
				System.out.println("——————————>借阅书籍《" + name + "》成功！！");
				search();
			}
			System.out.println("请问还需要借阅图书么？(y OR press any key to exit)");
			@SuppressWarnings("resource")
			Scanner input1 = new Scanner(System.in);
			confirm = input1.next().charAt(0);
			if (confirm != 'y') {
				cycle = 0;
			} else
				;
		}
	}

	public float charge(String s1, String s2) {
		float money = 0;
		SimpleDateFormat date = new SimpleDateFormat("yyyy.mm.dd");
		try {
			Date d1 = date.parse(s1);
			Date d2 = date.parse(s2);
			money = (d2.getTime() - d1.getTime()) / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return money;
	}

	public void returnbook() {
		char cycle = 1, confirm = 0;
		int i = 0, returnpoint = -1;
		float money = 0;
		while (cycle == 1) {
			search();
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			System.out.printf("请输入需要归还的图书：");
			String name = input.next();
			for (i = 0; i <= n - 1; i++) {
				if (books[i].name.equals(name)) {
					returnpoint = i;
					break;
				}
			}
			if (returnpoint == -1) {
				System.out.println("找不到匹配的书籍！！");
			} else if (books[i].State == 0) {
				System.out.println("对不起，该本书从未借出！！");
			} else {
				@SuppressWarnings("resource")
				Scanner input1 = new Scanner(System.in);
				System.out.printf("请输入归还的日期，年-月-日：");

				String date = input1.next();
				money = charge(books[returnpoint].getdate(), date);
				System.out.println("借阅日期为：" + books[returnpoint].getdate());
				System.out.println("归还日期为：" + date);

				System.out.println("需支付金额为：" + money);
				System.out.println("是否同意支付金额？(y OR press any key to exit)");
				@SuppressWarnings("resource")
				Scanner input2 = new Scanner(System.in);
				confirm = input2.next().charAt(0);
				if (confirm == 'y') {
					System.out.println("——————————>感谢支持，归还书籍《" + name + "》成功！！");
					books[returnpoint].setdate("---------");
					books[returnpoint].setstate(0);
					search();
				} else {
					System.out.println("——————————>归还书籍《" + name + "》失败！！");
					search();
				}
			}
			System.out.println("请问还需要归还图书么？(y OR press any key to exit)");
			@SuppressWarnings("resource")
			Scanner input3 = new Scanner(System.in);
			confirm = input3.next().charAt(0);
			if (confirm != 'y') {
				cycle = 0;
			} else
				;

		}
	}

	public void menu() {
		initial();
		char cycle = 1;
		int a = 0, i = 0;
		System.out.println("欢迎使用图书借阅系统 Rev1.0");
		System.out.println("Powered by Jarvis,2020.9.16");
		for (i = 0; i < 20; i++)
			System.out.print("-");
		System.out.print("\n");
		System.out.println("0.借出排行榜");
		System.out.println("1.新增图书");
		System.out.println("2.查看图书");
		System.out.println("3.删除图书");
		System.out.println("4.借出图书");
		System.out.println("5.归还图书");
		System.out.println("6.退出");
		for (i = 0; i < 20; i++)
			System.out.print("-");
		System.out.print("\n");
		while (cycle == 1) {
			System.out.print("请选择:");
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			try {
				a = input.nextInt();
				switch (a) {
				case 0:
					System.out.println("————>借出排行榜功能启动");
					list();
					break;
				case 1:
					System.out.println("————>新增图书功能启动");
					add();
					break;
				case 2:
					System.out.println("————>查看图书功能启动");
					search();
					break;
				case 3:
					System.out.println("————>删除图书功能启动");
					delete();
					break;
				case 4:
					System.out.println("————>借出图书功能启动");
					borrow();
					break;
				case 5:
					System.out.println("————>归还图书功能启动");
					returnbook();
					break;
				case 6:
					cycle = 0;
					System.out.println("退出成功，感谢使用");
					break;
				default:
					System.out.println("输入非法，请重新输入！！");
					break;
				}
			} catch (Exception ex) {
				System.out.println("输入非法，请重新输入！！");
			}
		}
	}

}
