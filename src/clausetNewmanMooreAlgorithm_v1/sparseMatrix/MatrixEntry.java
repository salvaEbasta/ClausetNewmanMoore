package clausetNewmanMooreAlgorithm_v1.sparseMatrix;

public class MatrixEntry {
	private Double value;
	private int x;
	private int y;
	
	public MatrixEntry(Double value, int x, int y) {
		this.x=x; this.y=y; this.value=value;
	}
	
	public Double getValue() {return this.value;}
	public int getx() {return this.x;}
	public int gety() {return this.y;}
	public void setValue(Double v) {this.value=v;}
	public void setx(int x) {this.x=x;}
	public void sety(int y) {this.y=y;}
	public boolean valid() {return x>=0&&y>=0?true:false;}
	public static MatrixEntry generateInvalid() {return new MatrixEntry(0.0, -1, -1);}
	public String toString() {return String.format("(%d, %d): %f", x, y, value);}
}
