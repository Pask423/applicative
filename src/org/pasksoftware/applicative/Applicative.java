package org.pasksoftware.applicative;

import org.pasksoftware.functor.Functor;

import java.util.function.Function;

public final class Applicative<T> implements Functor<T> {

    private final T value;

    private Applicative(T value) {
        this.value = value;
    }

    static <T> Applicative<T> pure(T value) {
        return new Applicative<>(value);
    }

    <U> Applicative<U> apply(Applicative<Function<T, U>> functionApplicative) {
        U apply = functionApplicative.value.apply(value);
        return pure(apply);
    }

    @Override
    public <R> Functor<R> map(Function<T, R> f) {
        return apply(pure(f));
    }

    // For sake of asserting in LawsValidator
    public boolean valueEquals(Applicative<T> compare) {
        return this.value.equals(compare.value);
    }
}