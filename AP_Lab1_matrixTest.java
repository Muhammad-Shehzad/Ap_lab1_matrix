import static org.junit.Assert.*;

import org.junit.Test;

public class AP_Lab1_matrixTest {

	@Test
	public void test() {
		AP_Lab1_matrix junit = new AP_Lab1_matrix();
        int[][] first = new int[2][2];
        int[][] second = new int[2][2];
		int[][] multiply = new int[2][2];
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
				first[i][j]=2;

		}
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
				second[i][j] = 2;

		}
		
		multiply = junit.iterative(2,2,2,2,first,second);
		int[][] expected = new int[][]{
			{8,8},
			{8,8}
		};
		
		boolean check = true;
		for(int i=0; check && i<2; i++){
			for(int j=0; check && j<2; j++){
				if (multiply[i][j]!=expected[i][j])
					check = false;
			}
		}
		
		assertEquals(true, check);
		
	//	fail("Not yet implemented");
	}

	
	@Test
	public void test1() {
		AP_Lab1_matrix junit = new AP_Lab1_matrix();
        int[][] first = new int[2][2];
        int[][] second = new int[2][2];
		int[][] multiply = new int[2][2];
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
				first[i][j]=2;

		}
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
				second[i][j] = 2;

		}
		
		multiply = junit.multiply(first,second);
		int[][] expected = new int[][]{
			{8,8},
			{8,8}
		};
		
		boolean check = true;
		for(int i=0; check && i<2; i++){
			for(int j=0; check && j<2; j++){
				if (multiply[i][j]!=expected[i][j])
					check = false;
			}
		}
		
		assertEquals(true, check);
		
	//	fail("Not yet implemented");
	}
}
