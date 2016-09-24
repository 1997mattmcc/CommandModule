package commandmodule.interfaces;

import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.api.IDiscordClient;
import java.util.Optional;

public interface IContext {

    public abstract IDiscordClient getClient();

    public abstract IMessage getMessage();

    public abstract Optional<IVoiceChannel> getVoiceChannel();

    public abstract Optional<Boolean> getLogical();

    public abstract Optional<String> getString();

    public abstract Optional<Double> getDouble();

    public abstract Optional<Long> getLong();
}
