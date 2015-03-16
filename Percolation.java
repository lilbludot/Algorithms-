public class Percolation {
   //private int[] parent;   // parent[i] = parent of i
   //private int[] size;     // size[i] = number of objects in subtree rooted at i
   private boolean[][] full; // full[i][j] = true if i,j is full, false otherwise
   private boolean[][] open;  // open[i][j] = true if i,j is open, false otherwise
   private int count;      // number of components 
   private int grid_size;   //size of the grid
   private int location;  //1D location 
   private int upper_location;  //1D location of site i-1, j
   private int lower_location;  //1D location of site i+1, j
   private int left_location; //1D location of site i, j-1
   private int right_location; // 1D location of site i, j+1
   private int top_virtual_site; // 1D location of top virtual site
   private int bottom_virtual_site; //1D location of bottom virtual site
   private int dummy; //variable to be used as I please
   private WeightedQuickUnionUF uf;
   
   private int OneDtoX(int i) { // gives x-coordinate of 1D location
       return i/grid_size + 1;
   }
   private int OneDtoY(int i) { //gives y-coordinate of 1D location
       return i%grid_size + 1;
       
   }
   private int xyTo1D(int i, int j) {  //private method gives 1D location of site i,j
       return (i-1)*grid_size + (j-1);
       
   }
   private void validate(int i, int j) {   // validates i,j, throws exception if out of bounds
       if (i <= 0 || i > grid_size) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > grid_size) throw new IndexOutOfBoundsException("row index j out of bounds");
       
   }
   public Percolation(int N) {              // create N-by-N grid, with all sites blocked 
        uf = new WeightedQuickUnionUF(N*N + 2);
        //count = N;
        grid_size = N;
        top_virtual_site = N*N;
        bottom_virtual_site = N*N + 1;
       // parent = new int[N*N];
       // size = new int[N*N];
        full = new boolean [N + 1] [N + 1];
        open = new boolean [N + 1] [N + 1];
       
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                full[i][j] = false;
                open[i][j] = false;
                
            }
        }
       
        }
   public void open(int i, int j) {         // open site (row i, column j) if it is not open already
       validate(i, j);  // validating the indices i,j
       if (!isOpen(i,j)) {
             open[i][j] = true;     // opening the site if it is not already open   
       }
       location = xyTo1D(i,j);  
           
       if (i > 1){   //dealing with the upper neighbor
           if (isOpen(i-1,j)) {
               upper_location = xyTo1D(i-1, j);
               uf.union(location, upper_location);
           }  
       }
       if (i < grid_size) { //dealing with the lower neighbor
           if (isOpen(i+1,j)) {
               full[i][j]=true;
               lower_location = xyTo1D(i+1,j);
               uf.union(location, lower_location);
           }    
       }
       
       if (j>1) {  //dealing with the left neighbor
           if (isOpen(i,j-1)) {
            full[i][j]=true;   
            left_location = xyTo1D(i,j-1);
            uf.union(location, left_location);
           }
       }
       if (j< grid_size) {  //dealing with the right neighbor
           if (isOpen(i,j+1)) {
            full[i][j]=true;   
            left_location = xyTo1D(i,j+1);
            uf.union(location, left_location);
           }
       }
       if (i  == 1) { //dealing with top row and top virtual site
          uf.union(location, top_virtual_site); 
          full[i][j]=true;
       }
       
       if (i == grid_size) { //dealing with bottom row and bottom virtual site
           uf.union(location, bottom_virtual_site); 
           full[i][j]= true;
       }
       if (uf.connected(top_virtual_site, location) || uf.connected(bottom_virtual_site, location)) {  
           //marking current and connected sites as full 
           full[i][j] = true;
           
         //  while (location != uf.parent[location]){
               //location = uf.parent[location];
               //full[OneDtoX(location)][OneDtoY(location)]= true;
            
         //  }
       }
       
   }
   public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
       return open[i][j];
   }
   public boolean isFull(int i, int j) {    // is site (row i, column j) full?
       return full[i][j];
   }
   public boolean percolates() {            // does the system percolate?
       return uf.connected(top_virtual_site, bottom_virtual_site);
   }

   public static void main(String[] args ) {  // test client (optional) 
        int N = StdIn.readInt();
        Percolation perc = new Percolation(N);
         while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (perc.isOpen(p, q)) continue;
            perc.open(p, q);
            perc.dummy = perc.xyTo1D(p,q);
            StdOut.println(p + " " + q);
            StdOut.println("Its 1D location is: " + perc.dummy);
            StdOut.println("This site is open: "+ perc.isOpen(p,q));
            StdOut.println("This site is full: " + perc.isFull(p,q));
           // for (int k = 0; k < N*N + 1; k++) {
            //    StdOut.println(k+"th location's parent is:" + perc.uf.parent[k]);
           // }
           
          // while (perc.dummy != perc.uf.parent[perc.dummy]){
             //  StdOut.println("the location is: " + perc.dummy);
              // StdOut.println("parent's location " + perc.uf.parent[perc.dummy]);
              // StdOut.println("the x coordinate of the location is: " + perc.OneDtoX(perc.dummy));
              // StdOut.println("the y coordinate of the location is: " +perc.OneDtoY(perc.dummy));
              // StdOut.println(perc.full[perc.OneDtoX(perc.dummy)][perc.OneDtoY(perc.dummy)]);
              // perc.dummy = perc.uf.parent[perc.dummy];
        //}
        //perc.open(1,1);
        //perc.open(1,2);
        //perc.uf.connected(0,1);
        //StdOut.println(perc.uf.connected(0,1));
        //StdOut.println(perc.xyTo1D(1,2));
        StdOut.println("The system percolates is a " + perc.percolates() + " statement." );
        }
        
      
  
       
   }
}