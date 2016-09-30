package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentBigInteger;
import commandmodule.argument.defaults.ArgumentString;
import commandmodule.utils.StringComparator.Comparators;
import sx.blah.discord.util.audio.AudioPlayer;
import sx.blah.discord.handle.obj.IGuild;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.context.Context;
import java.math.BigInteger;
import java.util.Optional;

public class CommandSkip implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!skip", Comparators.EQUALS_IGNORE_CASE), new ArgumentBigInteger()},
        {new ArgumentString("!skip", Comparators.EQUALS_IGNORE_CASE)}
    };

    @Override
    public void invoke(Context context) {
        IGuild guild = context.message.getGuild();
        Optional<BigInteger> optional = context.getBigIntegerOptinoal();
        if (optional.isPresent()) {
            AudioPlayer.getAudioPlayerForGuild(guild).skipTo(optional.get().intValue());
        } else {
            AudioPlayer.getAudioPlayerForGuild(guild).skip();
        }
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!skip";
    }

    @Override
    public String getDescription() {
        return "Makes the client skip audio tracks";
    }
}
