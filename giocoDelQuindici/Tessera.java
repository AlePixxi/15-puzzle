package giocoDelQuindici;

public class Tessera {

	private int valore;
	private int posX;
	private int posY;
	
	public Tessera(int valore, int posX, int posY) {

		this.valore = valore;
		this.posX = posX;
		this.posY = posY;
	}
	
	public String stampaTessera() {
		if(this.valore == 16)
			return "[  ]";
		if(this.valore < 10)
			return "[ " + this.valore + "]";
		return "[" + this.valore + "]";
	}
	
	public int getValore() { return this.valore; }
	public int getPosX() { return this.posX; }
	public int getPosY() { return this.posY; }
	public String toString() { return "("+this.posX+", "+this.posY+")"; }
	
	public void setPosX(int posX) { this.posX = posX; }
	public void setPosY(int posY) { this.posY = posY; }
	
}
