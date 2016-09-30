package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentString;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IGuild;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.context.Context;

public class CommandLeave implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!leave", StringComparator.Comparators.EQUALS_IGNORE_CASE)}
    };

    @Override
    public void invoke(Context context) {
        IGuild guild = context.message.getGuild();
        guild.getVoiceChannels().stream().filter(voiceChannel -> {
            return voiceChannel.isConnected();
        }).forEach(voiceChannel -> {
            voiceChannel.leave();
        });
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!leave";
    }

    @Override
    public String getDescription() {
        return "Makes the client leave its current voice channel";
    }
}
