package org.playerscout;



import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("playerscout")
public interface PlayerScoutConfig extends Config
{
    @ConfigItem(
            keyName = "showLevel",
            name = "Mostrar nivel del jugador",
            description = "Mostrar o no el nivel del jugador"
    )
    default boolean showLevel()
    {
        return true;
    }
}
