
public class Position<T> {
	
	private T element;
	private static int rank;
	
	public Position(T data) {
		rank = rank++;
		this.element = data;
	}
	/*
	public Position(Position original) {
		this.rank = original.rank;
		this.element = (T)original.element.clone();
	}
	*/
	
	public T element() {
		return element;
	}
	
	public int getRank() {
		return this.rank;
	}

}
