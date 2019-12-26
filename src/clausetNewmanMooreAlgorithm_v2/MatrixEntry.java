package clausetNewmanMooreAlgorithm_v2;

import java.util.stream.*;

public class MatrixEntry {
	private Double value;
	private int x;
	private int y;
	
	public MatrixEntry(Double value, int x, int y) {
		this.x=x; this.y=y; this.value=value;
	}
	
	public Double value() {return this.value;}
	public int getx() {return this.x;}
	public int gety() {return this.y;}
	public void setValue(Double v) {this.value=v;}
	public void setx(int x) {this.x=x;}
	public void sety(int y) {this.y=y;}
	public boolean isValid() {return x>=0&&y>=0?true:false;}
	public int compareTo(MatrixEntry toCompare) {return value.compareTo(toCompare.value);}
	public static MatrixEntry nullEntry() {return new MatrixEntry(0.0, -1, -1);}
	public String toString() {return String.format("(%d, %d): %f", x, y, value);}
	
	public static void main(String[] args) {
		Double a = 1.0;
		Double b = 0.0;
		System.out.println(String.format("Prevision: 1, Got: %d", a.compareTo(b)));
		Double[] test = new Double[] {a,b};
		System.out.println(String.format("%f", Stream.of(test).max((x,y)->x.compareTo(y)).get()));
	}
}
