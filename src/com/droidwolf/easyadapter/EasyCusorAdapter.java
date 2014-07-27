package com.droidwolf.easyadapter;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;

/**
 * ”Œ±Í  ≈‰∆˜ºÃ≥–◊‘{@link android.widget.CursorAdapter}
 */
public class EasyCusorAdapter extends android.widget.CursorAdapter{
	private final SparseArray<ViewItem<Cursor>> mViewItems;
	private final ScrollListenerImp mScrollListenerImp;
	private final CursorViewItemFactory mCursorViewItemFactory;
	
	public EasyCusorAdapter(Context context, Cursor c,AbsListView listview,CursorViewItemFactory factory) {
		this(context, c,true,listview,factory);
	}
	
	public EasyCusorAdapter(Context context, Cursor c, boolean autoRequery,AbsListView listview,CursorViewItemFactory factory) {
		super(context, c, autoRequery);
		mCursorViewItemFactory=factory;
		listview.setOnScrollListener(mScrollListenerImp= new ScrollListenerImp());
		mViewItems= new SparseArray<ViewItem<Cursor>>(factory.getViewTypeCount());
	}

	@Override
	public int getItemViewType(int position) {
		final Cursor c = getCursor();
		final boolean flag=position== c.getPosition() ||c.moveToPosition(position);
		if (flag) {
			return mCursorViewItemFactory.getItemViewType(c, position);
		}
		return AbsListView.ITEM_VIEW_TYPE_IGNORE;
	}

	@Override
	public int getViewTypeCount() {
		return mCursorViewItemFactory.getViewTypeCount();
	}
	
	@Override
	public boolean isEnabled(int position) {
		final Cursor c = getCursor();
		final boolean flag=position== c.getPosition() ||c.moveToPosition(position);
		if(flag){
			return getViewItem(c, position).isEnabled(c, position);
		}
		return true;
	}
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		final int position=cursor.getPosition();
		final ViewItem<Cursor> viewItem = getViewItem(cursor, position);
//		viewItem.setPosition(position);
		return viewItem.newView(context, cursor,  parent);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final int position=cursor.getPosition();
		final ViewItem<Cursor> viewItem = getViewItem(cursor, position);
//		viewItem.setPosition(position);
		viewItem.bindView(view, context, cursor,mScrollListenerImp.getScrollState());
	}
	
	private final ViewItem<Cursor> getViewItem(Cursor data,int position){
		int vit=mCursorViewItemFactory.getItemViewType(data, position);
		ViewItem<Cursor> viewItem = (ViewItem<Cursor>) mViewItems.get(vit);
		if(viewItem==null){
			viewItem=(ViewItem<Cursor>) mCursorViewItemFactory.newViewItem(data, position);
			mViewItems.put(vit, viewItem);
		}
		return viewItem;
	}
	
	public void setAbsListViewOnScrollListener(OnScrollListener li){
		mScrollListenerImp.setOutsideOnScrollListener(li);
	}
	public OnScrollListener getAbsListViewOnScrollListener(){
		return mScrollListenerImp.getOutsideOnScrollListener();
	}
	
}
