package cn.loveapple.client.android.gaia;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class GaiaActivity extends MapActivity {
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    /**
     * 
     * {@inheritDoc}
     */
	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
}