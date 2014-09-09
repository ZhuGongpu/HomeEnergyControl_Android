package enn.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import enn.view.R;


/**
 * Created by zhugongpu on 14-9-9.
 */
public class MonitorFragment extends Fragment {
    private static final String TAG = "MonitorFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        return inflater.inflate(R.layout.monitor_fragment, container, false);
    }
}
