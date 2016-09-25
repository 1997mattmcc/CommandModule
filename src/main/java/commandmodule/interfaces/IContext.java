package commandmodule.interfaces;

import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.api.IDiscordClient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public interface IContext {

    public abstract IDiscordClient getClient();

    public abstract IMessage getMessage();

    public abstract Optional<IVoiceChannel> getVoiceChannelOptinoal();

    public abstract Optional<BigDecimal> getBigDecimalOptinoal();

    public abstract Optional<BigInteger> getBigIntegerOptinoal();

    public abstract Optional<Boolean> getLogicalOptinoal();

    public abstract Optional<String> getStringOptinoal();

    public abstract IVoiceChannel getVoiceChannel();

    public abstract BigDecimal getBigDecimal();

    public abstract BigInteger getBigInteger();

    public abstract Boolean getLogical();

    public abstract String getString();
}
