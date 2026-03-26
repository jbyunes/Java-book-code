package chapter.generic.joker;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class JokerTest {
	
	public static <T> void rot(List<T> l, T e) {
//			l.addFirst(l.removeLast());		
		}
	/*
}
	public static void print(List<?> l) {
		for (Object e: l)
			System.out.println(e);
		System.out.println();
	}
	*/
	public static void print(List<? extends Number> l) {
	    for (Number n : l) {
	        System.out.println(n);
	    }
	    System.out.println();
	}
	
	public static void ff() {
		ArrayList<?> a = new ArrayList<Integer>();
		f(a);
	}
	
	interface I<T> {}
	interface J<T> extends I<T> {}
	class K implements J<K> {}
	class L extends K {}
	
	@SuppressWarnings("unused")
	public static void f(Cloneable a) {
		{
			Integer i = null;
			Comparable<Integer> ci = i;
			Comparable<? extends Integer> co = ci;
			Comparable<? extends Comparable<Integer>> cci = co;
			Comparable<? extends Comparable<? extends Integer>> ccci = cci;
		}
		
		List<Integer> li = null;
		List<? extends Comparable<? super Integer>> lsi = li;
		
		{
			Integer i = null;
			Comparable<Integer> ci = i;
			Comparable<? super Integer> co = ci;
			Comparable<? extends Comparable<Integer>> cci = i;
			Comparable<? extends Comparable<? super Integer>> ccci = i;
			Comparable<? extends Comparable<? extends Comparable<? super Integer>>> cccci = i;
			
		}
		
	}
	static void g(List<? extends Serializable> l) {
		inout2(l);
	}
	static <T extends Serializable> void inout2(List<T> l) {
		l.addFirst(l.removeLast());
	}
	static <T> void ffff(List<T> l) {
		l.addFirst(l.removeLast());
	}
	static void fff(List<?> l) {
		inout(l);
	}
	static <T> void inout(List<T> l) {
		l.addFirst(l.removeLast());
	}

	public static void ff(Comparable<? super Comparable<Integer>> c) {
		
	}
	

	public static void main(String []args) {
	    System.out.println("Joker test");
	    List<Integer> li = null;
	    List<LocalDate> ld = null;
	    List<?> lj = li;
	    lj = ld;
	    
	    List<? extends Number> len = li;
//	    len = ld;
	    
	 // ok
	    List<?>[] xx = { List.of(3), List.of(4.5), List.of(12)};
	    // fail
//	    List<? extends Object>[] yy = {};

	    
	    
		var x = Builder.build((Number y)->{},(CharSequence y)->{});
		List<Double> ls = List.of(0.5,3.1415);
		print(ls);
		li = List.of(1,2);
		print(li);
		
		try {
			Scanner s = new Scanner(System.in);
			String name = s.nextLine();
			Class<?> c = Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
	}
	public static List<Integer> f() {
		return null;
	}
	public static void toto() {
		A[] a = { new A(), new A() };
		var sa = Arrays.stream(a);
	}
}
class A{}

class Builder {
    public static <T,U> BiConsumer<T,U> build(Consumer<T> ct, Consumer<U> cu) {
        return (t,u) -> { ct.accept(t); cu.accept(u); };
    }
}

