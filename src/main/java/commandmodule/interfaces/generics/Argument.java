package commandmodule.interfaces.generics;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;
import java.util.function.BiPredicate;
import java.util.function.BiConsumer;
import sx.blah.discord.Discord4J;

public final class Argument implements IArgument {

    private final BiConsumer<ContextBuilder, String> consumer;
    private final BiPredicate<IMessage, String> predicate;
    private final Integer minWordCount;
    private final Integer maxWordCount;
    private final String description;
    private final String name;

    public Argument(ArgumentBuilder builder) {
        this.minWordCount = builder.minWordCount;
        this.maxWordCount = builder.maxWordCount;
        this.description = builder.description;
        this.predicate = builder.predicate;
        this.consumer = builder.consumer;
        this.name = builder.name;
    }

    @Override
    public final boolean test(IMessage message, String string) {
        return predicate.test(message, string);
    }

    @Override
    public final void accept(ContextBuilder contextBuilder, String string) {
        consumer.accept(contextBuilder, string);
    }

    @Override
    public final int getLowerRequiredWordCountBound() {
        return minWordCount;
    }

    @Override
    public final int getUpperRequiredWordCountBound() {
        return maxWordCount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    public static final class ArgumentBuilder {

        private BiConsumer<ContextBuilder, String> consumer = (message, string) -> Discord4J.LOGGER.debug("This argument does not do anything...");
        private BiPredicate<IMessage, String> predicate = (message, string) -> true;
        private int maxWordCount = IMessage.MAX_MESSAGE_LENGTH;
        private String description = new String();
        private String name = new String();
        private int minWordCount = 0;

        public final ArgumentBuilder predicate(BiPredicate<IMessage, String> predicate) {
            this.predicate = predicate;
            return this;
        }

        public final ArgumentBuilder consumer(BiConsumer<ContextBuilder, String> consumer) {
            this.consumer = consumer;
            return this;
        }

        public final ArgumentBuilder description(String description) {
            this.description = description;
            return this;
        }

        public final ArgumentBuilder min(int minWordCount) {
            this.minWordCount = minWordCount;
            return this;
        }

        public final ArgumentBuilder max(int maxWordCount) {
            this.maxWordCount = maxWordCount;
            return this;
        }

        public final ArgumentBuilder name(String name) {
            this.name = name;
            return this;
        }

        public final IArgument build() {
            return new Argument(this);
        }
    }
}
