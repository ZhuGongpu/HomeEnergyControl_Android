package org.eazegraph.lib.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.BaseModel;
import org.eazegraph.lib.models.StackedBarModel;
import org.eazegraph.lib.utils.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * A rather simple type of a bar chart, where all the bars have the same height and their inner bars
 * heights are dependent on each other.
 */
public class StackedBarChart extends BaseBarChart {
    private static final String LOG_TAG = BarChart.class.getSimpleName();
    private List<StackedBarModel> mData;

    /**
     * 记录stack bar中最大值，用于缩放
     */
    private float maxStackBarValue = 1;
    private int selectedBarIndex = -1;

    /**
     * Simple constructor to use when creating a view from code.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     */
    public StackedBarChart(Context context) {
        super(context);
        initializeGraph();
    }

    /**
     * Constructor that is called when inflating a view from XML. This is called
     * when a view is being constructed from an XML file, supplying attributes
     * that were specified in the XML file. This version uses a default style of
     * 0, so the only attribute values applied are those in the Context's Theme
     * and the given AttributeSet.
     * <p/>
     * <p/>
     * The method onFinishInflate() will be called after all children have been
     * added.
     *
     * @param context The Context the view is running in, through which it can
     *                access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    public StackedBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeGraph();
    }

    /**
     * Adds a new {@link org.eazegraph.lib.models.StackedBarModel} to the BarChart.
     *
     * @param _Bar The StackedBarModel which will be added to the chart.
     */
    public void addBar(StackedBarModel _Bar) {
        mData.add(_Bar);
        if (_Bar.getSumValue() > maxStackBarValue)
            maxStackBarValue = _Bar.getSumValue();
        setBarSelected(mData.size() - 1);
        onDataChanged();
    }

    /**
     * Adds a new list of {@link org.eazegraph.lib.models.StackedBarModel} to the BarChart.
     *
     * @param _List The StackedBarModel list which will be added to the chart.
     */
    public void addBarList(List<StackedBarModel> _List) {
        mData = _List;
        maxStackBarValue = 1;
        for (StackedBarModel model : _List) {
            if (model.getSumValue() > maxStackBarValue)
                maxStackBarValue = model.getSumValue();
        }
        setBarSelected(mData.size() - 1);
        onDataChanged();
    }

    /**
     * Returns the data which is currently present in the chart.
     *
     * @return The currently used data.
     */
    @Override
    public List<StackedBarModel> getData() {
        return mData;
    }

    /**
     * Resets and clears the data object.
     */
    @Override
    public void clearChart() {
        mData.clear();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is the main entry point after the graph has been inflated. Used to initialize the graph
     * and its corresponding members.
     */
    @Override
    protected void initializeGraph() {
        super.initializeGraph();
        mData = new ArrayList<StackedBarModel>();

        if (this.isInEditMode()) {
            StackedBarModel s1 = new StackedBarModel();

            s1.addBar(new BarModel(2.3f, 0xFF123456));
            s1.addBar(new BarModel(2.f, 0xFF1EF556));
            s1.addBar(new BarModel(3.3f, 0xFF1BA4E6));

            StackedBarModel s2 = new StackedBarModel();
            s2.addBar(new BarModel(1.1f, 0xFF123456));
            s2.addBar(new BarModel(2.7f, 0xFF1EF556));
            s2.addBar(new BarModel(0.7f, 0xFF1BA4E6));

            addBar(s1);
            addBar(s2);
        }
    }

    /**
     * Should be called after new data is inserted. Will be automatically called, when the view dimensions
     * has changed.
     */
    @Override
    protected void onDataChanged() {
        calculateBarPositions(mData.size());
        super.onDataChanged();
    }

    //##############################################################################################
    // Variables
    //##############################################################################################

    /**
     * Calculates the bar boundaries based on the bar width and bar margin.
     *
     * @param _Width  Calculated bar width
     * @param _Margin Calculated bar margin
     */
    protected void calculateBounds(float _Width, float _Margin) {

        int last = 0;

        for (StackedBarModel model : mData) {
            float lastHeight = 0;

            last += _Margin / 2;

            for (BarModel barModel : model.getBars()) {
                // calculate height for the StackedBarModel part
                float height = ((barModel.getValue() * mGraphHeight) / maxStackBarValue) + lastHeight;// 高度需要比例缩放:当前总高度除以最大值
                barModel.setBarBounds(new RectF(last, lastHeight, last + _Width, height));
                lastHeight = height;
            }
            model.setLegendBounds(new RectF(last, 0, last + _Width, mLegendHeight));

            last += _Width + (_Margin / 2);
        }

        Utils.calculateLegendInformation(mData, 0, mContentRect.width(), mLegendPaint);
    }

    /**
     * Callback method for drawing the bars in the child classes.
     *
     * @param _Canvas The canvas object of the graph view.
     */
    protected void drawBars(Canvas _Canvas) {
        int index = 0;
        for (StackedBarModel model : mData) {
            float lastTop;
            float lastBottom = mGraphHeight;

            RectF bounds = model.getBounds();
            float left = bounds.left;
            float right = bounds.right;

            if (index == selectedBarIndex) {
                float strokeWidth = bounds.width() / 5;
                left -= strokeWidth;
                right += strokeWidth;
            }

            for (BarModel barModel : model.getBars()) {
                mGraphPaint.setColor(barModel.getColor());

                float height = (barModel.getBarBounds().height() * mRevealValue);
                lastTop = lastBottom - height;

                _Canvas.drawRect(
                        left,
                        lastTop,
                        right,
                        lastBottom,
                        mGraphPaint);

                lastBottom = lastTop;
            }

            index++;
        }
    }

    /**
     * Returns the list of data sets which hold the information about the legend boundaries and text.
     *
     * @return List of BaseModel data sets.
     */
    @Override
    protected List<? extends BaseModel> getLegendData() {
        return mData;
    }

    @Override
    protected List<RectF> getBarBounds() {
        ArrayList<RectF> bounds = new ArrayList<RectF>();
        for (StackedBarModel model : mData) {
            bounds.add(model.getBounds());
        }
        return bounds;
    }

    @Override
    public void setBarSelected(int barIndex) {
        this.selectedBarIndex = barIndex;
        invalidate();
    }

}
