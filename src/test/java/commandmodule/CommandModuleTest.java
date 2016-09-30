package commandmodule;

import commandmodule.utils.StringComparator.Comparators;
import commandmodule.argument.defaults.ArgumentString;
import sx.blah.discord.util.DiscordException;
import commandmodule.command.CommandBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.ClientBuilder;
import commandmodule.command.Command;

public class CommandModuleTest {

    public static void main(String[] args) throws DiscordException {

        //GETTING COMMAND MODULE
        IDiscordClient client = new ClientBuilder().withToken("CLIENT TOKEN HERE").login();      // Discord4J Client Builder
        CommandModule commandModule = CommandModule.getCommandModuleForDiscordClient(client);    // Gets the command module for a given IDiscordClient

        //COMMAND BUILDER
        CommandBuilder builder = new CommandBuilder("!Test");                                    // Creates a command builder with unique id
        builder.newChain(new ArgumentString("!Test", Comparators.EQUALS_IGNORE_CASE, true));     // Adds a simple argument chain
        builder.consumer(context -> System.out.println(context.getString()));                    // Sets the command listener

        //COMMAND REGISTRATION
        Command command = builder.build();                                                       // Builds the command
        commandModule.registerCommand(command);                                                  // Registers the command for listening
    }
}
