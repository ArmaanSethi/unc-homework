/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

	public class LinkedListImpl implements LIST_Interface {
	  Node root;//this will be the entry point to your linked list (the head)
	  int size = 0;
	  
	  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
	    root=new Node(0); //Note that the root's data is not a true part of your data set!
	  }
	  
	  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
	  
	  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
	    return root;
	  }
	
	public boolean insert(Node n, int index) {
		if(index > size || index < 0){
			//System.out.println("index is bad");
			return false;
		}	
		Node curr = root;
		int i = 0;
		while(curr.getNext() != null && i < index){
			//System.out.println("iterating");
			curr = curr.getNext();
			i++;
		}
		//System.out.println("setting");
		n.next = curr.getNext();
		curr.next = n;
		++size;
		return true;
	}
	
	public boolean remove(int index) {
		if(index > size-1 || index < 0){
			//System.out.println("index is bad");
			return false;
		}
		Node curr = root; 
		int i = 0;
		while(curr.getNext()!= null && i < index){
			curr = curr.getNext();
			//System.out.println("iterating");
			++i;
		}
		//System.out.println("deleting");
		--size;
		curr.next = curr.getNext().getNext();
		return true;
	}
	
	public Node get(int index) {
		if(index > size||index < 0){
			return null;
		}else{
			Node curr = root;
			int i = 0;
			while(curr.getNext() != null && i < index){
				curr = curr.getNext();
				++i;
			}
			return curr.getNext();
	
		}
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		if(size==0){
			return true;
		}
		return false;
	}
	
	public void clear() {
		root.next = null;
		size = 0;
	}
}
