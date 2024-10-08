package net.hectus.neobb.turn.default_game.other;

import net.hectus.neobb.NeoBB;
import net.hectus.neobb.player.NeoPlayer;
import net.hectus.neobb.turn.Turn;
import net.hectus.neobb.turn.default_game.attributes.clazz.WaterClazz;
import net.hectus.neobb.turn.default_game.attributes.function.EventFunction;
import net.hectus.neobb.turn.default_game.attributes.usage.OtherUsage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.inventory.ItemStack;

public class TBoat extends Turn<Boat> implements OtherUsage<Boat>, EventFunction, WaterClazz {
    public TBoat(NeoPlayer player) { super(null, null, player); }
    public TBoat(Boat data, NeoPlayer player) { super(data, data.getLocation(), player); }

    @Override
    public boolean requiresUsageGuide() { return true; }

    @Override
    public ItemStack item() {
        return new ItemStack(Material.OAK_BOAT);
    }

    @Override
    public int cost() {
        return 3;
    }

    @Override
    public Boat getValue() {
        return data;
    }

    @Override
    public void triggerEvent() {
        player.nextPlayer().addModifier("boat-damage");
        Bukkit.getScheduler().runTaskTimer(NeoBB.PLUGIN, r -> {
            if (player.nextPlayer().hasModifier("boat-damage")) {
                player.nextPlayer().player.damage(1.0);
            } else {
                r.cancel();
            }
        }, 0, 20);
    }
}
