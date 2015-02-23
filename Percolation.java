public class Percolation {
   private int[][] parent;   // parent[i][j] = parent of i,j
   private int[][] size;     // size[i][j] = number of objects in subtree rooted at i,j
   private boolean[][] full; // full[i][j] = true if i,j is full, false otherwise
   private boolean[][] open;  // open[i][j] = true if i,j is open, false otherwise
   private int count;      // number of components 
   private int xyTo1D(int i, int j, int N) {  //private method gives 1D location of site i,j
       return (i-1)*N + j;
       
   }
   public Percolation(int N) {              // create N-by-N grid, with all sites blocked 
        count = N;
        parent = new int[N + 1][N + 1];
        size = new int[N + 1][N + 1];
        full = new boolean [N + 1] [N + 1];
        open = new boolean [N + 1] [N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                parent[i][j] = xyto1D(i,j,N);
                size[i][j] = 1;
                full[i][j] = false;
                open[i][j] = false;
                
            }
        }
       
        }
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   public boolean percolates()             // does the system percolate?

   public static void main(String[] args   // test client (optional)
}