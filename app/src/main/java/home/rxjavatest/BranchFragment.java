package home.rxjavatest;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import home.rxjavatest.rest.PBBransches;

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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branch,container,false);
        ButterKnife.bind(this,view);
        return view ;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setText(((MainActivity)getActivity()).getBransch());
        Log.d("TAG1",((MainActivity)getActivity()).getBransch().toString());



    }
    public void setText (PBBransches bransch){
        tv_address.setText(bransch.getAddress());
        tv_city.setText(bransch.getCity());
        tv_country.setText(bransch.getCountry());
        tv_email.setText(bransch.getEmail());
        tv_id.setText(String.valueOf(bransch.getId()));
        tv_index.setText(String.valueOf(bransch.getIndex()));
        tv_name.setText(bransch.getName());
        tv_phone.setText(bransch.getPhone());
        tv_state.setText(bransch.getState());
    }


}
