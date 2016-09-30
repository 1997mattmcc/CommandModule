package commandmodule.command;

import commandmodule.argument.IArgument;
import commandmodule.context.Context;
import java.util.function.Consumer;
import sx.blah.discord.Discord4J;
import java.util.ArrayList;
import java.util.List;

public class CommandBuilder {

    //Required
    protected final String uniqueNameOrID;

    //Optional
    protected Consumer<Context> consumer = context -> Discord4J.LOGGER.debug("This argument does not do anything...");
    protected List<List<IArgument>> argumentChains = new ArrayList<>();
    protected String description = new String();

    public CommandBuilder(String uniqueNameOrID) {
        this.uniqueNameOrID = uniqueNameOrID;
    }

    public CommandBuilder argumentChains(List<List<IArgument>> arguments) {
        this.argumentChains = arguments;
        return this;
    }

    public CommandBuilder argumentChain(List<IArgument> argumentChain) {
        this.argumentChains.add(argumentChain);
        return this;
    }

    public CommandBuilder consumer(Consumer<Context> consumer) {
        this.consumer = consumer;
        return this;
    }

    public CommandBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CommandBuilder newChain(IArgument argument) {
        this.argumentChains.add(new ArrayList<>());
        return this.next(argument);
    }

    public CommandBuilder next(IArgument argument) {
        this.argumentChains.get(argumentChains.size() - 1).add(argument);
        return this;
    }

    public Command build() {
        return new Command(this);
    }
}
