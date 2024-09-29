package org.makinstan;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import org.playerscout.PlayerScoutPlugin;

public class PluginLauncher
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ExamplePlugin.class);
		ExternalPluginManager.loadBuiltin(PlayerScoutPlugin.class);
		RuneLite.main(args);
	}
}