package ru.incrementstudio.incbosses.abilities.incbossesability_regeneration;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import ru.incrementstudio.incbosses.api.AbilityBase;
import ru.incrementstudio.incbosses.api.StartReason;
import ru.incrementstudio.incbosses.api.StopReason;
import ru.incrementstudio.incbosses.api.bosses.Boss;
import ru.incrementstudio.incbosses.api.bosses.phases.Phase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ability extends AbilityBase {
    private long delay = 0, period = 20;
    private double health = 1;
    private BukkitTask task;

    public Ability(Boss boss, Phase phase, FileConfiguration bossConfig, ConfigurationSection abilityConfig) {
        super(boss, phase, bossConfig, abilityConfig);
        if (abilityConfig.contains("delay")) {
            String delayString = abilityConfig.getString("delay");
            Matcher matcher = Pattern.compile("^(\\d+)(\\w+)$").matcher(delayString);
            if (matcher.matches()) {
                long delayValue = Long.parseLong(matcher.group(1));
                String delayMetric = matcher.group(2);
                if (delayMetric.equalsIgnoreCase("t") || delayMetric.equalsIgnoreCase("tick"))
                    delay = delayValue;
                else if (delayMetric.equalsIgnoreCase("s") || delayMetric.equalsIgnoreCase("sec"))
                    delay = delayValue * 20;
                else if (delayMetric.equalsIgnoreCase("m") || delayMetric.equalsIgnoreCase("min"))
                    delay = delayValue * 20 * 60;
                else
                    Main.logger().error("Неверная единица измерения параметра 'delay': '" + delayMetric + "'!");
            } else {
                Main.logger().error("Значение параметра 'delay' имеет неверный формат: '" + delayString + "'!");
            }
        }
        if (abilityConfig.contains("period")) {
            String periodString = abilityConfig.getString("period");
            Matcher matcher = Pattern.compile("^(\\d+)(\\w+)$").matcher(periodString);
            if (matcher.matches()) {
                long periodValue = Long.parseLong(matcher.group(1));
                String periodMetric = matcher.group(2);
                if (periodMetric.equalsIgnoreCase("t") || periodMetric.equalsIgnoreCase("tick"))
                    period = periodValue;
                else if (periodMetric.equalsIgnoreCase("s") || periodMetric.equalsIgnoreCase("sec"))
                    period = periodValue * 20;
                else if (periodMetric.equalsIgnoreCase("m") || periodMetric.equalsIgnoreCase("min"))
                    period = periodValue * 20 * 60;
                else
                    Main.logger().error("Неверная единица измерения параметра 'period': '" + periodMetric + "'!");
            } else {
                Main.logger().error("Значение параметра 'period' имеет неверный формат: '" + periodString + "'!");
            }
        }
        if (abilityConfig.contains("health"))
            health = abilityConfig.getDouble("health");
    }

    @Override
    public void start(StartReason reason) {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                boss.getEntity().setHealth(Math.min(boss.getEntity().getMaxHealth(), boss.getEntity().getHealth() + health));
            }
        }.runTaskTimer(Main.getInstance(), delay, period);
    }

    @Override
    public void stop(StopReason reason) {
        task.cancel();
    }
}
