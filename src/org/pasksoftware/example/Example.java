package org.pasksoftware.example;

import java.util.Optional;
import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
        int x = 2;
        Function<Integer, String> f = Object::toString;
        // Task: applying function wrapped in context to value inside that context.

        // Non-applicative
        Optional<Integer> ox = Optional.of(x);
        Optional<Function<Integer, String>> of = Optional.of(f);
        Optional<String> ofx = ox.flatMap(d -> of.map(h -> h.apply(d)));
        // One liner -> Optional.of(x).flatMap(d -> Optional.of(f).map(h -> h.apply(d)));

        // Applicative
        OptionalApplicative<Integer> ax = OptionalApplicative.pure(x);
        OptionalApplicative<Function<Integer, String>> af = OptionalApplicative.pure(f);
        OptionalApplicative<String> afx = ax.apply(af);
        // One liner -> OptionalApplicative.pure(x).apply(OptionalApplicative.pure(f));

        if (afx.valueEquals(ofx)) {
            System.out.println("Values inside wrappers are equal");
        } else {
            throw new RuntimeException("Values inside wrappers are not equal");
        }
    }
}