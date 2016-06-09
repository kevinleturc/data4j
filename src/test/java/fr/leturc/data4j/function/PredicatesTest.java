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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.function.Predicate;

import org.junit.Test;

/**
 * Test class for {@link Predicates}.
 */
public class PredicatesTest {

    @Test
    public void testCompose() {
        assertTrue(Predicates.<String, Integer>compose(String::length, Predicate.isEqual(3)).test("bob"));
    }

    @Test
    public void testAndArray() {
        assertTrue(Predicates.and("bob"::equals, "boby"::endsWith).test("bob"));
        assertTrue(Predicates.and("boby"::endsWith, "bob"::equals).test("bob"));
        assertFalse(Predicates.and("bob"::equals, "jack"::equals).test("pete"));
        assertFalse(Predicates.and("jack"::equals, "bob"::equals).test("pete"));
    }

    @Test
    public void testOrArray() {
        assertTrue(Predicates.or("bob"::equals, "jack"::equals).test("bob"));
        assertTrue(Predicates.or("jack"::equals, "bob"::equals).test("bob"));
        assertFalse(Predicates.or("bob"::equals, "jack"::equals).test("pete"));
        assertFalse(Predicates.or("jack"::equals, "bob"::equals).test("pete"));
    }

}
