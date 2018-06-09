package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will
												// initially
												// be null. This is ok! Just
												// build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for
													// simplicity
													// of child/parent
													// computations...
													// the book/animation page
													// both do this.
	}

	// Please do not remove or modify this method! Used to test your entire
	// Heap.
	public EntryPair[] getHeap() {
		return this.array;
	}

	public void insert(EntryPair entry) {
		int index = size + 1;
		array[index] = entry;
		while ((index / 2 != 0) && (array[index].getPriority() < array[index / 2].getPriority())) {
			parentSwap(index);
			index /= 2;
		}
		size++;
	}

	public void delMin() {
		if (size != 0) {
			array[1] = array[size--];
			bubbleDown(1);
		}
	}

	public EntryPair getMin() {
		return array[1];
	}

	public int size() {
		return size;
	}

	private void bubbleDown(int hole) {
		int child;
		EntryPair temp = array[hole];
		boolean moveOn = true;
		while (moveOn == true && (hole * 2 <= size)) {
			child = hole * 2;
			if (child != size && array[child + 1].getPriority() < array[child].getPriority()) {
				child++;
			}
			if (array[child].getPriority() < temp.getPriority()) {
				array[hole] = array[child];
			} else {
				moveOn = false;
			}
			if (moveOn) {
				hole = child;
			}
		}
		array[hole] = temp;
	}

	public void build(EntryPair[] entries) {
		size = entries.length;
		for (int i = 1; i < size + 1; i++) {
			array[i] = entries[i - 1];
		}
		for (int i = size / 2; i > 0; i--) {
			bubbleDown(i);
		}
	}

	public void parentSwap(int index) {
		EntryPair temp = array[index / 2];
		array[index / 2] = array[index];
		array[index] = temp;
	}

}
