package fr.ayato.farmranking.utils.config;

import fr.ayato.farmranking.utils.FactionObject;
import java.util.Collections;
import java.util.List;

public class ConfigJSON {
    public List<FactionObject> factionList = Collections.emptyList();

    public String usage = "§7[§bFarm-Ranking§7] §f» §a/farmpoint <add/set> <faction> <point>";

    public String factionDoesNotExist = "§7[§bFarm-Ranking§7] §f» §cLa faction n'existe pas.";

    public String pointAdded = "§7[§bFarm-Ranking§7]§7 Vous venez d'ajouter §b%point% §7point(s) à la faction §b%faction%";

    public String pointSet = "§7[§bFarm-Ranking§7]§7 Vous venez de définir §b%point% §7point(s) à la faction §b%faction%";
}
