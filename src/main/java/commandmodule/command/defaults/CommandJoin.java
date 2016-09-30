package commandmodule.command.defaults;

import commandmodule.argument.defaults.ArgumentVoiceChannel;
import commandmodule.utils.StringComparator.Comparators;
import sx.blah.discord.util.MissingPermissionsException;
import commandmodule.argument.defaults.ArgumentString;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.audio.AudioPlayer;
import sx.blah.discord.handle.obj.IGuild;
import commandmodule.argument.IArgument;
import sx.blah.discord.handle.obj.IUser;
import commandmodule.command.ICommand;
import commandmodule.context.Context;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Optional;

public class CommandJoin implements ICommand {

    private static final IArgument[][] ARGUMENT_CHAINS = new IArgument[][]{
        {new ArgumentString("!join", Comparators.EQUALS_IGNORE_CASE), new ArgumentVoiceChannel()},
        {new ArgumentString("!join", Comparators.EQUALS_IGNORE_CASE)}
    };

    @Override
    public void invoke(Context context) {
        IUser author = context.message.getAuthor();
        IGuild guild = context.message.getGuild();
        try {
            Optional<IVoiceChannel> optional = context.getVoiceChannelOptinoal();
            if (optional.isPresent()) {
                AudioPlayer.getAudioPlayerForGuild(guild).setVolume(0.25f);
                optional.get().join();
            } else {
                for (IVoiceChannel voiceChannel : author.getConnectedVoiceChannels()) {
                    if (voiceChannel.getGuild() == guild) {
                        AudioPlayer.getAudioPlayerForGuild(guild).setVolume(0.25f);
                        voiceChannel.join();
                    }
                }
            }
        } catch (MissingPermissionsException ex) {
            String client = context.client.getOurUser().getName();
            String message = String.format("Client %s does not have the required permissions to join the given voice channel", client);
            Logger.getLogger(CommandJoin.class.getName()).log(Level.WARNING, message, ex);
        }
    }

    @Override
    public IArgument[][] getArgumentChains() {
        return ARGUMENT_CHAINS;
    }

    @Override
    public String getUniqueNameOrID() {
        return "!join";
    }

    @Override
    public String getDescription() {
        return "Makes the client join a voice channel";
    }
}
