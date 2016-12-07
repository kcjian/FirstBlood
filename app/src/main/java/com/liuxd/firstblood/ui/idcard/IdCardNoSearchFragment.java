package com.liuxd.firstblood.ui.idcard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liuxd.firstblood.R;
import com.liuxd.firstblood.entity.IdCard;
import com.liuxd.firstblood.network.ApiException;
import com.liuxd.firstblood.network.HttpResultFunction;
import com.liuxd.firstblood.network.HttpUtil;
import com.liuxd.firstblood.ui.base.BaseFragment;
import com.liuxd.firstblood.widget.view.MultiStatusView;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Liuxd on 2016/12/6 16:15.
 */

public class IdCardNoSearchFragment extends BaseFragment {
    @BindView(R.id.et_idCardNo)
    EditText mEtIdCardNo;
    @BindView(R.id.btn_search)
    Button mBtnSearch;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.multiStatusView)
    MultiStatusView mMultiStatusView;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_birthday)
    TextView mTvBirthday;

    @Override
    public int setLayoutId() {
        return R.layout.fragment_idcardnosearch;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        mMultiStatusView.setContentGoneFirst();
        mMultiStatusView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    @OnClick(R.id.btn_search)
    public void onClick() {
        search();
    }

    private void search() {
        mMultiStatusView.showLoading();
        HttpUtil.getInstance().getApiService().searchIdCardByCardNo(
                mEtIdCardNo.getText().toString().trim())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new HttpResultFunction<IdCard>())
                .subscribe(new Subscriber<IdCard>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof ApiException) {
                            mMultiStatusView.showError(e.getMessage());
                        } else {
                            mMultiStatusView.showError();
                        }
                    }

                    @Override
                    public void onNext(IdCard idCard) {
                        mMultiStatusView.showContent();
                        mTvArea.setText(idCard.getArea());
                        mTvSex.setText(idCard.getSex());
                        mTvBirthday.setText(idCard.getBirthday());
                    }
                });
    }
}
