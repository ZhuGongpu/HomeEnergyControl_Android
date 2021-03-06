package enn.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import enn.view.fragments.BillFragment;
import enn.view.fragments.HomeFragment;
import enn.view.fragments.MonitorFragment;
import enn.view.fragments.QueryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhugongpu on 14-9-3.
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity";

    private ViewPager viewPager = null;
    private FragmentPagerAdapter adapter = null;

    /**
     * Fragments
     */
    private List<Fragment> fragments = new ArrayList<Fragment>(4);

    private ArrayList<Integer> tabs = new ArrayList<Integer>(4);
    private ArrayList<Integer> tabTexts = new ArrayList<Integer>(4);

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        init();
    }

    /**
     * onCreate时初始化
     */
    private void init() {


        this.fragments.add(new HomeFragment());
        this.fragments.add(new MonitorFragment());
        this.fragments.add(new BillFragment());
        this.fragments.add(new QueryFragment());

        this.tabs.add(R.id.tab_home);
        this.tabs.add(R.id.tab_monitor);
        this.tabs.add(R.id.tab_bill);
        this.tabs.add(R.id.tab_query);

        this.tabTexts.add(R.id.home_textView);
        this.tabTexts.add(R.id.monitor_textView);
        this.tabTexts.add(R.id.bill_textView);
        this.tabTexts.add(R.id.query_textView);


        this.viewPager = (ViewPager) findViewById(R.id.viewpager);


        this.adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        viewPager.setAdapter(this.adapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        onTabSelected(0);
    }

    /**
     * 点击tab时调用
     *
     * @param v
     */
    public void onTabClick(View v) {
        onTabSelected(tabs.indexOf(v.getId()));
    }

    /**
     * 当页面被选中时调用
     *
     * @param tabindex
     */
    private void onTabSelected(int tabindex) {
        for (int i = 0; i < tabs.size(); i++) {
            if (i != tabindex) {
                findViewById(tabs.get(i)).setSelected(false);
                ((TextView) findViewById(tabTexts.get(i))).setTextColor(getResources().getColor(R.color.text_color_default));
            }
        }
        if (tabindex >= 0 && tabindex < tabs.size()) {
            findViewById(tabs.get(tabindex)).setSelected(true);
            ((TextView) findViewById(tabTexts.get(tabindex))).setTextColor(getResources().getColor(R.color.blue));
        }
//        this.viewPager.setCurrentItem(index, true);//平滑过渡
        this.viewPager.setCurrentItem(tabindex);
    }

}
