package commandmodule.context;

import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;
import commandmodule.command.ICommand;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class Context {

    public final IDiscordClient client;
    public final ICommand command;
    public final IMessage message;
    public final IChannel channel;
    public final IGuild guild;
    public final IUser user;

    private final IVoiceChannel voiceChannel;
    private final BigDecimal bigDecimal;
    private final BigInteger bigInteger;
    private final Boolean logical;
    private final String string;
    private final Object object;

    public Context(ContextBuilder builder) {
        this.voiceChannel = builder.voiceChannel;
        this.bigDecimal = builder.bigDecimal;
        this.bigInteger = builder.bigInteger;
        this.command = builder.command;
        this.message = builder.message;
        this.logical = builder.logical;
        this.channel = builder.channel;
        this.client = builder.client;
        this.string = builder.string;
        this.object = builder.object;
        this.guild = builder.guild;
        this.user = builder.user;
    }

    public IVoiceChannel getVoiceChannel() {
        return voiceChannel;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public BigInteger getBigInteger() {
        return bigInteger;
    }

    public Boolean getLogical() {
        return logical;
    }

    public Object getObject() {
        return object;
    }

    public String getString() {
        return string;
    }

    public Optional<IVoiceChannel> getVoiceChannelOptinoal() {
        return Optional.ofNullable(this.getVoiceChannel());
    }

    public Optional<BigDecimal> getBigDecimalOptinoal() {
        return Optional.ofNullable(this.getBigDecimal());
    }

    public Optional<BigInteger> getBigIntegerOptinoal() {
        return Optional.ofNullable(this.getBigInteger());
    }

    public Optional<Boolean> getLogicalOptinoal() {
        return Optional.ofNullable(this.getLogical());
    }

    public Optional<Object> getObjectOptinoal() {
        return Optional.ofNullable(this.getObject());
    }

    public Optional<String> getStringOptinoal() {
        return Optional.ofNullable(this.getString());
    }
}
