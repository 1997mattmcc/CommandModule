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

public class ContextBuilder {

    //Required
    protected final IDiscordClient client;
    protected final ICommand command;
    protected final IMessage message;
    protected final IChannel channel;
    protected final IGuild guild;
    protected final IUser user;

    //Optional
    protected IVoiceChannel voiceChannel = null;
    protected BigDecimal bigDecimal = null;
    protected BigInteger bigInteger = null;
    protected Boolean logical = null;
    protected Object object = null;
    protected String string = null;

    public ContextBuilder(ICommand command, IMessage message) {
        this.channel = message.getChannel();
        this.client = message.getClient();
        this.user = message.getAuthor();
        this.guild = message.getGuild();
        this.command = command;
        this.message = message;
    }

    public ContextBuilder setVoiceChannel(IVoiceChannel voiceChannel) {
        this.voiceChannel = voiceChannel;
        return this;
    }

    public ContextBuilder setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
        return this;
    }

    public ContextBuilder setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
        return this;
    }

    public ContextBuilder setLogical(Boolean logical) {
        this.logical = logical;
        return this;
    }

    public ContextBuilder setObject(Object object) {
        this.object = object;
        return this;
    }

    public ContextBuilder setString(String string) {
        this.string = string;
        return this;
    }

    public IDiscordClient getClient() {
        return client;
    }

    public ICommand getCommand() {
        return command;
    }

    public IMessage getMessage() {
        return message;
    }

    public IChannel getChannel() {
        return channel;
    }

    public IGuild getGuild() {
        return guild;
    }

    public IUser getUser() {
        return user;
    }

    public Context build() {
        return new Context(this);
    }
}
