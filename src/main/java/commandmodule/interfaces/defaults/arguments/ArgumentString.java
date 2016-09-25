package commandmodule.interfaces.defaults.arguments;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import commandmodule.utils.StringComparator.Comparators;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;

public class ArgumentString implements IArgument {

    private final StringComparator stringComparator;
    private final boolean setString;
    private final String string;
    private final int lowerBound;
    private final int upperBound;

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
    public Integer getLowerWordCountBound(IMessage message) {
        return lowerBound;
    }

    @Override
    public Integer getUpperWordCountBound(IMessage message) {
        return upperBound;
    }

    @Override
    public String getDescription() {
        return String.format("%s : %s", string, stringComparator.toString());
    }

    @Override
    public String getName() {
        return "string";
    }
}
