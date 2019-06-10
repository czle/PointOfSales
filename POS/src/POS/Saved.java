package POS;

public class Saved {
	public String Code;
	public String Name;
	public String Price;
	public int Amount;

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public int getAmount() {
		return Amount;
	}

	public void setAmount(int amount) {
		Amount = amount;
	}

	public Saved(String code, String name, String price, int amount) {
		this.Code = code;
		this.Name = name;
		this.Price = price;
		this.Amount = amount;

//		String Quqry = "Insert into Pratice_java " + "(Code,Name,Price,Amount)" + "values(" + "'" + code + "'" + ","
//				+ "'" + name + "'" + "," + "'" + price + "'" + "," + amount + ");";

	}

	public void show() {
		System.out.println(getCode() + ":" + getName() + ":" + getPrice() + ":" + getAmount());
	}

}
