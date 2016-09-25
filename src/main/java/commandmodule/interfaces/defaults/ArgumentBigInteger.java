package commandmodule.interfaces.defaults;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;
import java.math.BigInteger;
import java.util.Objects;

public class ArgumentBigInteger implements IArgument {

    private final BigInteger minInteger;
    private final BigInteger maxInteger;
    private final boolean round;

    public ArgumentBigInteger(BigInteger minInteger, BigInteger maxInteger, boolean round) {
        this.round = round;
        this.minInteger = minInteger;
        this.maxInteger = maxInteger;
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
    public Integer getLowerWordCountBound(IMessage message) {
        return 1;
    }

    @Override
    public Integer getUpperWordCountBound(IMessage message) {
        return 1;
    }

    @Override
    public String getDescription() {
        String minString = Objects.isNull(minInteger) ? "-∞" : minInteger.toString();
        String maxString = Objects.isNull(maxInteger) ? "∞" : maxInteger.toString();
        return String.format("%s : %s ≤ x ≤ %s", this.getName(), minString, maxString);
    }

    @Override
    public String getName() {
        return "integer";
    }
}
