package commandmodule.interfaces;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import java.util.function.BiPredicate;
import java.util.function.BiConsumer;

public interface IArgument extends BiPredicate<IMessage, String>, BiConsumer<ContextBuilder, String> {

    @Override
    public abstract boolean test(IMessage message, String string);

    @Override
    public abstract void accept(ContextBuilder builder, String string);

    public abstract int getLowerRequiredWordCountBound();

    public abstract int getUpperRequiredWordCountBound();

    public abstract String getDescription();

    public abstract String getName();
}
