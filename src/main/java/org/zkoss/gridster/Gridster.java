package org.zkoss.gridster;

import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A <a href="http://gridster.net/">gridster.js</a> component.
 *
 * @author Sean Connolly
 */
public class Gridster extends XulElement {

	static {
		addClientEvent(Gridster.class, GridsterEvents.ON_CHANGE, ComponentCtrl.CE_DUPLICATE_IGNORE);
		addClientEvent(Gridster.class, GridsterEvents.ON_DRAG_START, ComponentCtrl.CE_IMPORTANT);
		addClientEvent(Gridster.class, GridsterEvents.ON_DRAG, ComponentCtrl.CE_IMPORTANT);
		addClientEvent(Gridster.class, GridsterEvents.ON_DRAG_STOP, ComponentCtrl.CE_IMPORTANT);
		addClientEvent(Gridster.class, GridsterEvents.ON_RESIZE_START, ComponentCtrl.CE_IMPORTANT);
		addClientEvent(Gridster.class, GridsterEvents.ON_RESIZE, ComponentCtrl.CE_IMPORTANT);
		addClientEvent(Gridster.class, GridsterEvents.ON_RESIZE_STOP, ComponentCtrl.CE_IMPORTANT);
	}

	private final int[] widgetMargins = new int[] { 10, 10 };
	private final int[] widgetBaseDimensions = new int[] { 140, 140 };

	private int extraRows = 0;
	private int extraCols = 0;

	private int minRows = 15;
	private int minCols = 1;

	private Integer maxCols = null;
	private Integer maxSizeX = null;

	private boolean resizeEnabled = false;
	private String resizeAxes = "both";
	private final Integer[] resizeMaxSize = new Integer[] { null, null };
	private final Integer[] resizeMinSize = new Integer[] { null, null };

	public Gridster() {
		addEventListener(0, GridsterEvents.ON_CHANGE, new EventListener<Event>() {

			@Override
			public void onEvent(Event event) {
				Object[] data = (Object[])event.getData();
				for(Object state : data) {
					saveGridItemState((JSONObject)state);
				}
			}
		});
	}

	/**
	 * Save the {@link GridItem} state returned from the client. Note that adjusting the state of one item often affects
	 * many, as such it is the gridster's responsibility to notify all items.
	 * 
	 * @param state the state of a grid item
	 */
	private void saveGridItemState(JSONObject state) {
		String uuid = state.get("uuid").toString();
		String row = state.get("row").toString();
		String col = state.get("col").toString();
		String sizeX = state.get("size_x").toString();
		String sizeY = state.get("size_y").toString();
		GridItem item = getGridItem(uuid);
		item.setRow(Integer.valueOf(row));
		item.setCol(Integer.valueOf(col));
		item.setSizeX(Integer.valueOf(sizeX));
		item.setSizeY(Integer.valueOf(sizeY));
	}

	/**
	 * Get the {@link GridItem} component in the grid with the specified {@code uuid}.
	 * 
	 * @param uuid the UUID of the item (assigned by ZK when bound to the Desktop)
	 * @return the grid item for the UUID
	 * @throws IllegalStateException if no grid item was found with the id
	 */
	private GridItem getGridItem(String uuid) {
		for(GridItem item : getGridItems()) {
			if(uuid.equals(item.getUuid())) {
				return item;
			}
		}
		throw new IllegalStateException("Cannot find GridItem '" + uuid + "'.");
	}

