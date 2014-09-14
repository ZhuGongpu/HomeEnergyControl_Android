package enn.view.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import enn.view.R;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.communication.IOnItemFocusChangedListener;
import org.eazegraph.lib.models.PieModel;

/**
 * Created by zhugongpu on 14-9-9.
 */
public class HomeFragment extends Fragment {

    private PieChart pieChart = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        this.pieChart = (PieChart) view.findViewById(R.id.piechart);
        loadData();
        this.pieChart.startAnimation();
        return view;
    }

    private void loadData() {
        //TODO load data
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

    private void setGasUsage(String gas_usage) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_gas_usage)).setText(gas_usage);
    }

    private void setGasMoney(String gas_money) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_gas_money)).setText(gas_money);
    }

    private void setElectricityUsage(String electricity_usage) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_electricity_usage)).setText(electricity_usage);
    }

    private void setElecetricityMoney(String elecetricity_money) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_electricity_money)).setText(elecetricity_money);
    }

    private void setHeatUsage(String heat_usage) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_heat_usage)).setText(heat_usage);
    }

    private void setHeatMoney(String heat_money) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_heat_money)).setText(heat_money);
    }

    private void setWaterUsage(String water_usage) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_water_usage)).setText(water_usage);
    }

    private void setWaterMoney(String water_money) {
        ((TextView) getActivity().findViewById(R.id.home_fragment_water_money)).setText(water_money);
    }

    private void setCurrentMonthMoney(String money) {
        this.pieChart.setUpperLabelText(money);
    }

    private void setLastMonthMoney(String money) {
        this.pieChart.setLowerLabelValue(money);
    }
}
