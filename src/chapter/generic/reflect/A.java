package chapter.generic.reflect;

import java.io.Serializable;

public class A<T extends Serializable & CharSequence> {
    public T f() { return null; }
}

