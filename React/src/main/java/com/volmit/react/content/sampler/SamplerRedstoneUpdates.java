package com.volmit.react.content.sampler;

import com.volmit.react.React;
import com.volmit.react.api.sampler.ReactCachedRateSampler;
import com.volmit.react.util.format.Form;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class SamplerRedstoneUpdates extends ReactCachedRateSampler implements Listener {
    public static final String ID = "redstone";

    public SamplerRedstoneUpdates() {
        super(ID, 1000);
    }

    @Override
    public Material getIcon() {
        return Material.REDSTONE;
    }

    @Override
    public void start() {
        super.start();
        React.instance.registerListener(this);
    }

    @Override
    public void stop() {
        super.stop();
        React.instance.unregisterListener(this);
    }

    @EventHandler
    public void on(BlockRedstoneEvent event) {
        increment();
        getChunkCounter(event.getBlock()).addAndGet(1D);
    }

    @Override
    public String formattedValue(double t) {
        return Form.f(Math.round(t));
    }

    @Override
    public String formattedSuffix(double t) {
        return "RED/s";
    }
}
