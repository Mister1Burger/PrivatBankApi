package home.rxjavatest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.rxjavatest.rest.Manager;
import home.rxjavatest.rest.PBBransches;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.et_city)
    EditText et_city;
    @BindView(R2.id.et_address)
    EditText et_address;
    @BindView(R2.id.button_search)
    Button search_btn;
    @BindView(R2.id.listV)
    RecyclerView grid_list;
    Gson gson;
    AdapterList adapter;
    PBBransches bransch;

    public PBBransches getBransch() {
        return bransch;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        grid_list.setLayoutManager(llm);
        adapter = new AdapterList(new ArrayList<PBBransches>(), bransch -> { this.bransch = bransch;
            Intent intent = new Intent(MainActivity.this, BranchActivity.class);
            intent.putExtra("Branch",bransch.toString() );
            startActivity(intent);
            Log.d("TAG",bransch.toString());
        });
        grid_list.setAdapter(adapter);

      // PBBransches response = gson.fromJson("", PBBransches.class);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("TAG", "onNewIntent");
    }


    @Override
    protected void onDestroy() {
        if (NotificationService.isStarted) {
            stopService(new Intent(this, NotificationService.class));
        }
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        search_btn.setOnClickListener(view -> {
            String address = et_address.getText().toString();
            if (address.length() == 0) {
                Toast toast = Toast.makeText(getBaseContext(),
                        "Enter the Address", Toast.LENGTH_SHORT);
                toast.show();
            } else {

                et_address.setText(null);

            }
            String city = et_city.getText().toString();
            if (city.length() == 0) {
                Toast toast = Toast.makeText(getBaseContext(),
                        "Enter the City", Toast.LENGTH_SHORT);
                toast.show();
            } else {

                et_city.setText(null);

            }
            findBranches(address, city);


        });

    }

    public void findBranches(String address, String city) {
        Manager manager = new Manager();
        manager.getBranches().pboffice(address, city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(pbBransches1 -> adapter.clear())
                .flatMap(Observable::fromIterable)
                .doOnNext(bransches -> adapter.addBranch(bransches))
                .toList()
                .toObservable()
                .subscribe(pbBransches -> {
                            Log.d("TAG", pbBransches.toString());
                        },
                        throwable -> {
                        }
                );
    }


}
