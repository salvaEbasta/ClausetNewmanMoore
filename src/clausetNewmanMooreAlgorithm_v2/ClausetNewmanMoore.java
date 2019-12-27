package clausetNewmanMooreAlgorithm_v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ClausetNewmanMoore {
	private final static Logger log = Logger.getLogger(ClausetNewmanMoore.class.getName());
	
	private AdjMatrix adj;
	private double Q;
	private SparseMatrix deltaQ;
	private TreeMap<Integer,MatrixEntry> H; //max_heap
	private double[] a;
	private ArrayList<Community> communities;
	private State bestQstate;
	
	public ClausetNewmanMoore(AdjMatrix adj) {
		log.info("Init . . . ");
		this.adj = adj;
		init_a(this.adj);	//initialize a
		log.info("a : " + Arrays.toString(a));
		init_Q(a);			//initialize Q
		log.info("Q : " + new Double(Q).toString());
		init_deltaQ_H(this.adj);		//initialize deltaQ, H
		log.info("deltaQ : " + deltaQ.toString());
		log.info("H : " + H.toString());
		init_communities(this.adj);		//initialize communities
		log.info("communities : " + communities.toString());
		bestQstate = new State(Q, communities);		//initialize status (Qmax, communities state)
		
	}
	
	public State extractCommunities() {
		log.info("Finding communities . . . ");
		MatrixEntry maxEntry = H.keySet()
				.stream()
				.map((i)->H.get(i))
				.max((a,b)->a.compareTo(b))
				.get();		//	select max deltaQ_ij from H
		log.info("maxEntry: " + maxEntry.toString());
		while(maxEntry.isValid() && maxEntry.value() > 0) {		//til communities.size == 1
			double deltaQji = maxEntry.value();
			int i = maxEntry.getx();
			int j = maxEntry.gety();
			communities = join_communities(j, i, communities);		//	join community i to j
			log.info("communities joint: " + communities.toString());
			update_deltaQ_H_adjMatrix(j, i, adj);		//	update deltaQ, H (and Adj matrix) 
			log.info("deltaQ : " + deltaQ.toString());
			log.info("H : " + H.toString());
			update_a(j, i);		//	update a
			log.info("a : " + Arrays.toString(a));
			Q += deltaQji;		//	update Q: Q+=deltaQ_ij
			log.info("Q : " + new Double(Q).toString());
			if(Q > bestQstate.getQ())	//	update status ( you want Q to be as high as possible)
				bestQstate = new State(Q, communities);
			log.info("New best state: " + bestQstate.toString());
			maxEntry = H.keySet()
						.stream()
						.map((index)->H.get(index))
						.max((a,b)->a.compareTo(b))
						.get();		//	select max deltaQ_ij from H
			log.info("maxEntry: " + maxEntry.toString());
		}	//repeat
		return bestQstate;		//return communities state when Q is highest
	}
	
	/**Initialize modularity Q
	 * @param a
	 */
	private void init_Q(double[] a) {
		log.info("Init Q . . . ");
		Q = Modularity.nodesAsCommunities(a);
	}
	
	/**Initialize the a array
	 * @param adj
	 * @return
	 */
	private void init_a(AdjMatrix adj) {
		log.info("Init a . . . ");
		a = IntStream.range(0, adj.size())
							.mapToDouble((i)->adj.degree(i)/(2*adj.edgesNumeber()))
							.toArray();
	}
	
	/**Initialize deltaQ and H
	 * @param adj
	 */
	private void init_deltaQ_H(AdjMatrix adj) {
		log.info("Init deltaQ + H . . . ");
		deltaQ = new SparseMatrix(adj.size());
		H = new TreeMap<Integer,MatrixEntry>();
		IntStream.range(0, adj.size())
					.forEach((i)->{
						H.put(i, MatrixEntry.nullEntry());
						IntStream.range(0, adj.size())
									.forEach((j)->{
										if(adj.get(i, j)>0) {
											double newValue = 1.0/(2*adj.edgesNumeber())-a[i]*a[j];
											deltaQ.set(i, j, newValue);
											deltaQ.set(j, i, newValue);
											if(!H.get(i).isValid() || H.get(i).value().compareTo(newValue)<0)
												H.put(i, new MatrixEntry(newValue, i, j));
										}
									});
					});
	}
	
	/**Each row/column in adj is a community formed by itself
	 * @param adj
	 * @return
	 */
	private void init_communities(AdjMatrix adj){
		log.info("Init communities . . . ");
		communities = new ArrayList<Community>();
		String nameTemplate = "var_%d";
		IntStream.range(0, adj.size())
					.forEach((i)->communities.add(new Community(String.format(nameTemplate, i))));
	}
	
	/**Join community i to community j
	 * @param j
	 * @param i
	 */
	private ArrayList<Community> join_communities(int j, int i, ArrayList<Community> communities) {
		ArrayList<Community> tmp = new ArrayList<Community>();
		IntStream.range(0, communities.size()).forEach((n)->tmp.add(communities.get(n)));
		tmp.get(j).joinWith(tmp.get(i));
		tmp.remove(i);
		return tmp;
	}
	
	/**Update a[j] after you join community i to community j
	 * @param j
	 * @param i
	 */
	private void update_a(int j, int i) {
		a[j] = a[j] + a[i];
		a = IntStream.range(0, a.length).filter((k)->k!=i).mapToDouble((k)->a[k]).toArray();
	}
	
	/**Update jth row&column in deltaQ, adjMatrix, H + remove ith row&col in deltaQ, adjmatrix, H
	 * @param j
	 * @param i
	 * @param adj
	 */
	private void update_deltaQ_H_adjMatrix(int j, int i, AdjMatrix adj) {
		IntStream.range(0, deltaQ.size())
					.forEach((k)->{
						if(adj.get(k, i)>0.0 && adj.get(k, j)>0.0) {
							double value = deltaQ.get(i, k) + deltaQ.get(j, k);
							deltaQ.set(j, k, value);
							deltaQ.set(k, j, value);
						}else if(adj.get(k, i)>0.0 && adj.get(k, j)==0.0) {
							double value = deltaQ.get(i, k) - 2 * a[j] * a[k];
							deltaQ.set(j, k, value);
							deltaQ.set(k, j, value);
						}else if(adj.get(k, i)==0.0 && adj.get(k, j)>0.0) {
							double value = deltaQ.get(j, k) - 2 * a[i] * a[k];
							deltaQ.set(j, k, value);
							deltaQ.set(k, j, value);
						}
					});
		deltaQ.removeRowCol(i);
		
		H = new TreeMap<Integer,MatrixEntry>();
		IntStream.range(0, deltaQ.size())
					.forEach((ii)->{
						H.put(ii, MatrixEntry.nullEntry());
						IntStream.range(0, deltaQ.size())
									.forEach((jj)->{
										if(!H.get(ii).isValid() || H.get(ii).value().compareTo(deltaQ.get(ii, jj))<0)
											H.put(ii, new MatrixEntry(deltaQ.get(ii, jj), ii, jj));
									});
					});
		
		adj.merge(j, i);
	}
	
	public static void main(String[] args) {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
											            {1,0,1,1,0,0,0},
											            {1,1,0,1,0,0,0},
											            {0,1,1,0,1,0,0},
											            {0,0,0,1,0,1,1},
											            {0,0,0,0,1,0,1},
											            {0,0,0,0,1,1,0}});
//		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0, 1, 0},{1, 0, 0},{0, 0, 0}});
		System.out.println(String.format("Adj matrix : %s", adjMat.toString()));
		System.out.println("Executing cnm . . . ");
		ClausetNewmanMoore cnm = new ClausetNewmanMoore(adjMat);
		System.out.println(String.format("Communities found: %s", cnm.extractCommunities().toString()));
	}
}
