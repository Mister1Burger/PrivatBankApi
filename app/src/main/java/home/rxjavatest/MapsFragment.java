package home.rxjavatest;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapsFragment extends Fragment {

    private GoogleMap googleMap;

    private MainActivity activity;

    private Marker myMarkerMap;

    public Context context;

    private CurrentLocationRX currentLocationRX;

    private List<Polyline> polylines = new ArrayList<>();
    private static final int[] COLORS = new int[]{R.color.colorAccent,
            R.color.colorPrimary,
            R.color.colorPrimaryDark,
            android.R.color.holo_purple,
            android.R.color.holo_orange_light};

    float zoom = 14;

    @BindView(R2.id.mapView)
    MapView mapView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mapFragmentView = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, mapFragmentView);

        mapView.onCreate(savedInstanceState);
        mapView.onLowMemory();
        MapsInitializer.initialize(context);

        return mapFragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();

        currentLocationRX = new CurrentLocationRX(activity, location -> plotMarker1(location.getLatitude(), location.getLongitude()));

    }

    private void initGoogleMap() {

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings myUiSettings = googleMap.getUiSettings();
        myUiSettings.setZoomControlsEnabled(true);

        googleMap.setOnCameraMoveListener(() -> zoom = googleMap.getCameraPosition().zoom);

//        googleMap.setOnMapClickListener(latLng -> plotMarker1(latLng.latitude, latLng.longitude));

    }


    public void plotMarker1(double lat, double lon) {

        if (lat != 0 && lon != 0) {

            if (myMarkerMap != null)
                myMarkerMap.remove();

            MarkerOptions usMarker = new MarkerOptions().position(
                    new LatLng(lat, lon)).title("I").anchor(Float.parseFloat("0.5"), Float.parseFloat("0.5"));
            usMarker.icon(activity.getBitmapDescriptor(R.drawable.marker_1));
            myMarkerMap = googleMap.addMarker(usMarker);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,
                    lon), zoom));
          //  plotMarker2(lat, lon);
        }
    }

    public void plotMarker2(double lat, double lon) {

        LatLng latLng = new LatLng(48.4506, 32.0505);
        if (latLng.latitude != 0 && latLng.longitude != 0) {
            MarkerOptions markerOption = new MarkerOptions().position(latLng).title("name");
            markerOption.icon(activity.getBitmapDescriptor(R.drawable.marker_2));
            googleMap.addMarker(markerOption);
            track(latLng, lat, lon);
        }

    }

    private void track(LatLng end, double lat, double lon) {

        Routing routing = new Routing.Builder()
                .travelMode(AbstractRouting.TravelMode.DRIVING)
                .withListener(new RoutingListener() {
                    @Override
                    public void onRoutingFailure(RouteException e) {
                    }

                    @Override
                    public void onRoutingStart() {

                    }

                    @Override
                    public void onRoutingSuccess(ArrayList<Route> route, int i) {

                        try {

                            if (polylines.size() > 0) {
                                for (Polyline poly : polylines) {
                                    poly.remove();
                                }
                            }

                            polylines = new ArrayList<>();
                            //add route(s) to the map.
                            for (i = 0; i < route.size(); i++) {

                                //In case of more than 5 alternative routes
                                int colorIndex = i % COLORS.length;

                                PolylineOptions polyOptions = new PolylineOptions();
                                polyOptions.color(ContextCompat.getColor(getActivity(), COLORS[colorIndex]));
                                polyOptions.width(10 + i * 3);
                                polyOptions.addAll(route.get(i).getPoints());
                                Polyline polyline = googleMap.addPolyline(polyOptions);
                                polylines.add(polyline);

                                //                               Toast.makeText(activity, "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Log.e("TAG", "route " + e.toString());
                        }

                    }

                    @Override
                    public void onRoutingCancelled() {

                    }
                })
                .alternativeRoutes(true)
                .waypoints(new LatLng(lat,
                        lon), end)
                .build();
        routing.execute();

    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mapView.onResume();
            mapView.getMapAsync(gMap -> {
                googleMap = gMap;
                initGoogleMap();
            });
        } catch (NullPointerException e) {
            Log.e("TAG", "Map " + e.toString());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            mapView.onPause();
        } catch (Exception e) {
            Log.e("TAG", "Map " + e.toString());
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
        System.gc();
    }

    @Override
    public void onDestroy() {
        currentLocationRX.removeUpdateLocation();
        super.onDestroy();
    }
}
