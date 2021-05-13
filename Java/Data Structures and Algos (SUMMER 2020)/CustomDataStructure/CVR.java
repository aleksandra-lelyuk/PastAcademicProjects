import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/* Data structure (CVR) for storing and accessing collection of car ID numbers. The CVR adapts to it's usage (e.g. number of entries) 
by selecting appropriate algorithms and data structures in effort to maintain balance between memory and runtime requirements.
* The CVR is built as a sequence implemented as an array for lower number of entries. Sorted using in place quick sort.
* For larger entry numbers, the CVR is implemented as a mix of a hash map and AVL tree, with entries sorted with radix sort.
*/


public class CVR {
	private static Integer count = 0;
	private int keylength;
	private boolean isSequence;
	private int startSize;
	private int maxSize;
	private int threshold;

	private HashMap<String, Integer> hash = new HashMap<String, Integer>();
	// private TreeMap<Integer, Vehicle> tree = new TreeMap<Integer, Vehicle>();
	private TreeAVL<Vehicle> tree = new TreeAVL<Vehicle>();
	private ArraySequence<Vehicle> sequence;

	public void printTree() {
		tree.preOrderFull();
	}

	Scanner in = new Scanner(System.in);

	public CVR(int size) {
		this.startSize = size;
		System.out.print("Creating new CVR of size " + size + "...");
		Scanner in = new Scanner(System.in);
		boolean legal = false;
		while (!legal) {
			System.out.print("Enter the maximum size: ");
			int maxSize = in.nextInt();
			if (maxSize < size) {
				System.out.print("Maximum size can't be smaller than current size: " + size);
				continue;
			} else {
				this.maxSize = maxSize;
				legal = true;
			}
		}

		legal = false;
		while (!legal) {
			System.out.print("Enter new threshold: ");
			int threshold = in.nextInt();
			legal = setThreshold(threshold);
			if (legal && this.threshold >= this.maxSize) {
				System.out.println("Threshold has to be smaller than maximum size: " + this.maxSize);
				legal = false;
			}
		}
		legal = false;
		while (!legal) {
			System.out.print("Enter new keylength: ");
			int keyL = in.nextInt();
			legal = setKeyLength(keyL);
		}
		legal = false;

		if (size < threshold) {
			isSequence = true;
			sequence = new ArraySequence<>();
		}

		System.out.println("Created new CRV: Keylength = " + this.keylength + ", Maximum Size = " + this.maxSize
				+ ", Threshold = " + this.threshold);
		if (isSequence) {
			System.out.println("It's a sequence!");
		} else {
			System.out.println("It's a tree map!");
		}

	}

	/*
	 * public CVR(int keyL) { boolean legal = setKeyLength(keyL); while (!legal) {
	 * System.out.print("Enter new keylength: "); keyL = in.nextInt(); legal =
	 * setKeyLength(keyL); } }
	 */

	public CVR(int keyL, int threshold, int maxSize) {
		this.maxSize = maxSize;

		boolean legal = setThreshold(threshold);
		while (!legal) {
			System.out.print("Enter new threshold: ");
			threshold = in.nextInt();
			legal = setThreshold(threshold);
		}

		legal = setKeyLength(keyL);
		while (!legal) {
			System.out.print("Enter new keylength: ");
			keyL = in.nextInt();
			legal = setKeyLength(keyL);
		}

		if (maxSize < threshold) {
			isSequence = true;
			sequence = new ArraySequence<>();
		}

		System.out.println(
				"Created new CRV: Keylength = " + keyL + ", Maximum Size = " + maxSize + ", Threshold = " + threshold);
		if (isSequence) {
			System.out.println("It's a sequence!");
		} else {
			System.out.println("It's a tree map!");
		}

	}

	private boolean setThreshold(int t) {
		boolean tOk = false;
		if (t >= 100 || t <= 900000) {
			this.threshold = t;
			tOk = true;
		} else {
			System.out.println("The threshold has to be between 100 and 900,000. Threshold not set.");
		}
		return tOk;
	}

	public boolean setKeyLength(int l) {
		boolean lengthOk = false;
		if (l >= 10 && l <= 17) {
			this.keylength = l;
			lengthOk = true;
		} else {
			System.out.println("The key has to be between 10 and 17. Keylength not set.");
		}
		return lengthOk;
	}

	// double check that no repeat
	public String generate() {

		String alphanum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();

		String newVin = "";

		for (int i = 0; i < this.keylength; i++) {
			int n = rand.nextInt(36);
			newVin += alphanum.charAt(n);
		}

		return newVin;

	}

