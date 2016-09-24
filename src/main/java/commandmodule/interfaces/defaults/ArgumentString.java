package commandmodule.interfaces.defaults;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.interfaces.IArgument;

public class ArgumentString implements IArgument {

    private final boolean startsWith;
    private final boolean ignoreCase;
    private final boolean setString;
    private final String string;
    private final int minWords;
    private final int maxWords;

    public ArgumentString(String string, boolean startsWith, boolean ignoreCase, boolean setString) {
        this.maxWords = startsWith ? IMessage.MAX_MESSAGE_LENGTH : string.split(" ").length;
        this.string = ignoreCase ? string.toLowerCase() : string;
        this.minWords = string.split(" ").length;
        this.startsWith = startsWith;
        this.ignoreCase = ignoreCase;
        this.setString = setString;
    }

    @Override
    public boolean test(IMessage message, String string) {
        string = ignoreCase ? string.toLowerCase() : string;
        return startsWith ? string.startsWith(this.string) : string.equals(this.string);
    }

    @Override
    public void accept(ContextBuilder builder, String string) {
        if (setString) {
            builder.setString(string);
        }
    }

    @Override
    public int getLowerRequiredWordCountBound() {
        return minWords;
    }

    @Override
    public int getUpperRequiredWordCountBound() {
        return maxWords;
    }

    @Override
    public String getDescription() {
        String description = string.concat(" : ");
        description = description.concat(startsWith ? "starts-with" : "equals");
        description = description.concat("-");
        description = description.concat(ignoreCase ? "ignore-case" : "obey-case");
        return description;
    }

    @Override
    public String getName() {
        return "string";
    }
}
