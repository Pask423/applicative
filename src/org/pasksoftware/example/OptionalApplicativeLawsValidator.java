package org.pasksoftware.example;

import java.util.function.Function;
import java.util.function.Supplier;

public class OptionalApplicativeLawsValidator {

    public static void main(String[] args) {

        int x = 5;
        Function<Integer, String> f = Object::toString;
        Function<String, Long> g = Long::valueOf;

        // Identity
        OptionalApplicative<Integer> identity = OptionalApplicative.pure(x).apply(OptionalApplicative.pure(Function.identity()));
        boolean identityLawFulfilled = identity.valueEquals(OptionalApplicative.pure(x));
        if (identityLawFulfilled) {
            System.out.println("Identity law fulfilled");
        } else {
            throw new RuntimeException("Identity law unfulfilled");
        }

        // Homomorphism
        OptionalApplicative<String> leftSide = OptionalApplicative.pure(x).apply(OptionalApplicative.pure(f));
        OptionalApplicative<String> rightSide = OptionalApplicative.pure(f.apply(x));
        boolean homomorphismLawFulfilled = leftSide.valueEquals(rightSide);
        if (homomorphismLawFulfilled) {
            System.out.println("Homomorphism law fulfilled");
        } else {
            throw new RuntimeException("Homomorphism law unfulfilled");
        }

        // Interchange
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        OptionalApplicative<String> interchangeLeftSide = OptionalApplicative.pure(x).apply(OptionalApplicative.pure(f));
        Supplier<Integer> supplier = () -> x;
        Function<Supplier<Integer>, String> tmp = i -> f.apply(i.get());
        OptionalApplicative<String> interchangeRightSide = OptionalApplicative.pure(supplier).apply(OptionalApplicative.pure(tmp));
        boolean interchangeLawFulfilled = interchangeLeftSide.valueEquals(interchangeRightSide);
        if (interchangeLawFulfilled) {
            System.out.println("Interchange law fulfilled");
        } else {
            throw new RuntimeException("Interchange law unfulfilled");
        }

        // Composition
        // As far as I can tell it should be in line with what is expected from this Law
        OptionalApplicative<Long> compositionLeftSide = OptionalApplicative.pure(x).apply(OptionalApplicative.pure(f)).apply(OptionalApplicative.pure(g));
        OptionalApplicative<Long> compositionRightSide = OptionalApplicative.pure(x).apply(OptionalApplicative.pure(f.andThen(g)));
        boolean compositionLawFulfilled = compositionLeftSide.valueEquals(compositionRightSide);
        if (compositionLawFulfilled) {
            System.out.println("Composition law fulfilled");
        } else {
            throw new RuntimeException("Composition law unfulfilled");
        }

    }
}
