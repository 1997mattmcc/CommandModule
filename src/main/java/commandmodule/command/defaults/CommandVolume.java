package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentBigDecimal;
import commandmodule.argument.defaults.ArgumentString;
import sx.blah.discord.util.audio.AudioPlayer;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IGuild;
import commandmodule.argument.IArgument;
import commandmodule.command.ICommand;
import commandmodule.context.Context;
import java.math.BigInteger;
import java.util.Optional;

public class CommandVolume implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!volume", StringComparator.Comparators.EQUALS_IGNORE_CASE), new ArgumentBigDecimal()},
        {new ArgumentString("!volume", StringComparator.Comparators.EQUALS_IGNORE_CASE)}
    };

    @Override
    public void invoke(Context context) {
        IGuild guild = context.message.getGuild();
        Optional<BigInteger> optional = context.getBigIntegerOptinoal();
        if (optional.isPresent()) {
            AudioPlayer.getAudioPlayerForGuild(guild).setVolume(optional.get().floatValue() / 10);
        } else {
            AudioPlayer.getAudioPlayerForGuild(guild).setVolume(0.1f);
        }
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!volume";
    }

    @Override
    public String getDescription() {
        return "Sets the clients audio track volume";
    }
}
