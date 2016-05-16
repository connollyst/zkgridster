zul.gridster.Gridster = zk.$extends(zk.Widget, {
    $define: {
        gridster: function (g) {
            var n = this.$n();
            if (n)
                n.gridster = g;
        },
        widgetMargins: null,
        widgetBaseDimensions: null,
        extraRows: null,
        extraCols: null,
        minRows: null,
        minCols: null,
        maxCols: null,
        maxSizeX: null,
        resizeEnabled: null,
        resizeAxes: null,
        resizeMaxSize: null,
        resizeMinSize: null
    },
    bind_: function () {
        this.$supers(zul.gridster.Gridster, 'bind_', arguments);
        var widget = this;
        var config = {
            widget_margins: this._widgetMargins,
            widget_base_dimensions: this._widgetBaseDimensions,
            extra_rows: this._extraRows,
            extra_cols: this._extraCols,
            min_rows: this._minRows,
            min_cols: this._minCols,
            max_cols: this._maxSizeX,
            max_size_x: this._maxSizeX,
            draggable: {
                start: function () {
                    widget.fire('onDragStart');
                },
                drag: function () {
                    widget.fire('onDrag');
                },
                stop: function () {
                    widget.fire('onDragStop');
                    widget.fire('onChange', widget._gridster.serialize());
                }
            },
            resize: {
                enabled: this._resizeEnabled,
                axes: [this._resizeAxes],
                max_size: this._resizeMaxSize,
                min_size: this._resizeMinSize,
                start: function () {
                    widget.fire('onResizeStart');
                },
                resize: function () {
                    widget.fire('onResize');
                },
                stop: function () {
                    widget.fire('onResizeStop');
                    widget.fire('onChange', widget._gridster.serialize());
                }
            },
            serialize_params: this.serializeParams
        };
        this.setGridster(
            $('#' + this.uuid + ' > ul').gridster(config).data('gridster')
        );
        this.fire('onChange', this._gridster.serialize());
    },
    serializeParams: function (w, wgd) {
        return {
            col: wgd.col,
            row: wgd.row,
            size_x: wgd.size_x,
            size_y: wgd.size_y,
            uuid: w.context.id
        }
    },
    insertChildHTML_: function (child, before, desktop) {
        var ben, html = child.redrawHTML_();
        var sizex = child._sizeX,
            sizey = child._sizeY,
            col = child._col,
            row = child._row;
        this._gridster.add_widget(html, sizex, sizey, col, row);
        // TODO support 'before'
        child.bind(desktop);
        this.fire('onChange', this._gridster.serialize());
    },
    removeChildHTML_: function (child, ignoreDom) {
        this._gridster.remove_widget(child);
        this.fire('onChange', this._gridster.serialize());
    },
    domClass_: function (no) {
        var classes = this.$supers("domClass_", no) || '';
        if (classes) {
            classes += ' ';
        }
        classes += 'gridster';
        return classes.trim();
    }
});