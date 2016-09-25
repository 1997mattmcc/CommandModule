package commandmodule;

import commandmodule.interfaces.generics.Command.CommandBuilder;
import commandmodule.interfaces.defaults.ArgumentString;
import sx.blah.discord.util.DiscordException;
import commandmodule.utils.StringComparator;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.ClientBuilder;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CommandModuleTest {

    public static void main(String[] args) {
        try {
            IDiscordClient client = new ClientBuilder().withToken("CLIENT TOKEN HERE").login();
            CommandModule commandModule = CommandModule.getCommandModuleForDiscordClient(client);
            commandModule.registerCommand(new CommandBuilder("!Test").next(new ArgumentString("!Test", new StringComparator(StringComparator.Options.EQUALS, true), true)).consumer(context -> System.out.println(context.getString())).build());
        } catch (DiscordException ex) {
            Logger.getLogger(CommandModuleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
