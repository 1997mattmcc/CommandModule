package commandmodule.interfaces.defaults;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;

public class ArgumentString implements IArgument {

    private final StringComparator comparator;
    private final boolean setString;
    private final String string;
    private final int lowerBound;
    private final int upperBound;

    public ArgumentString(String string, StringComparator comparator, boolean setString) {
        this.upperBound = comparator.getOption() == StringComparator.Options.EQUALS ? string.split(" ").length : IMessage.MAX_MESSAGE_LENGTH;
        this.lowerBound = string.split(" ").length;
        this.comparator = comparator;
        this.setString = setString;
        this.string = string;
    }

    public ArgumentString(String string, StringComparator comparator, boolean setString, int lowerBound, int upperBound) {
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.comparator = comparator;
        this.setString = setString;
        this.string = string;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        if (comparator.compare(string, this.string)) {
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
        return String.format("%s : %s", string, comparator.toString());
    }

    @Override
    public String getName() {
        return "string";
    }
}
