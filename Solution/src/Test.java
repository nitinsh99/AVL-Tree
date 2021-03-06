import java.util.Scanner;

class Node 
{
	 int val;   //Value
	   int ht;      //Height
	   Node left;   //Left child
	   Node right;   //Right child
}
  

public class Test 
{
	
	public static Node pivot=null;
	static Node pivotParent=null;
	static boolean pivotFound=false;
	static Node mainroot=null;
	
	public static void main(String args[])
	{
		//System.out.println("Hello World");
		
		Scanner sc = new Scanner(System.in);
		//pivot=mainroot = null;
		//pivotFound=false;
		
		Node ultra=null;
		
		while(true)
		{
			int temp = sc.nextInt();
			if(temp==-1)
			{
				break;
			}
			ultra=insert(mainroot,temp);

			
		}
		printTree(ultra);

	}
	
	static void rotationCheck(int temp)
	{
		//printTree(mainroot);
		//System.out.println();
		
		if(pivotFound)
		{
			findit(mainroot);
			String type = findRotation(pivot,temp);
			//System.out.println(pivot.val+" "+pivotParent.val+" "+type);
			rotate(type);
			//System.out.println(mainroot.left.val+" "+mainroot.val+" "+mainroot.right.val);

		}
		
		pivotFound=false;
	}
	
	static Node insert(Node root, int val)
	{
		
		
	   Node temp  = new Node();
	   
	   temp.val=val;
	   temp.ht=1;
	   temp.left=null;
	   temp.right=null;
	   
	   if(root == null)
	   {
		   root=temp;
		   mainroot=root;
	   }
	   else
	   {
		    mainroot=insertIt(root,temp);
		    rotationCheck(val);

	   }
	    return mainroot;

	}
	
	static Node insertIt(Node root, Node temp)
	{
		
		
		if(temp.val<root.val)
		{
			if(root.left==null)
			{
				root.left = temp;
				increaseHeight(root);
				if(checkIfPivot(root))
				{
					if(!pivotFound)
					{
						pivot=root;
						pivotFound=true;

					}
				}
				return root;
				
			}
			else
			{
				insertIt(root.left,temp);
				increaseHeight(root);
				if(checkIfPivot(root))
				{
					if(!pivotFound)
					{
						pivot=root;
						pivotFound=true;

					}
				}
				return root;

			}
		}
		else
		{
			if(root.right==null)
			{
				root.right = temp;
				increaseHeight(root);

				if(checkIfPivot(root))
				{
					if(!pivotFound)
					{
						pivot=root;
						pivotFound=true;

					}
				}
				return root;
				
			}
			else
			{
				 insertIt(root.right,temp);
					increaseHeight(root);

				if(checkIfPivot(root))
				{
					if(!pivotFound)
					{
						pivot=root;
						pivotFound=true;

					}
				}
				return root;

			}		
			
		}
	}
	
