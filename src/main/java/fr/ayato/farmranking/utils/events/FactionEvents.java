package fr.ayato.farmranking.utils.events;

import com.massivecraft.factions.event.FactionCreateEvent;
import com.massivecraft.factions.event.FactionDisbandEvent;
import com.massivecraft.factions.event.FactionRenameEvent;
import fr.ayato.farmranking.Main;
import fr.ayato.farmranking.utils.ClassementUtils;
import fr.ayato.farmranking.utils.FactionObject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class FactionEvents implements Listener {

    @EventHandler
    public void onFactionCreate(FactionCreateEvent e) {
        System.out.println("Faction créée : " + e.getFactionTag());
        (Main.getInstance()).loadConfig.factionList.add(new FactionObject(0, e.getFactionTag()));
        ClassementUtils.sortList();
    }

    @EventHandler
    public void onFactionDelete(FactionDisbandEvent e) {
        System.out.println("Faction supprimée : " + e.getFaction().getTag());
        (Main.getInstance()).loadConfig.factionList.remove(ClassementUtils.getFactionObject(e.getFaction().getTag()));
        ClassementUtils.sortList();
    }

    @EventHandler
    public void onFactionRename(FactionRenameEvent e) {
        int points = 0;
        String oldFaction = String.valueOf(e.getFaction().getTag());
        String newFaction = e.getFactionTag();
        for (int i = 0; i != (Main.getInstance()).loadConfig.factionList.size(); i++) {
            if (Objects.equals((Main.getInstance()).loadConfig.factionList.get(i).factionName, oldFaction)) {
                points = (Main.getInstance()).loadConfig.factionList.get(i).points;
            }
        }
        (Main.getInstance()).loadConfig.factionList.remove(ClassementUtils.getFactionObject(oldFaction));
        ClassementUtils.sortList();
        (Main.getInstance()).loadConfig.factionList.add(new FactionObject(points, newFaction));
        ClassementUtils.sortList();
    }
}
