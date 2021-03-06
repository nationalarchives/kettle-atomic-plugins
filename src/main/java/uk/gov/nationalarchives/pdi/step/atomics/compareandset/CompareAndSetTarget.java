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
package uk.gov.nationalarchives.pdi.step.atomics.compareandset;

import org.pentaho.di.trans.step.StepMeta;

import javax.annotation.Nullable;

public class CompareAndSetTarget implements Cloneable {

    private String compareValue;
    private String setValue;
    private String targetStepname;

    @Nullable
    private StepMeta targetStep;

    public CompareAndSetTarget(final String compareValue, final String setValue, final String targetStepname) {
        this.compareValue = compareValue;
        this.setValue = setValue;
        this.targetStepname = targetStepname;
    }

    public CompareAndSetTarget(final String compareValue, final String setValue, @Nullable final StepMeta targetStep) {
        this.compareValue = compareValue;
        this.setValue = setValue;
        this.targetStep = targetStep;
    }

    public String getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(final String compareValue) {
        this.compareValue = compareValue;
    }

    public String getSetValue() {
        return setValue;
    }

    public void setSetValue(final String setValue) {
        this.setValue = setValue;
    }

    public String getTargetStepname() {
        return targetStepname;
    }

    public void setTargetStepname(final String targetStepname) {
        this.targetStepname = targetStepname;
    }

    @Nullable public StepMeta getTargetStep() {
        return targetStep;
    }

    public void setTargetStep(@Nullable final StepMeta targetStep) {
        this.targetStep = targetStep;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "[" + compareValue + "->" + setValue + "] => " + (targetStep != null ? targetStep.getName() : targetStepname);
    }
}