	static boolean checkIfPivot(Node subject)
	{
		int leftHeight=0;
		int rightHeight=0;
		
		if(subject.left!=null)
		{
			leftHeight=subject.left.ht;
		}
		else
		{
			leftHeight=0;
		}
		
		if(subject.right!=null)
		{
			rightHeight=subject.right.ht;
		}
		else
		{
			rightHeight=0;
		}
		
		int bf = Math.abs(leftHeight-rightHeight);
		//System.out.println(subject.val);
		//System.out.println(bf);
		if(bf>1)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	static void printTree(Node root)
	{
		if(root==null)
		{
			return;
		}
		
		Node subject=root;
		int bf=0;
		if(subject.left!=null && subject.right!=null)
		{
			bf=subject.left.ht-subject.right.ht;
			
			
		}
		else if(subject.right==null && subject.left!=null)
		{
			bf= subject.left.ht;

		}
		else if(subject.left==null && subject.right!=null)
		{
			bf= subject.right.ht;

		}
		else
		{
			bf=0;
		}
		
		printTree(root.left);
		System.out.print(root.val+" BF("+bf+") ");
		printTree(root.right);
	}

	static void increaseHeight(Node subject)

	{
		if(subject.left!=null && subject.right!=null)
		{
			if(subject.left.ht>subject.right.ht)
			{
				subject.ht= subject.left.ht+1;
			}
			else
			{
				subject.ht= subject.right.ht+1;
			}
		}
		else if(subject.right==null && subject.left!=null)
		{
			subject.ht= subject.left.ht+1;

		}
		else if(subject.left==null && subject.right!=null)
		{
			subject.ht= subject.right.ht+1;

		}
		else
		{
			subject.ht=1;
		}
		
	}

	static String findRotation(Node pivot,int temp)
	{
		try
		{
			if(pivot.left!=null)
			{
				if(pivot.left.left!=null)
				{
					if(temp == pivot.left.left.val)
					{
						return "LL";
					}
				}
				if(pivot.left.right!=null)
				{
					if(temp == pivot.left.right.val)
					{
						return "LR";
					}	
				}
			}
			
			
			if(pivot.right!=null)
			{
				if(pivot.right.left!=null)
				{
					if(temp == pivot.right.left.val)
					{
						return "RL";
					}	
				}
				
				if(pivot.right.right!=null)
				{
					if(temp == pivot.right.right.val)
					{
						return "RR";
					}	
				}
			}
			
			
			
			
		}
		catch(NullPointerException n)
		{
			return "duder";
		}
		return "";
		
	}
	
	static void rotate(String type)
	{
		
		if(type=="LL")
		{
			if(pivotParent==null)
			{
				Node newPivot = pivot.left;
				Node pivotChildRight = pivot.left.right;
				pivot.left.right = pivot;
				pivot.left = pivotChildRight;
				mainroot = newPivot;
				increaseHeight(newPivot.right);

				increaseHeight(newPivot);
				
			}
			else
			{
				if(pivotParent.left==pivot)
				{
					
					Node newPivot = pivot.left;
					
					Node pivotChildRight = pivot.left.right;
					pivot.left.right = pivot;
					pivot.left = pivotChildRight;
					pivotParent.left=newPivot;
					increaseHeight(newPivot.right);

					increaseHeight(newPivot);
					increaseHeight(pivotParent);
				}
				else
				{
					Node newPivot = pivot.left;
					
					Node pivotChildRight = pivot.left.right;
					pivot.left.right = pivot;
					pivot.left = pivotChildRight;
					pivotParent.right=newPivot;
					increaseHeight(newPivot.right);

					increaseHeight(newPivot);
					increaseHeight(pivotParent);

					
				}
				
			}
		}
		

		if(type=="RR")
		{
			if(pivotParent==null)
			{
				Node newPivot = pivot.right;
				Node pivotChildLeft = pivot.right.left;
				pivot.right.left = pivot;
				pivot.right = pivotChildLeft;
				mainroot = newPivot;
				increaseHeight(newPivot.left);
				increaseHeight(newPivot);
				
			}
			else
			{
				if(pivotParent.right==pivot)
				{
					
					Node newPivot = pivot.right;
					
					Node pivotChildLeft = pivot.right.left;
					pivot.right.left = pivot;
					pivot.right = pivotChildLeft;
					pivotParent.right=newPivot;
					increaseHeight(newPivot.left);
					increaseHeight(newPivot);
					increaseHeight(pivotParent);


				}
				else
				{
					Node newPivot = pivot.right;
					
					Node pivotChildLeft = pivot.right.left;
					pivot.right.left = pivot;
					pivot.right = pivotChildLeft;
					pivotParent.left=newPivot;
					increaseHeight(newPivot.left);

					increaseHeight(newPivot);
					increaseHeight(pivotParent);


					
				}
				
			}
		}
		
		if(type=="RL")
		{
			//System.out.println("RL");
			Node actualPivotParent = pivotParent;
			Node actualPivot = pivot;
			
			pivotParent = pivot;
			pivot = pivot.right;
			
			rotate("LL");
			
			pivotParent = actualPivotParent;
			pivot = actualPivot;
			
			rotate("RR");
			
		}
		
		if(type=="LR")
		{
			//System.out.println("RL");
			Node actualPivotParent = pivotParent;
			Node actualPivot = pivot;
			
			pivotParent = pivot;
			pivot = pivot.left;
			
			rotate("RR");
			
			pivotParent = actualPivotParent;
			pivot = actualPivot;
			
			rotate("LL");
		}
		
	}
	
	static void findit(Node root)
	{
		if(root==null)
		{
			return;
		}
		
		if(root.left==pivot || root.right==pivot)
		{
			pivotParent=root;
		}
		else
		{
			findit(root.left);
			findit(root.right);
		}
	}
}




