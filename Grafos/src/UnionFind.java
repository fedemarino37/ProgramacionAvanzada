
public class UnionFind {
	private int[] padre;
	
	public UnionFind(int orden) {
		padre = new int[orden + 1];
		for (int i = 1; i <= orden; i++) {
			padre[i] = i;
		}
	}
	
	public void union(int x, int y) {
		padre[find(x)] = find(y);
	}
	
	private int find(int x) {
		while (padre[x] != x) {
			x = padre[x];
		}
		return x;
	}
	
	public boolean connected(int x, int y) {
		return find(x) == find(y);
	}
}
