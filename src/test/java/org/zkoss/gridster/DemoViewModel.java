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

/**
 * @author Sean Connolly
 */
public class DemoViewModel {

	@Wire("#win")
	private Window base;
	@Wire
	private Gridster dynamicGridster;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	public void addGriditem() {
		GridItem item = new GridItem();
		item.setRow(1);
		item.setCol(1);
		item.setSizeX(1);
		item.setSizeY(1);
		item.setStyle("background-color: black;");
		Button closeButton = new Button("Remove");
		closeButton.addEventListener(Events.ON_CLICK, new CloseItemEventListener(item));
		item.appendChild(closeButton);
		dynamicGridster.appendChild(item);
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
}
