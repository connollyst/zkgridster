function (out) {
    out.push('<div', this.domAttrs_(), '>');
    out.push('<ul style="list-style-type:none; padding:0; margin:0;">');
    for (var w = this.firstChild; w; w = w.nextSibling) {
        w.redraw(out);
    }
    out.push('</ul>');
    out.push('</div>');
}