package commandmodule.command;

import commandmodule.argument.IArgument;
import commandmodule.context.Context;
import java.util.function.Consumer;
import java.util.List;

public class Command implements ICommand {

    private final IArgument[][] argumentChains;
    private final Consumer<Context> consumer;
    private final String uniqueNameOrID;
    private final String description;

    public Command(CommandBuilder builder) {
        this.argumentChains = new IArgument[builder.argumentChains.size()][];
        for (int i = 0; i < builder.argumentChains.size(); i++) {
            List<IArgument> arguments = builder.argumentChains.get(i);
            argumentChains[i] = arguments.toArray(new IArgument[arguments.size()]);
        }
        this.uniqueNameOrID = builder.uniqueNameOrID;
        this.description = builder.description;
        this.consumer = builder.consumer;
    }

    @Override
    public void invoke(Context context) {
        consumer.accept(context);
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return argumentChains;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUniqueNameOrID() {
        return uniqueNameOrID;
    }
}
