package clausetNewmanMooreAlgorithm_v2;

import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.ArrayList;

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
	
	public static void main(String[] args) {
		Community c1 = new Community("prova1");
		Community c2 = new Community("prova2");
		c1.joinWith(c2);
		System.out.println(c1.toString());
		ArrayList<Community> comm = new ArrayList<Community>();
		comm.add(c1); comm.add(c2);
		System.out.println(comm.toString());
		ArrayList<Community> comm1 = new ArrayList<Community>();
		int it = 7;
		IntStream.range(0, it)
					.forEach((i)->comm1.add(new Community(String.format("var_%d", i))));
		IntStream.range(0, it-1)
					.forEach((bho)->{
						System.out.println(String.format("Iterazione %d", bho));
						int i = new Double(Math.random()*(comm1.size()+1)).intValue();
						while(i>=comm1.size())
							i = new Double(Math.random()*(comm1.size()+1)).intValue();
						int j = new Double(Math.random()*(comm1.size()+1)).intValue();
						while(j==i || j>=comm1.size())
							j = new Double(Math.random()*(comm1.size()+1)).intValue();
						comm1.get(j).joinWith(comm1.get(i));
						System.out.println(String.format("After join of %d with %d : ", i, j) + comm1.toString());
						comm1.remove(i);
						System.out.println(String.format("After removing %d : ", i) + comm1.toString());
					});
	}
}
