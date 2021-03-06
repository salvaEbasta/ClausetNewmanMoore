package greedyModularity_badImplementation;

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
	public void setRow(int x) {this.x=x;}
	public void setColumn(int y) {this.y=y;}
	public void decrementRow() {x=x-1;}
	public void decrementColumn() {y=y-1;}
	
	public boolean isValid() {return x>=0&&y>=0?true:false;}
	public int compareTo(MatrixEntry toCompare) {return value.compareTo(toCompare.value);}
	public boolean sameRow(MatrixEntry m) {return this.x==m.x;}
	public boolean hasCoords(int i, int j) {return i==x&&y==j;}
	
	public String toString() {return String.format("(%d, %d): %f", x, y, value);}
}
