# zkgridster
ZK wrapper for [gridster.js](http://gridster.net/)

## Download

### Maven

    <dependency>
    	<groupId>org.zkoss</groupId>
    	<artifactId>zkgridster</artifactId>
    	<version>0.1.4</version>
    </dependency>

### Gradle

    runtime group: 'org.zkoss', name: 'zkgridster', version: '0.1.4'

## Quick Start

All features are supported in both ZUL and Java. The two following code snippets are functionally equivalent. Both will create a ``Gridster`` component with two ``Griditem``s, one taller (``sizeX="2"``) and one wider (``sizeY="2"``). Note that a ``Griditem`` can contain any ZK component.

### ZUL

    <gridster>
        <griditem row="1" col="1" sizeX="2" sizeY="1">
        	<label value="Hello"/>
        </griditem>
        <griditem row="1" col="3" sizeX="1" sizeY="2">
        	<label value="World!"/>
        </griditem>
    <gridster>

### Java

    Gridster gridster = new Gridster();
    Griditem item1 = new Griditem();
    item1.setRow(1);
    item1.setCol(1);
    item1.setSizeX(2);
    item1.setSizeY(1);
    item1.appendChild(new Label("Hello"));
    item1.setParent(gridster);
    Griditem item2 = new Griditem();
    item2.setRow(1);
    item2.setCol(3);
    item2.setSizeX(1);
    item2.setSizeY(2);
    item2.appendChild(new Label("World!"));
    item2.setParent(gridster);
    
## Demo

A small demo app is included to showcase functionality and use. To run the demo with Maven, start Jetty:

    mvn jetty:run
    
and navigate to [http://localhost:8080/zkgridster/](http://localhost:8080/zkgridster/)

## Advanced Examples

Refer to the [gridster.js documentation](http://gridster.net/) for a list of all features and examples of their usage. zkgridster wraps all original features and function names in Java are analogous to those in JavaScript.

### Gridster Parameters

The ``Gridster`` component accepts a number of 

    <gridster baseWidth="100"
              baseHeight="100"
              widgetMarginVertical="10"
              widgetMarginHorizontal="10"
              resizeEnabled="true"
              resizeMaxCols="5"
              resizeMaxRows="3"
              resizeMinCols="2"
              resizeMinRows="2"
              style="background-color: gainsboro">
        <griditem row="1" col="1" sizeX="1" sizeY="1"/>
        <griditem row="2" col="1" sizeX="1" sizeY="1"/>
        <griditem row="3" col="1" sizeX="1" sizeY="1"/>
        
        <griditem row="1" col="2" sizeX="2" sizeY="1"/>
        <griditem row="2" col="2" sizeX="2" sizeY="2"/>
        
        <griditem row="1" col="4" sizeX="1" sizeY="1"/>
        <griditem row="2" col="4" sizeX="2" sizeY="1"/>
        <griditem row="3" col="4" sizeX="1" sizeY="1"/>
        
        <griditem row="1" col="5" sizeX="1" sizeY="1"/>
        <griditem row="3" col="5" sizeX="1" sizeY="1"/>
        
        <griditem row="1" col="6" sizeX="1" sizeY="1"/>
        <griditem row="2" col="6" sizeX="1" sizeY="2"/>
    <gridster>

| zkgridster Parameter       | gridster.js Analogue |
|----------------------------|----------------------|
| ``widgetMarginHorizontal`` | [``widget_margins``](http://gridster.net/#widget_margins_opt)
| ``widgetMarginVertical``   | [``widget_margins``](http://gridster.net/#widget_margins_opt)
| ``widgetBaseWidth``        | [``widget_base_dimensions``](http://gridster.net/#widget_base_dimensions_opt)
| ``widgetBaseHeight``       | [``widget_base_dimensions``](http://gridster.net/#widget_base_dimensions_opt)
| ``extraRows``              | [``extra_rows``](http://gridster.net/#extra_rows_opt)
| ``extraCols``              | [``extra_cols``](http://gridster.net/#extra_cols_opt)
| ``minRows``                | [``min_rows``](http://gridster.net/#min_rows_opt)
| ``minCols``                | [``min_cols``](http://gridster.net/#min_cols_opt)
| ``maxCols``                | [``max_cols``](http://gridster.net/#max_cols_opt)
| ``maxSizeX``               | [``max_size_x``](http://gridster.net/#max_size_x_opt)
| ``maxSizeY``               | [``max_size_y``](http://gridster.net/#max_size_y_opt)
| ``resizeEnabled``          | [``resize.enabled``](http://gridster.net/#resize_enabled_opt)
| ``resizeAxes``             | [``resize.axes``](http://gridster.net/#resize_axes_opt)
| ``resizeMaxRows``          | [``resize.max_size``](http://gridster.net/#resize_max_size_opt)
| ``resizeMaxCols``          | [``resize.max_size``](http://gridster.net/#resize_max_size_opt)
| ``resizeMinRows``          | [``resize.min_size``](http://gridster.net/#resize_min_size_opt)
| ``resizeMinCols``          | [``resize.min_size``](http://gridster.net/#resize_min_size_opt)

### Event Listening

zkgridster provides native ZK server side events for client side interactions. Supported events are:

| Event Name    | Java Static Variable               | gridster.js Analogue | Event Data | Description |
|---------------|------------------------------------|----------------------|------------|-------------|
| onChange      | ``GridsterEvents.ON_CHANGE``       | _none_ | Serialized State | Fired when the ``Gridster`` is first rendered with it's data, and when the state changes, either by moving or resizing a ``Griditem``. | 
| onDragStart   | ``GridsterEvents.ON_DRAG_START``   | [``draggable.start``](http://gridster.net/#draggable_start_opt) | _none_ | Fired once when the user _starts_ dragging a ``Griditem`` |
| onDrag        | ``GridsterEvents.ON_DRAG``         | [``draggable.drag``](http://gridster.net/#draggable_drag_opt)  | _none_ | Fired as the user moves their mouse while dragging a ``Griditem`` |
| onDragStop    | ``GridsterEvents.ON_DRAG_STOP``    | [``draggable.stop``](http://gridster.net/#draggable_stop_opt)  | _none_ | Fired once when the user _stops_ dragging a ``Griditem`` |
| onResizeStart | ``GridsterEvents.ON_RESIZE_START`` | [``resize.start``](http://gridster.net/#resize_start_opt)    | _none_ | Fired once when the user _starts_ resizing a ``Griditem`` |
| onResize      | ``GridsterEvents.ON_RESIZE``       | [``resize.resize``](http://gridster.net/#resize_resize_opt)   | _none_ | Fired as the user moves their mouse while resizing a ``Griditem`` |
| onResizeStop  | ``GridsterEvents.ON_RESIZE_STOP``  | [``resize.stop``](http://gridster.net/#resize_stop_opt)     | _none_ | Fired once when the user _stops_ resizing a ``Griditem`` |

__Note:__ All events are fired on the ``Gridster`` component, _no_ events are fired for individual ``Griditem``s. This is because, for example, resizing a single item can cause a reorganization of all other items; it is difficult to track individual changes.

#### Event Listening - Example

    gridster.addEventListener(GridsterEvents.ON_DRAG, new EventListener<Event>() {
    	@Override
    	public void onEvent(Event event) {
    		// Handle dragging
    	}
    });

#### Event Listening - State

Note that no data is passed for any event other than ``onChange``, where the data is a [``org.zkoss.json.JSONObject``](http://www.zkoss.org/javadoc/7.0.3/zk/org/zkoss/json/JSONObject.html) of the [serialized state](http://gridster.net/demos/serialize.html). Normal use, however, should not require you interact with this serialized state directly. Instead, this is used internally to keep the server state in-sync with the client at all times. As such, you can assume the server state is accurate when handling events server side. For example:

    gridster.addEventListener(GridsterEvents.ON_CHANGE, new EventListener<Event>() {
    	@Override
    	public void onEvent(Event event) {
    		Gridster gridster = (Gridster) event.getTarget();
    		for(GridItem item : gridster.getGridItems()) {
      		    // item values reflect already recent client side changes:
    		    System.out.println(item.getUuid() + ": " +
    		    		" row=" + item.getRow()
    		    		" col=" + item.getCol()
    		    		" sizeX=" + item.getSizeX()
    		    		" sizeY=" + item.getSizeY()
    		    );
    		}
    	}
    });


## Requirements

zkgridster requires a minimum of __Java 7__ and __ZK 7.0.3__
