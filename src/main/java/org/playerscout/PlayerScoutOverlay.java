package org.playerscout;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class PlayerScoutOverlay extends Overlay
{
    private final Client client;
    private final PlayerScoutPlugin plugin;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public PlayerScoutOverlay(Client client, PlayerScoutPlugin plugin)
    {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();

        for (Player player : client.getPlayers())
        {
            // Obtenemos el valor total del equipo del jugador
            //int totalValue = plugin.calculatePlayerEquipmentValue(player);
            int totalValue = plugin.calculatePlayerWeaponValues(player);
            // Mostramos el nombre del jugador y el valor de su equipo
            panelComponent.getChildren().add(TitleComponent.builder()
                    .text(player.getName() + " - Valor equipo: " + totalValue + " gp")
                    .build());
        }

        return panelComponent.render(graphics);
    }
}
