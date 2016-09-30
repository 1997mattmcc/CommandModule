package commandmodule.argument.defaults;

import commandmodule.context.ContextBuilder;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.CommandModule;
import java.util.OptionalInt;
import java.util.ArrayList;
import java.util.List;

public class ArgumentCommand implements IArgument {

    private final StringComparator stringComparator;

    public ArgumentCommand() {
        this(StringComparator.Comparators.STARTS_WITH_IGNORE_CASE);
    }

    public ArgumentCommand(StringComparator.Comparators comparator) {
        this.stringComparator = comparator.getStringComparator();
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        List<ICommand> matched = new ArrayList<>();
        List<ICommand> commands = CommandModule.getCommandModuleForDiscordClient(builder.getClient()).getCommands();
        commands.stream().filter((command) -> (stringComparator.compare(string, command.getUniqueNameOrID()))).forEach((command) -> {
            matched.add(command);
        });
        if (!matched.isEmpty()) {
            builder.setObject(matched);
            return true;
        }
        return false;
    }

    @Override
    public Integer getLowerBound(IMessage message) {
        List<ICommand> commands = CommandModule.getCommandModuleForDiscordClient(message.getClient()).getCommands();
        OptionalInt max = commands.stream().mapToInt(command -> command.getUniqueNameOrID().split(" ").length).max();
        return max.isPresent() ? max.getAsInt() : 0;
    }

    @Override
    public Integer getUpperBound(IMessage message) {
        List<ICommand> commands = CommandModule.getCommandModuleForDiscordClient(message.getClient()).getCommands();
        OptionalInt min = commands.stream().mapToInt(command -> command.getUniqueNameOrID().split(" ").length).min();
        return min.isPresent() ? min.getAsInt() : 0;
    }

    @Override
    public String getDescription() {
        return String.format("[%s : %s]", this.getName(), stringComparator.toString());
    }

    @Override
    public String getName() {
        return "command-name";
    }
}
