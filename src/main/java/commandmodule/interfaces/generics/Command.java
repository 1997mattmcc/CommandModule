package commandmodule.interfaces.generics;

import commandmodule.interfaces.IArgument;
import commandmodule.interfaces.ICommand;
import commandmodule.interfaces.IContext;
import java.util.function.Consumer;
import sx.blah.discord.Discord4J;
import java.util.ArrayList;
import java.util.List;

public final class Command implements ICommand {

    private final Consumer<IContext> consumer;
    private final IArgument[] arguments;
    private final String description;
    private final String uniqueID;
    private final String name;

    public Command(CommandBuilder builder) {
        this.arguments = builder.arguments.toArray(new IArgument[builder.arguments.size()]);
        this.description = builder.description;
        this.uniqueID = builder.uniqueID;
        this.consumer = builder.consumer;
        this.name = builder.name;
    }

    @Override
    public void invoke(IContext context) {
        consumer.accept(context);
    }

    @Override
    public IArgument[] getArguments() {
        return arguments;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUniqueID() {
        return uniqueID;
    }

    @Override
    public String getName() {
        return name;
    }

    public static final class CommandBuilder {

        //Required
        private final String uniqueID;

        //Optional
        private Consumer<IContext> consumer = context -> Discord4J.LOGGER.debug("This argument does not do anything...");
        private List<IArgument> arguments = new ArrayList<>();
        private String description = new String();
        private String name = new String();

        public CommandBuilder(String uniqueID) {
            this.uniqueID = uniqueID;
        }

        public final CommandBuilder consumer(Consumer<IContext> consumer) {
            this.consumer = consumer;
            return this;
        }

        public final CommandBuilder arguments(List<IArgument> arguments) {
            this.arguments = arguments;
            return this;
        }

        public final CommandBuilder description(String description) {
            this.description = description;
            return this;
        }

        public final CommandBuilder next(IArgument argument) {
            this.arguments.add(argument);
            return this;
        }

        public final CommandBuilder name(String name) {
            this.name = name;
            return this;
        }

        public final Command build() {
            return new Command(this);
        }
    }
}
