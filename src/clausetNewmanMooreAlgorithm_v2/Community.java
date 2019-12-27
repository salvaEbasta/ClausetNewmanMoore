package clausetNewmanMooreAlgorithm_v2;

import java.util.TreeSet;

public class Community {
	private TreeSet<String> members;  
	public Community(String memeber) {
		members = new TreeSet<String>();
		members.add(memeber);
	}
	public boolean joinWith(Community c) {
		members.addAll(c.members);
		return true;
	}
	public String toString() {return members.toString();}
}
