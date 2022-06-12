package fr.ayato.farmranking;

import java.util.List;
import static fr.ayato.farmranking.Main.instance;

public class GetGlass {

    public static List<Integer> getGlassPlace() {
        return Main.getInstance().getConfig().getIntegerList("Glass.place");
    }

    public static String getGlassName() {
        return instance.getConfig().getString("Glass.name");
    }
}
