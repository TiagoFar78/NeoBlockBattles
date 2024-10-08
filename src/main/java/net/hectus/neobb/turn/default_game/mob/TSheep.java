package net.hectus.neobb.turn.default_game.mob;

import com.marcpg.libpg.util.Randomizer;
import net.hectus.neobb.buff.Buff;
import net.hectus.neobb.player.NeoPlayer;
import net.hectus.neobb.shop.Shop;
import net.hectus.neobb.turn.Turn;
import net.hectus.neobb.turn.default_game.attributes.clazz.ColdClazz;
import net.hectus.neobb.turn.default_game.attributes.clazz.SupernaturalClazz;
import net.hectus.neobb.turn.default_game.attributes.function.BuffFunction;
import net.hectus.neobb.turn.default_game.attributes.usage.MobUsage;
import org.bukkit.Material;
import org.bukkit.entity.Sheep;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class TSheep extends Turn<Sheep> implements MobUsage, BuffFunction, SupernaturalClazz {
    public TSheep(NeoPlayer player) { super(null, null, player); }
    public TSheep(Sheep data, NeoPlayer player) { super(data, data.getLocation(), player); }

    @Override
    public ItemStack item() {
        return new ItemStack(Material.SHEEP_SPAWN_EGG);
    }

    @Override
    public int cost() {
        return 4;
    }

    @Override
    public Sheep getValue() {
        return data;
    }

    @Override
    public void apply() {
        switch (data.getColor()) {
            case PINK -> player.game.win(player);
            case LIGHT_GRAY -> new Buff.ExtraTurn().apply(player);
            case GRAY -> new Buff.Luck(Buff.BuffTarget.YOU, 25).apply(player);
            case BLACK -> new Buff.Luck(Buff.BuffTarget.OPPONENTS, -15).apply(player);
            case BROWN -> {
                Turn<?> turn = Shop.turn(Randomizer.fromCollection(player.game.shop().turns), player);
                player.inventory.addToDeck(turn.item(), turn);
            }
            case null, default -> {
                if (player.game.allowedClazzes().contains(ColdClazz.class))
                    new Buff.ExtraTurn().apply(player);
            }
        }
    }

    @Override
    public List<Buff> buffs() {
        return List.of(new Buff.Luck(Buff.BuffTarget.YOU, 5));
    }
}
