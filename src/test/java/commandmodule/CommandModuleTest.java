package commandmodule;

import commandmodule.interfaces.generics.Command.CommandBuilder;
import commandmodule.interfaces.defaults.ArgumentLogical;
import commandmodule.utils.StringComparator.Comparators;
import commandmodule.interfaces.defaults.ArgumentString;
import commandmodule.interfaces.generics.Command;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.ClientBuilder;

public class CommandModuleTest {

    public static void main(String[] args) throws DiscordException {

        //GETTING COMMAND MODULE
        IDiscordClient client = new ClientBuilder().withToken("CLIENT TOKEN HERE").login(); // Discord4J Client Builder
        CommandModule commandModule = CommandModule.getCommandModuleForDiscordClient(client); // Gets the command module for a given IDiscordClient

        //COMMAND BUILDER
        CommandBuilder commandBuilder = new CommandBuilder("!Test"); // Creates a command builder with unique id
        commandBuilder.newChain(new ArgumentString("!Test", Comparators.EQUALS_IGNORE_CASE, true)).next(new ArgumentLogical()); // Adds a simple argument chain
        commandBuilder.newChain(new ArgumentString("!Test", Comparators.EQUALS_IGNORE_CASE, true)); // Adds a second argument chain
        commandBuilder.consumer(context -> System.out.println(String.format("%s: %s", context.getString(), context.getLogical()))); // Sets the command listener
        Command command = commandBuilder.build(); // Builds the command

        //COMMAND REGISTRATION
        commandModule.registerCommand(command); // Registers the command for listening
    }
}
