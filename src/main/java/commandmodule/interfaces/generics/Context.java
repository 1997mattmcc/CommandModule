package commandmodule.interfaces.generics;

import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.api.IDiscordClient;
import commandmodule.interfaces.IContext;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public final class Context implements IContext {

    private final IVoiceChannel voiceChannel;
    private final IDiscordClient client;
    private final BigDecimal bigDecimal;
    private final BigInteger bigInteger;
    private final IMessage message;
    private final Boolean logical;
    private final String string;

    public Context(ContextBuilder builder) {
        this.voiceChannel = builder.voiceChannel;
        this.bigDecimal = builder.bigDecimal;
        this.bigInteger = builder.bigInteger;
        this.message = builder.message;
        this.logical = builder.logical;
        this.client = builder.client;
        this.string = builder.string;
    }

    @Override
    public final Optional<IVoiceChannel> getVoiceChannelOptinoal() {
        return Optional.ofNullable(voiceChannel);
    }

    @Override
    public final Optional<Boolean> getLogicalOptinoal() {
        return Optional.ofNullable(logical);
    }

    @Override
    public final Optional<String> getStringOptinoal() {
        return Optional.ofNullable(string);
    }

    @Override
    public Optional<BigDecimal> getBigDecimalOptinoal() {
        return Optional.ofNullable(bigDecimal);
    }

    @Override
    public Optional<BigInteger> getBigIntegerOptinoal() {
        return Optional.ofNullable(bigInteger);
    }

    @Override
    public IVoiceChannel getVoiceChannel() {
        return voiceChannel;
    }

    @Override
    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    @Override
    public BigInteger getBigInteger() {
        return bigInteger;
    }

    @Override
    public Boolean getLogical() {
        return logical;
    }

    @Override
    public String getString() {
        return string;
    }

    @Override
    public final IDiscordClient getClient() {
        return client;
    }

    @Override
    public final IMessage getMessage() {
        return message;
    }

    public static final class ContextBuilder {

        //Required
        private final IDiscordClient client;
        private final IMessage message;

        //Optional
        private IVoiceChannel voiceChannel = null;
        private BigDecimal bigDecimal = null;
        private BigInteger bigInteger = null;
        private Boolean logical = null;
        private String string = null;

        public ContextBuilder(IMessage message) {
            this.client = message.getClient();
            this.message = message;
        }

        public final ContextBuilder setVoiceChannel(IVoiceChannel voiceChannel) {
            this.voiceChannel = voiceChannel;
            return this;
        }

        public final ContextBuilder setBigDecimal(BigDecimal bigDecimal) {
            this.bigDecimal = bigDecimal;
            return this;
        }

        public final ContextBuilder setBigInteger(BigInteger bigInteger) {
            this.bigInteger = bigInteger;
            return this;
        }

        public final ContextBuilder setLogical(Boolean logical) {
            this.logical = logical;
            return this;
        }

        public final ContextBuilder setString(String string) {
            this.string = string;
            return this;
        }

        public IDiscordClient getClient() {
            return client;
        }

        public IMessage getMessage() {
            return message;
        }

        public Context build() {
            return new Context(this);
        }
    }
}
