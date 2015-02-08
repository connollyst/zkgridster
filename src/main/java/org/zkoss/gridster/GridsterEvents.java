package org.zkoss.gridster;

/**
 * Events fired by Gridster component.
 * 
 * @author Sean Connolly
 */
public class GridsterEvents {

	/**
	 * Event fired when anything changes, order or size.
	 */
	public static final String ON_CHANGE = "onChange";

	/**
	 * Event fired when dragging starts.
	 */
	public static final String ON_DRAG_START = "onDragStart";

	/**
	 * Event fired when the mouse is moved while dragging.
	 */
	public static final String ON_DRAG = "onDrag";

	/**
	 * Event fired when dragging stops.
	 */
	public static final String ON_DRAG_STOP = "onDragStop";

	/**
	 * Event fired when resizing starts.
	 */
	public static final String ON_RESIZE_START = "onResizeStart";

	/**
	 * Event fired when the mouse is moved while resizing.
	 */
	public static final String ON_RESIZE = "onResize";

	/**
	 * Event fired when resizing stops.
	 */
	public static final String ON_RESIZE_STOP = "onResizeStop";

}
