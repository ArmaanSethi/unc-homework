package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;

	public BST() {
		size = 0;
		root = null;
	}

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {
		return root;
	}

	public boolean insert(String s) {
		if (root == null) {
			root = new BST_Node(s);
			size++;
			return true;
		} else {
			if (root.insertNode(s)) {
				size++;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean remove(String s) {
		// TODO Auto-generated method stub
		if(root == null){
			return false;
		}
		else if(root.getData() == s)
		{//delete root
			BST_Node left = root.getLeft();
			BST_Node right = root.getRight();
			if((left == null)&&(right == null)){
				root = null;
				size--;
				return true;
			}
			else if((left != null)&&(right == null)){
				root = left;
				size--;
				return true;
			}
			else if((left == null)&&(right != null)){
				root = right;
				size--;
				return true;
			}
			else //if((left != null)&&(right != null))
			{
				root.data = root.getRight().findMin().data;
				root.getRight().removeNode(root.getData());
				size--;
				return true;
			}
		}		
		else{
			if(root.containsNode(s)){
				root.removeNode(s);
				size--;
				return true;
			}else{
				return false;
			}
		}
	}

	public String findMin() {
		if (root == null) {
			return null;
		} else {
			return root.findMin().getData();
		}
	}

	public String findMax() {
		if (root == null) {
			return null;
		} else {
			return root.findMax().getData();
		}
	}

	public boolean empty() {
		if(size==0){
			return true;
		}
		return false;
	}

	public boolean contains(String s) {
		if(root == null){
			return false;
		}else{
			return root.containsNode(s);
		}
	}
	
	public int size() {
		return size;
	}

	public int height() {
		if (root == null) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

}
