zul.gridster.Gridster = zk.$extends(zk.Widget, {
    $define: {
        gridster: function (g) {
            var n = this.$n();
            if (n)
                n.gridster = g;
        }
    },
    bind_: function () {
        this.$supers(zul.gridster.Gridster, 'bind_', arguments);
        var g = $('#' + this.uuid + ' ul').gridster().data('gridster');
        this.setGridster(g);
    },
    insertChildHTML_: function (child, before, desktop) {
        this.$supers(zul.gridster.Gridster, 'insertChildHTML_', arguments);
        this.getGridster().add_widget(child);
    },
    removeChildHTML_: function (child, ignoreDom) {
        this.getGridster().remove_widget(child);
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