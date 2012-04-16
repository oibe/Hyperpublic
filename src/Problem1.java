import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class User
{
	boolean visitied = false;
	int id;
	int influence = 0;
	LinkedList<Integer> friendList = new LinkedList<Integer>();
	public User(int id)
	{
		this.id = id;
	}
}

public class Problem1 
{
	
	static ArrayList<User> userList = new ArrayList<User>();
	static HashMap<Integer,ArrayList<User>> reverseIndex = new HashMap<Integer,ArrayList<User>>();
	static int[] scores;
	
	public static void calculateInfluence(int id)
	{
		if(scores[id] == -1)
		{
			Stack<User> sta = new Stack<User>();
			Queue<User> q = new LinkedList<User>();
			User current = userList.get(id);
			q.add(current);
			
			while(!q.isEmpty())
			{
				User temp = q.poll();
				sta.push(temp);
				for(Integer i : temp.friendList)
				{
					if(userList.get(i).visitied  == false)
						q.add(userList.get(i));
				}
			}
			
			while(!sta.isEmpty())
			{
				User temp = sta.pop();
				for(Integer i: temp.friendList)
				{
					if(scores[i] != -1)
						temp.influence+= scores[i];
				}
				scores[temp.id] = temp.influence;
				temp.visitied = true;
			}
		}
	}
	
	
	public static void main(String[] args) 
	{
		if(args.length == 0)
		{
			System.err.println("You need to enter a file path as a command line argument.");
			return;
		}
		Scanner sc = null;
		try {
			sc = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int userid = 0;
		while(sc.hasNext())
		{
			String line = sc.nextLine();
			User newUser = new User(userid);
			for(int childIndex = 0; childIndex < line.length();childIndex++)
			{
				if(line.charAt(childIndex) == 'X')
				{
					newUser.influence++;
					
					newUser.friendList.add(childIndex);
				}
			}
			userList.add(newUser);
			userid++;
		}
		scores = new int[userid];
		for(int i = 0; i < userid;i++)
		{
			scores[i]=-1;
		}
		
		for(int i = 0; i < userid;i++)
			calculateInfluence(i);
		
		ArrayList<Integer> sortScores = new ArrayList<Integer>();
		for(int i = 0; i < userid;i++)
			sortScores.add(scores[i]);
		

		Collections.sort(sortScores);
		Collections.reverse(sortScores);

		System.out.println(sortScores.get(0)+""+sortScores.get(1)+""+sortScores.get(2)+"");
	}

}
