package home.rxjavatest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import home.rxjavatest.rest.PBBransches;

public class BranchActivity extends Activity {
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
    String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_branch);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        PBBransches pbBransch = (PBBransches)getIntent().getParcelableExtra("Branch");
        setText(pbBransch);
        address = pbBransch.getName();
        Log.d("TAG1",address);
    }

    public void setText (PBBransches bransch){
       address = bransch.getAddress();
        tv_address.setText(address);
//        tv_city.setText(bransch.getCity());
//        tv_country.setText(bransch.getCountry());
//        tv_email.setText(bransch.getEmail());
//        tv_id.setText(String.valueOf(bransch.getId()));
//        tv_index.setText(String.valueOf(bransch.getIndex()));
//        tv_name.setText(bransch.getName());
//        tv_phone.setText(bransch.getPhone());
//        tv_state.setText(bransch.getState());
    }
}
