package commandmodule.interfaces.generics;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Argument implements IArgument {

    private final BiFunction<ContextBuilder, String, Boolean> argument;
    private final Function<IMessage, Integer> lowerWordCountBound;
    private final Function<IMessage, Integer> upperWordCountBound;
    private final String description;
    private final String name;

    public Argument(ArgumentBuilder builder) {
        this.lowerWordCountBound = builder.lowerWordCountBound;
        this.upperWordCountBound = builder.upperWordCountBound;
        this.description = builder.description;
        this.argument = builder.argument;
        this.name = builder.name;
    }

    @Override
    public final Boolean applyArgument(ContextBuilder builder, String string) {
        return argument.apply(builder, name);
    }

    @Override
    public final Integer getLowerWordCountBound(IMessage message) {
        return lowerWordCountBound.apply(message);
    }

    @Override
    public final Integer getUpperWordCountBound(IMessage message) {
        return upperWordCountBound.apply(message);
    }

    @Override
    public final String getDescription() {
        return description;
    }

    @Override
    public final String getName() {
        return name;
    }

    public static final class ArgumentBuilder {

        private Function<IMessage, Integer> upperWordCountBound = message -> IMessage.MAX_MESSAGE_LENGTH;
        private BiFunction<ContextBuilder, String, Boolean> argument = (builder, string) -> true;
        private Function<IMessage, Integer> lowerWordCountBound = message -> 0;
        private String description = new String();
        private String name = new String();

        public final ArgumentBuilder setUpperWordCountBound(Function<IMessage, Integer> upperWordCountBound) {
            this.upperWordCountBound = upperWordCountBound;
            return this;
        }

        public final ArgumentBuilder setArgument(BiFunction<ContextBuilder, String, Boolean> argument) {
            this.argument = argument;
            return this;
        }

        public final ArgumentBuilder setLowerWordCountBound(Function<IMessage, Integer> lowerWordCountBound) {
            this.lowerWordCountBound = lowerWordCountBound;
            return this;
        }

        public final ArgumentBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public final ArgumentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public final Argument build() {
            return new Argument(this);
        }
    }
}
