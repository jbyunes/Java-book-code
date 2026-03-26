package chapter.generic.example;

public interface MyList<T> extends Iterable<T> {
	public void add(T e);
	public T get(int idx);
	public int size();
}
