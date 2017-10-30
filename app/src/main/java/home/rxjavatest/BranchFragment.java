package home.rxjavatest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.rxjavatest.rest.PBBransches;

/**
 * Created by Burge on 27.10.2017.
 */

public class BranchFragment extends Fragment {
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_country)
    TextView tv_country;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_id)
    TextView tv_id;
    @BindView(R.id.tv_index)
    TextView tv_index;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_state)
    TextView tv_state;
    BranchListner listner;


    public BranchFragment() {
    }

    public BranchFragment(BranchListner listner) {
        this.listner = listner;
        ;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ButterKnife.bind(getActivity());
        return inflater.inflate(R.layout.fragment_branch,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_address.setText(listner.getPbBransch().getAddress());
        tv_city.setText(listner.getPbBransch().getCity());
        tv_country.setText(listner.getPbBransch().getCountry());
        tv_email.setText(listner.getPbBransch().getEmail());
        tv_id.setText(String.valueOf(listner.getPbBransch().getId()));
        tv_index.setText(String.valueOf(listner.getPbBransch().getIndex()));
        tv_name.setText(listner.getPbBransch().getName());
        tv_phone.setText(listner.getPbBransch().getPhone());
        tv_state.setText(listner.getPbBransch().getState());



    }


}
