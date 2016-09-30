package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentString;
import sx.blah.discord.util.audio.AudioPlayer;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IGuild;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.context.Context;

public class CommandShuffle implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!shuffle", StringComparator.Comparators.EQUALS_IGNORE_CASE)}
    };

    @Override
    public void invoke(Context context) {
        IGuild guild = context.message.getGuild();
        AudioPlayer.getAudioPlayerForGuild(guild).shuffle();
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!shuffle";
    }

    @Override
    public String getDescription() {
        return "Makes the client shuffle its current audio queue";
    }
}
