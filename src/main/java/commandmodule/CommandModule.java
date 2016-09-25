package commandmodule;

import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.api.IDiscordClient;
import commandmodule.interfaces.ICommand;
import sx.blah.discord.modules.IModule;
import java.util.ArrayList;
import java.util.Objects;
import java.util.HashMap;
import java.util.List;

public class CommandModule implements IModule, IListener<MessageReceivedEvent> {

    private static final HashMap<IDiscordClient, CommandModule> COMMAND_MODULES = new HashMap<>();
    private final List<ICommand> commands = new ArrayList<>();
    private IDiscordClient discordClient = null;

    public static final CommandModule getCommandModuleForDiscordClient(IDiscordClient client) {
        if (!COMMAND_MODULES.containsKey(client)) {
            CommandModule module = new CommandModule();
            module.enable(client);
            COMMAND_MODULES.put(client, module);
        }
        return COMMAND_MODULES.get(client);
    }

    public final void registerCommand(ICommand command) {
        commands.add(command);
    }

    public final void unregisterCommand(ICommand command) {
        commands.remove(command);
    }

    @Override
    public final void handle(MessageReceivedEvent event) {
        IMessage message = event.getMessage();
        commands.forEach(command -> command.process(message));
    }

    @Override
    public final boolean enable(IDiscordClient client) {
        if (Objects.isNull(discordClient) && !COMMAND_MODULES.containsKey(client)) {
            COMMAND_MODULES.put(discordClient = client, this);
            discordClient.getDispatcher().registerListener(this);
            return true;
        }
        return false;
    }

    @Override
    public final void disable() {
        if (!Objects.isNull(discordClient)) {
            discordClient.getDispatcher().unregisterListener(this);
            COMMAND_MODULES.remove(discordClient);
        }
    }

    @Override
    public final String getName() {
        return "Command Module";
    }

    @Override
    public final String getAuthor() {
        return "Matthew McCormick";
    }

    @Override
    public final String getVersion() {
        return "0.0.1";
    }

    @Override
    public final String getMinimumDiscord4JVersion() {
        return "2.6.0";
    }
}
