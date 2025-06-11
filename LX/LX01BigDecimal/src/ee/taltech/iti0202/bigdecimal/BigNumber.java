package ee.taltech.iti0202.bigdecimal;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigNumber {

    /**
     * Multiplay
     * @param factor1
     * @param factor2
     * @return
     */
    public BigInteger multiplyBigInteger(int factor1, int factor2) {
        return BigInteger.valueOf(factor1).multiply(BigInteger.valueOf(factor2));
    }

    BigInteger divideBigInteger(BigInteger dividend, int divisor) {
        if (divisor == 0) {
            return BigInteger.ZERO;
        }
        return dividend.divide(BigInteger.valueOf(divisor));
    }

    BigInteger addBigInteger(int add1, int add2) {
        return BigInteger.valueOf(add1).add(BigInteger.valueOf(add2));
    }

    BigInteger subtractBigInteger(BigInteger minuend, int subtrahend) {
        return minuend.subtract(BigInteger.valueOf(subtrahend));
    }

    /**
     * Multiplies a double value by an integer multiplier and rounds the result
     * to the specified number of significant digits using HALF_UP rounding mode.
     * <p><b>About MathContext:</b>
     * A {@link MathContext} object encapsulates two settings:
     * <ol>
     *   <li><b>Precision:</b> The number of significant digits to maintain</li>
     *   <li><b>RoundingMode:</b> The rounding algorithm to use (HALF_UP in this case)</li>
     * </ol>
     */
     public BigDecimal multiplyAndRoundBigDecimal(double value, int multiplier, int rounding) {
        BigDecimal result = BigDecimal.valueOf(value)
                .multiply(BigDecimal.valueOf(multiplier));
        return result.round(new MathContext(rounding, RoundingMode.HALF_UP));
    }


    BigInteger factorial(int n) {
        if (n <= 0) {
            return BigInteger.ONE;
        }
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    /**
     * Power
     * @param base
     * @param exponent
     */
    BigInteger power(int base, int exponent) {
        return BigInteger.valueOf(base).pow(exponent);
    }

    /**
     * Is same
     * @param val1
     * @param val2
     */
    public boolean isSame(BigDecimal val1, BigDecimal val2, int rounding) {
        BigDecimal rounded1 = val1.round(new MathContext(rounding, RoundingMode.HALF_UP));
        BigDecimal rounded2 = val2.round(new MathContext(rounding, RoundingMode.HALF_UP));
        return rounded1.compareTo(rounded2) == 0;
    }


    BigInteger fibonacci(int n) {
        if (n == 0) {
            return BigInteger.ZERO;
        } else if (n == 1) {
            return BigInteger.ONE;
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            BigInteger temp = b;
            b = a.add(b);
            a = temp;
        }
        return b;
    }

    BigInteger lucas(int n) {
        if (n == 0) {
            return BigInteger.valueOf(2);
        } else if (n == 1) {
            return BigInteger.valueOf(1);
        }

        BigInteger a = BigInteger.valueOf(2);
        BigInteger b = BigInteger.valueOf(1);
        for (int i = 2; i <= n; i++) {
            BigInteger temp = b;
            b = a.add(b);
            a = temp;
        }
        return b;
    }
}
