package clausetNewmanMooreAlgorithm_v2;

public class MatrixEntry {
	private Double value;
	private int x;
	private int y;
	
	public MatrixEntry(Double value, int row, int column) {
		this.x=row; this.y=column; this.value=value;
	}
	
	public Double value() {return this.value;}
	public int row() {return this.x;}
	public int column() {return this.y;}
	public void setValue(Double v) {this.value=v;}
	public void setx(int x) {this.x=x;}
	public void sety(int y) {this.y=y;}
	public boolean isValid() {return x>=0&&y>=0?true:false;}
	public int compareTo(MatrixEntry toCompare) {return value.compareTo(toCompare.value);}
	public boolean sameRow(MatrixEntry m) {return this.x==m.x;}
	public boolean hasCoords(int i, int j) {return i==x&&y==j;}
	public static MatrixEntry nullEntry() {return new MatrixEntry(0.0, -1, -1);}
	
	public String toString() {return String.format("(%d, %d): %f", x, y, value);}
}
