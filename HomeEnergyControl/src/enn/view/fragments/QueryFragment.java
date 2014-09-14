package enn.view.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import enn.view.R;
import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;


/**
 * Created by zhugongpu on 14-9-9.
 */
public class QueryFragment extends Fragment {
    private static final String TAG = "QueryFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.query_fragment, container, false);
        demo(view);
        return view;
    }

    private void demo(View view) {
        ValueLineChart mCubicValueLineChart = (ValueLineChart) view.findViewById(R.id.linechart);

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(0xFF56B7F1);

        series.addPoint(new ValueLinePoint("Jan", 2.4f));
        series.addPoint(new ValueLinePoint("Feb", 3.4f));
        series.addPoint(new ValueLinePoint("March", .4f));
        series.addPoint(new ValueLinePoint("Apr", 1.2f));
        series.addPoint(new ValueLinePoint("May", 2.6f));
        series.addPoint(new ValueLinePoint("Jun", 1.0f));
        series.addPoint(new ValueLinePoint("Jul", 3.5f));
        series.addPoint(new ValueLinePoint("Aug", 2.4f));
        series.addPoint(new ValueLinePoint("Sep", 2.4f));
        series.addPoint(new ValueLinePoint("Oct", 3.4f));
        series.addPoint(new ValueLinePoint("Nov", .4f));
        series.addPoint(new ValueLinePoint("Dec", 1.3f));

        series.addPoint(new ValueLinePoint("" + 0, 0f));
        series.addPoint(new ValueLinePoint("" + 1, 1f));
        series.addPoint(new ValueLinePoint("" + 2, 2f));
        series.addPoint(new ValueLinePoint("" + 3, 3f));
        series.addPoint(new ValueLinePoint("" + 4, 4f));
        series.addPoint(new ValueLinePoint("" + 5, 5f));
        series.addPoint(new ValueLinePoint("" + 6, 6f));
        series.addPoint(new ValueLinePoint("" + 7, 7f));
        series.addPoint(new ValueLinePoint("" + 8, 8f));
        series.addPoint(new ValueLinePoint("" + 9, 9f));

        mCubicValueLineChart.addSeries(series);

        mCubicValueLineChart.startAnimation();
    }
}
