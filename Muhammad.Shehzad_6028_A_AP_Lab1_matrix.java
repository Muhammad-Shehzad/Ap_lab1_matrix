import java.util.Scanner;

public class AP_Lab1_matrix {
     private static final int SIZE = 10000;
public static int[][] multiply = new int[SIZE][SIZE];

public int[][] iterative(int r1, int c1, int r2, int c2, int[][] first, int[][] second)
{
	int i, j, k, sum = 0;
	if (c1 != r2)
	{
		System.out.println ("Sorry matrix multilication is not Possible\nPossible only when number of columns of 1st matrix is equal to \nnumber of rows of 2nd matrix ");
	}
	else
	{
		for (i = 0; i < r1; i++)
		{
			for (j = 0; j < c2; j++)
			{
				for (k = 0; k < r2; k++)
				{
					sum = sum + first[i][k] * second[k][j];
				}
				multiply[i][j] = sum;
				sum = 0;
			}
		}
	}
	return multiply;
} //end iterative

 public int[][] multiply(int[][] first, int[][] second)
    {        
        int n = first.length;
        int[][] R = new int[n][n];
        /** base case **/
        if (n == 1)
            R[0][0] = first[0][0] * second[0][0];
        else
        {
            int[][] A11 = new int[n/2][n/2];
            int[][] A12 = new int[n/2][n/2];
            int[][] A21 = new int[n/2][n/2];
            int[][] A22 = new int[n/2][n/2];
            int[][] B11 = new int[n/2][n/2];
            int[][] B12 = new int[n/2][n/2];
            int[][] B21 = new int[n/2][n/2];
            int[][] B22 = new int[n/2][n/2];
 
            /** Dividing matrix A into 4 halves **/
            split(first, A11, 0 , 0);
            split(first, A12, 0 , n/2);
            split(first, A21, n/2, 0);
            split(first, A22, n/2, n/2);
            /** Dividing matrix B into 4 halves **/
            split(second, B11, 0 , 0);
            split(second, B12, 0 , n/2);
            split(second, B21, n/2, 0);
            split(second, B22, n/2, n/2);
 
            /** 
              M1 = (A11 + A22)(B11 + B22)
              M2 = (A21 + A22) B11
              M3 = A11 (B12 - B22)
              M4 = A22 (B21 - B11)
              M5 = (A11 + A12) B22
              M6 = (A21 - A11) (B11 + B12)
              M7 = (A12 - A22) (B21 + B22)
            **/
 
            int [][] M1 = multiply(add(A11, A22), add(B11, B22));
            int [][] M2 = multiply(add(A21, A22), B11);
            int [][] M3 = multiply(A11, sub(B12, B22));
            int [][] M4 = multiply(A22, sub(B21, B11));
            int [][] M5 = multiply(add(A11, A12), B22);
            int [][] M6 = multiply(sub(A21, A11), add(B11, B12));
            int [][] M7 = multiply(sub(A12, A22), add(B21, B22));
 
            /**
              C11 = M1 + M4 - M5 + M7
              C12 = M3 + M5
              C21 = M2 + M4
              C22 = M1 - M2 + M3 + M6
            **/
            int [][] C11 = add(sub(add(M1, M4), M5), M7);
            int [][] C12 = add(M3, M5);
            int [][] C21 = add(M2, M4);
            int [][] C22 = add(sub(add(M1, M3), M2), M6);
 
            /** join 4 halves into one result matrix **/
            join(C11, R, 0 , 0);
            join(C12, R, 0 , n/2);
            join(C21, R, n/2, 0);
            join(C22, R, n/2, n/2);
        }
        
        /** return result **/    
        return R;
    }
 
  public int[][] sub(int[][] first, int[][] second)
    {
        int n = first.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = first[i][j] - second[i][j];
        return C;
    }
    public int[][] add(int[][] first, int[][] second)
    {
        int n = first.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = first[i][j] + second[i][j];
        return C;
    }
    public void split(int[][] P, int[][] C, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                C[i1][j1] = P[i2][j2];
    }
    public void join(int[][] C, int[][] P, int iB, int jB) 
    {
        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }    
    
    public static void main(String[] args) {
        // TODO code application logic here
        AP_Lab1_matrix rc=new AP_Lab1_matrix();
        Scanner scan = new Scanner(System.in);
        int[][] first = new int[SIZE][SIZE];
        int[][] second = new int[SIZE][SIZE];
        char choice;
        
        	for (;;)					//Infinite Loop.
	{
		System.out.println ( "Please enter \n\t 1: for Iterative Multiplication.\n\t 2: for Strassen Multiplication.\n\t 3: To exit at anytime. " );
		choice= scan.next().charAt(0);
		switch (choice)
		{
		case '1':			//for Iterative Multiplication
			System.out.println ( "Enter no of rows and columns of 1st matrix!\n");
                        int r1 = scan.nextInt();
                        int c1 = scan.nextInt();
                        System.out.println ( "Enter no of rows and columns of 2nd matrix!\n");
                        int r2 = scan.nextInt();
                        int c2 = scan.nextInt();
            			for (int i = 0; i < r1; i++)
            			{
            				for (int j = 0; j < c1; j++)
            					first[i][j]=(int )(Math.random() * 10 + 1);

            			}
            			
            			for (int i = 0; i < r2; i++)
            			{
            				for (int j = 0; j < c2; j++)
            					second[i][j] = (int )(Math.random() * 10 + 1);

            			}
            			long start = System.nanoTime();
            			multiply= rc.iterative(r1, c1,r2,c2, first, second);
            	        long stop = System.nanoTime();
            	        System.out.println("Time taken by Iterative multiplication is:  "+((stop - start)/1000000000.0000));
/*            	        
                        System.out.println("\nProduct of matrices A and  B using Iterative Multiplication : ");
                        for (int i = 0; i < r1; i++)
                        {
                            for (int j = 0; j < c2; j++)
                                System.out.print(multiply[i][j] +"   ");  //Printing elements of resultant Matrix
                            System.out.println();
                        } 
 */                                  			
                        	break;
                case '2':			//for Strassen Multiplication
                        System.out.println("Enter order of Matrix :");
                        int N = scan.nextInt();
                        int[][] first1 = new int[N][N];
                        for (int i = 0; i < N; i++)
                            for (int j = 0; j < N; j++)
                                first1[i][j] = (int)(Math.random()*10); //Giving random elements to 1st Matrix

                        int[][] second1 = new int[N][N];
                        for (int i = 0; i < N; i++)
                            for (int j = 0; j < N; j++){
                                second1[i][j] = (int)(Math.random()*10);  //Giving random elements to 1st Matrix
                            }
                        long start1 = System.nanoTime();
                        int[][] multiply1 = rc.multiply(first1, second1);
                        long stop1 = System.nanoTime();
            	        System.out.println("Time taken by Strassen multiplication is:  "+((stop1 - start1)/1000000000.0000));
/*
                        System.out.println("\nProduct of matrices A and  B using Strassen Multiplication : ");
                        for (int i = 0; i < N; i++)
                        {
                            for (int j = 0; j < N; j++)
                                System.out.print(multiply1[i][j] +"   ");  //Printing elements of resultant Matrix
                            System.out.println();
                        }
*/                        
                        	break;
		case '3':						//to exit
			return;
		default:
			System.out.println ("Sorry wrong no entered" );
		}
		
	}							//End for loop    	
    } //end main
    
}
