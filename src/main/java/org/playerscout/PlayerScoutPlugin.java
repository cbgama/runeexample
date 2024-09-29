package org.playerscout;

import javax.inject.Inject;

import net.runelite.api.*;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.Item;


@PluginDescriptor(
        name = "Player Scout",
        description = "Muestra el valor del equipamiento de los jugadores",
        tags = {"scout", "player", "equipment", "value"}
)
public class PlayerScoutPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ItemManager itemManager;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private PlayerScoutOverlay playerScoutOverlay;

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(playerScoutOverlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(playerScoutOverlay);
    }
/*
    public Item[] getPlayerEquipment(Player player)
    {
        // Verifica que el jugador no sea nulo
        if (player == null)
        {
            return new Item[0]; // Retorna un array vacío si el jugador no es válido
        }

        // Obtiene la composición del jugador
        int[] equipmentIds = player.getPlayerComposition().getEquipmentIds();

        // Crea un array para los ítems
        Item[] items = new Item[equipmentIds.length];

        // Convierte los IDs a objetos Item
        for (int i = 0; i < equipmentIds.length; i++)
        {
            if (equipmentIds[i] != -1) // -1 significa que el slot está vacío
            {
                items[i] = new Item(equipmentIds[i], 1); // Crea un nuevo objeto Item con el ID y la cantidad
            }
            else
            {
                items[i] = null; // Si el slot está vacío, asigna null
            }
        }

        return items;
    }
*/
public void showWeaponPrice(Player player)
{
    Item weapon = getPlayerWeapon(player); // Obtener el arma equipada

    if (weapon != null)
    {
        // Obtener el precio del ítem usando el ItemManager
        int price = itemManager.getItemPrice(weapon.getId()); // Obtener el precio del ítem

        // Mostrar el precio en la consola
        System.out.println("Precio del arma: " + price);
    }
    else
    {
        System.out.println("El jugador no tiene un arma equipada.");
    }
}
    public boolean isValidItemId(int itemId) {
        return itemManager.getItemComposition(itemId) != null;
    }

    private Item getPlayerWeapon(Player player) {
        if (player == null || player.getPlayerComposition() == null) {
            return null; // Retorna null si el jugador no es válido
        }

        // Obtiene los IDs de los ítems equipados
        int[] equipmentIds = player.getPlayerComposition().getEquipmentIds();
        int weaponSlotIdx = EquipmentInventorySlot.WEAPON.getSlotIdx();
        int weaponId = equipmentIds[weaponSlotIdx];

        // Verificar si el ID de arma es válido
        if (weaponId != -1) {
            System.out.println("ID original (con desplazamiento): " + weaponId);

            // Si el ID está desplazado, resta 2048
            if (weaponId >= 2048) {
                weaponId -= 2048;
                System.out.println("ID mayor a 2048 corrigiendo " + weaponId);
            }


            ItemComposition itemComposition = itemManager.getItemComposition(weaponId);
            if (itemComposition != null) {
                System.out.println("ID del arma corregido: " + weaponId);
                return new Item(itemComposition.getId(), 1);
            } else {
                System.out.println("No se encontró la composición del ítem para el ID: " + weaponId);
            }
        } else {
            System.out.println("Slot de arma vacío.");
        }

        return null; // Retorna null si no hay arma equipada
    }

    // Método para calcular el valor del arma equipada
    public int calculatePlayerWeaponValues(Player player) {
        Item weapon = getPlayerWeapon(player); // Obtener el arma equipada
        int totalValue = 0;

        if (weapon != null) {
            // Obtener el precio del ítem usando el ItemManager
            int price = itemManager.getItemPrice(weapon.getId());
            totalValue = price; // Asignar el precio del arma al total
            System.out.println("Valor del arma (ID: " + weapon.getId() + "): " + totalValue + " gp");
        } else {
            System.out.println("No hay arma equipada.");
        }

        return totalValue; // Retorna el valor total del arma
    }

    // Método para calcular el valor total del equipo
    public int calculatePlayerEquipmentValue(Player player) {
        Item[] equipment = getPlayerEquipment(player); // Método que obtiene todos los ítems equipados
        int totalValue = 0;
        int contador = 0;
        for (Item item : equipment) {
            if (item != null) {
                contador++;
                // Obtener el precio del ítem usando el ItemManager
                int price = itemManager.getItemPrice(item.getId());
                totalValue += price; // Sumar el precio al total
                System.out.println("debug id "+ item.getId()+" "+totalValue +" aaa"+contador);
            }
        }

        return totalValue; // Retorna el valor total del equipamiento
    }

    private Item[] getPlayerEquipment(Player player) {
        // Verifica que el jugador no sea nulo
        if (player == null) {
            return new Item[0]; // Retorna un array vacío si el jugador no es válido
        }

        // Obtiene la composición del jugador
        int[] equipmentIds = player.getPlayerComposition().getEquipmentIds();
        Item[] items = new Item[equipmentIds.length];

        // Convierte los IDs a objetos Item
        for (int i = 0; i < equipmentIds.length; i++) {
            if (equipmentIds[i] != -1) { // -1 significa que el slot está vacío
                items[i] = new Item(equipmentIds[i], 1); // Crea un nuevo objeto Item
            } else {
                items[i] = null; // Si el slot está vacío, asigna null
            }
        }

        return items; // Retorna el array de ítems
    }



}
