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
        IDiscordClient client = new ClientBuilder().withToken("CLIENT TOKEN HERE").login();      // Discord4J Client Builder
        CommandModule commandModule = CommandModule.getCommandModuleForDiscordClient(client);    // Gets the command module for a given IDiscordClient

        //COMMAND BUILDER
        CommandBuilder builder = new CommandBuilder("!Test");                                    // Creates a command builder with unique id
        builder.newChain(new ArgumentString("!Test", Comparators.EQUALS_IGNORE_CASE, true));     // Adds a simple argument chain
        builder.consumer(context -> System.out.println(context.getString()));                    // Sets the command listener

        //COMMAND REGISTRATION
        Command command = builder.build();                                                       // Builds the command
        commandModule.registerCommand(command);                                                  // Registers the command for listening
    }
}
```