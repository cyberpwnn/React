package com.volmit.react.content.command;

import com.volmit.react.React;
import com.volmit.react.api.action.Action;
import com.volmit.react.content.action.ActionPurgeEntities;
import com.volmit.react.core.gui.MonitorConfigGUI;
import com.volmit.react.util.decree.DecreeExecutor;
import com.volmit.react.util.decree.DecreeOrigin;
import com.volmit.react.util.decree.annotations.Decree;
import com.volmit.react.util.decree.annotations.Param;
import com.volmit.react.util.decree.handlers.WorldHandler;
import org.bukkit.World;

@Decree(
    name = "action",
    aliases = {"act", "a"},
    origin = DecreeOrigin.BOTH,
    description = "The action command"
)
public class CommandAction implements DecreeExecutor {
    @Decree(
        name = "purge-entities",
        aliases = {"pe", "kill"},
        description = "Purge entities in the area"
    )
    public void purgeEntities(
        @Param(
            name = "radius",
            description = "The chunk radius around you to purge entities from. 0 is all chunks.",
            defaultValue = "0",
            aliases = {"r"}
        )
        int radius,

        @Param(
            name = "world",
            description = "The world to purge entities from.",
            aliases = {"w"}
        )
        World world
    ) {
        Action<ActionPurgeEntities.Params> pe = React.instance.getActionController().getAction("purge-entities");
    }
}
