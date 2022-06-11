package fr.ayato.farmranking.utils;
import fr.ayato.farmranking.Main;
import java.util.Objects;

public class ClassementUtils {
    public static FactionObject getFactionObject(String factionName) {
        for (FactionObject factionObject : (Main.getInstance()).loadConfig.factionList) {
            if (factionObject.factionName.equalsIgnoreCase(factionName))
                return factionObject;
        }
        return null;
    }

    public static void sortList() {
        (Main.getInstance()).loadConfig.factionList.sort((factionObject, factionObject1) -> factionObject1.points - factionObject.points);
    }

    public static void add(String factionName, int points) {
        FactionObject factionObject = getFactionObject(factionName);
        assert factionObject != null;
        factionObject.points += points;
        if (Objects.requireNonNull(getFactionObject(factionName)).points < 0) {
            Objects.requireNonNull(getFactionObject(factionName)).points = 0;
        }
        sortList();
    }

    public static void set(String factionName, int points) {
        if (points < 0)
            points = 0;
        Objects.requireNonNull(getFactionObject(factionName)).points = points;
        sortList();
    }
}
