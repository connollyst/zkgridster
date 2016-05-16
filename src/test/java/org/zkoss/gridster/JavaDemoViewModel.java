package org.zkoss.gridster;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;

/**
 * View-Model for the demo of {@link Gridster} in action.
 *
 * @author Sean Connolly
 */
public class JavaDemoViewModel extends AbstractViewModel {

    @Wire
    private Div wrapper;

    @AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
        Selectors.wireComponents(view, this, false);
        Gridster gridster = new Gridster();
        gridster.setParent(wrapper);
        init(gridster);
    }


}
