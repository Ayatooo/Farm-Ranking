package fr.ayato.farmranking.utils.config;

import fr.ayato.farmranking.utils.FactionObject;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConfigJSON {
    public List<FactionObject> factionList = Collections.emptyList();

    public List<String> classementDisplay = Arrays.asList("§b➊ §a§l%faction% §favec §e%point% §fpoints", "§b➋ §a§l%faction% §favec §e%point% §fpoints", "§b➌ §a§l%faction% §favec §e%point% §fpoints");

    public String usage = "§7[§eClassement§7]§r§c Usage: /farmpoint <add/set> <faction> <point>";

    public String factionDoesNotExist = "§7[§eClassement§7]§r§c La faction n'existe pas.";

    public String pointAdded = "§7[§bClassement§7]§7 Vous venez d'ajouter §b%point% §7point(s) à la faction §b%faction%";

    public String pointSet = "§7[§bClassement§7]§7 Vous venez de définir §b%point% §7point(s) à la faction §b%faction%";
}
