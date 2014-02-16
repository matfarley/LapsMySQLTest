package com.matthewfarley.lapsmysqltest;

import android.support.v4.app.Fragment;

public class LapsActivity extends SingleFragmentActivity {

	@Override
	public Fragment createFragment(){
		return new LapFragment();
	}

}
