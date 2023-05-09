package com.volmit.react.content.action;

import com.volmit.react.React;
import com.volmit.react.api.action.ActionParams;
import com.volmit.react.api.action.ActionTicket;
import com.volmit.react.api.action.ReactAction;
import com.volmit.react.content.sampler.SamplerMemoryUsed;
import com.volmit.react.util.format.Form;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

public class ActionCollectGarbage extends ReactAction<ActionCollectGarbage.Params> {
    public static final String ID = "collect-garbage";

    public ActionCollectGarbage() {
        super(ID);
    }

    @Override
    public String getCompletedMessage(ActionTicket<Params> ticket) {
        return "Freed " + React.instance.getSampleController().getSampler(SamplerMemoryUsed.ID).format(ticket.getCount()) + " in " + Form.duration(ticket.getDuration(), 1);
    }

    @Override
    public void workOn(ActionTicket<Params> ticket) {
        int bytesBefore = (int) React.instance.getSampleController().getSampler(SamplerMemoryUsed.ID).sample();
        System.gc();
        int bytesAfter = (int) React.instance.getSampleController().getSampler(SamplerMemoryUsed.ID).sample();

        if (bytesBefore > bytesAfter) {
            ticket.setCount(bytesBefore - bytesAfter);
        }

        ticket.complete();
    }

    @Override
    public Params getDefaultParams() {
        return Params.builder().build();
    }

    @Override
    public void onInit() {

    }

    @Builder
    @Data
    @Accessors(chain = true)
    public static class Params implements ActionParams {

    }
}
