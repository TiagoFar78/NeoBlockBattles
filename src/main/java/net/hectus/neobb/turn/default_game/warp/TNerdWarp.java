package net.hectus.neobb.turn.default_game.warp;

import com.marcpg.libpg.storing.Pair;
import net.hectus.neobb.structure.PlacedStructure;
import net.hectus.neobb.turn.default_game.attributes.clazz.*;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.List;

public class TNerdWarp extends Warp {
    public TNerdWarp(World world) { super(world, "nerd"); }
    public TNerdWarp(PlacedStructure data, World world) { super(data, world, "nerd"); }

    @Override
    public int chance() {
        return 20;
    }

    @Override
    public List<Class<? extends Clazz>> allows() {
        return List.of(NeutralClazz.class, NatureClazz.class, RedstoneClazz.class, SupernaturalClazz.class);
    }

    @Override
    public Pair<Material, Material> materials() {
        return Pair.of(Material.LECTERN, Material.BOOKSHELF);
    }

    @Override
    public int cost() {
        return 4;
    }
}
