package com.deeep.jam.sequencer;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 10/2/13
 * Time: 8:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class FormulaTypes {
    /** TODO  Y = ab^X */
    public static class Exponential extends Formula {
        private float b;
        private float a;

        public Exponential(float period, float endValue) {
            super(period, endValue);
        }

        @Override
        protected float calculate(float stateTime) {
            return (float) (Math.pow(stateTime, a) + b);  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void initialize(float startValue) {
            a = 1;
            // endvalue = ? ^ period + startvalue
            b = startValue;

        }
    }

    /** Y = aX + b */
    public static class Linear extends Formula {
        private float a = 0;
        private float b = 0;

        /**
         * multiply the value with a pre calculated slope
         *
         * @param period   the period to reach the end value in
         * @param endValue the final to reach value
         */
        public Linear(float period, float endValue) {
            super(period, endValue);
        }

        @Override
        protected float calculate(float stateTime) {
            return a * stateTime + b;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void initialize(float startValue) {
            b = startValue;
            a = (endValue - startValue) / period;
        }
    }

    /** Y = b */
    public static class Sleep extends Formula {
        /**
         * Sleep method. The value wont change
         *
         * @param period the period to sleep for
         */
        public Sleep(float period) {
            super(period, -1);
        }

        @Override
        protected float calculate(float stateTime) {
            return value;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void initialize(float startValue) {
            this.value = startValue;
        }
    }
}
