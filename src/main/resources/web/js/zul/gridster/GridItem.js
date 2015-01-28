zul.gridster.GridItem = zk.$extends(zk.Widget, {
    $define: {
        row: function (r) {
            var n = this.$n();
            if (n)
                n.row = r;
        },
        col: function (c) {
            var n = this.$n();
            if (n)
                n.col = c;
        },
        sizex: function (x) {
            var n = this.$n();
            if (n)
                n.sizex = x;
        },
        sizey: function (y) {
            var n = this.$n();
            if (n)
                n.sizey = y;
        }
    },
    //super//
    domAttrs_: function (no) {
        var row = this._row,
            col = this._col,
            sizex = this._sizex,
            sizey = this._sizey,
            attr = this.$supers('domAttrs_', arguments);
        return attr
            + ' data-row="' + row + '"'
            + ' data-col="' + col + '"'
            + ' data-sizex="' + sizex + '"'
            + ' data-sizey="' + sizey + '"';
    }
});