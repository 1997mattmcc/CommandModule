package commandmodule.argument.defaults;

import commandmodule.utils.StringComparator.Comparators;
import sx.blah.discord.handle.obj.IVoiceChannel;
import commandmodule.utils.StringComparator;
import commandmodule.context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.argument.IArgument;
import java.util.OptionalInt;
import java.util.List;

public class ArgumentVoiceChannel implements IArgument {

    private final StringComparator stringComparator;
    private final boolean setVoiceChannel;

    public ArgumentVoiceChannel() {
        this(Comparators.STARTS_WITH_IGNORE_CASE, true);
    }

    public ArgumentVoiceChannel(Comparators comparator, boolean setVoiceChannel) {
        this(Comparators.STARTS_WITH_IGNORE_CASE.getStringComparator(), setVoiceChannel);
    }

    public ArgumentVoiceChannel(StringComparator stringComparator, boolean setVoiceChannel) {
        this.setVoiceChannel = setVoiceChannel;
        this.stringComparator = stringComparator;
    }

    @Override
    public Boolean applyArgument(ContextBuilder builder, String string) {
        List<IVoiceChannel> voiceChannels = builder.getMessage().getGuild().getVoiceChannels();
        for (IVoiceChannel voiceChannel : voiceChannels) {
            if (stringComparator.compare(string, voiceChannel.getName())) {
                if (setVoiceChannel) {
                    builder.setVoiceChannel(voiceChannel);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer getLowerBound(IMessage message) {
        List<IVoiceChannel> voiceChannels = message.getGuild().getVoiceChannels();
        OptionalInt max = voiceChannels.stream().mapToInt(voiceChannel -> voiceChannel.getName().split(" ").length).max();
        return max.isPresent() ? max.getAsInt() : 0;
    }

    @Override
    public Integer getUpperBound(IMessage message) {
        List<IVoiceChannel> voiceChannels = message.getGuild().getVoiceChannels();
        OptionalInt min = voiceChannels.stream().mapToInt(voiceChannel -> voiceChannel.getName().split(" ").length).min();
        return min.isPresent() ? min.getAsInt() : 0;
    }

    @Override
    public String getDescription() {
        return String.format("[%s : %s]", this.getName(), stringComparator.toString());
    }

    @Override
    public String getName() {
        return "voice-channel";
    }
}
