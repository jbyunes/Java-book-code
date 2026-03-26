package chapter.functional.lazyness;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

public sealed interface LazyListFunctor<T>
              extends Iterable<T> permits SimpleLazyListFunctor {
    static public <T> LazyListFunctor<T> of(Iterator<? extends T> it) {
        return new SimpleLazyListFunctor<>(it);
    }  
    static public <T> LazyListFunctor<T> concat(
            LazyListFunctor<? extends T> first,
            LazyListFunctor<? extends T> second) {
        return SimpleLazyListFunctor.concat(first, second);
    }
    
    public Optional<T> get(int n);
    default public <U> LazyListFunctor<U> map(Function<? super T,U> mapper) {
        return new SimpleLazyListFunctor<>(new Iterator<U>() {
            private Iterator<T> iterator = iterator();
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public U next() {
                return mapper.apply(iterator.next());
            }        
        });
    }
    public String content();
}
