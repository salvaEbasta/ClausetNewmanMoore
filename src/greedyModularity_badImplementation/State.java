package greedyModularity_badImplementation;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class State {
	private double Q;
	private ArrayList<Community> commComp;
	
	public State(double Q, ArrayList<Community> cComp) {
		this.Q = Q;
		this.commComp = new ArrayList<Community>();
		IntStream.range(0, cComp.size()).forEach((i)->commComp.add(cComp.get(i)));
	}
	
	public ArrayList<Community> getComposition(){return commComp;}
	public double getQ() {return Q;}
	public boolean isBetter(State toCompare) {return this.Q > toCompare.Q;}
	public String toString() {return String.format("Q: %f , %s", Q, commComp.toString());}
	
	public static void main(String[] args) {
		ArrayList<Community> commComp = new ArrayList<Community>();
		commComp.add(new Community("m1"));
		commComp.add(new Community("m2"));
		commComp.add(new Community("m3"));
		System.out.println(commComp.toString());
		State s = new State(0, commComp);
		commComp.remove(0);
		System.out.println(commComp.toString());
		System.out.println(s.toString());
	}
}
