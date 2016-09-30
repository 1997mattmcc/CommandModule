package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentLogical;
import commandmodule.argument.defaults.ArgumentString;
import sx.blah.discord.util.audio.AudioPlayer;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IGuild;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.context.Context;
import java.util.Optional;

public class CommandLoop implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!loop", StringComparator.Comparators.EQUALS_IGNORE_CASE), new ArgumentLogical()},
        {new ArgumentString("!loop", StringComparator.Comparators.EQUALS_IGNORE_CASE)}
    };

    @Override
    public void invoke(Context context) {
        IGuild guild = context.message.getGuild();
        Optional<Boolean> optional = context.getLogicalOptinoal();
        if (optional.isPresent()) {
            AudioPlayer.getAudioPlayerForGuild(guild).setLoop(optional.get());
        } else {
            boolean looping = AudioPlayer.getAudioPlayerForGuild(guild).isLooping();
            AudioPlayer.getAudioPlayerForGuild(guild).setLoop(!looping);
        }
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!loop";
    }

    @Override
    public String getDescription() {
        return "Makes the client loop any queued audio tracks";
    }
}
