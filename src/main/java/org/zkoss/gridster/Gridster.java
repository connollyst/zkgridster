package org.zkoss.gridster;

import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

import java.io.IOException;

/**
 * A <a href="http://gridster.net/">gridster.js</a> component.
 *
 * @author Sean Connolly
 */
public class Gridster extends XulElement {

	private final int[] widgetMargins = new int[] { 10, 10 };
	private final int[] widgetBaseDimensions = new int[] { 140, 140 };

	private int extraRows = 0;
	private int extraCols = 0;

	private int minRows = 15;
	private int minCols = 1;

	private Integer maxCols = null;
	private Integer maxSizeX = null;

	public int[] getWidgetMargins() {
		return widgetMargins;
	}

	public int getWidgetMarginHorizontal() {
		return widgetMargins[0];
	}

	public int getWidgetMarginVertical() {
		return widgetMargins[1];
	}

	public int[] getWidgetBaseDimensions() {
		return widgetBaseDimensions;
	}

	public int getWidgetBaseWidth() {
		return widgetBaseDimensions[0];
	}

	public int getWidgetBaseHeight() {
		return widgetBaseDimensions[1];
	}

	public int getExtraRows() {
		return extraRows;
	}

	public int getExtraCols() {
		return extraCols;
	}

	public int getMinRows() {
		return minRows;
	}

	public int getMinCols() {
		return minCols;
	}

	public Integer getMaxCols() {
		return maxCols;
	}

	public Integer getMaxSizeX() {
		return maxSizeX;
	}

	public void setWidgetMargins(int horizontalMargin, int verticalMargin) {
		setWidgetMarginHorizontal(horizontalMargin);
		setWidgetMarginVertical(verticalMargin);
	}

	public void setWidgetMarginHorizontal(int horizontalMargin) {
		widgetMargins[0] = horizontalMargin;
	}

	public void setWidgetMarginVertical(int verticalMargin) {
		widgetMargins[1] = verticalMargin;
	}

	public void setWidgetBaseDimensions(int width, int height) {
		setWidgetBaseWidth(width);
		setWidgetBaseHeight(height);
	}

	public void setWidgetBaseWidth(int width) {
		if(widgetBaseDimensions[0] != width) {
			widgetBaseDimensions[0] = width;
			smartUpdate("widgetBaseDimensions", widgetBaseDimensions);
		}
	}

	public void setWidgetBaseHeight(int height) {
		if(widgetBaseDimensions[1] != height) {
			widgetBaseDimensions[1] = height;
			smartUpdate("widgetBaseDimensions", widgetBaseDimensions);
		}
	}

	public void setExtraRows(int extraRows) {
		if(this.extraRows != extraRows) {
			this.extraRows = extraRows;
			smartUpdate("extraRows", extraRows);
		}
	}

	public void setExtraCols(int extraCols) {
		if(this.extraCols != extraCols) {
			this.extraCols = extraCols;
			smartUpdate("extraCols", extraCols);
		}
	}

	public void setMinRows(int minRows) {
		if(this.minRows != minRows) {
			this.minRows = minRows;
			smartUpdate("minRows", minRows);
		}
	}

	public void setMinCols(int minCols) {
		if(this.minCols != minCols) {
			this.minCols = minCols;
			smartUpdate("minCols", minCols);
		}
	}

	public void setMaxCols(Integer maxCols) {
		if(this.maxCols == null || maxCols == null || !this.maxCols.equals(maxCols)) {
			this.maxCols = maxCols;
			smartUpdate("maxCols", maxCols);
		}
	}

	public void setMaxSizeX(Integer maxSizeX) {
		if(this.maxSizeX == null || maxSizeX == null || !this.maxSizeX.equals(maxSizeX)) {
			this.maxSizeX = maxSizeX;
			smartUpdate("maxSizeX", maxSizeX);
		}
	}

	@Override
	protected void renderProperties(ContentRenderer renderer) throws IOException {
		super.renderProperties(renderer);
		render(renderer, "widgetMargins", widgetMargins);
		render(renderer, "widgetBaseDimensions", widgetBaseDimensions);
		render(renderer, "extraRows", extraRows);
		render(renderer, "extraCols", extraCols);
		render(renderer, "minRows", minRows);
		render(renderer, "minCols", minCols);
		render(renderer, "maxCols", maxCols);
		render(renderer, "maxSizeX", maxSizeX);
	}

}
