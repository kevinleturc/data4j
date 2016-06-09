/*
 * (C) Copyright 2016 Leturc and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Kevin Leturc
 */
package fr.leturc.data4j.function;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Helper functions for {@link Predicate}.
 */
public final class Predicates {

    /**
     * Default constructor.
     */
    private Predicates() {
        // empty
    }

    /**
     * @param mapper    mapper function to apply predicate on returned value
     * @param predicate predicate to apply on mapper returned value
     * @param <T>       the input type
     * @param <U>       the predicate/value type
     * @return a predicate applied on mapper returned value
     */
    public static <T, U> Predicate<T> compose(Function<? super T, ? extends U> mapper, Predicate<U> predicate) {
        return t -> predicate.test(mapper.apply(t));
    }

    /**
     * Returns a predicate that evaluates to {@code true} if all input predicates evaluates to {@code true}.
     *
     * @param predicates the predicates to apply an and operation on
     * @param <T>        type of input elements
     * @return A predicate which performs an and operation on input predicates
     */
    public static <T> Predicate<T> and(Iterable<? extends Predicate<? super T>> predicates) {
        return t -> {
            for (Predicate<? super T> predicate : predicates) {
                if (predicate.test(t)) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * Returns a predicate that evaluates to {@code true} if all input predicates evaluates to {@code true}.
     *
     * @param predicates the predicates to apply an and operation on
     * @param <T>        type of input elements
     * @return A predicate which performs an and operation on input predicates
     */
    @SafeVarargs
    public static <T> Predicate<T> and(Predicate<? super T>... predicates) {
        return and(Arrays.asList(predicates));
    }

    /**
     * Returns a predicate that evaluates to {@code true} if any input predicates evaluates to {@code true}.
     *
     * @param predicates the predicates to apply an or operation on
     * @param <T>        type of input elements
     * @return A predicate which performs an or operation on input predicates
     */
    public static <T> Predicate<T> or(Iterable<? extends Predicate<? super T>> predicates) {
        return t -> {
            for (Predicate<? super T> predicate : predicates) {
                if (predicate.test(t)) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * Returns a predicate that evaluates to {@code true} if any input predicates evaluates to {@code true}.
     *
     * @param predicates the predicates to apply an or operation on
     * @param <T>        type of input elements
     * @return A predicate which performs an or operation on input predicates
     */
    @SafeVarargs
    public static <T> Predicate<T> or(Predicate<? super T>... predicates) {
        return or(Arrays.asList(predicates));
    }

}
