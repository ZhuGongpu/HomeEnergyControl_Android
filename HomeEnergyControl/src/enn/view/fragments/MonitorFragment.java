package enn.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import enn.view.R;
import enn.view.entity.DeviceEntity;
import enn.view.widgets.SwitchButton;

import java.util.ArrayList;


/**
 * Created by zhugongpu on 14-9-9.
 */
public class MonitorFragment extends Fragment {
    private static final String TAG = "MonitorFragment";

    private ArrayList<DeviceEntity> devices = new ArrayList<DeviceEntity>();

    private DeviceListAdapter adapter = null;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.monitor_fragment, container, false);
        adapter = new DeviceListAdapter(inflater);
        loadData();
        ((ListView) view.findViewById(R.id.device_list)).setAdapter(adapter);
        return view;
    }

    /**
     * 加载页面数据
     */
    private void loadData() {

        //TODO 向服务器请求各标签数据

        //TODO 向服务器请求设备列表

        //dummy implementation
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
        devices.add(new DeviceEntity(""));
    }

    private class DeviceListAdapter extends BaseAdapter {

        private LayoutInflater inflater = null;

        public DeviceListAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public int getCount() {
            return devices.size();
        }

        @Override
        public Object getItem(int i) {
            return devices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            view = inflater.inflate(R.layout.device_list_item, null);
            //根据device调整ui

            //设置设备图标
            ((ImageView) view.findViewById(R.id.device_icon)).setImageResource(devices.get(i).getDeviceIconResourceID());
            //获取设备名
            String deviceName = devices.get(i).getDeviceName();
            if (deviceName == null) {//设备名不存在
                //根据设备类型获取设备默认名
                deviceName = getText(DeviceEntity.getDefaultDeviceNameResourceID(devices.get(i).getDeviceType())).toString();
                devices.get(i).setDeviceName(deviceName);//更新设备名
            }

            //设置设备名称
            ((TextView) view.findViewById(R.id.device_name)).setText(deviceName);
            //设置设备能耗数据
            ((TextView) view.findViewById(R.id.device_energy_consumption_real_time)).setText(devices
                    .get(i).getEnergyConsumptionRealTime());
            //设置设备状态
            SwitchButton switchButton = (SwitchButton) view.findViewById(R.id.device_status_switch);
            switchButton.setChecked(devices.get(i).isInUse());
            final View finalView = view;
            switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Log.e(TAG, b + " at " + i);
                    devices.get(i).setInUse(b);
                    if (b) {
                        finalView.findViewById(R.id.device_energy_consumption_layout).setVisibility(View.VISIBLE);
                        //设置设备能耗数据
                        ((TextView) finalView.findViewById(R.id.device_energy_consumption_real_time)).setText(devices
                                .get(i).getEnergyConsumptionRealTime());
                    } else {
                        finalView.findViewById(R.id.device_energy_consumption_layout).setVisibility(View.INVISIBLE);
                    }
                }
            });


            return view;
        }
    }
}

