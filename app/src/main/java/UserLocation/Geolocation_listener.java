package UserLocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class Geolocation_listener extends AppCompatActivity implements LocationListener
{
    private static int COARSE_LOCATION;
    private static int FINE_LOCATION;

    private static Location location_info = null;

    public static void SetUpLocationListener(Context context)
    {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            Log.d("ACCESS STATUS: ", "TRUE");

            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            LocationListener locationListener = new Geolocation_listener();
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 100, locationListener);

            location_info = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
        else permissions(context);
    }

    @Override
    public void onLocationChanged(Location location) { location_info = location; }

    public static String get_location_XY()
    {
        Log.d("GET LOCATION X: ", String.valueOf(location_info.getLatitude() + "," + location_info.getLongitude()));
        return String.valueOf(location_info.getLatitude() + "," + location_info.getLongitude());
    }

    @Override
    public void onProviderDisabled(String provider) {}
    @Override
    public void onProviderEnabled(String provider) {}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}


    public static void permissions(Context context)
    {
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION);
        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, COARSE_LOCATION);
    }
}