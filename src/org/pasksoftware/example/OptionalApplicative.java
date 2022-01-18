package org.pasksoftware.example;

import org.pasksoftware.functor.Functor;

import java.util.Optional;
import java.util.function.Function;

public final class OptionalApplicative<T> implements Functor<T> {

    private final Optional<T> value;

    private OptionalApplicative(T value) {
        this.value = Optional.of(value);
    }

    private OptionalApplicative() {
        this.value = Optional.empty();
    }

    static <T> OptionalApplicative<T> pure(T value) {
        return new OptionalApplicative<>(value);
    }

    <U> OptionalApplicative<U> apply(OptionalApplicative<Function<T, U>> functionApplicative) {
        Optional<U> apply = functionApplicative.value.flatMap(value::map);
        return apply.map(OptionalApplicative::new).orElseGet(OptionalApplicative::new);
    }

    @Override
    public <R> Functor<R> map(Function<T, R> f) {
        return apply(pure(f));
    }

    // For sake of asserting in Example and LawsValidator
    public boolean valueEquals(Optional<T> s) {
        return value.equals(s);
    }

    public boolean valueEquals(OptionalApplicative<T> s) {
        return this.valueEquals(s.value);
    }
}
