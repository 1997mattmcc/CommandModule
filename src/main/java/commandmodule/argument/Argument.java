package commandmodule.argument;

import commandmodule.context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Argument implements IArgument {

    private final BiFunction<ContextBuilder, String, Boolean> argument;
    private final Function<IMessage, Integer> upperBound;
    private final Function<IMessage, Integer> lowerBound;
    private final String description;
    private final String name;

    public Argument(ArgumentBuilder builder) {
        this.upperBound = builder.upperBound;
        this.lowerBound = builder.lowerBound;
        this.description = builder.description;
        this.argument = builder.argument;
        this.name = builder.name;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        return argument.apply(builder, name);
    }

    @Override
    public Integer getUpperBound(IMessage message) {
        return upperBound.apply(message);
    }

    @Override
    public Integer getLowerBound(IMessage message) {
        return lowerBound.apply(message);
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