	// return new VINs as sequence O(n)
	public ArraySequence<String> generate(int num) {

		ArraySequence<String> toReturn = new ArraySequence<String>();
		String alphanum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		boolean duplicate = false;
		String newVin = "";
		for (int k = 1; k <= num;) {
			newVin = generate();
			duplicate = false;
			if (isSequence) {
				if (!sequence.isEmpty()) {
					for (int i = 0; i <= sequence.indexOf(sequence.last()); i++) {
						if (sequence.get(i).getVin().equals(newVin)) {
							System.out.println("VIN " + newVin + " already exists!");

						}
					}
				}
			}

			else {
				if (hash.get(newVin) != null) {
					System.out.println("VIN " + newVin + " already exists!");
					duplicate = true;
				}
			}
			if (!duplicate) {
				k++;
				toReturn.addLast(newVin);
			}
		}
		return toReturn;

	}

	public int getKeyL() {
		return this.keylength;
	}
	
	public int getSize() {
		return this.startSize;
	}

	// 
	public void add(String vin, String brand) {

		Vehicle v = new Vehicle(vin, brand);
		//addLast() = O(1) + need to check for duplicate O(n)
		if (isSequence) {
			if (!sequence.isEmpty()) {
				for (int i = 0; i <= sequence.indexOf(sequence.last()); i++) {
					if (sequence.get(i).getVin().equals(vin)) {
						System.out.println("VIN " + vin + " already exists!");
						return;
					}
				}
			}
			sequence.addLast(v);
			count++;
		}
		//put() + check if duplicate in hash table = O(1), in AVL tree = O(logn)
		else {
			if (hash.get(vin) != null) {
				System.out.println("VIN " + vin + " already exists!");
				return;
			}
			hash.put(vin, ++count); // add the VIN to the hashtable mapped to the order of addition
			tree.put(count, v); // add the entry number and the vehicle object to tree
		}
		System.out.println("Added [VIN: " + vin + "] [Brand: " + brand + "] at position " + count);
	}

