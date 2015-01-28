package org.zkoss.gridster;

import java.io.IOException;

import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

/**
 * An item in a {@link Gridster}.
 *
 * @author Sean Connolly
 */
public class GridItem extends XulElement {

	private int row = 1;
	private int col = 1;
	private int sizeX = 1;
	private int sizeY = 1;

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setRow(int row) {
		if (this.row != row) {
			this.row = row;
			smartUpdate("row", row);
		}
	}

	public void setCol(int col) {
		if (this.col != col) {
			this.col = col;
			smartUpdate("col", col);
		}
	}

	public void setSizex(int sizeX) {
		setSizeX(sizeX);
	}

	public void setSizeX(int sizeX) {
		if (this.sizeX != sizeX) {
			this.sizeX = sizeX;
			smartUpdate("sizeX", sizeX);
		}
	}

	public void setSizey(int sizeY) {
		setSizeY(sizeY);
	}

	public void setSizeY(int sizeY) {
		if (this.sizeY != sizeY) {
			this.sizeY = sizeY;
			smartUpdate("sizeY", sizeY);
		}
	}

	@Override
	protected void renderProperties(ContentRenderer renderer)
			throws IOException {
		super.renderProperties(renderer);
		render(renderer, "row", row);
		render(renderer, "col", col);
		render(renderer, "sizex", sizeX);
		render(renderer, "sizey", sizeY);
	}

}
