package commandmodule.argument.defaults;

import commandmodule.utils.StringComparator.Comparators;
import commandmodule.utils.StringComparator;
import commandmodule.context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.argument.IArgument;

public class ArgumentString implements IArgument {

    private final StringComparator stringComparator;
    private final boolean setString;
    private final int lowerBound;
    private final int upperBound;
    private final String string;

    public ArgumentString() {
        this("", Comparators.STARTS_WITH_IGNORE_CASE, true);
    }

    public ArgumentString(String string) {
        this(string, Comparators.EQUALS_IGNORE_CASE, false);
    }

    public ArgumentString(String string, Comparators comparator) {
        this(string, comparator, true);
    }

    public ArgumentString(String string, Comparators comparator, boolean setString) {
        this(string, comparator.getStringComparator(), setString);
    }

    public ArgumentString(String string, StringComparator stringComparator, boolean setString) {
        this.upperBound = stringComparator.getOption() == StringComparator.Options.EQUALS ? string.split(" ").length : IMessage.MAX_MESSAGE_LENGTH;
        this.lowerBound = string.split(" ").length;
        this.stringComparator = stringComparator;
        this.setString = setString;
        this.string = string;
    }

    public ArgumentString(String string, StringComparator comparator, boolean setString, int lowerBound, int upperBound) {
        this.stringComparator = comparator;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.setString = setString;
        this.string = string;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        if (stringComparator.compare(string, this.string)) {
            if (setString) {
                builder.setString(string);
            }
            return true;
        }
        return false;
    }

    @Override
    public Integer getLowerBound(IMessage message) {
        return lowerBound;
    }

    @Override
    public Integer getUpperBound(IMessage message) {
        return upperBound;
    }

    @Override
    public String getDescription() {
        return String.format("[%s : %s]", string, stringComparator.toString());
    }

    @Override
    public String getName() {
        return "string";
    }
}
