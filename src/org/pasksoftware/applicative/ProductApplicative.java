package org.pasksoftware.applicative;

import org.pasksoftware.functor.Functor;
import org.pasksoftware.util.Tuple;

import java.util.function.Function;

public final class ProductApplicative<T> implements Functor<T> {

    private final T value;

    private ProductApplicative(T value) {
        this.value = value;
    }

    static <T> ProductApplicative<T> pure(T value) {
        return new ProductApplicative<>(value);
    }

    <U> ProductApplicative<Tuple<T, U>> product(ProductApplicative<U> second) {
        Tuple<T, U> product = new Tuple<>(value, second.value);
        return pure(product);
    }

    @Override
    public <R> ProductApplicative<R> map(Function<T, R> f) {
        return pure(f.apply(value));
    }

    // For sake of asserting in LawsValidator
    public boolean valueEquals(ProductApplicative<T> compare) {
        return this.value.equals(compare.value);
    }
}
