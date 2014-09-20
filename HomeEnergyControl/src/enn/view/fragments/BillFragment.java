package enn.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import enn.view.R;
import org.eazegraph.lib.charts.StackedBarChart;
import org.eazegraph.lib.communication.IOnBarClickedListener;
import org.eazegraph.lib.models.BarModel;
import org.eazegraph.lib.models.StackedBarModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by zhugongpu on 14-9-9.
 */
public class BillFragment extends Fragment {
    private static final String TAG = "BillFragment";

    private View view = null;

    private List<StackedBarModel> models = new ArrayList<StackedBarModel>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        view = inflater.inflate(R.layout.bill_fragment, container, false);

        demo(view);

        return view;
    }

    private void demo(View view) {
        StackedBarChart mStackedBarChart = (StackedBarChart) view.findViewById(R.id.stackedbarchart);

        models.add(generateStackedBarModel("01月"));
        models.add(generateStackedBarModel("02月"));
        models.add(generateStackedBarModel("03月"));
        models.add(generateStackedBarModel("04月"));
        models.add(generateStackedBarModel("05月"));
        models.add(generateStackedBarModel("06月"));

        mStackedBarChart.addBarList(models);

        setBarSelected(models.size() - 1);
        setYear(Calendar.getInstance().get(Calendar.YEAR) + "年");

        mStackedBarChart.startAnimation();
        mStackedBarChart.setOnBarClickedListener(new IOnBarClickedListener() {
            @Override
            public void onBarClicked(int _Position) {
                Log.e(TAG, "onBarClicked : " + _Position);
                //响应点击事件 : 切换下部的标签内容
                setBarSelected(_Position);
            }
        });
    }


    /**
     * 根据选中的StackBar设置各种标签
     *
     * @param position
     */
    private void setBarSelected(int position) {
        StackedBarModel model = models.get(position);
        List<BarModel> barModels = model.getBars();
        //设置月份
        setMonth(model.getLegendLabel());
        //设置总费用
        setSumCost(model.getSumValue() + "");

        //TODO 向服务器请求或本地计算usage

        //设置水相关的数据
        setWaterFee(barModels.get(0).getValue() + "");

        //设置热相关的数据
        setHeatFee(barModels.get(1).getValue() + "");

        //设置电相关的数据
        setElectricityFee(barModels.get(2).getValue() + "");

        //设置气相关的数据
        setGasFee(barModels.get(3).getValue() + "");

    }


    /**
     * 生成StackedBarModel
     * dummy implementation
     *
     * @param name
     * @return
     */
    private StackedBarModel generateStackedBarModel(String name) {
        StackedBarModel model = new StackedBarModel(name);

        model.addBar(new BarModel(1f, getResources().getColor(R.color.green)));
        model.addBar(new BarModel(1f, getResources().getColor(R.color.red)));
        model.addBar(new BarModel(1f, getResources().getColor(R.color.blue)));
        model.addBar(new BarModel(1f, getResources().getColor(R.color.yellow)));

        return model;
    }

    private void setYear(String year) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_year))).setText(year);
        }
    }

    private void setMonth(String month) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_month))).setText(month);
        }
    }

    private void setSumCost(String sumCost) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_sum_cost))).setText(sumCost);
        }
    }

    private void setGasUsage(String gasUsage) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_gas_usage))).setText(gasUsage);
        }
    }

    private void setGasFee(String gasFee) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_gas_money))).setText(gasFee);
        }
    }

    private void setElectricityUsage(String electricityUsage) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_electricity_usage))).setText(electricityUsage);
        }
    }

    private void setElectricityFee(String electricityFee) {
        if (this.view != null) {
            ((TextView) (this.view.findViewById(R.id.bill_fragment_electricity_money))).setText(electricityFee);
        }
    }

    private void setHeatUsage(String heatUsage) {
        if (this.view != null)
            ((TextView) (this.view.findViewById(R.id.bill_fragment_heat_usage))).setText(heatUsage);

    }

    private void setHeatFee(String heatFee) {
        if (this.view != null)
            ((TextView) (this.view.findViewById(R.id.bill_fragment_heat_money))).setText(heatFee);
    }

    private void setWaterUsage(String waterUsage) {
        if (this.view != null)
            ((TextView) (this.view.findViewById(R.id.bill_fragment_water_usage))).setText(waterUsage);
    }

    private void setWaterFee(String waterFee) {
        if (this.view != null)
            ((TextView) (this.view.findViewById(R.id.bill_fragment_water_money))).setText(waterFee);
    }

}
