package commandmodule.utils;

public final class StringComparator {

    private final boolean ignoreCase;
    private final Options option;

    public StringComparator(Options option, boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        this.option = option;
    }

    public final boolean compare(String main, String target) {
        if (ignoreCase) {
            target = target.toLowerCase();
            main = main.toLowerCase();
        }
        switch (option) {
            case EQUALS:
                return main.equals(target);
            case CONTAINS:
                return main.contains(target);
            case STARTS_WITH:
                return main.startsWith(target);
            default:
                return false;
        }
    }

    public final boolean isIgnoreCase() {
        return ignoreCase;
    }

    public final Options getOption() {
        return option;
    }

    @Override
    public final String toString() {
        return ignoreCase ? option.getDescription().concat("-ignore-case") : option.getDescription().concat("-obey-case");
    }

    public static enum Options {
        STARTS_WITH("starts-with"),
        CONTAINS("contains"),
        EQUALS("equals");

        private final String description;

        private Options(String description) {
            this.description = description;
        }

        public final String getDescription() {
            return description;
        }
    };
}
