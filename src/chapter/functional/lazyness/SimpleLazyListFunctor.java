package chapter.functional.lazyness;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public sealed class SimpleLazyListFunctor<T> implements LazyListFunctor<T> {
    private static final class ConcatenatedLazyListFunctor<T>
                         extends SimpleLazyListFunctor<T> {
        private final Supplier<String> contentSupplier;
        ConcatenatedLazyListFunctor(
               LazyListFunctor<? extends T> first,
               LazyListFunctor<? extends T> second) {
            super(Iterators.concat(first.iterator(),
                                    second.iterator()));
            contentSupplier = () -> first.content() + second.content();
        }
           public String content() {
               return contentSupplier.get();
           }
           
       };
    static public <T> LazyListFunctor<T> concat(
            LazyListFunctor<? extends T> first,
            LazyListFunctor<? extends T> second) {
        return new ConcatenatedLazyListFunctor<>(first, second);
    }

    private List<T> cache = new ArrayList<>(); // std List as cache ;-)
    private final Iterator<? extends T> iterator;
    
    SimpleLazyListFunctor(Iterator<? extends T> it) {
        this.iterator = it;
    }
        
    @Override
    public Iterator<T> iterator() {
        return Iterators.cachedIterator(cache, iterator);
    }
    
    @Override
    final public Optional<T> get(int n) {
        if (n<cache.size()) {
            return Optional.of(cache.get(n));
        }
        n -= cache.size();
        while (n-- >= 0) {
            if (!iterator.hasNext()) {
                return Optional.empty();
            }
            cache.add(iterator.next());
        }
        return Optional.of(cache.getLast());
    }
    @Override
    public String content() {
        StringBuffer s= new StringBuffer();
        cache.forEach(e -> s.append(e+", "));
        if (iterator.hasNext()) s.append("..., ");
        return s.toString();
    }
    @Override
    final public String toString() {
        return "["+content()+"]";
    }    
}
