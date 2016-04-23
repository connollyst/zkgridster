package org.zkoss.gridster;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;

import java.util.Random;

/**
 * View-Model for the demo of {@link Gridster} in action.
 *
 * @author Sean Connolly
 */
public class DemoViewModel {

    // https://kuler.adobe.com/Campfire-color-theme-2528696
    private static final String[] CAMPFIRE = new String[]{"#588C73", "#F2E394", "#F2AE72", "#D96459", "#8C4646"};

    private final Random random = new Random();

    @Wire("#win")
    private Window base;
    @Wire
    private Gridster dynamicGridster;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        PrintEventListener printer = new PrintEventListener();
        dynamicGridster.addEventListener(GridsterEvents.ON_DRAG_START, printer);
        dynamicGridster.addEventListener(GridsterEvents.ON_DRAG, printer);
        dynamicGridster.addEventListener(GridsterEvents.ON_DRAG_STOP, printer);
    }

    @Command
    public void addSmallItem() {
        addItem(1, 1);
    }

    @Command
    public void addLargeItem() {
        addItem(randomSize(), randomSize());
    }

    private int randomSize() {
        return random.nextInt(3) + 1;
    }

    private void addItem(int sizeX, int sizeY) {
        GridItem item = new GridItem();
        item.setSizeX(sizeX);
        item.setSizeY(sizeY);
        item.setRow(randomPosition());
        item.setCol(randomPosition());
        item.setStyle("background-color: " + randomColor());
        Button closeButton = new Button("Remove");
        closeButton.addEventListener(Events.ON_CLICK, new CloseItemEventListener(item));
        item.appendChild(closeButton);
        dynamicGridster.appendChild(item);
    }

    private int randomPosition() {
        return random.nextInt(5) + 1;
    }

    private String randomColor() {
        return CAMPFIRE[random.nextInt(5)];
    }

    @Command
    public void reinitialize() {
        dynamicGridster.invalidate();
    }

    private static final class CloseItemEventListener implements EventListener<Event> {

        private final GridItem item;

        private CloseItemEventListener(GridItem item) {
            this.item = item;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            item.detach();
        }
    }

    private static final class PrintEventListener implements EventListener<Event> {

        @Override
        public void onEvent(Event event) throws Exception {
            System.out.println(event.getName());
        }
    }
}
