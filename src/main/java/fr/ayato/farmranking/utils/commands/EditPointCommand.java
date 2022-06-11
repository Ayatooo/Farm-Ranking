package fr.ayato.farmranking.utils.commands;

import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;
import fr.ayato.farmranking.Main;
import fr.ayato.farmranking.utils.ClassementUtils;
import fr.ayato.farmranking.utils.FactionObject;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EditPointCommand implements CommandExecutor {

    public boolean factionExist(String factionName) {
        Faction faction = Factions.getInstance().getByTag(factionName);
        FactionObject factionObject = ClassementUtils.getFactionObject(factionName);
        return (faction != null && factionObject != null);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String arg, String[] args) {
        if (args.length == 3 && StringUtils.isNumeric(args[2])) {
            if (!factionExist(args[1])) {
                commandSender.sendMessage((Main.getInstance()).loadConfig.factionDoesNotExist);
            }
            if (args[0].equalsIgnoreCase("add")) {
                ClassementUtils.add(args[1], Integer.parseInt(args[2]));
                commandSender.sendMessage((Main.getInstance()).loadConfig.pointAdded.replace("%point%", "" + args[2]).replace("%faction%", args[1]));
            } else {

                if (!args[0].equalsIgnoreCase("set")) {
                    commandSender.sendMessage((Main.getInstance()).loadConfig.usage);
                    return false;
                }
                ClassementUtils.set(args[1], Integer.parseInt(args[2]));
                commandSender.sendMessage((Main.getInstance()).loadConfig.pointSet.replace("%point%", "" + args[2]).replace("%faction%", args[1]));
            }
            return true;
        }
        commandSender.sendMessage((Main.getInstance()).loadConfig.usage);
        return false;
    }
}
