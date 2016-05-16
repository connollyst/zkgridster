package org.zkoss.gridster;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;

import java.util.Random;

/**
 * @author Sean Connolly
 */
public abstract class AbstractViewModel {

    // https://kuler.adobe.com/Campfire-color-theme-2528696
    private static final String[] CAMPFIRE = new String[]{"#588C73", "#F2E394", "#F2AE72", "#D96459", "#8C4646"};

    private final Random random = new Random();

    private Gridster gridster;

    public void init(Gridster gridster) {
        PrintEventListener printer = new PrintEventListener();
        this.gridster = gridster;
        this.gridster.setWidgetBaseDimensions(100, 100);
        this.gridster.addEventListener(GridsterEvents.ON_DRAG_START, printer);
        this.gridster.addEventListener(GridsterEvents.ON_DRAG, printer);
        this.gridster.addEventListener(GridsterEvents.ON_DRAG_STOP, printer);
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
        item.setStyle("background-color: " + randomColor());
        Button closeButton = new Button("X");
        closeButton.addEventListener(Events.ON_CLICK, new CloseItemEventListener(item));
        item.appendChild(closeButton);
        gridster.appendChild(item);
    }

    private String randomColor() {
        return CAMPFIRE[random.nextInt(5)];
    }

    @Command
    public void reinitialize() {
        gridster.invalidate();
    }

    private static final class CloseItemEventListener implements EventListener<Event> {

        private final GridItem item;

        private CloseItemEventListener(GridItem item) {
            this.item = item;
        }

        @Override
        public void onEvent(Event event) throws Exception {
            System.out.println("Removing item at [" + item.getRow() + "," + item.getCol() + "]");
            item.detach();
        }
    }

    protected static final class PrintEventListener implements EventListener<Event> {

        @Override
        public void onEvent(Event event) throws Exception {
            System.out.println(event.getName());
        }
    }

}
