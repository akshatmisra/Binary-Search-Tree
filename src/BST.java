
public class BST {
	
	Tree root;
	class Tree
	{
		int data;
		Tree left;
		Tree right;
		Tree(int val)
		{
			data = val;
			left = right = null;
		}
	}

	public Tree search( Tree temp, int val)
	{
		if(temp == null)
			return null;
		while(temp != null)
		{
			if(temp.data > val)
				temp = temp.left;
			else if (temp.data < val)
				temp = temp.right;
			else 
				return temp;	
		}
		return null;
	}
	
	public void insert(int val)
	{
		root = insert(root,val);
	}
	
    Tree insert(Tree root,int val)
	{
		Tree node = new Tree(val);
		if (root == null)
		{
			root = node;
			return root;
		}
		if(root.data < val)
			root.right = insert(root.right,val);
		else
			root.left = insert(root.left, val);
		return root;
	}
	
	public void Inorder(Tree temp)
	{
		if(temp == null)
			return;
		Inorder(temp.left);
		System.out.println(temp.data);
		Inorder(temp.right);
	}
	
	public Tree FindMin(Tree temp)
	{
		if(temp == null)
			return null;
		else
		{
			if(temp.left == null)
				return temp;
			else
				return FindMin(temp.left);	
		}
	}


	public Tree FindMax(Tree temp)
	{
		if(temp == null)
			return null;
		else
		{
			if(temp.right == null)
				return temp;
			else
				return FindMax(temp.right);
		}
	}

	
	public Tree Delete(Tree temp, int val)
	{
		Tree node;
		if(temp == null)
			System.out.println("Value not fonud in the tree");
		else if(temp.data > val)
			temp.left = Delete(temp.left, val);
		else if (temp.data < val)
			temp.right = Delete(temp.right, val);
		else
		{
			//If value is found
			if(temp.left != null && temp.right !=null)
			{
				// replace with the largest value in the left tree
				node = FindMax(temp.left);
				temp.data = node.data;
				temp.left = Delete(temp.left, temp.data);
			}
			else
			{
				// if only 1 child
				node = temp;
				if(temp.left == null)
					return temp.right;
				if(temp.right == null)
					return temp.left;	
			}
		}
		return temp;
	}

	public Tree FindLCA(Tree temp, Tree a, Tree b)
	{
		while(1==1)
		{
			if((a.data < temp.data && b.data > temp.data) || (a.data > temp.data && b.data < temp.data))
					return temp;
			if(a.data < temp.data)
				temp = temp.left;
			else
				temp = temp.right;	
		}
	}

	public boolean IsBST(Tree temp)
	{
		if (temp == null)
			return true;
		if(temp.left != null && temp.data < temp.left.data)
			return false;
		if(temp.right != null && temp.data > temp.right.data)
			return false;
		if(!IsBST(temp.left) || !IsBST(temp.right))
			return false;
		return true;
	}

	public Tree BSTfromArray(int [] arr, int left, int right)
	{
		if(left > right)
			return null;
		int mid;
		Tree node;
		if(left == right)
		{
			node = new Tree(arr[left]);
		}
		else
		{
			mid = left + (right -left)/2;
			node = new Tree(arr[mid]);
			node.left = BSTfromArray(arr,left, mid-1);
			node.right = BSTfromArray(arr, mid+1, right);
		}
		return node;
	}
	
	int count = 0;

	public Tree KthSmallestElement(Tree temp,int k)
	{
		if (temp == null)
			return null;
		// Check the left node since the smaller elements 
		// will be in left subtree once done move to right subtree
		Tree left = KthSmallestElement(temp.left, k);
	
		if(left != null)
			return left;
		if(++count == k)// if the kth element is temp
			return temp;
	
		return KthSmallestElement(temp.right, k);
	}
	
	public static void main(String args[])
	{
		BST bst = new BST();
		bst.insert(15);
		bst.insert(25);
		bst.insert(5);
		bst.insert(151);
		bst.insert(52);
		bst.insert(8);
		bst.insert(11);
		bst.insert(10);
		bst.insert(12);
		bst.insert(9);
		bst.insert(23);
		bst.insert(45);
		
		//bst.Inorder(bst.root);
		
		Tree node = bst.search(bst.root, 151);
		
		Tree min = bst.FindMin(bst.root);
		
		Tree max = bst.FindMax(bst.root);
		
		Tree lca = bst.FindLCA(bst.root, bst.root.right.left, bst.root.right.right.left.left);
		
		//bst.Delete(bst.root, 5);
		System.out.println(bst.IsBST(bst.root));
		
		int [] arr = {12, 25, 34, 49, 51, 60, 78, 80};
		Tree bstfromarr = bst.BSTfromArray(arr, 0, 7);
		int count = 0;
		//Tree smallest = bst.KthSmallestElement(bstfromarr, 3,count);
		
		//System.out.println("kth smallest value is " +smallest.data);
		//bst.Inorder(bstfromarr);
		
//		if(max != null)
//			System.out.println("Max Value is " + max.data);
//		else
//			System.out.println("tree is empty");
		
//		if(node !=null)
//			System.out.println("Value found ");
//		else 
//			System.out.println("Value not found");
	}
}
