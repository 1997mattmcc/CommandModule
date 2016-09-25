package commandmodule.interfaces.defaults;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import org.apache.commons.lang3.math.NumberUtils;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;
import java.math.BigDecimal;
import java.util.Objects;

public class ArgumentBigDecimal implements IArgument {

    private final BigDecimal minDecimal;
    private final BigDecimal maxDecimal;
    private final boolean round;

    public ArgumentBigDecimal(BigDecimal minDecimal, BigDecimal maxDecimal, boolean round) {
        this.round = round;
        this.minDecimal = minDecimal;
        this.maxDecimal = maxDecimal;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        if (NumberUtils.isNumber(string)) {
            BigDecimal bigDecimal = new BigDecimal(string);
            int minCompare = bigDecimal.compareTo(minDecimal);
            int maxCompare = bigDecimal.compareTo(maxDecimal);
            if ((minCompare == 1 || minCompare == 0) && (maxCompare == -1 || minCompare == 0)) {
                builder.setBigDecimal(bigDecimal);
                return true;
            } else if (round && !(minCompare == 1 || minCompare == 0)) {
                builder.setBigDecimal(minDecimal);
                return true;
            } else if (round && !(maxCompare == -1 || minCompare == 0)) {
                builder.setBigDecimal(maxDecimal);
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
        String minString = Objects.isNull(minDecimal) ? "-∞" : minDecimal.toString();
        String maxString = Objects.isNull(maxDecimal) ? "∞" : maxDecimal.toString();
        return String.format("%s : %s ≤ x ≤ %s", this.getName(), minString, maxString);
    }

    @Override
    public String getName() {
        return "deciaml";
    }
}
