package net.mrpaul.PB150.ps13;

public class Tile {
	private int value;

	Tile (){
		value = 0;
	}

	Tile (int num){
		if (num == 0)
			this.value = 0;
		else
			this.value = num;
	}

	public boolean isEmpty() {

		if (value == 0)
			return true;
		else
			return false;

	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {

		if (value  == 0 )
			return "0";
		else
			return value + "";
	}

	public Tile clone() {
		Tile t1 = new Tile(this.value);
		return t1;
	}
}