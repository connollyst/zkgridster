zul.gridster.Gridster = zk.$extends(zk.Widget, {
    $define: {
        gridster: function (g) {
            var n = this.$n();
            if (n)
                n.gridster = g;
        },
        widgetBaseDimensions: [
            function (data) {
                return data; // TODO this shouldn't be necessary!?
            },
            function () {
                if (this.desktop) this.rerender();
            }
        ]

    },
    bind_: function () {
        this.$supers(zul.gridster.Gridster, 'bind_', arguments);
        var g = $('#' + this.uuid + ' ul').gridster({
            widget_base_dimensions: this.getWidgetBaseDimensions()
        }).data('gridster');
        this.setGridster(g);
    },
    insertChildHTML_: function (child, before, desktop) {
        var ben, html = child.redrawHTML_();
        var sizex = child._sizex,
            sizey = child._sizey,
            col = child._col,
            row = child._row;
        this._gridster.add_widget(html, sizex, sizey, col, row);
        // TODO support 'before'
        child.bind(desktop);
    },
    removeChildHTML_: function (child, ignoreDom) {
        this._gridster.remove_widget(child);
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