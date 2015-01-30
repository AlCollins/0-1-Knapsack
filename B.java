import java.util.Scanner;

/**********************************************************
 * Application of the 0-1 knapsack problem.
 * A Dynamic Programming Solution.
 * 
 * value: number of customers
 * weight: power requirement
 * input line consists of 
 * 	substation number, power required, number of customers
 *  goal is to maximize the number of customers recieving 
 *  power without overloading the system. 
 * 
 *@author al collins
 *********************************************************/
public class B {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int cases = scan.nextInt();
		
		//get input 
		for(int cas = 0; cas < cases; cas++) {
			int cap = scan.nextInt();
			int n = scan.nextInt();
			
			//storage arrays
			int[] names = new int[n+1];
			int[] v = new int[n+1];
			int[] w = new int[n+1];
			
			for (int i=1; i <= n; i++) {
				names[i] = scan.nextInt();
				w[i] = scan.nextInt();
				v[i] = scan.nextInt();
			}
			z1knapsack(v, w, n, cap);
		}

		
	}
	
	public static void z1knapsack(int[] v, int[] w, int n, int cap) {
		int[][] value = new int[n+1][cap+1];
		int[][] keep = new int[n+1][cap+1];
		
		//set the top row of value and keep arrays to all 0.
		for(int i=0; i<=cap; i++) {
			value[0][i] = 0;
			keep[0][i] = 0;
		}
		
		//set leftmost column of value array to all 0.
		for(int i=0; i<=n; i++) {
			value[i][0] = 0;
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=cap; j++) {
				if(w[i] > j) {
					value[i][j] = value[i-1][j];
					keep[i][j] = 0;
				} else {
					if(value[i-1][j] >= v[i] + value[i-1][j-w[i]]) {
						value[i][j] = value[i-1][j];
						keep[i][j] = 0;
					} else {
						value[i][j] = v[i] + value[i-1][j-w[i]];
						keep[i][j] = 1;
					}
				}
				//System.out.println("i: " + i + " j: " + j + " value: " + value[i][j] );
				//System.out.println("i: " + i + " j: " + j + " keep: " + keep[i][j] );
			}
		}
		System.out.println("Total Customers: " + value[n][cap]);
		System.out.print("substations: ");
		
		int i = n;
		int j = cap;
		while (i > 0) {
			if (keep[i][j] == 1) {
				System.out.print(1000 + i);
				System.out.print(" ");
				j -= w[i];
			}
			i--;
		}
		System.out.println("\n");
	}
}
