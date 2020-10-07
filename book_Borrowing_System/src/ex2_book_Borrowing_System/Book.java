//实体类
package ex2_book_Borrowing_System;

public class Book {
	String name, Date;
	int State, Count;

	public Book(String name, String date, int state, int count) {
		// super();
		//
		this.name = name;
		Date = date;
		State = state;
		Count = count;
	}

	public void setallvalue(String name, String date, int state, int count) {
		// super();
		//
		this.name = name;
		Date = date;
		State = state;
		Count = count;
	}

	public String getname() {
		return name;
	}

	public String getdate() {
		return Date;
	}

	public int getstate() {
		return State;
	}

	public int getcount() {
		return Count;
	}

	public void setname(String name) {
		this.name = name;
	}

	public void setdate(String date) {
		this.Date = date;
	}

	public void setstate(int state) {
		this.State = state;
	}

	public void setcount(int count) {
		this.Count = count;
	}

}
