function (out) {
    out.push('<div', this.domAttrs_(), '>');
    out.push('<ul>');
    if (this.items) {
        this.items.redraw(out);
    }
    out.push('</ul>');
    out.push('</div>');
}