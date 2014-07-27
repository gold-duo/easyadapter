package com.droidwolf.easyadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * ListView、GridView视图创建、更新接口。
 */
public interface ViewItem <T>{
	/**
	 * 创建视图,可参考{@link android.widget.CursorAdapter} newView
	 * @param context
	 * @param data
	 * @param parent
	 * @return
	 */
	 View newView(Context context, T data, ViewGroup parent);

	 /**
	  * 更新视图,可参考{@link android.widget.CursorAdapter} bindView
	  * @param convertView
	  * @param context
	  * @param data
	  * @param ScrollState 参考{@link android.widget.AbsListView.OnScrollListener} SCROLL_XX
	  */
	 void bindView(View convertView,Context context,T data ,int scrollState);

	 /**
	  * 参考{@link android.widget.ListAdapter} isEnabled
	  * @param data
	  * @param position
	  * @return
	  */
	 boolean isEnabled(T data, int position);
	 
	 /**
	  * 设置当前行在listview的position,由EasyXXAdapter调用
	  * @param pos
	  */
	 void setPosition(int pos);
	 
	 /**
	  * 获取当前Position 在listview的position
	  * @param pos
	  */
	 int getPosition();
}
