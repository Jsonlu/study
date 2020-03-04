package top.jsonlu.app.frg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import top.jsonlu.app.R;
import top.jsonlu.app.m.ResultData;
import top.jsonlu.app.p.IPayPresent;
import top.jsonlu.app.p.impl.PayPresentImpl;
import top.jsonlu.app.v.IBuyView;
import top.jsonlu.baselibrary.fetch.biz.d.GoodsData;
import top.jsonlu.baselibrary.fetch.biz.d.TypeItem;

/**
 * Author:JsonLu
 * DateTime:2020/1/8 14:25
 * Email:jsonlu@qq.com
 * Desc:
 **/
public class BuyFrg extends BaseDialogFragment implements IBuyView {

    private IPayPresent present;
    private TypeItem data;
    private TextView tv_good_name, tv_good_perice, tv_good_coupon, tv_pay_amount, tv_state;

    public static BuyFrg newInstance(TypeItem item) {
        Bundle args = new Bundle();
        args.putSerializable("data", item);
        BuyFrg fragment = new BuyFrg();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        tv_good_name = view.findViewById(R.id.tv_good_name);
        tv_good_perice = view.findViewById(R.id.tv_good_perice);
        tv_good_coupon = view.findViewById(R.id.tv_good_coupon);
        tv_pay_amount = view.findViewById(R.id.tv_pay_amount);
        tv_state = view.findViewById(R.id.tv_state);
        initData();
    }

    private void initData() {
        data = gett("data");
        present = new PayPresentImpl(getActivity(), this);
        present.getOrder(data.getId() + "");
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_order_info;
    }

    @Override
    public void close() {
        dismiss();
    }

    @Override
    public void showGoodInfo(GoodsData data) {
        tv_good_name.setText(String.format(getString(R.string.good_name), this.data.getName()));
        tv_good_perice.setText(String.format(getString(R.string.good_perice), data.getPerice()));
        if (!TextUtils.isEmpty(data.getCouponId())) {
            tv_good_coupon.setText(String.format(getString(R.string.good_coupon), data.getCouponAmount()));
            tv_pay_amount.setText(String.format(getString(R.string.pay_amount), data.getCouponAmount()));
        }
    }

    @Override
    public void showStates(int type) {
        if (type == 0) {
            tv_state.setText(R.string.take_order);
        } else if (type == 1) {
            tv_state.setText(R.string.take_pay);
        } else if (type == 2) {
        }
    }

    @Override
    public void showResult(ResultData o) {
        System.out.println(JSON.toJSONString(o));
    }
}
