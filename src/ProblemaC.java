import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ProblemaC {

	public static void main (String[] args) throws Exception{
		ProblemaC instance = new ProblemaC();
		String endProblems = "0 0";
		int number = 1;
		int n,k;
		n=k=0;
		int[] a,p,m,c;
		a=p=m=c=new int[0];
		try(
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				){
			String line = br.readLine();
			while(line!=null & line.length()>0 && !line.equals(endProblems)) {
				final String [] dataStr = line.split(" ");
				final int[] numbers = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();

				switch (number) {
				case 1:
					n = numbers[0];
					k = numbers[1];
					a = p = new int[n];
					m = c = new int[k];
					break;
				case 2:
					a = numbers;
					break;
				case 3:
					p = numbers;
					break;
				case 4:
					m = numbers;
					break;
				case 5:
					c = numbers;
					break;
				}
				if(number==5) {
					number = 1;
					int[] answers = instance.processProblem(n,k,a,p,m,c);
					System.out.println(answers[0]+ " " + answers[1]);
					n=k=0;
				}
				else number++;
				line = br.readLine();
			}
		}
	}

	private int[] processProblem(int n, int k, int[] a, int[] p, int[] m, int[] c) {
		int[] ans = new int [2];
		int[][] gains = new int [n][k];
		int g;
		for (int j = 0; j < k; j++) {
			for(int i = 0; i< n; i++) {
				if(i==0) gains[i][j]=gain(i,j,n,k,a,p,m,c);
				else gains[i][j]=Math.max(gain(i,j,n,k,a,p,m,c), gains[i-1][j]);
				if (i==n-1 && j==0) {
					ans[0]=0;
					ans[1]=gains[i][j];
				}
				else if(i==n-1){
					g = Math.max(gains[i][j], ans[1]);
					if(ans[1]!=g) {
						ans[1]=g;
						ans[0]=j;
					}
				}
			}
		}
		if(ans[1]<0) {
			ans[0]=0;
			ans[1]=-1;
		}
		else ans[0]++;
		return ans;
	}

	private int gain(int first_i_products, int indexSupplier, int n, int k, int[] a, int[] p, int[] m, int[] c) {
		int ans = 0;
		int cost = c[indexSupplier];
		int amount = m[indexSupplier];
		int kilos = 0;
		int difference = amount;
		int unit = 0;

		for(int i = first_i_products; i >= 0 && difference!=0;i--) {
			unit = (Math.floorDiv(difference,a[i]));
			kilos = unit*a[i];
			difference-=kilos;
			ans+=(unit*p[i]);
		}
		
		return ans-cost;
	}

}
