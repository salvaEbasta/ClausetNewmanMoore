package clausetNewmanMooreAlgorithm_v1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import clausetNewmanMooreAlgorithm_v1.sparseMatrix.MatrixEntry;
import clausetNewmanMooreAlgorithm_v1.sparseMatrix.SparseMatrix;

public class ClausetNewmanMoore {
	private AdjMatrix adj;
	private SparseMatrix deltaQ;
	private List<Double> ai;
	
	public ClausetNewmanMoore(AdjMatrix adj) {
		this.adj = adj;
		this.deltaQ = init_deltaQ(adj);
		this.ai = init_ai(adj);
	}
	
	/**
	 * As described above we start off with each vertex being
		the sole member of a community of one, in which case
		eij = 1/2m if i and j are connected and zero otherwise,
		and ai = ki/2m. Thus we initially set
			∆Qij =1/2m − kikj/(2m)^2   if i, j are connected,
			0 otherwise,
	 * @param adj
	 * @return
	 */
	private SparseMatrix init_deltaQ(AdjMatrix adj) {
		SparseMatrix intermedia = new SparseMatrix(adj.length());
		IntStream.range(0, adj.length())
					.forEach((i)->IntStream.range(0, adj.length()).forEach((j)->{
								if(adj.getIndex(i, j)!=0)
									//deltaQij = 1/(2m)-ki*kj/(2m)^2
									intermedia.setIndex(i, j, 1.0/(2*adj.getEdgeNumber()) - adj.getDegree(i)*adj.getDegree(j)/Math.pow(2.0*adj.getEdgeNumber(),2.0));
								else
									intermedia.setIndex(i, j, 0.0);
							}));
		return intermedia;
	}
	
	/**
	 * ai = ki/2m
	 * @param adj
	 * @return
	 */
	private List<Double> init_ai(AdjMatrix adj){
		ArrayList<Double> results = new ArrayList<Double>();
		IntStream.range(0, adj.length())
					.forEach((node)->results.add(new Double(adj.getDegree(node)/(2*adj.getEdgeNumber()))));
		return results;
	}
	
	public ArrayList<ArrayList<String>> findCommunities(){
		ArrayList<ArrayList<String>> communities = new ArrayList<ArrayList<String>>();
		IntStream.range(0, adj.length())
					.forEach((i)->{
							communities.add(new ArrayList<String>());
							communities.get(i).add(String.format("%d", i));
						});
		while(deltaQ.maxEntry().getValue()>0){
			MatrixEntry maxDeltaQ = deltaQ.maxEntry();
			communities.get(maxDeltaQ.gety()).addAll(communities.get(maxDeltaQ.getx()));
			communities.remove(maxDeltaQ.getx());
			deltaQ = joinCommunities(maxDeltaQ.getx(), maxDeltaQ.gety());
			ai.remove(maxDeltaQ.gety());
			ai.add(maxDeltaQ.gety(), ai.get(maxDeltaQ.gety()) + ai.get(maxDeltaQ.getx()));
		}
		return communities;
	}

	/**
	 * Merge the community i into the community j: update j(rows&columns) & remove i(rows&columns)
	 * @param i
	 * @param j
	 * @return
	 */
	private SparseMatrix joinCommunities(int i, int j) {
		SparseMatrix tmp = deltaQ;
		IntStream.range(0, deltaQ.length())
					.forEach((k)->{
						if(adj.getIndex(k, i)!=0 && adj.getIndex(k, j)!=0)
							tmp.setIndex(j, k, deltaQ.getIndex(i, k) + deltaQ.getIndex(j, k));
						else if(adj.getIndex(k, i)!=0 && adj.getIndex(k, j)==0)
							tmp.setIndex(j, k, deltaQ.getIndex(i, k) - 2*ai.get(j)*ai.get(k));
						else if(adj.getIndex(k, i)==0 && adj.getIndex(k, j)!=0)
							tmp.setIndex(j, k, deltaQ.getIndex(j, k) - 2*ai.get(i)*ai.get(k));
					});
		return tmp;
	}
	
	public static void main(String[] args) {
		AdjMatrix adjMat = new AdjMatrix(new int[][] {{0,1,1,0,0,0,0},
            {1,0,1,1,0,0,0},
            {1,1,0,1,0,0,0},
            {0,1,1,0,1,0,0},
            {0,0,0,1,0,1,1},
            {0,0,0,0,1,0,1},
            {0,0,0,0,1,1,0}});
		ClausetNewmanMoore cnm = new ClausetNewmanMoore(adjMat);
		ArrayList<ArrayList<String>> result = cnm.findCommunities();
		IntStream.range(0, result.size()).forEach((i)->System.out.println(result.get(i).toString()));
	}
}
