
public class UnionFind {

	private int[] p;

	public UnionFind(int orden) {
		p = new int[orden + 1];
		for (int i = 1; i <= orden; i++) {
			p[i] = i;
		}
	}

	public void union(int x, int y) {
		p[find(x)] = find(y);
	}

	private int find(int x) {
		while (x != p[x])
			x = p[x];
		return x;
	}
	
	public boolean connected(int x, int y) {
		return find(x) == find(y);
	}
}
