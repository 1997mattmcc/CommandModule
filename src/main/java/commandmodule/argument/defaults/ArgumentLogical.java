package commandmodule.argument.defaults;

import commandmodule.utils.StringComparator.Comparators;
import commandmodule.utils.StringComparator;
import commandmodule.context.ContextBuilder;
import commandmodule.argument.IArgument;

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
    public String getDescription() {
        return String.format("[%s : true-false-1-0]", this.getName());
    }

    @Override
    public String getName() {
        return "logical";
    }
}
