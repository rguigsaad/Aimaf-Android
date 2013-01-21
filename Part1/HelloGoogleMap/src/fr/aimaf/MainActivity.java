package fr.aimaf;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MainActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    //MAP Creation
			setContentView(R.layout.activity_main);
			MapView mapView = (MapView) findViewById(R.id.mapview);
		    mapView.setBuiltInZoomControls(true);
		    mapView.setClickable(true);
	        mapView.setEnabled(true);
	   
	   // Add Marker
		    List<Overlay> mapOverlays = mapView.getOverlays();
		    Drawable drawable = this.getResources().getDrawable(R.drawable.marker);
		    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable, this);
		    GeoPoint point = new GeoPoint(48856628,2370228);
		    OverlayItem overlayitem = new OverlayItem(point, "Aimaf Formation Android", "Salut les amis! nous sommes ici :)");
		    itemizedoverlay.addOverlay(overlayitem);
		    mapOverlays.add(itemizedoverlay);
		   
	   //Add controller and animate to GeoPoint
	        MapController mc = mapView.getController();
	        MyLocationOverlay myLoc = new MyLocationOverlay(this, mapView);
	        myLoc.enableCompass();
	        mapView.getOverlays().add(myLoc);
	        mc.animateTo(point);
        
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
