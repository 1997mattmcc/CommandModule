package commandmodule.command;

import commandmodule.context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.utils.Permutationer;
import commandmodule.argument.IArgument;
import commandmodule.context.Context;
import java.util.Optional;

public interface ICommand {

    public abstract void invoke(Context context);

    public abstract IArgument[][] getArgumentChains();

    public abstract String getUniqueNameOrID();

    public abstract String getDescription();

    public default boolean process(IMessage message) {
        Optional<Context> optional = this.applyArguments(message);
        if (optional.isPresent()) {
            Context context = optional.get();
            this.invoke(context);
            return true;
        }
        return false;
    }

    public default Optional<Context> applyArguments(IMessage message) {
        IArgument[][] argumentChains = this.getArgumentChains();
        int[][] lowerBound = this.getAllLowerWordCountBounds(message);
        int[][] upperBound = this.getAllUpperWordCountBounds(message);
        String[] content = message.getContent().split(" ");
        for (int i = 0; i < argumentChains.length; i++) {
            Permutationer permutationer = new Permutationer(content, argumentChains[i].length);
            while (permutationer.nextPermutationToMatch(lowerBound[i], upperBound[i])) {
                ContextBuilder builder = new ContextBuilder(this, message);
                for (int j = 0; j < argumentChains[i].length; j++) {
                    if (!argumentChains[i][j].process(builder, permutationer.buildPermutation(j))) {
                        break;
                    } else if (j == argumentChains[i].length - 1) {
                        return Optional.ofNullable(builder.build());
                    }
                }
            }
        }
        return Optional.empty();
    }

    public default int[][] getAllLowerWordCountBounds(IMessage message) {
        IArgument[][] argumentChains = this.getArgumentChains();
        int[][] lower = new int[argumentChains.length][];
        for (int i = 0; i < argumentChains.length; i++) {
            lower[i] = new int[argumentChains[i].length];
            for (int j = 0; j < lower[i].length; j++) {
                lower[i][j] = argumentChains[i][j].getLowerBound(message);
            }
        }
        return lower;
    }

    public default int[][] getAllUpperWordCountBounds(IMessage message) {
        IArgument[][] argumentChains = this.getArgumentChains();
        int[][] upper = new int[argumentChains.length][];
        for (int i = 0; i < argumentChains.length; i++) {
            upper[i] = new int[argumentChains[i].length];
            for (int j = 0; j < upper[i].length; j++) {
                upper[i][j] = argumentChains[i][j].getUpperBound(message);
            }
        }
        return upper;
    }

    public default String[] generateUsageMessages(boolean detailed) {
        IArgument[][] argumentChains = this.getArgumentChains();
        String[] messages = new String[argumentChains.length];
        for (int i = 0; i < argumentChains.length; i++) {
            String[] argumentChain = new String[argumentChains[i].length];
            for (int j = 0; j < argumentChains[i].length; j++) {
                if (detailed) {
                    argumentChain[j] = argumentChains[i][j].getDescription();
                } else {
                    argumentChain[j] = argumentChains[i][j].getName();
                }
            }
            messages[i] = String.join(" ", argumentChain);
        }
        return messages;
    }
}
