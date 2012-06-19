package br.unicamp.busfinder;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class ListPoints extends ItemizedOverlay<PItem> {

	private ArrayList<PItem> pinpoints = new ArrayList<PItem>();
	protected Context context;
	protected static final String TAG = "PointsList";

	public ListPoints(Drawable m, Context context) {
		super(boundCenter(m));
		this.populate();
		this.context = context;
	}

	@Override
	protected PItem createItem(int i) {
		return getPinpoints().get(i);

	}

	@Override
	public int size() {
		return getPinpoints().size();
	}

	/*
	 * public void insertPinpoint(OverlayItem item) { getPinpoints().add(item);
	 * this.populate();
	 */
	public void insertPinpoint(PItem item) {
		if (!getPinpoints().contains(item)) {
			getPinpoints().add(item);
			setLastFocusedIndex(-1);
			this.populate();
		}

	}
	
	public void clear(){
		this.pinpoints.clear();
	}

	public void removePinpoint(PItem item) {
		// getBlacklist().add(index);

		pinpoints.remove(item);
		this.setLastFocusedIndex(-1);
		this.populate();

	}

	public ArrayList<PItem> getPinpoints() {
		return pinpoints;
	}

	public void setPinpoints(ArrayList<PItem> npinpoints) {
		this.pinpoints.clear();
		for (PItem p : npinpoints) {
			this.insertPinpoint(p);
		}

	}

}
