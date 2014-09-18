package enn.view.entity;

import enn.view.R;

/**
 * Created by zhugongpu on 14-9-17.
 */

public class DeviceEntity {

    public static final DeviceType[] deviceTypes = {
            DeviceType.UNKNOWN,
            DeviceType.AC,
            DeviceType.TV,
            DeviceType.FRIDGE,
            DeviceType.WATER_HEATER,
            DeviceType.STEREO,
            DeviceType.WASHING_MACHINE
    };
    public static final int[] deviceNameIDs = {
            R.string.unknown_device,
            R.string.ac,
            R.string.tv,
            R.string.fridge,
            R.string.water_heater,
            R.string.stereo,
            R.string.water_heater};
    public static final int[] deviceIconIDs = {
            R.drawable.unknown_device,
            R.drawable.ac,
            R.drawable.tv,
            R.drawable.fridge,
            R.drawable.water_heater,
            R.drawable.stereo,
            R.drawable.water_heater};

    private static final String TAG = "DeviceEntity";
    private DeviceType deviceType = DeviceType.UNKNOWN;
    private String deviceName = null;
    private boolean isInUse = false;
    private String deviceID = null;
    private String energy_consumption_real_time = "0";

    public DeviceEntity(String deviceID) {
        this.deviceID = deviceID;
        //TODO 设置device type
    }

    /**
     * 根据设备类型获取设备默认名称的resource id
     *
     * @param deviceType
     * @return
     */
    public static int getDefaultDeviceNameResourceID(DeviceType deviceType) {
        int index = 0;
        while (deviceTypes[index] != deviceType) {
            index++;
        }
        return deviceNameIDs[index];
    }

    /**
     * 向服务器请求设备实时能耗数据
     *
     * @return
     */
    public String getEnergyConsumptionRealTime() {
        return this.energy_consumption_real_time;//TODO 向服务器请求实时数据
    }

    /**
     * 根据device id 获取 device name
     *
     * @return device name
     */
    public String getDeviceName() {
        return deviceName;//TODO
    }

    public void setDeviceName(String name) {
        this.deviceName = name;
    }

    /**
     * 根据 device id 获取 device type
     *
     * @return device type
     */
    public DeviceType getDeviceType() {
        return deviceType;
    }

    public int getDeviceIconResourceID() {
        int index = 0;
        while (deviceTypes[index] != deviceType) {
            index++;
        }

        return deviceIconIDs[index];
    }

    /**
     * 获取设备状态
     *
     * @return 是否正在使用
     */
    public boolean isInUse() {
        return isInUse;
    }

    /**
     * 设置设备状态
     *
     * @param inUse 是否正在使用
     */
    public void setInUse(boolean inUse) {
        this.isInUse = inUse;
    }

    public static enum DeviceType {
        UNKNOWN, AC, TV, FRIDGE, WATER_HEATER, STEREO, WASHING_MACHINE
    }


}