	// 
	public void remove(String vin) {
		System.out.println("Trying to remove VIN " + vin + "...");
		//get = O(1), remove() --> O(n)
		if (isSequence) {
			boolean found = false;
			for (int i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (sequence.get(i).getVin().equals(vin)) {
					sequence.remove(i);
					System.out.println("Removed VIN: " + vin);
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");
		} else { 
			//get() and remove usually take O(1) based on the load factor
			if (hash.get(vin) != null) {
				tree.remove(hash.remove(vin));
				System.out.println("Removed VIN: " + vin);
			} else
				System.out.println("VIN " + vin + " not found!");
		}

	}

	public void allKeys() {
		System.out.println("Printing all VIN's in lexicographic order...");
		String[] array;
		// uses in place quicksort with randomized pivot --> average O(nlogn)
		if (isSequence) {
			array = new String[sequence.indexOf(sequence.last())+1];
			for (int i = 0; i < array.length; i++) {
				array[i] = sequence.get(i).getVin();

			}
			System.out.println("the length of array to sort = " + array.length);
			// SORT ARRAY using quicksort
			quickSortInPlace(array, 0, array.length - 1);
		}
		// O(n) time using radix sort
		else {
			Set<String> allVins = hash.keySet();
			array = allVins.toArray(new String[allVins.size()]);
 
			int aLength = array.length;
			int kLength = this.keylength;
			String[] temp = new String[aLength]; // temp array

			// go from right to left one char at a time through all the VIN's
			// radix sort
			for (int d = kLength - 1; d >= 0; d--) {
				int[] count = new int[257]; // all chars
				for (int i = 0; i < aLength; i++) // count frequency of each char at d's frequency
					count[array[i].charAt(d) + 1]++; 
				//keep track of how many chars there are that are < than current char to help distribution
				for (int j = 0; j < 256; j++) //
					count[j + 1] += count[j];
				 //place in the right position of the array based on current ch
				for (int i = 0; i < aLength; i++)
					temp[count[array[i].charAt(d)]++] = array[i];
				for (int i = 0; i < aLength; i++)
					array[i] = temp[i]; // place back into array
			}

		}

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	public void getValues(String vin) {
		System.out.println("Trying to print values for VIN " + vin + "...");
		// sequence.get(i) takes O(1), with the loop the method tales O(n)
		if (isSequence) {
			boolean found = false;
			for (int i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (sequence.get(i).getVin().equals(vin)) {
					System.out.println(sequence.get(i));
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");

		}
		// tree.get() takes O(logn), hash.get() takes O(1)
		else {
			if (hash.get(vin) != null)
				System.out.println(tree.get(hash.get(vin)));
			else
				System.out.println("VIN " + vin + " not found!");

		}

	}
	//O(n) for sequence because need to go through all the elements
	//O(1) for search in hash table +  up to n*log(n) to find the next element in the list
	public String nextKey(String vin) {
		System.out.println("Trying to get successor of VIN " + vin + "...");
		String nextkey = null;
		if (isSequence) {
			boolean found = false;
			// go through sequence looking for mathing vin
			// once found, save the position int
			// if position int < index, search from last to first
			int position;
			int i;
			//go through all elements and compare --> O(n)
			for (i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (sequence.get(i).getVin().equals(vin)) {
					found = true;
					if (vin.equals(sequence.last().element().getVin())) {
						System.out.println("VIN " + vin + " has no successor (last element on the list)!");
					} else {
						nextkey = sequence.get(i + 1).getVin();
					}
					break;
				}
			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");
		} else {
			if (hash.get(vin) != null) {
				int position = hash.get(vin) + 1;
				// check next chronologically added entry until an existing entry is found
				while (nextkey == null && position <= maxSize) {
					if (tree.get(position) != null) {
						nextkey = tree.get(position).getVin();

					}
					position++;
				}
				if (position > maxSize) {
					System.out.println("VIN " + vin + " has no successor (last element on the list)!");
				}
			} else
				System.out.println("VIN " + vin + " not found!");

		}
		if (nextkey != null)
			System.out.println("The successor is: " + nextkey);
		return nextkey;

	}
	
	//O(n) for sequence because need to go through all the elements
		//O(1) for search in hash table +  up to n*log(n) to find previous element in the list
	public String prevKey(String vin) {
		System.out.println("Trying to get predecessor of VIN " + vin + "...");
		String prevkey = null;
		if (isSequence) {
			// go through sequence looking for mathing vin
			// once found, save the position int
			// if position int < index, search from last to first
			int i;
			boolean found = false;
			for (i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (sequence.get(i).getVin().equals(vin)) {
					found = true;
					if (vin.equals(sequence.first().element().getVin())) {
						System.out.println("VIN " + vin + " has no predecessor (first element on the list)!");
						return vin;
					}
					prevkey = sequence.get(i - 1).getVin();
					break;
				}

			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");
		} else {
			if (hash.get(vin) != null) {
				Integer position = hash.get(vin) - 1;
				while (prevkey == null && position >= 0) {
					if (tree.get(position) != null)
						prevkey = tree.get(position).getVin();
					position--;
				}
				if (position < 0) {
					System.out.println("VIN " + vin + " has no predecessor (first element on the list)!");
				}
			}

			else
				System.out.println("VIN " + vin + " not found!");

		}
		if (prevkey != null)
			System.out.println("The predecessor is: " + prevkey);
		return prevkey;

	}
	
	//
	public ArraySequence<String> prevAccids(String vin) {
		System.out.println("Trying to get previous accidents for VIN " + vin + "...");
		ArraySequence<String> toReturn = new ArraySequence<String>();
		//O(n) to find the VIN + number of accidents
		if (isSequence) {
			boolean found = false;
			int i;
			for (i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (vin.equals(sequence.get(i).getVin())) {
					toReturn = sequence.atIndex(i).element().getAccidents();
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");
		} else {
			//O(1)
			if (hash.get(vin) != null) {
				toReturn = tree.get(hash.get(vin)).getAccidents();
			} else
				System.out.println("VIN " + vin + " not found!");
		}

		return toReturn;
	}

	public void addAccident(String vin) {
		System.out.println("Trying to add new accident for VIN " + vin + "...");
		if (isSequence) {
			boolean found = false;
			for (int i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (vin.equals(sequence.get(i).getVin())) {
					sequence.atIndex(i).element().addAccident();
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");
		} else {
			if (hash.get(vin) != null) {
				tree.get(hash.get(vin)).addAccident();
			} else
				System.out.println("VIN " + vin + " not found!");

		}

	}

	public void addAccident(String vin, String date) {
		System.out.println("Trying to add new accident for VIN " + vin + "...");
		if (isSequence) {
			boolean found = false;
			for (int i = 0; i <= sequence.indexOf(sequence.last()); i++) {
				if (vin.equals(sequence.get(i).getVin())) {
					sequence.atIndex(i).element().addAccident(date);
					found = true;
					break;
				}
			}
			if (!found)
				System.out.println("VIN " + vin + " not found!");
		} else {
			if (hash.get(vin) != null) {
				tree.get(hash.get(vin)).addAccident(date);
			} else
				System.out.println("VIN " + vin + " not found!");

		}

	}

	// expected runtime of quicksort with randomized pivot is O(nlogn)
	private static void quickSortInPlace(String[] array, int left, int right) {
		if (left >= right)
			return;
		if (left < 0) return;
		if (right> array.length-1) return;
		
		Random rand = new Random();
		int n = rand.nextInt(right + 1);
		n =+ left;
		String pivot = array[n];
		
		//place pivot into last position
		swap(array, n, right);
		
		// index for the "left-to-right scan"
		int l = left;
		int r = right - 1;
		
		// index for the "right-to-left scan"
		while (l <= r) {
			
			// scan from left until reaching value larger or equal to pivot
			if(array[l].compareTo(pivot) < 0) l++;
			//once l >= pivot, move r left until it reaches a value smaller or equal to pivot 
			else {
				if(array[r].compareTo(pivot) > 0) r--;
				//swap l and r values
				else {
					swap(array, l, r);
					l++;
					r--;
				}
				
			}
			
		}
		// put pivot where it belongs
		swap(array, l, right);
		quickSortInPlace(array, left, l - 1);
		quickSortInPlace(array, l + 1, right);

	}

	private static void swap(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

}
