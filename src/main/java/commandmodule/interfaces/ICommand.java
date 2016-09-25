package commandmodule.interfaces;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.utils.Permutationer;
import java.util.Optional;

public interface ICommand {

    public abstract void invoke(IContext context);

    public abstract IArgument[][] getArgumentChains();

    public abstract String getDescription();

    public abstract String getUniqueID();

    public abstract String getName();

    public default boolean process(IMessage message) {
        Optional<IContext> optional = this.applyArguments(message);
        if (optional.isPresent()) {
            IContext context = optional.get();
            this.invoke(context);
            return true;
        }
        return false;
    }

    public default Optional<IContext> applyArguments(IMessage message) {
        IArgument[][] argumentChains = this.getArgumentChains();
        int[][] lowerBound = this.getAllLowerWordCountBounds(message);
        int[][] upperBound = this.getAllLowerWordCountBounds(message);
        String[] content = message.getContent().split(" ");
        for (int i = 0; i < argumentChains.length; i++) {
            System.out.println("TESTING ARGUMENT : " + i + " with length " + argumentChains[i].length);
            Permutationer permutationer = new Permutationer(content, argumentChains[i].length);
            while (permutationer.nextPermutationToMatch(lowerBound[i], upperBound[i])) {
                ContextBuilder builder = new ContextBuilder(this, argumentChains[i], message);
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
                lower[i][j] = argumentChains[i][j].getLowerWordCountBound(message);
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
                upper[i][j] = argumentChains[i][j].getUpperWordCountBound(message);
            }
        }
        return upper;
    }
}
