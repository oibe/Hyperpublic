
public class Problem2 
{	
	public static int solve(int total,int[] coins)
	{
		int[] cache = new int[total+1];
		for(int k = 1; k <= total; k++)
			cache[k]= -1;
		
		cache[0] = 0;
		for(int value = 1; value <= total;value++)
		{
			int best = Integer.MAX_VALUE;
			for(int j = 0; j < coins.length;j++)
			{
				if(value-coins[j]== 0)
				{
					best = 1;
				}
				else if(value-coins[j] > 0)
				{
					if(cache[value-coins[j]] == -1)
					{
						continue;
					}
					else	
					{
						int val = cache[value-coins[j]]+1;
						best = (val < best)? val: best;
					}
				}
			}
			cache[value] = best == Integer.MAX_VALUE ? -1 : best;
		}

		return cache[cache.length-1];
	}
	public static void main(String[] args)
	{
		int[] coins = {2,3,17,23,42,98};
		System.out.println(solve(2349, coins)*solve(2102,coins)*solve(2001,coins)*solve(1747,coins));
	}
}
