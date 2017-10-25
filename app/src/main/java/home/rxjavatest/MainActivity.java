package home.rxjavatest;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.LruCache;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import home.rxjavatest.rest.Manager;
import home.rxjavatest.rest.PBBransches;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;


public class MainActivity extends AppCompatActivity {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;

    private LruCache<String, BitmapDescriptor> cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
//        initCache();
//        isAccessPermissions();

        Manager manager = new Manager();

        manager.getPayService().pboffice("Тл", "Днепр")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pbBransches -> {Log.d("TAG", pbBransches.toString());
                        },
                        throwable -> {
                        }
                );

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        PBBransches response = gson.fromJson("", PBBransches.class);
    }

    public void isAccessPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PERMISSION_GRANTED) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_main, new MapsFragment())
                    .commit();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED)
            isAccessPermissions();
        else
            Toast.makeText(this, "not permission", Toast.LENGTH_LONG).show();

    }


    private void initCache() {
        //Use 1/8 of available memory
        cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory() / 1024 / 8));
    }

    public BitmapDescriptor getBitmapDescriptor(int id) {
        BitmapDescriptor result = cache.get(String.valueOf(id));
        if (result == null) {
            result = BitmapDescriptorFactory.fromResource(id);
            cache.put(String.valueOf(id), result);
        }

        return result;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
