package chapter.threads.parallel.streams;

public class TestIntStruct {
	public static void main(String[]args) {
		IntStruct s = new IntStruct();
		
		for (int i=0; i<8; i++) {
			s.add(i);
			System.out.println(s);
		}

		IntStruct s2 = new IntStruct();
		for (int i=10; i<13; i++) {
			s2.add(i);
			System.out.println(s2);
		}
		
		s.addAllShallow(s2);
		System.out.println(s);
		s.addAllShallow(s2);
		System.out.println(s);
	}
}
