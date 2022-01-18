package org.pasksoftware.applicative;

import java.util.function.Function;
import java.util.function.Supplier;

public class LawsValidator {

    public static void main(String[] args) {

        int x = 5;
        Function<Integer, String> f = Object::toString;
        Function<String, Long> g = Long::valueOf;

        // Identity
        Applicative<Integer> identity = Applicative.pure(x).apply(Applicative.pure(Function.identity()));
        boolean identityLawFulfilled = identity.valueEquals(Applicative.pure(x));
        if (identityLawFulfilled) {
            System.out.println("Identity law fulfilled");
        } else {
            throw new RuntimeException("Identity law unfulfilled");
        }

        // Homomorphism
        Applicative<String> leftSide = Applicative.pure(x).apply(Applicative.pure(f));
        Applicative<String> rightSide = Applicative.pure(f.apply(x));
        boolean homomorphismLawFulfilled = leftSide.valueEquals(rightSide);
        if (homomorphismLawFulfilled) {
            System.out.println("Homomorphism law fulfilled");
        } else {
            throw new RuntimeException("Homomorphism law unfulfilled");
        }

        // Interchange
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        Applicative<String> interchangeLeftSide = Applicative.pure(x).apply(Applicative.pure(f));
        Supplier<Integer> supplier = () -> x;
        Function<Supplier<Integer>, String> tmp = i -> f.apply(i.get());
        Applicative<String> interchangeRightSide = Applicative.pure(supplier).apply(Applicative.pure(tmp));
        boolean interchangeLawFulfilled = interchangeLeftSide.valueEquals(interchangeRightSide);
        if (interchangeLawFulfilled) {
            System.out.println("Interchange law fulfilled");
        } else {
            throw new RuntimeException("Interchange law unfulfilled");
        }

        // Composition
        // As far as I can tell it should be in line with what is expected from this Law
        Applicative<Long> compositionLeftSide = Applicative.pure(x).apply(Applicative.pure(f)).apply(Applicative.pure(g));
        Applicative<Long> compositionRightSide = Applicative.pure(x).apply(Applicative.pure(f.andThen(g)));
        boolean compositionLawFulfilled = compositionLeftSide.valueEquals(compositionRightSide);
        if (compositionLawFulfilled) {
            System.out.println("Composition law fulfilled");
        } else {
            throw new RuntimeException("Composition law unfulfilled");
        }

    }
}
