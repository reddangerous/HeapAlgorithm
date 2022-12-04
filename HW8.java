import java.util.Scanner;

public class HW8 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        System.out.println("Enter size");
		int n = sc.nextInt();
		int[][] adjacency_list = new int[n][];
		for (int i = 0; i < n; i++) {
            System.out.println("enter vertex");
			int v = sc.nextInt();
            System.out.println("enter the matrix");
			int m = sc.nextInt();
			adjacency_list[v - 1] = new int[2 * m];
			for (int j = 0; j < m; j++) {
				adjacency_list[v - 1][2 * j] = sc.nextInt();
				adjacency_list[v - 1][2 * j + 1] = sc.nextInt();
			}
		}
		sc.close();
		int[] back_pointer = new int[n];
		int[] value = new int[n];
		boolean[] visited= new boolean[n];
		for (int i = 0; i < n; i++) {
			back_pointer[i] = -1;
			value[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}
		value[0] = 0;
		HeapPQueue heap = new HeapPQueue(n);
		heap.insert(0, 0);
		while (!heap.isEmpty()) {
			int u = heap.deleteMin();
			visited[u] = true;
			for (int i = 0; i < adjacency_list[u].length; i += 2) {
				int v = adjacency_list[u][i] - 1;
				int w = adjacency_list[u][i + 1];
				if (!visited[v] && value[v] > value[u] + w) {
					value[v] = value[u] + w;
					back_pointer[v] = u;
					if (heap.contains(v)) {
						heap.decrease(v, value[v]);
					} else {
						heap.insert(v, value[v]);
					}
				}
			}
		}
		for (int i = 1; i < n; i++) {
			System.out.print(i + ": ");
			int j = i;
			while (j != -1) {
				System.out.print(j + " ");
				j = back_pointer[j];
			}
			System.out.println();
		}
	}
}


class HeapPQueue implements PQueue {
	private int[] heap;
	private int[] index;
	private int[] key;
	private int size;
	private int capacity;
	
	public HeapPQueue(int capacity) {
		this.capacity = capacity;
		heap = new int[capacity];
		index = new int[capacity];
		key = new int[capacity];
		size = 0;
	}
	
	public void insert(int v, int k) {
		if (size == capacity) {
			System.out.println("Heap overflow");
			return;
		}
		heap[size] = v;
		index[v] = size;
		key[v] = k;
		size++;
		int i = size - 1;
		while (i > 0 && key[heap[i]] < key[heap[(i - 1) / 2]]) {
			int temp = heap[i];
			heap[i] = heap[(i - 1) / 2];
			heap[(i - 1) / 2] = temp;
			index[heap[i]] = i;
			index[heap[(i - 1) / 2]] = (i - 1) / 2;
			i = (i - 1) / 2;
		}
	}
	
	public int deleteMin() {
		if (size == 0) {
			System.out.println("Heap underflow");
			return -1;
		}
		int min = heap[0];
		heap[0] = heap[size - 1];
		index[heap[0]] = 0;
		size--;
		int i = 0;
		while (2 * i + 1 < size) {
			int j = 2 * i + 1;
			if (j + 1 < size && key[heap[j + 1]] < key[heap[j]]) {
				j++;
			}
			if (key[heap[i]] <= key[heap[j]]) {
				break;
			}
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
			index[heap[i]] = i;
			index[heap[j]] = j;
			i = j;
		}
		return min;
	}
	
	public void decrease(int v, int k) {
		if (k > key[v]) {
			System.out.println("New key is greater than current key");
			return;
		}
		key[v] = k;
		int i = index[v];
		while (i > 0 && key[heap[i]] < key[heap[(i - 1) / 2]]) {
			int temp = heap[i];
			heap[i] = heap[(i - 1) / 2];
			heap[(i - 1) / 2] = temp;
			index[heap[i]] = i;
			index[heap[(i - 1) / 2]] = (i - 1) / 2;
			i = (i - 1) / 2;
		}
	}
	
	public boolean contains(int v) {
		return index[v] < size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}