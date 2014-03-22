package com.digimax.shop.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Field;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.internal.util.CaptureResultCallback;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jon on 2014-03-22.
 */
@Import(library = {"context:js/onevent.js"})
public class OnEvent {
    @Parameter(required=true, defaultPrefix=BindingConstants.LITERAL)
    private String event;

    @Parameter(defaultPrefix=BindingConstants.LITERAL)
    private String clientEvent;

    @Parameter(required=true, defaultPrefix=BindingConstants.PROP)
    private Zone refreshZone;

    @Parameter
    private Object context;

    @Parameter
    private String[] fields;

    @Inject
    private ComponentResources resources;

    @InjectContainer
    private ClientElement container;

    @Inject
    private JavaScriptSupport jss;

    @Inject
    private Request request;

    private void afterRender() {
        String[] calculatedFields = getFields();
        int fieldCount = calculatedFields == null ? 0 : calculatedFields.length;
        String eventUrl = resources.createEventLink("clientEvent", event, context, fieldCount).toURI();
        JSONObject spec = new JSONObject(
                "url", eventUrl,
                "event", getClientEvent(),
                "id", container.getClientId(),
                "zone", refreshZone.getClientId()
        );
        if (calculatedFields != null) {
            spec.put("fieldIds", new JSONArray((Object[]) calculatedFields));
        }

        jss.addInitializerCall("onEvent", spec);
    }

    private Object onClientEvent(String event, String context, int fieldCount) {
        List<Object> contextValues = new ArrayList<>();
        if (context != null) {
            contextValues.add(context);
        }
        for (int i = 0; i < fieldCount; ++ i) {
            String paramName = "onEvent" + i;
            contextValues.add(request.getParameter(paramName));
        }
        CaptureResultCallback<Object> callback = new CaptureResultCallback<>();
        resources.triggerEvent(event, contextValues.toArray(), callback);
        return callback.getResult();
    }

    private String getClientEvent() {
        return clientEvent != null ? clientEvent : event;
    }

    private String[] getFields() {
        if (fields != null) {
            return fields;
        }
        if (container instanceof Field) {
            return new String[] { container.getClientId() };
        }
        return null;
    }
}
