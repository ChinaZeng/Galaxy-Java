package com.brox.livewallpaper.space;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class AndroidLauncher extends Activity {

	Intent intent;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
			intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
					new ComponentName(getPackageName(), getPackageName() + ".LiveWallpaper"));
			startActivityForResult(intent, 666);
		} catch (android.content.ActivityNotFoundException e3) {
            System.out.println("All failed 3! "+e3.getMessage());
			try {
				intent = new Intent(WallpaperManager.ACTION_LIVE_WALLPAPER_CHOOSER);
				startActivityForResult(intent, 667);
			} catch (android.content.ActivityNotFoundException e2) {
				try {
					intent = new Intent();
					intent.setAction("com.bn.nook.CHANGE_WALLPAPER");
					startActivityForResult(intent, 668);
					System.out.println("All failed 2! "+e2.getMessage());
				} catch (android.content.ActivityNotFoundException e) {
					System.out.println("All failed! "+e.getMessage());
				}
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		finish();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}
}
