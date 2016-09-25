package commandmodule.interfaces.defaults;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import commandmodule.utils.StringComparator.Comparators;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;

public class ArgumentLogical implements IArgument {

    private final StringComparator stringComparator;

    public ArgumentLogical() {
        this(Comparators.EQUALS_IGNORE_CASE);
    }

    public ArgumentLogical(Comparators comparator) {
        this.stringComparator = comparator.getStringComparator();
    }

    public ArgumentLogical(StringComparator stringComparator) {
        this.stringComparator = stringComparator;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        System.out.println("BOOLEAN : " + string);
        if (stringComparator.compare(string, "true") || stringComparator.compare(string, "1")) {
            builder.setLogical(true);
            return true;
        } else if (stringComparator.compare(string, "false") || stringComparator.compare(string, "0")) {
            builder.setLogical(false);
            return true;
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
        return String.format("%s : true-false-1-0", this.getName());
    }

    @Override
    public String getName() {
        return "logical";
    }
}
