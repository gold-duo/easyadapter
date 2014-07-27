package com.droidwolf.easyadapter;

import android.database.Cursor;

/**
 * 抽象游标视图(ViewItem)类,简化ViewItem接口实现，用于{@link CursorViewItemFactory}
 */
public abstract class AbsCursorViewItem implements ViewItem<Cursor> {
	private int mPosition;
	public boolean isEnabled(Cursor c, int position) {
		return true;
	}
	
	@Deprecated
	public void setPosition(int pos){
		mPosition=pos;
	 }
	@Deprecated
	public int getPosition(){
		return mPosition;
	}
}
