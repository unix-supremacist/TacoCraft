package io.github.frqnny.tacocraft.config;

import draylar.omegaconfig.api.Comment;
import draylar.omegaconfig.api.Syncing;

@Syncing
public class TacoCraftConfig implements draylar.omegaconfig.api.Config {
    @Comment("Adds Corn Seeds to Grass Loot Table. Turn off if you do not want TacoCraft Seeds to drop its seeds when grass breaks")
    public boolean addCornSeedsToGrassLootTable = true;

    @Comment("Amount of time pencas take to cook in ticks (20 per second)")
    public int penca_cook_time = 6000;

    @Comment("Amount of time tortillas take to cook (20 per second)")
    public int tortilla_cook_time = 300;

    @Override
    public String getName() {
        return "tacocraft";
    }
}
