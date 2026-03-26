package chapter.generic.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.stream.Collectors;


public class GenericReflectExample {
  
    public static void isGenericOrNot(Class<?> c) {
        System.out.println("=========Class "+c.getName());
        var parameters = c.getTypeParameters();      
        if (parameters.length == 0) {
            System.out.println("Not a generic class "+c.getName());
        } else {
            System.out.println("Generic class "+c.getName()
                +" with parameters");
            for (var p: parameters) {
                String bounds = Arrays.stream(p.getBounds())
                    .map(Type::getTypeName)
                    .collect(Collectors.joining(" & "," extends ",""));
                System.out.println("\t"+p+bounds);
            }
        }    
        var methods = Arrays.stream(c.getMethods())
            .filter(m -> m.getName().equals("f"))
            .toList();
        if (methods.size()==0) {
            System.out.println("No f()");
        } else {
            System.out.println(methods.stream()
                .map(Method::getReturnType)
                .map(Type::getTypeName)
                .collect(Collectors.joining("","Return type of f() : ","")));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        isGenericOrNot(String.class);
        isGenericOrNot(A.class);
        isGenericOrNot(B.class);
        isGenericOrNot(new B<String,String>().getClass()); //
    }
}
