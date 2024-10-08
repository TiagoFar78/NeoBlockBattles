package net.hectus.neobb.turn.default_game.item;

import net.hectus.neobb.player.NeoPlayer;
import net.hectus.neobb.turn.Turn;
import net.hectus.neobb.turn.default_game.attributes.clazz.NeutralClazz;
import net.hectus.neobb.turn.default_game.attributes.function.EventFunction;
import net.hectus.neobb.turn.default_game.attributes.usage.ItemUsage;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class TChorusFruit extends Turn<ItemStack> implements ItemUsage, EventFunction, NeutralClazz {
    public static Random random = new Random();

    public TChorusFruit(NeoPlayer player) { super(null, null, player); }
    public TChorusFruit(ItemStack data, Location location, NeoPlayer player) { super(data, location, player); }

    @Override
    public ItemStack item() {
        return new ItemStack(Material.CHORUS_FRUIT);
    }

    @Override
    public int cost() {
        return 2;
    }

    @Override
    public ItemStack getValue() {
        return data;
    }

    @Override
    public void triggerEvent() {
        Location loc = player.game.warp().lowCorner().clone().add(random.nextDouble(0.0, 9.0), 0, random.nextDouble(0.0, 9.0));
        loc.setPitch(random.nextFloat(-90.0f, 90.0f));
        loc.setYaw(random.nextFloat(0.0f, 360.0f));
        player.player.teleport(loc);
    }
}
