package commandmodule.interfaces;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;

public interface IArgument {

    public abstract Boolean applyArgument(ContextBuilder builder, String string);

    public abstract Integer getLowerWordCountBound(IMessage message);

    public abstract Integer getUpperWordCountBound(IMessage message);

    public abstract String getDescription();

    public abstract String getName();

    public default boolean process(ContextBuilder builder, String string) {
        int lowerBound = this.getLowerWordCountBound(builder.getMessage());
        int upperBound = this.getUpperWordCountBound(builder.getMessage());
        int length = string.split(" ").length;
        if (!(length < lowerBound || length > upperBound)) {
            return this.applyArgument(builder, string);
        }
        return false;
    }
}
