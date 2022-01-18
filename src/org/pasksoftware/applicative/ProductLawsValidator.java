package org.pasksoftware.applicative;

import org.pasksoftware.util.Tuple;

public class ProductLawsValidator {

    public static void main(String[] args) {

        ProductApplicative<Integer> ax = ProductApplicative.pure(5);
        ProductApplicative<Integer> ay = ProductApplicative.pure(6);
        ProductApplicative<Integer> az = ProductApplicative.pure(7);

        // Left Identity
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ProductApplicative<Tuple<Integer, Integer>> leftIdentity = ax.product(ay);
        ProductApplicative<Integer> leftIdentityMapped = leftIdentity.map(f -> f.second);
        boolean leftIdentityFulfilled = leftIdentityMapped.valueEquals(ay);
        if (leftIdentityFulfilled) {
            System.out.println("Left Identity fulfilled");
        } else {
            throw new RuntimeException("Left Identity law unfulfilled");
        }

        // Right Identity
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ProductApplicative<Tuple<Integer, Integer>> rightIdentity = ay.product(ax);
        ProductApplicative<Integer> rightIdentityMapped = rightIdentity.map(f -> f.first);
        boolean rightIdentityFulfilled = rightIdentityMapped.valueEquals(ay);
        if (rightIdentityFulfilled) {
            System.out.println("Right Identity fulfilled");
        } else {
            throw new RuntimeException("Right Identity law unfulfilled");
        }

        // Associativity
        // As far as I can tell it is as close, to original meaning of this Law, as possible in Java
        ProductApplicative<Tuple<Tuple<Integer, Integer>, Integer>> leftSide = ax.product(ay).product(az);
        ProductApplicative<Tuple<Integer, Tuple<Integer, Integer>>> rightSide = ax.product(ay.product(az));
        ProductApplicative<Tuple<Tuple<Integer, Integer>, Integer>> rightSideMapped = rightSide.map(f -> new Tuple<>(new Tuple<>(f.first, f.second.first), f.second.second));
        boolean associativityFulfilled = leftSide.valueEquals(rightSideMapped);
        if (associativityFulfilled) {
            System.out.println("Associativity law fulfilled");
        } else {
            throw new RuntimeException("Associativity law unfulfilled");
        }
    }
}

