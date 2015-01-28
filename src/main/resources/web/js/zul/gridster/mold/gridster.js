function (out) {
    out.push('<div', this.domAttrs_(), '>');
    out.push('<ul>');
    for (var w = this.firstChild; w; w = w.nextSibling) {
        w.redraw(out);
    }
    out.push('</ul>');
    out.push('</div>');
}