zul.gridster.Gridster = zk.$extends(zk.Widget, {
    bind_: function () {
        this.$supers(zul.gridster.Gridster, 'bind_', arguments);
        $('#' + this.uuid + ' ul').gridster();
    }
});