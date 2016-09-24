package commandmodule.interfaces.generics;

import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.api.IDiscordClient;
import commandmodule.interfaces.IContext;
import java.util.Optional;

public final class Context implements IContext {

    private final IVoiceChannel voiceChannel;
    private final IDiscordClient client;
    private final IMessage message;
    private final Double doubleVal;
    private final Boolean logical;
    private final String string;
    private final Long longVal;

    public Context(ContextBuilder builder) {
        this.voiceChannel = builder.voiceChannel;
        this.doubleVal = builder.doubleVal;
        this.message = builder.message;
        this.logical = builder.logical;
        this.longVal = builder.longVal;
        this.client = builder.client;
        this.string = builder.string;
    }

    @Override
    public final Optional<IVoiceChannel> getVoiceChannel() {
        return Optional.ofNullable(voiceChannel);
    }

    @Override
    public final Optional<Boolean> getLogical() {
        return Optional.ofNullable(logical);
    }

    @Override
    public final Optional<String> getString() {
        return Optional.ofNullable(string);
    }

    @Override
    public final Optional<Double> getDouble() {
        return Optional.ofNullable(doubleVal);
    }

    @Override
    public final IDiscordClient getClient() {
        return client;
    }

    @Override
    public final Optional<Long> getLong() {
        return Optional.ofNullable(longVal);
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
        private Double doubleVal = null;
        private Boolean logical = null;
        private String string = null;
        private Long longVal = null;

        public ContextBuilder(IMessage message) {
            this.client = message.getClient();
            this.message = message;
        }

        public final ContextBuilder setVoiceChannel(IVoiceChannel voiceChannel) {
            this.voiceChannel = voiceChannel;
            return this;
        }

        public final ContextBuilder setDouble(Double doubleVal) {
            this.doubleVal = doubleVal;
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

        public final ContextBuilder setLong(Long longVal) {
            this.longVal = longVal;
            return this;
        }

        public Context build() {
            return new Context(this);
        }
    }
}
