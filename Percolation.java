public class Percolation {
   private int[][] parent;   // parent[i][j] = parent of i,j
   private int[][] size;     // size[i][j] = number of objects in subtree rooted at i,j
   private boolean[][] full; // full[i][j] = true if i,j is full, false otherwise
   private boolean[][] open;  // open[i][j] = true if i,j is open, false otherwise
   private int count;      // number of components 
   private 
   public Percolation(int N) {              // create N-by-N grid, with all sites blocked
        count = N;
        parent = new int[N][N];
        size = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j=0; i < N; i++) {
                parent[i][j] = i;
                size[i][j] = 1;
                
            }
        }
       
        }
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   public boolean percolates()             // does the system percolate?

   public static void main(String[] args   // test client (optional)
}