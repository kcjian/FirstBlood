package com.liuxd.firstblood.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.ui.adapter.CommonPagerAdapter;
import com.liuxd.firstblood.ui.base.BaseFragment;
import com.liuxd.firstblood.ui.idcard.IdCardLeakSearchFragment;
import com.liuxd.firstblood.ui.idcard.IdCardLostSearchFragment;
import com.liuxd.firstblood.ui.idcard.IdCardNoSearchFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

/**
 * Created by Liuxd on 2016/11/21 16:47.
 */

public class IdCardFragment extends BaseFragment {
    @BindView(R.id.tab_cardNo)
    TabLayout mTabCardNo;
    @BindView(R.id.vp_cardNo)
    ViewPager mVpCardNo;
    @BindArray(R.array.array_idCard)
    String[] mTitles;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_idcard;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        CommonPagerAdapter adapter = new CommonPagerAdapter(getChildFragmentManager(), getFragments()
                , mTitles);
        mVpCardNo.setOffscreenPageLimit(1);
        mVpCardNo.setAdapter(adapter);
        mTabCardNo.setupWithViewPager(mVpCardNo);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IdCardNoSearchFragment());
        fragments.add(new IdCardLeakSearchFragment());
        fragments.add(new IdCardLostSearchFragment());
        return fragments;
    }
}
