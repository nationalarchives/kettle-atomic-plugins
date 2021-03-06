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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtomicStorageTest {

    @AfterEach
    public void resetStorage() {
        AtomicStorage.INSTANCE.clear();
    }

    @Test
    public void getNoSuchAtomic() {
        assertNull(AtomicStorage.INSTANCE.getAtomic("no-such-id", AtomicType.Integer));
    }

    @Test
    public void getAtomicInteger() {
        // 1st prepare the storage
        final AtomicValue atomicInt1 = new AtomicIntegerValue(1);
        AtomicStorageTestHelper.set("atomicInt1", atomicInt1);

        // 2nd retrieve
        final AtomicValue atomicInt11 = AtomicStorage.INSTANCE.getAtomic("atomicInt1", AtomicType.Integer);
        assertNotNull(atomicInt11);
        assertTrue(atomicInt11 instanceof AtomicIntegerValue);
        assertTrue(atomicInt11 == atomicInt1);
        assertEquals(1, ((AtomicIntegerValue) atomicInt11).get());
    }

    @Test
    public void getAtomicBoolean() {
        // 1st prepare the storage
        final AtomicValue atomicBool1 = new AtomicBooleanValue(true);
        AtomicStorageTestHelper.set("atomicBool1", atomicBool1);

        // 2nd retrieve
        final AtomicValue atomicBool11 = AtomicStorage.INSTANCE.getAtomic("atomicBool1", AtomicType.Boolean);
        assertNotNull(atomicBool11);
        assertTrue(atomicBool11 instanceof AtomicBooleanValue);
        assertTrue(atomicBool11 == atomicBool1);
        assertTrue(((AtomicBooleanValue) atomicBool11).get());
    }

    @Test
    public void createThenGetAtomicInteger() {
        // 1st create
        final AtomicValue atomicInt1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicInt1", AtomicType.Integer, "1");
        assertNotNull(atomicInt1);
        assertTrue(atomicInt1 instanceof AtomicIntegerValue);
        assertEquals(1, ((AtomicIntegerValue) atomicInt1).get());

        // 2nd retrieve
        final AtomicValue atomicInt11 = AtomicStorage.INSTANCE.getAtomic("atomicInt1", AtomicType.Integer);
        assertNotNull(atomicInt11);
        assertTrue(atomicInt11 instanceof AtomicIntegerValue);
        assertTrue(atomicInt11 == atomicInt1);
        assertEquals(1, ((AtomicIntegerValue) atomicInt11).get());
    }

    @Test
    public void createThenGetAtomicBoolean() {
        // 1st create
        final AtomicValue atomicBool1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Boolean, "true");
        assertNotNull(atomicBool1);
        assertTrue(atomicBool1 instanceof AtomicBooleanValue);
        assertTrue(((AtomicBooleanValue) atomicBool1).get());

        // 2nd retrieve
        final AtomicValue atomicBool11 = AtomicStorage.INSTANCE.getAtomic("atomicBool1", AtomicType.Boolean);
        assertNotNull(atomicBool11);
        assertTrue(atomicBool11 instanceof AtomicBooleanValue);
        assertTrue(atomicBool11 == atomicBool1);
        assertTrue(((AtomicBooleanValue) atomicBool11).get());
    }

    @Test
    public void createThenGetAtomicIntegerInvalidType() {
        // 1st create
        final AtomicValue atomicInt1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicInt1", AtomicType.Integer, "1");
        assertNotNull(atomicInt1);
        assertTrue(atomicInt1 instanceof AtomicIntegerValue);
        assertEquals(1, ((AtomicIntegerValue) atomicInt1).get());

        // 2nd retrieve
        assertThrows(IllegalArgumentException.class,
                () -> AtomicStorage.INSTANCE.getAtomic("atomicInt1", AtomicType.Boolean)
        );
    }

    @Test
    public void createThenGetAtomicBooleanInvalidType() {
        // 1st create
        final AtomicValue atomicBool1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Boolean, "true");
        assertNotNull(atomicBool1);
        assertTrue(atomicBool1 instanceof AtomicBooleanValue);
        assertTrue(((AtomicBooleanValue) atomicBool1).get());

        // 2nd retrieve
        assertThrows(IllegalArgumentException.class,
                () -> AtomicStorage.INSTANCE.getAtomic("atomicBool1", AtomicType.Integer)
        );
    }

    @Test
    public void getOrCreateAtomicInteger() {
        // 1st call - create
        final AtomicValue atomicInt1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicInt1", AtomicType.Integer, "1");
        assertNotNull(atomicInt1);
        assertTrue(atomicInt1 instanceof AtomicIntegerValue);
        assertEquals(1, ((AtomicIntegerValue) atomicInt1).get());

        // 2nd call - get
        final AtomicValue atomicInt11 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicInt1", AtomicType.Integer, "2");
        assertNotNull(atomicInt11);
        assertTrue(atomicInt11 instanceof AtomicIntegerValue);
        assertTrue(atomicInt11 == atomicInt1);
        assertEquals(1, ((AtomicIntegerValue) atomicInt11).get());
    }

    @Test
    public void getOrCreateAtomicBoolean() {
        // 1st call - create
        final AtomicValue atomicBool1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Boolean, "true");
        assertNotNull(atomicBool1);
        assertTrue(atomicBool1 instanceof AtomicBooleanValue);
        assertTrue(((AtomicBooleanValue) atomicBool1).get());

        // 2nd call - get
        final AtomicValue atomicBool11 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Boolean, "false");
        assertNotNull(atomicBool11);
        assertTrue(atomicBool11 instanceof AtomicBooleanValue);
        assertTrue(atomicBool11 == atomicBool1);
        assertTrue(((AtomicBooleanValue) atomicBool11).get());
    }

    @Test
    public void getOrCreateAtomicIntegerInvalidType() {
        // 1st call - create
        final AtomicValue atomicInt1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicInt1", AtomicType.Integer, "1");
        assertNotNull(atomicInt1);
        assertTrue(atomicInt1 instanceof AtomicIntegerValue);
        assertEquals(1, ((AtomicIntegerValue) atomicInt1).get());

        // 2nd call - get
        assertThrows(IllegalArgumentException.class,
                () -> AtomicStorage.INSTANCE.getOrCreateAtomic("atomicInt1", AtomicType.Boolean, "2")
        );
    }

    @Test
    public void getOrCreateAtomicBooleanInvalidType() {
        // 1st call - create
        final AtomicValue atomicBool1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Boolean, "true");
        assertNotNull(atomicBool1);
        assertTrue(atomicBool1 instanceof AtomicBooleanValue);
        assertTrue(((AtomicBooleanValue) atomicBool1).get());

        // 2nd call - get
        assertThrows(IllegalArgumentException.class,
                () -> AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Integer, "false")
        );
    }

    @Test
    public void removeNoSuchAtomic() {
        assertFalse(AtomicStorage.INSTANCE.removeAtomic("no-such-id"));
    }

    @Test
    public void remove() {
        // 1st call - create
        final AtomicValue atomicBool1 = AtomicStorage.INSTANCE.getOrCreateAtomic("atomicBool1", AtomicType.Boolean, "true");
        assertNotNull(atomicBool1);
        assertTrue(atomicBool1 instanceof AtomicBooleanValue);
        assertTrue(((AtomicBooleanValue) atomicBool1).get());

        // 2nd call - remove
        assertTrue(AtomicStorage.INSTANCE.removeAtomic("atomicBool1"));

        // 2nd call - get
        assertNull(AtomicStorage.INSTANCE.getAtomic("atomicBool1", AtomicType.Boolean));
    }
}
