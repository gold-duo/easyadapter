package com.droidwolf.easyadapter;

import android.database.Cursor;

/**
 * 游标视图(ViewItem)创建工厂ViewItem接口，用于EasyCusorAdapter
 */
public interface CursorViewItemFactory {
	/**
	 * 创建一个新的ViewItem
	 * @param c 游标
	 * @param position 当前游标位置
	 * @return ViewItem
	 */
	ViewItem<Cursor> newViewItem(Cursor c, int position);
	
	/**
	 * 参考{@link android.widget.Adapter} getItemViewType函数
	 * @param data 游标
	 * @param position 当前游标位置
	 * @return
	 */
	int getItemViewType(Cursor data, int position);
	
	
	/**
	 * 参考{@link android.widget.Adapter} getViewTypeCount函数
	 * @return
	 */
	int getViewTypeCount();
}
