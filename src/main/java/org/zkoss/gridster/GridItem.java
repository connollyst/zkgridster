package org.zkoss.gridster;

import org.zkoss.zk.ui.sys.ContentRenderer;
import org.zkoss.zul.impl.XulElement;

import java.io.IOException;

/**
 * An item in a {@link Gridster}.
 *
 * @author Sean Connolly
 */
public class GridItem extends XulElement implements Comparable<GridItem> {

    private Integer row = null;
    private Integer col = null;
    private Integer sizeX = null;
    private Integer sizeY = null;
    private Integer maxSizeX = null;
    private Integer maxSizeY = null;
    private Integer minSizeX = null;
    private Integer minSizeY = null;

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public Integer getSizeX() {
        return sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public Integer getMaxSizeX() {
        return maxSizeX;
    }

    public Integer getMaxSizeY() {
        return maxSizeY;
    }

    public Integer getMinSizeX() {
        return minSizeX;
    }

    public Integer getMinSizeY() {
        return minSizeY;
    }

    public void setRow(int row) {
        if (this.row == null || this.row != row) {
            this.row = row;
            smartUpdate("row", row);
        }
    }

    public void setCol(int col) {
        if (this.col == null || this.col != col) {
            this.col = col;
            smartUpdate("col", col);
        }
    }

    public void setSizeX(int sizeX) {
        if (this.sizeX == null || this.sizeX != sizeX) {
            this.sizeX = sizeX;
            smartUpdate("sizeX", sizeX);
        }
    }

    public void setSizeY(int sizeY) {
        if (this.sizeY == null || this.sizeY != sizeY) {
            this.sizeY = sizeY;
            smartUpdate("sizeY", sizeY);
        }
    }

    public void setMaxSizeX(int maxSizeX) {
        if (this.maxSizeX == null || this.maxSizeX != maxSizeX) {
            this.maxSizeX = maxSizeX;
            smartUpdate("maxSizeX", maxSizeX);
        }
    }

    public void setMaxSizeY(int maxSizeY) {
        if (this.maxSizeY == null || this.maxSizeY != maxSizeY) {
            this.maxSizeY = maxSizeY;
            smartUpdate("maxSizeY", maxSizeY);
        }
    }

    public void setMinSizeX(int minSizeX) {
        if (this.minSizeX == null || this.minSizeX != minSizeX) {
            this.minSizeX = minSizeX;
            smartUpdate("minSizeX", minSizeX);
        }
    }

    public void setMinSizeY(int minSizeY) {
        if (this.minSizeY == null || this.minSizeY != minSizeY) {
            this.minSizeY = minSizeY;
            smartUpdate("minSizeY", minSizeY);
        }
    }

    @Override
    protected void renderProperties(ContentRenderer renderer) throws IOException {
        super.renderProperties(renderer);
        render(renderer, "row", row);
        render(renderer, "col", col);
        render(renderer, "sizeX", sizeX);
        render(renderer, "sizeY", sizeY);
        render(renderer, "maxSizeX", maxSizeX);
        render(renderer, "maxSizeY", maxSizeY);
        render(renderer, "minSizeX", minSizeX);
        render(renderer, "minSizeY", minSizeY);
    }

    @Override
    public int compareTo(GridItem that) {
        if (this.row == null && that.row == null) {
            return 0;
        }
        if (this.row == null || that.row == null) {
            return (this.row == null) ? -1 : 1;
        }
        int order = Integer.compare(this.row, that.row);
        if (order != 0) {
            return order;
        }
        if (this.col == null && that.col == null) {
            return 0;
        }
        if (this.col == null || that.col == null) {
            return (this.col == null) ? -1 : 1;
        }
        return Integer.compare(this.col, that.col);
    }

}
