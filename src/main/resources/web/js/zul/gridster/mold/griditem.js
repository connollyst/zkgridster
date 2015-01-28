function (out) {
    out.push('<li ', this.domAttrs_(), '>');
    for (var w = this.firstChild; w; w = w.nextSibling) {
        w.redraw(out);
    }
    out.push('</li>');
}