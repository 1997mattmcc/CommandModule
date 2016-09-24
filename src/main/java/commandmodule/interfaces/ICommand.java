package commandmodule.interfaces;

import commandmodule.interfaces.generics.Context.ContextBuilder;
import sx.blah.discord.handle.obj.IMessage;
import commandmodule.utils.Permutationer;
import java.util.function.Consumer;
import java.util.Optional;

public interface ICommand {

    public abstract Consumer<IContext> getConsumer();

    public abstract IArgument[] getArguments();

    public abstract String getDescription();

    public abstract String getUniqueID();

    public abstract String getName();

    public default boolean process(IMessage message) {
        Optional<IContext> optional = this.applyArguments(message);
        if (optional.isPresent()) {
            IContext context = optional.get();
            this.getConsumer().accept(context);
            return true;
        }
        return false;
    }

    public default Optional<IContext> applyArguments(IMessage message) {
        Optional<String[]> optional = this.testArguments(message);
        if (optional.isPresent()) {
            String[] permutation = optional.get();
            ContextBuilder contextBuilder = new ContextBuilder(message);
            IArgument[] arguments = this.getArguments();
            for (int i = 0; i < arguments.length; i++) {
                arguments[i].accept(contextBuilder, permutation[i]);
            }
            return Optional.ofNullable(contextBuilder.build());
        }
        return Optional.empty();
    }

    public default Optional<String[]> testArguments(IMessage message) {
        IArgument[] arguments = this.getArguments();
        String[] content = message.getContent().split(" ");
        Permutationer permutationer = new Permutationer(content, arguments.length);
        int[] lower = this.getAllLowerRequiredWordCountBounds();
        int[] upper = this.getAllLowerRequiredWordCountBounds();
        while (permutationer.nextPermutationToMatch(lower, upper)) {
            String[] permutation = permutationer.buildPermutation();
            for (int i = 0; i < arguments.length; i++) {
                if (!arguments[i].test(message, permutation[i])) {
                    break;
                } else if (i == arguments.length - 1) {
                    return Optional.ofNullable(permutation);
                }
            }
        }
        return Optional.empty();
    }

    public default int[] getAllLowerRequiredWordCountBounds() {
        IArgument[] arguments = this.getArguments();
        int[] lower = new int[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            lower[i] = arguments[i].getLowerRequiredWordCountBound();
        }
        return lower;
    }

    public default int[] getAllUpperRequiredWordCountBounds() {
        IArgument[] arguments = this.getArguments();
        int[] upper = new int[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            upper[i] = arguments[i].getLowerRequiredWordCountBound();
        }
        return upper;
    }

    public default int getLength() {
        return this.getArguments().length;
    }
}
