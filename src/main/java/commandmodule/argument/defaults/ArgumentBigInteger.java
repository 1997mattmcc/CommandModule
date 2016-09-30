package commandmodule.argument.defaults;

import org.apache.commons.lang3.math.NumberUtils;
import commandmodule.context.ContextBuilder;
import commandmodule.argument.IArgument;
import java.math.BigInteger;
import java.util.Objects;

public class ArgumentBigInteger implements IArgument {

    private final BigInteger minInteger;
    private final BigInteger maxInteger;
    private final boolean round;

    public ArgumentBigInteger() {
        this.minInteger = null;
        this.maxInteger = null;
        this.round = false;
    }

    public ArgumentBigInteger(BigInteger minInteger, BigInteger maxInteger, boolean round) {
        this.minInteger = minInteger;
        this.maxInteger = maxInteger;
        this.round = round;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        if (NumberUtils.isNumber(string) && !string.contains(".")) {
            BigInteger bigInteger = new BigInteger(string);
            int minCompare = bigInteger.compareTo(minInteger);
            int maxCompare = bigInteger.compareTo(maxInteger);
            if ((minCompare == 1 || minCompare == 0) && (maxCompare == -1 || minCompare == 0)) {
                builder.setBigInteger(bigInteger);
                return true;
            } else if (round && !(minCompare == 1 || minCompare == 0)) {
                builder.setBigInteger(minInteger);
                return true;
            } else if (round && !(maxCompare == -1 || minCompare == 0)) {
                builder.setBigInteger(maxInteger);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        String minString = Objects.isNull(minInteger) ? "-∞" : minInteger.toString();
        String maxString = Objects.isNull(maxInteger) ? "∞" : maxInteger.toString();
        return String.format("[%s : %s ≤ x ≤ %s]", this.getName(), minString, maxString);
    }

    @Override
    public String getName() {
        return "integer";
    }
}
