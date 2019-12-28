package clausetNewmanMooreAlgorithm_v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class ClausetNewmanMoore {
	private final static Logger log = Logger.getLogger(ClausetNewmanMoore.class.getName());
	private static final String nameTemplate = "var_%d";
	
	private AdjMatrix adj;
	private double Q;
	private SparseMatrix deltaQ;
	private MaxHeap H;
	//	private TreeMap<Integer,MatrixEntry> H; //max_heap
	private double[] a;
	private ArrayList<Community> communities;
	
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
	}
	
	public boolean extractCommunities() {
		log.info("Finding communities . . . ");
		MatrixEntry maxEntry = H.poll();
		log.info("maxEntry: " + maxEntry.toString());
		while(maxEntry.isValid() && maxEntry.value() > 0) {		//til communities.size == 1
			double deltaQji = maxEntry.value();
			int i = maxEntry.row();
			int j = maxEntry.column();
			communities = join_communities(j, i, communities);		//	join community i to j
			log.info("communities joint: " + communities.toString());
			update_deltaQ_H_adjMatrix(j, i, adj);		//	update deltaQ, H (and Adj matrix) 
			log.info("deltaQ : " + deltaQ.toString());
			log.info("H : " + H.toString());
			update_a(j, i);		//	update a
			log.info("a : " + Arrays.toString(a));
			Q += deltaQji;		//	update Q: Q+=deltaQ_ij
			log.info("Q : " + new Double(Q).toString());
			maxEntry = H.poll();
			log.info("maxEntry: " + maxEntry.toString());
		}	//repeat
		return true;		//return communities state when Q is highest
	}
	
	/**Get the communities' composition
	 * @return
	 */
	public ArrayList<Community> communityComposition(){
		return communities;
	}
	
	/**Get the modularity of the adj matrix with given communities
	 * @return
	 */
	public double modularity() {
		return Q;
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
							.mapToDouble((i)->adj.degree(i)/(2*adj.edgesNumber()))
							.toArray();
	}
	
	/**Initialize deltaQ and H
	 * @param adj
	 */
	private void init_deltaQ_H(AdjMatrix adj) {
		log.info("Init deltaQ + H . . . ");
		deltaQ = new SparseMatrix(adj.size());
		H = new MaxHeap();
//		H = new TreeMap<Integer,MatrixEntry>();
		IntStream.range(0, adj.size())
					.forEach((i)->{
//						H.put(i, MatrixEntry.nullEntry());
						IntStream.range(0, adj.size())
									.forEach((j)->{
										if(adj.get(i, j)>0) {
											double newValue = 1.0/(2*adj.edgesNumber())-a[i]*a[j];
											deltaQ.set(i, j, newValue);
											deltaQ.set(j, i, newValue);
//											if(H.elementAt(i) == null || H.valueAt(i).compareTo(newValue) < 0)
//											if(!H.get(i).isValid() || H.get(i).value().compareTo(newValue)<0)
//												H.put(i, new MatrixEntry(newValue, i, j));
											H.add(new MatrixEntry(newValue, i, j));
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
		a = IntStream.range(0, a.length)
						.filter((k)->k!=i)
						.mapToDouble((k)->a[k])
						.toArray();
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
							if(H.contains(j,k) || H.contains(k,j))
								H.set(new MatrixEntry(...))
						}else if(adj.get(k, i)==0.0 && adj.get(k, j)>0.0) {
							double value = deltaQ.get(j, k) - 2 * a[i] * a[k];
							deltaQ.set(j, k, value);
							deltaQ.set(k, j, value);
							if(H.contains(j,k) || H.contains(k,j))
								H.set(new MatrixEntry(...))
						}
					});
		deltaQ.removeRowCol(i);
		
//		H = new TreeMap<Integer,MatrixEntry>();
//		IntStream.range(0, deltaQ.size())
//					.forEach((ii)->{
//						// 
//						H.put(ii, MatrixEntry.nullEntry());
//						IntStream.range(0, deltaQ.size())
//									.forEach((jj)->{
//										if(!H.get(ii).isValid() || H.get(ii).value().compareTo(deltaQ.get(ii, jj))<0)
//											H.put(ii, new MatrixEntry(deltaQ.get(ii, jj), ii, jj));
//									});
//					});
		adj.merge(j, i);
	}
}