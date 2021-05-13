
public class ArraySequence<T> {
	private Position<T> pos;
	private int l = 0;
	private Position<T>[] sequence; 
	
	public ArraySequence(){
		sequence = new Position[10];
	}
	//generic methods
	public int size() {
		return sequence.length;
	}
	
	public int getL() {
		return l;
	}
	
	public boolean isEmpty() {
		if (l == 0) {
			return true;
		}
		else
			return false;
	}
	//NodeList based methods
	public Position<T> first(){
		return sequence[0];
	}
	
	public Position<T> last(){
		return sequence[l-1];
	}
	//doesn't work for empty sequence
	public Position<T> addLast(T data){
		Position<T> toAdd = new Position(data);
		if(l == size()) {
			sequence = resize(sequence);
		}
		sequence[l++] = toAdd;
		return toAdd;
	}
	
	public Position<T> addFirst(T data){
		Position<T> toAdd = new Position(data);
		if(l == size()) {
			sequence = resize(sequence);
		}
		for (int i = l; i > 0; i--) {
			sequence[i] = sequence[i-1];
		}
		l++;
		sequence[0] = toAdd;
		return toAdd;
	}
	
	
	public Position<T> addBefore(Position<T> position, T data){
		if(l == size()) {
			sequence = resize(sequence);
		}
		int index = indexOf(position);
		Position<T> toReturn = new Position(data);
		for (int i = l; i > index; i--) {
			sequence[i]= sequence[i-1];
		}
		sequence[index] = toReturn;
		l++;
					
		return toReturn;
	}
	
	public Position<T> addAfter(Position<T> position, T data){
		if(l == size()) {
			sequence = resize(sequence);
		}
		int index = indexOf(position);
		Position<T> toReturn = new Position(data);
		for (int i = l; i > index+1; i++) {
			sequence[i]= sequence[i-1];
		}
		sequence[index+1] = toReturn;
		l++;
					
		return toReturn;
	}
	
	//arraylist based methods
	public T get(int i) {
		return sequence[i].element();
	}
	
	public Position<T> next(Position<T> p){
		if(indexOf(p) == indexOf(last())) {
			return null;
		}
		return atIndex(indexOf(p)+1);
	}
	
	//doesn't work for empty lists or lists with 1 element
	public T remove(int i) {
		T toReturn = sequence[i].element();
		for (int j = i; j < l; j++) {
			sequence[i] = sequence[i+1];
		}
		l--;
		return toReturn;
	}
	
	//Bridging methods
	
	public Position<T> atIndex(int i) {
		return sequence[i];
	}
	
	public int indexOf(Position<T> position) {
		int toReturn = -1;
		for (int i = 0; i < l; i++) {
			if(sequence[i].element().equals(position.element())) {
				toReturn = i;
				break;
			}
		}
		if (toReturn == -1)
			System.out.println("Position not found at any index.");
		return toReturn;
	}
	
	private Position<T>[] resize(Position<T>[] sequence) {
		Position<T>[] doubled = new Position[sequence.length*2];
		for (int i = 0; i < sequence.length; i++) {
			doubled[i] = sequence[i];
		}
		return doubled;
	}
}
