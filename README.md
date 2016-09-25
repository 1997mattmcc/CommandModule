# CommandModule
A Discord4J module to handle commands

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.github.1997mattmcc</groupId>
        <artifactId>CommandModule</artifactId>
        <version>@VERSION@</version>
    </dependency>
</dependencies>
```

### CommandModule
```java
public class Example {
    
    public static void main(String[] args) throws DiscordException {

        //GETTING COMMAND MODULE
        IDiscordClient client = new ClientBuilder().withToken("CLIENT TOKEN HERE").login(); // Discord4J Client Builder
        CommandModule commandModule = CommandModule.getCommandModuleForDiscordClient(client); // Gets the command module for a given IDiscordClient

        //COMMAND BUILDER
        CommandBuilder commandBuilder = new CommandBuilder("!Test"); // Creates a command builder with unique id
        commandBuilder.newChain(new ArgumentString("!Test", Comparators.EQUALS_IGNORE_CASE, true)).next(new ArgumentLogical()); // Adds a simple argument chain
        commandBuilder.newChain(new ArgumentString("!Test", Comparators.EQUALS_IGNORE_CASE, true)); // Adds a second argument chain
        commandBuilder.consumer(context -> System.out.println(String.format("%s: %s", context.getString(), context.getLogical()))); // Sets the command listener
        Command command = commandBuilder.build(); // Builds the command

        //COMMAND REGISTRATION
        commandModule.registerCommand(command); // Registers the command for listening
    }
}
```