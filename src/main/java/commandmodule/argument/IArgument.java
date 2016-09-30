package commandmodule.argument;

import commandmodule.context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;

public interface IArgument {

    public abstract Boolean applyArgument(ContextBuilder builder, String string);

    public abstract String getDescription();

    public abstract String getName();

    public default Integer getLowerBound(IMessage message) {
        return 1;
    }

    public default Integer getUpperBound(IMessage message) {
        return 1;
    }

    public default boolean process(ContextBuilder builder, String string) {
        int lowerBound = this.getLowerBound(builder.getMessage());
        int upperBound = this.getUpperBound(builder.getMessage());
        int length = string.split(" ").length;
        if (!(length < lowerBound || length > upperBound)) {
            return this.applyArgument(builder, string);
        }
        return false;
    }
}
