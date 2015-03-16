public class PercolationStats {
    private boolean[][] open; // keeps track of the blocked grid sites
    private Percolation perc;  // creating an instance of the Percolation class
    private double[] open_sites_needed;  // keeps track of the number of open sites needed for percolation per experiment
    private int count;  // keeps track of the number of open sites used during an experiment
    private int[] locations;  // 1D locations of the array
    private int counter;     // used for counting whatever...
    private int grid_size;   //size of the grid
    private int x; // denotes x coordinates of the grid sites
    private int y; // denotes y coordinates of the grid sites
    private double mean;  // the average of the number of sites need for each experiment
    private double std_dev; // the standard deviation
    private double low_bound; //lower bound for the 95% conf.int.
    private double upper_bound; //upper bound for the 95% conf.int.
    private int numb_of_exp; //the number of expriments, i.e. T
    private boolean dummy;//mine!
    private int dummy2; //also just mine!!
    private int dummy3; //also just mine!!
    private int OneDtoX(int i) {
       // gives x-coordinate of 1D location
       return i/grid_size + 1;
   }
    
   private int OneDtoY(int i) { //gives y-coordinate of 1D location
       return i%grid_size + 1;  
   }
    public PercolationStats(int N, int T) {   
        // perform T independent experiments on an N-by-N grid
        
        open = new boolean [N + 1] [N + 1];
        open_sites_needed = new double [T + 1]; 
        locations = new int [N * N];
        grid_size = N;
        numb_of_exp = T;
        
        for (int i = 1; i < N + 1; i++) {
           for (int j = 1; j < N + 1; j++) {
               open[i][j] = false;
            }
        }
        for (int i = 0; i < N*N ; i++){
            //initializing the locations array
            locations[i] = i;
        }
        StdRandom.shuffle(locations); //shuffling the locations array
        for (int i = 1; i <= T; i++) {
            perc = new Percolation(grid_size); 
            counter = 0;
            dummy = !perc.percolates();
            while (!perc.percolates()){
                x = locations[counter]/N + 1;
                y = locations[counter]%N + 1;
                if (!perc.isOpen(x, y)) 
                    perc.open(x, y);
                counter += 1;
                if (counter > 0) dummy2 = counter;
            } 
            dummy3 = counter;
            open_sites_needed[i] = (1.0d *counter)/(grid_size*grid_size);
         }
    }
    
    public double mean() {
        // sample mean of percolation threshold
        //int sum = 0;
        //for (int d : open_sites_needed) sum += d;
        //mean = (1.0d * sum) / open_sites_needed.length;
        mean = StdStats.mean(open_sites_needed);
        return mean;
    }
    public double stddev()  {
        // sample standard deviation of percolation threshold
        std_dev = StdStats.stddev(open_sites_needed);
        return std_dev;
    }   
   public double confidenceLo() {
        // low  endpoint of 95% confidence interval
        low_bound = mean - (1.96 * std_dev)/Math.sqrt(numb_of_exp);
        return low_bound;
   }
   public double confidenceHi() { 
        // high endpoint of 95% confidence interval
       upper_bound = mean + (1.96 * std_dev)/Math.sqrt(numb_of_exp);
       return upper_bound;
   }
       

   public static void main(String[] args){
       // test client (described below)
       int N = StdIn.readInt();
       int T = StdIn.readInt();
       PercolationStats perc_stat = new PercolationStats(N,T);
       //for (int i = 0; i < N*N; i++)
          // StdOut.println(perc_stat.locations[i]);
       //StdOut.println(perc_stat.perc.percolates());
       //for (int i = 1; i <=T; i++)
        //   StdOut.println(perc_stat.open_sites_needed[i]);
       //StdOut.println(perc_stat.dummy);
      // StdOut.println(perc_stat.dummy2);
       StdOut.println("mean                    = " + perc_stat.mean());
       StdOut.println("stddev                  = " + perc_stat.stddev());
       StdOut.println("95% confidence interval = " + perc_stat.confidenceLo() +" " + perc_stat.confidenceHi());
       
   }
}