package enn.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import enn.view.R;
import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.communication.IOnBarClickedListener;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;


/**
 * Created by zhugongpu on 14-9-9.
 */
public class BillFragment extends Fragment {
    private static final String TAG = "BillFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.bill_fragment, container, false);

        demo(view);

        return view;
    }

    private void demo(View view) {
        StackedBarChart mStackedBarChart = (StackedBarChart) view.findViewById(R.id.stackedbarchart);

        StackedBarModel s1 = new StackedBarModel("12.4");

        s1.addBar(new BarModel(1f, 0xFF63CBB0));
        s1.addBar(new BarModel(1f, 0xFF56B7F1));
        s1.addBar(new BarModel(1f, 0xFFCDA67F));

        StackedBarModel s2 = new StackedBarModel("13.4");
        s2.addBar(new BarModel(1f, 0xFF63CBB0));
        s2.addBar(new BarModel(2f, 0xFF56B7F1));
        s2.addBar(new BarModel(3f, 0xFFCDA67F));

        StackedBarModel s3 = new StackedBarModel("14.4");

        s3.addBar(new BarModel(2f, 0xFF63CBB0));
        s3.addBar(new BarModel(3f, 0xFF56B7F1));
        s3.addBar(new BarModel(4f, 0xFFCDA67F));

        StackedBarModel s4 = new StackedBarModel("15.4");
        s4.addBar(new BarModel(1f, 0xFF63CBB0));
        s4.addBar(new BarModel(2f, 0xFF56B7F1));
        s4.addBar(new BarModel(3f, 0xFFCDA67F));

        mStackedBarChart.addBar(s1);
        mStackedBarChart.addBar(s2);
        mStackedBarChart.addBar(s3);
        mStackedBarChart.addBar(s4);

        mStackedBarChart.startAnimation();

        mStackedBarChart.setOnBarClickedListener(new IOnBarClickedListener() {
            @Override
            public void onBarClicked(int _Position) {
                Log.e(TAG, "onBarClicked : " + _Position);
            }
        });
    }
}
