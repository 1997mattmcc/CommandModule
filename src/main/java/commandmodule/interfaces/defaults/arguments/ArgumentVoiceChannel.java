package commandmodule.interfaces.defaults.arguments;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IVoiceChannel;
import commandmodule.utils.StringComparator;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;
import java.util.OptionalInt;
import java.util.List;

public class ArgumentVoiceChannel implements IArgument {

    private final StringComparator comparator;
    private final boolean setVoiceChannel;

    public ArgumentVoiceChannel(StringComparator comparator, boolean setVoiceChannel) {
        this.setVoiceChannel = setVoiceChannel;
        this.comparator = comparator;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        List<IVoiceChannel> voiceChannels = builder.getMessage().getGuild().getVoiceChannels();
        for (IVoiceChannel voiceChannel : voiceChannels) {
            if (comparator.compare(voiceChannel.getName(), string)) {
                if (setVoiceChannel) {
                    builder.setVoiceChannel(voiceChannel);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer getLowerWordCountBound(IMessage message) {
        List<IVoiceChannel> voiceChannels = message.getGuild().getVoiceChannels();
        OptionalInt max = voiceChannels.stream().mapToInt(voiceChannel -> voiceChannel.getName().split(" ").length).max();
        return max.isPresent() ? max.getAsInt() : 0;
    }

    @Override
    public Integer getUpperWordCountBound(IMessage message) {
        List<IVoiceChannel> voiceChannels = message.getGuild().getVoiceChannels();
        OptionalInt min = voiceChannels.stream().mapToInt(voiceChannel -> voiceChannel.getName().split(" ").length).min();
        return min.isPresent() ? min.getAsInt() : 0;
    }

    @Override
    public String getDescription() {
        return String.format("%s : %s", this.getName(), comparator.toString());
    }

    @Override
    public String getName() {
        return "voice channel";
    }
}
