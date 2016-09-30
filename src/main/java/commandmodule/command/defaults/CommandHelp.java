package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentCommand;
import commandmodule.argument.defaults.ArgumentString;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.context.Context;
import commandmodule.CommandModule;
import commandmodule.utils.ListToString;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

public class CommandHelp implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!help"), new ArgumentCommand()},
        {new ArgumentString("!help")}
    };

    @Override
    public void invoke(Context context) {
        List<ICommand> commands = (List<ICommand>) context.getObjectOptinoal().orElse(CommandModule.getCommandModuleForDiscordClient(context.client).getCommands());
        List<String> commandHelpLines = new ArrayList<>();
        for (ICommand command : commands) {
            for (IArgument[] argumentChain : command.getArgumentChains()) {
                String helpLine = new String();
                for (IArgument argument : argumentChain) {
                    helpLine = helpLine.concat(argument.getName()).concat(" ");
                }
                commandHelpLines.add(helpLine);
            }
        }
        try {
            new MessageBuilder(context.client).withChannel(context.channel).withContent(ListToString.generateMessage("List of all Commands:", commandHelpLines, 15, 1, 1)).send();
        } catch (RateLimitException | DiscordException | MissingPermissionsException ex) {
            Logger.getLogger(CommandHelp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!help";
    }

    @Override
    public String getDescription() {
        return "Generates a help message for all or a single command";
    }
}
