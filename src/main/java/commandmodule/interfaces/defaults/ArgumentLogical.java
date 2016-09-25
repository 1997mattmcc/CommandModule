package commandmodule.interfaces.defaults;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;

public class ArgumentLogical implements IArgument {

    private final StringComparator comparator;

    public ArgumentLogical(StringComparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        if (comparator.compare(string, "true") || comparator.compare(string, "1")) {
            builder.setLogical(true);
            return true;
        } else if (comparator.compare(string, "false") || comparator.compare(string, "0")) {
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
