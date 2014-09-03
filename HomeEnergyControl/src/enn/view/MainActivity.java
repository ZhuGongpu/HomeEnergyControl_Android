package enn.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.communication.IOnItemFocusChangedListener;
import org.eazegraph.lib.models.PieModel;

/**
 * Created by zhugongpu on 14-9-3.
 */
public class MainActivity extends Activity {

    private PieChart pieChart = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        init();
    }

    private void init() {
        this.pieChart = (PieChart) findViewById(R.id.piechart);

        loadData();
        this.pieChart.startAnimation();
    }

    private void loadData() {
        pieChart.addPieSlice(new PieModel("Gas", 15, Color.argb(255, 248, 174, 50)));
        pieChart.addPieSlice(new PieModel("Electricity", 25, Color.argb(255, 46, 153, 212)));
        pieChart.addPieSlice(new PieModel("Heat", 35, Color.argb(255, 229, 39, 42)));
        pieChart.addPieSlice(new PieModel("Water", 9, Color.argb(255, 103, 203, 205)));

        pieChart.setOnItemFocusChangedListener(new IOnItemFocusChangedListener() {
            @Override
            public void onItemFocusChanged(int _Position) {
//                Log.d("PieChart", "Position: " + _Position);
            }
        });
    }
}
