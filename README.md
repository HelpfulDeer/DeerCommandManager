# DeerCommandManager

## Welcome to my Command Manager

## The total size of it is around 3mb, but if you download the dependencies at runtime, the size will be small

# Implementing the command manager

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.HelpfulDeer</groupId>
    <artifactId>DeerCommandManager</artifactId>
    <version>1.0</version>
</dependency>
```

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    implementation 'com.github.HelpfulDeer:DeerCommandManager:1.0'
}
```
 
## Now that you've implemented it
## We should initialize the DeerCommandManager class

```java
import com.github.helpfuldeer.deercommandmanager.DeerCommandManager;

public final class Testing extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new DeerCommandManager(this, "your commands package namespace");
        DeerCommandManager.getDeerCommandManager().getCommandManager().registerBasicCommands();
        DeerCommandManager.getDeerCommandManager().getCommandManager().registerSubCommands();
        // optional
        DeerCommandManager.getDeerCommandManager().setCommandSettings(/* Class That extends CommandSettings */);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
```
## The commands will be automatically registered, so no need to put them in the plugin.yml
## So? how do we create a command? 
## Here is how u create a command without subcommands!
```java
// name - required - The name of this command
// description - required - The description this command
// usage - required - The usage of this command
// permission - optional - The permission needed to run this command
// aliases - optional - The aliases of this command
@BasicCommand(name = "basictest", description = "Basic Test Command", usage = "a", aliases = {"basictesting"})
public class TestBasicCommand extends Command {

    // Leave this be, do not edit it, the BasicCommand annotation will do this
    public TestBasicCommand(String name, String description, String usageMessage, List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        sender.sendMessage("Hello!");
    }
}
``` 
## Alright, now u know how to make a command without subcommands, but how do u create one with subcommands?
```java
// name - required - The name of the command
@GroupCommand(name = "grouptest")
public class TestSubCommand extends SubCommand {

    // Leave this be, do not edit it, the GroupCommand annotation will do this
    public TestSubCommand(String name) {
        super(name);
    }
    
    // name - required - The name of this subcommand
    // name - optional - The name of this subcommand
    // description - optional - The description of this subcommand
    // usage - optional - The usage of this subcommand
    // permission - optional - The permission needed to run this sub command
    // aliases - optional - The aliases of this subcommand
    @Command(name = "test", aliases = {"testing"})
    public void runTestCommand(CommandSender sender, String[] args) {
        sender.sendMessage("This is a command of a subcommand :D");
    }
}
```

## So there is also an option to edit the "Invalid Permission" and the "Invalid Arguments" message

```java

import com.github.helpfuldeer.deercommandmanager.manager.CommandSettings;

public class TestCommandSettings implements CommandSettings {
    @Override
    public String getInvalidPermission() {
        return "message here";
    }

    @Override
    public String getInvalidArguments() {
        return "message here";
    }
}
```

## AND THATS ALL FOLKS, HAVE FUN!