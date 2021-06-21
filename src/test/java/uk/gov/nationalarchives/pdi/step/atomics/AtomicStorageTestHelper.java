/**
 * The MIT License
 * Copyright © 2021 The National Archives
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.gov.nationalarchives.pdi.step.atomics;

import com.evolvedbinary.j8fu.tuple.Tuple2;

import java.util.Collections;
import java.util.Map;

/**
 * Simple interface to make some {@link AtomicStorage} test methods
 * accessible.
 */
public interface AtomicStorageTestHelper {

    static void clear() {
        AtomicStorage.INSTANCE.clear();
    }

    static Map<String, Tuple2<AtomicType, Object>> copy() {
        return AtomicStorage.INSTANCE.copy();
    }

    static void set(final String id, final Tuple2<AtomicType, Object> atomicValue) {
        AtomicStorage.INSTANCE.set(Collections.singletonMap(id, atomicValue));
    }

    static void put(final String id, final Tuple2<AtomicType, Object> atomicValue) {
        AtomicStorage.INSTANCE.put(id, atomicValue);
    }
}
