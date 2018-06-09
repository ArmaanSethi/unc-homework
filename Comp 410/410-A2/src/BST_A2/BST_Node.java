package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	public boolean containsNode(String s) {//good
		BST_Node curr = this;
		int compare;
		while(curr != null){
			compare = s.compareTo(curr.getData());
			if(compare < 0)
				curr = curr.getLeft();
			else if (compare > 0)
				curr = curr.getRight();
			else
				return true;
		}
		return false;
	}

	public boolean insertNode(String s) {//good
		BST_Node curr = this;
		int compare;
		while (curr != null) {
			compare = s.compareTo(curr.getData());
			if (compare < 0) {
				if (curr.getLeft() == null) {
					curr.left = new BST_Node(s);
					return true;
				} else {
					curr = curr.getLeft();
				}
			} else if (compare > 0) {
				if (curr.getRight() == null) {
					curr.right = new BST_Node(s);
					return true;
				} else {
					curr = curr.getRight();
				}
			} else {
				return false;
			}
		}
//		System.out.println("Something went really wrong");
		return false;
	}

	public BST_Node removeNode(String s) {
		BST_Node curr = this;
		int compare = s.compareTo(curr.data);
		if(compare < 0){
			curr.left = curr.getLeft().removeNode(s);
		}else if(compare > 0){
			curr.right = curr.getRight().removeNode(s);
		}else{
			if(curr.left == null){
				return curr.right;
			}
			else if(curr.right == null){
				return curr.left;
			}
			BST_Node temp = curr;
			curr = temp.right.findMin();
			curr.right =temp.right.removeNode(curr.data);
			curr.left = temp.left;
		}
		return curr;
	}

	public BST_Node findMin() {//good
		if (left != null) 
			return left.findMin();
		 else 
			return this;
	}

	public BST_Node findMax() {//good
		if (right != null) 
			return right.findMax();
		 else 
			return this;
	}

	public int getHeight() {//good
		BST_Node curr = this;
		int left_height = 0;
		int right_height = 0;
		if ((curr.getLeft() == null) && (curr.getRight() == null)) {
			return 0;
		}
		if(curr.getLeft()!=null){
			left_height = curr.getLeft().getHeight();
		}
		if(curr.getRight()!= null){
			right_height = curr.getRight().getHeight();
		}
		if(left_height > right_height){
			left_height++;
			return left_height;
		}else{
			right_height++;
			return right_height;
		}
	}

	// --- end fill in these methods --------------------------------------

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
}
