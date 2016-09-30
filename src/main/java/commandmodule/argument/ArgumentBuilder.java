package commandmodule.argument;

import commandmodule.context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ArgumentBuilder {

    protected BiFunction<ContextBuilder, String, Boolean> argument = (builder, string) -> true;
    protected Function<IMessage, Integer> upperBound = message -> IMessage.MAX_MESSAGE_LENGTH;
    protected Function<IMessage, Integer> lowerBound = message -> 0;
    protected String description = new String();
    protected String name = new String();

    public ArgumentBuilder setArgument(BiFunction<ContextBuilder, String, Boolean> argument) {
        this.argument = argument;
        return this;
    }

    public ArgumentBuilder setUpperBound(Function<IMessage, Integer> upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    public ArgumentBuilder setLowerBound(Function<IMessage, Integer> lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }

    public ArgumentBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ArgumentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public Argument build() {
        return new Argument(this);
    }
}
