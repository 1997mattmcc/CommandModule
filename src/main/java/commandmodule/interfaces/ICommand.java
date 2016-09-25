package commandmodule.interfaces;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.utils.Permutationer;
import java.util.Optional;

public interface ICommand {

    public abstract void invoke(IContext context);

    public abstract IArgument[] getArguments();

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
        IArgument[] arguments = this.getArguments();
        String[] content = message.getContent().split(" ");
        Permutationer permutationer = new Permutationer(content, arguments.length);
        int[] lowerBound = this.getAllLowerWordCountBounds(message);
        int[] upperBound = this.getAllLowerWordCountBounds(message);
        while (permutationer.nextPermutationToMatch(lowerBound, upperBound)) {
            ContextBuilder builder = new ContextBuilder(message);
            for (int i = 0; i < arguments.length; i++) {
                if (!arguments[i].process(builder, permutationer.buildPermutation(i))) {
                    break;
                } else if (i == arguments.length - 1) {
                    return Optional.ofNullable(builder.build());
                }
            }
        }
        return Optional.empty();
    }

    public default int[] getAllLowerWordCountBounds(IMessage message) {
        IArgument[] arguments = this.getArguments();
        int[] lower = new int[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            lower[i] = arguments[i].getLowerWordCountBound(message);
        }
        return lower;
    }

    public default int[] getAllUpperWordCountBounds(IMessage message) {
        IArgument[] arguments = this.getArguments();
        int[] upper = new int[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            upper[i] = arguments[i].getUpperWordCountBound(message);
        }
        return upper;
    }
}
