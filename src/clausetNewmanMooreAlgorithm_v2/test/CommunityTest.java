package clausetNewmanMooreAlgorithm_v2.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import clausetNewmanMooreAlgorithm_v2.Community;

class CommunityTest {

	@Test
	void mergeTrivialTest() {
		String m1 = "prova1"; String m2 = "prova2";
		Community c1 = new Community(m1); Community c2 = new Community(m2);
		String expected1 = String.format("[%s, %s]", m1, m2);
		String expected2 = String.format("[%s]", m2);
		c1.joinWith(c2);
		assertTrue(c1.toString().equals(expected1));
		assertTrue(c2.toString().equals(expected2));
	}
	
	@Test
	void toStringOfCommunityInArray() {
		String m1 = "prova1"; String m2 = "prova2";
		Community c1 = new Community(m1); Community c2 = new Community(m2);
		String c1str = String.format("[%s, %s]", m1, m2);
		String c2str = String.format("[%s]", m2);
		String expected = String.format("[%s, %s]", c1str, c2str);
		c1.joinWith(c2);
		ArrayList<Community> comm = new ArrayList<Community>();
		comm.add(c1); comm.add(c2);
		assertTrue(comm.toString().equals(expected));
	}
	
	@Test
	void mergeObjectsInArray(){
		String member = "var_%d";
		ArrayList<Community> comm1 = new ArrayList<Community>();
		int it = 15;
		ArrayList<String> ls = new ArrayList<String>();
		IntStream.range(0, it)
					.forEach((i)->ls.add(String.format(member, i)));
		IntStream.range(0, it)
					.forEach((i)->comm1.add(new Community(String.format(member, i))));
		IntStream.range(0, it-1)
					.forEach((bho)->{
						int i = 0;
						int j = 0;
						if(bho%2==0)
							j = it - 1 - bho;
						else 
							i = it - 1 - bho;
						comm1.get(j).joinWith(comm1.get(i));
						comm1.remove(i);
					});
		String result = comm1.toString();
		assertTrue(comm1.size() == 1);
		boolean duplicates = IntStream.range(0, it)
										.anyMatch((i)->{
												String prova = result.replaceFirst(ls.get(i)+"(]|,)", "");
												if(prova.contains(ls.get(i)+"(]|,)"))
													return true;
												return false;
											});
		assertTrue(!duplicates);
	}
}
