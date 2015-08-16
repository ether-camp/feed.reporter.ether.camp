package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;



public class q extends BaseCommand {

    @Usage("get system status")
    @Command
    public Object status() {

        return "";
    }

}