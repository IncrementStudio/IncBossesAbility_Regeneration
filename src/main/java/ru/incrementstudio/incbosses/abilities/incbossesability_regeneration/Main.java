package ru.incrementstudio.incbosses.abilities.incbossesability_regeneration;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ru.incrementstudio.incbosses.api.AbilityBase;
import ru.incrementstudio.incbosses.api.AbilityPlugin;
import ru.incrementstudio.incbosses.api.bosses.Boss;
import ru.incrementstudio.incbosses.api.bosses.phases.Phase;

public final class Main extends AbilityPlugin {
    @Override
    public String getAbilityName() {
        return getName();
    }

    @Override
    public AbilityBase getAbility(Boss boss, Phase phase, FileConfiguration bossConfig, ConfigurationSection abilityConfig) {
        return new Ability(boss, phase, bossConfig, abilityConfig);
    }
}