	/**
	 * Get all {@link GridItem} components in the grid.
	 * 
	 * @return the grid items
	 * @throws IllegalStateException if one or more child is not a grid item
	 */
	public List<GridItem> getGridItems() {
		List<GridItem> items = new ArrayList<GridItem>();
		for(Component child : getChildren()) {
			if(child instanceof GridItem) {
				items.add((GridItem)child);
			} else {
				throw new IllegalStateException("Invalid child for Gridster, expected GridItem but found "
						+ child.getClass().getSimpleName());
			}
		}
		return items;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean insertBefore(Component newChild, Component refChild) {
		if(newChild instanceof GridItem) {
			return super.insertBefore(newChild, refChild);
		} else {
			throw new IllegalArgumentException("Invalid child for Gridster, expected GridItem but got "
					+ newChild.getClass().getSimpleName());
		}
	}

	public int[] getWidgetMargins() {
		return widgetMargins;
	}

	public int getWidgetMarginHorizontal() {
		return widgetMargins[0];
	}

	public int getWidgetMarginVertical() {
		return widgetMargins[1];
	}

	public int[] getWidgetBaseDimensions() {
		return widgetBaseDimensions;
	}

	public int getWidgetBaseWidth() {
		return widgetBaseDimensions[0];
	}

	public int getWidgetBaseHeight() {
		return widgetBaseDimensions[1];
	}

	public int getExtraRows() {
		return extraRows;
	}

	public int getExtraCols() {
		return extraCols;
	}

	public int getMinRows() {
		return minRows;
	}

	public int getMinCols() {
		return minCols;
	}

	public Integer getMaxCols() {
		return maxCols;
	}

	public Integer getMaxSizeX() {
		return maxSizeX;
	}

	public boolean isResizeEnabled() {
		return resizeEnabled;
	}

	public String getResizeAxes() {
		return resizeAxes;
	}

	public Integer[] getResizeMaxSize() {
		return resizeMaxSize;
	}

	public Integer getResizeMaxCols() {
		return resizeMaxSize[0];
	}

	public Integer getResizeMaxRows() {
		return resizeMaxSize[1];
	}

	public Integer[] getResizeMinSize() {
		return resizeMinSize;
	}

	public Integer getResizeMinCols() {
		return resizeMinSize[0];
	}

	public Integer getResizeMinRows() {
		return resizeMinSize[1];
	}

	public void setWidgetMargins(int horizontalMargin, int verticalMargin) {
		setWidgetMarginHorizontal(horizontalMargin);
		setWidgetMarginVertical(verticalMargin);
	}

	public void setWidgetMarginHorizontal(int horizontalMargin) {
		widgetMargins[0] = horizontalMargin;
	}

	public void setWidgetMarginVertical(int verticalMargin) {
		widgetMargins[1] = verticalMargin;
	}

	public void setWidgetBaseDimensions(int width, int height) {
		setWidgetBaseWidth(width);
		setWidgetBaseHeight(height);
	}

	public void setWidgetBaseWidth(int width) {
		if(widgetBaseDimensions[0] != width) {
			widgetBaseDimensions[0] = width;
			smartUpdate("widgetBaseDimensions", widgetBaseDimensions);
		}
	}

	public void setWidgetBaseHeight(int height) {
		if(widgetBaseDimensions[1] != height) {
			widgetBaseDimensions[1] = height;
			smartUpdate("widgetBaseDimensions", widgetBaseDimensions);
		}
	}

	public void setExtraRows(int extraRows) {
		if(this.extraRows != extraRows) {
			this.extraRows = extraRows;
			smartUpdate("extraRows", extraRows);
		}
	}

	public void setExtraCols(int extraCols) {
		if(this.extraCols != extraCols) {
			this.extraCols = extraCols;
			smartUpdate("extraCols", extraCols);
		}
	}

	public void setMinRows(int minRows) {
		if(this.minRows != minRows) {
			this.minRows = minRows;
			smartUpdate("minRows", minRows);
		}
	}

	public void setMinCols(int minCols) {
		if(this.minCols != minCols) {
			this.minCols = minCols;
			smartUpdate("minCols", minCols);
		}
	}

	public void setMaxCols(Integer maxCols) {
		if(this.maxCols == null || maxCols == null || !this.maxCols.equals(maxCols)) {
			this.maxCols = maxCols;
			smartUpdate("maxCols", maxCols);
		}
	}

	public void setMaxSizeX(Integer maxSizeX) {
		if(this.maxSizeX == null || maxSizeX == null || !this.maxSizeX.equals(maxSizeX)) {
			this.maxSizeX = maxSizeX;
			smartUpdate("maxSizeX", maxSizeX);
		}
	}

	public void setResizeEnabled(boolean resizeEnabled) {
		if(this.resizeEnabled != resizeEnabled) {
			this.resizeEnabled = resizeEnabled;
			smartUpdate("resizeEnabled", resizeEnabled);
		}
	}

	public void setResizeAxes(String resizeAxes) {
		if(resizeAxes == null) {
			throw new UiException("Resize axes cannot be null");
		}
		if(!"x".equals(resizeAxes) && !"y".equals(resizeAxes) && !"both".equals(resizeAxes)) {
			throw new UiException("Invalid resize axes, expected 'x', 'y' or 'both': " + resizeAxes);
		}
		if(!this.resizeAxes.equals(resizeAxes)) {
			this.resizeAxes = resizeAxes;
			smartUpdate("resizeAxes", resizeAxes);
		}
	}

	public void setResizeMaxSize(int resizeMaxCols, int resizeMaxRows) {
		setResizeMaxCols(resizeMaxCols);
		setResizeMaxRows(resizeMaxRows);
	}

	public void setResizeMaxCols(int resizeMaxCols) {
		if(resizeMaxSize[0] == null || resizeMaxSize[0] != resizeMaxCols) {
			resizeMaxSize[0] = resizeMaxCols;
			smartUpdate("resizeMaxSize", resizeMaxSize);
		}
	}

	public void setResizeMaxRows(int resizeMaxRows) {
		if(resizeMaxSize[1] == null || resizeMaxSize[1] != resizeMaxRows) {
			resizeMaxSize[1] = resizeMaxRows;
			smartUpdate("resizeMaxSize", resizeMaxSize);
		}
	}

	public void setResizeMinSize(int resizeMinCols, int resizeMinRows) {
		setResizeMinCols(resizeMinCols);
		setResizeMinRows(resizeMinRows);
	}

	public void setResizeMinCols(int resizeMinCols) {
		if(resizeMinSize[0] == null || resizeMinSize[0] != resizeMinCols) {
			resizeMinSize[0] = resizeMinCols;
			smartUpdate("resizeMinSize", resizeMinSize);
		}
	}

	public void setResizeMinRows(int resizeMinRows) {
		if(resizeMinSize[1] == null || resizeMinSize[1] != resizeMinRows) {
			resizeMinSize[1] = resizeMinRows;
			smartUpdate("resizeMinSize", resizeMinSize);
		}
	}

	@Override
	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);
		render(renderer, "widgetMargins", widgetMargins);
		render(renderer, "widgetBaseDimensions", widgetBaseDimensions);
		render(renderer, "extraRows", extraRows);
		render(renderer, "extraCols", extraCols);
		render(renderer, "minRows", minRows);
		render(renderer, "minCols", minCols);
		render(renderer, "maxCols", maxCols);
		render(renderer, "maxSizeX", maxSizeX);
		render(renderer, "resizeEnabled", resizeEnabled);
		render(renderer, "resizeAxes", resizeAxes);
		render(renderer, "resizeMaxSize", resizeMaxSize);
		render(renderer, "resizeMinSize", resizeMinSize);
	}

}
