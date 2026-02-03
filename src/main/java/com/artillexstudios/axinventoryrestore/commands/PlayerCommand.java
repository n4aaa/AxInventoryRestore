package com.artillexstudios.axinventoryrestore.commands;

import com.artillexstudios.axinventoryrestore.commands.subcommands.player.Claim;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.DefaultFor;

@Command({ "odbierzbackupa" })
public class PlayerCommand {

    @DefaultFor({ "~" })
    public void help(@NotNull Player sender) {
        Claim.INSTANCE.execute(sender);
    }

}
