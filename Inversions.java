public class Inversions {

    public static void main(String[] args) {
        In in = new In("5ints.txt");
        int n = in.readInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.readInt(); 
        }
        
        int[] aux = new int[n];
  
        
        //get start and end time for merge sort
        final long startTime = System.currentTimeMillis();
        System.out.println(getInversionCount(a, aux, 0, a.length-1));
        
        final long endTime = System.currentTimeMillis();
        System.out.println("\nMerge sort time: " + (endTime - startTime)/10);
    }

    private static long getInversionCount(int[] a, int[] aux, int lo, int hi) {
        long inversionCount = 0;
        if (hi <= lo) {
            return inversionCount;
        }
        int mid = lo + (hi - lo) / 2;
        inversionCount += getInversionCount(a, aux, lo, mid);
        inversionCount += getInversionCount(a, aux, mid + 1, hi);
        
        //YOUR CODE HERE
        
        // adds up final merge sorts
        inversionCount += merge(a, aux, lo, mid, hi);
        return inversionCount;
    }
    
    //Big O runtime is O(n^2) 2 for loops


    // MODIFY THIS METHOD SO THAT getInversionCount() COUNTS THE NUMBER
    // OF INVERSIONS CORRECTLY
    private static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        long inversionCount = 0;
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            
            //counts inversions
            
            //Big O is O(nlogn)
            else if (aux[j] < aux[i]) {    a[k] = aux[j++]; inversionCount+= (mid - i + 1); }
            else                           a[k] = aux[i++];
        }
        return inversionCount;
    }

}
