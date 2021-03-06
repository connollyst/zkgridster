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
        sizeX: function (x) {
            var n = this.$n();
            if (n)
                n.sizeX = x;
        },
        sizeY: function (y) {
            var n = this.$n();
            if (n)
                n.sizeY = y;
        },
        maxSizeX: function (x) {
            var n = this.$n();
            if (n)
                n.maxSizeX = x;
        },
        maxSizeY: function (y) {
            var n = this.$n();
            if (n)
                n.maxSizeY = y;
        },
        minSizeX: function (x) {
            var n = this.$n();
            if (n)
                n.minSizeX = x;
        },
        minSizeY: function (y) {
            var n = this.$n();
            if (n)
                n.minSizeY = y;
        }
    },
    //super//
    domAttrs_: function (no) {
        var attr = this.$supers('domAttrs_', arguments),
            row = this._row && 'data-row="' + this._row + '"',
            col = this._col && 'data-col="' + this._col + '"',
            sizeX = this._sizeX && 'data-sizex="' + this._sizeX + '"',
            sizeY = this._sizeX && 'data-sizey="' + this._sizeY + '"',
            maxSizeX = this._maxSizeX && 'data-max-sizex="' + this._maxSizeX + '"',
            maxSizeY = this._maxSizeY && 'data-max-sizey="' + this._maxSizeY + '"',
            minSizeX = this._minSizeX && 'data-min-sizex="' + this._minSizeX + '"',
            minSizeY = this._minSizeY && 'data-min-sizey="' + this._minSizeY + '"';
        return [attr, row, col, sizeX, sizeY, maxSizeX, maxSizeY, minSizeX, minSizeY].filter(function (val) {
            return val;
        }).join(' ');
    }
});