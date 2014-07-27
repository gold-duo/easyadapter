package com.droidwolf.easyadapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;

/**
 * ÊÊÅäÆ÷¼Ì³Ð×Ô{@link android.widget.BaseAdapter}
 */
public class EasyAdapter extends BaseAdapter{
	private final SparseArray<ViewItem<?>> mViewItems;
	private final ScrollListenerImp mScrollListenerImp;
	private final ViewItemFactory mViewItemFactory;
	
	private final List<Object> mData;
	private final Context mContext;
	public EasyAdapter(Context ctx,AbsListView listview,List<Object> listData, ViewItemFactory factory) {
		mContext=ctx;
		mViewItemFactory=factory;
		listview.setOnScrollListener(mScrollListenerImp= new ScrollListenerImp());
		mData =listData==null? new ArrayList<Object>(): listData;
		mViewItems= new SparseArray<ViewItem<?>>(factory.getViewTypeCount());
	}
	
	@Override
	public int getItemViewType(int position) {
		Object data = mData.get(position);
		return mViewItemFactory.getItemViewType(data, position);
	}

	@Override
	public int getViewTypeCount() {
		return mViewItemFactory.getViewTypeCount();
	}

	@Override
	public int getCount() {
		return mData == null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData == null ? null : mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public boolean isEnabled(int position) {
		Object data = mData.get(position);
		ViewItem<Object> viewItem = getViewItem(data, position);
		return viewItem.isEnabled(data, position);
	}

	public void setAbsListViewOnScrollListener(OnScrollListener li){
		mScrollListenerImp.setOutsideOnScrollListener(li);
	}
	public OnScrollListener getAbsListViewOnScrollListener(){
		return mScrollListenerImp.getOutsideOnScrollListener();
	}
	
	public void addData(Object data){
		mData.add(data);
		notifyDataSetChanged();
	}
	public void addData(Object data,int location){
		mData.add(location, data);
		notifyDataSetChanged();
	}
	
	public void removeAllData(){
		mData.clear();
		notifyDataSetChanged();
	}
	public void removeData(Object data){
		mData.remove(data);
		notifyDataSetChanged();
	}
	public void removeData(int location){
		mData.remove(location);
		notifyDataSetChanged();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Object data = mData.get(position);
		ViewItem<Object> viewItem = getViewItem(data, position);
		viewItem.setPosition(position);
		if (convertView == null) {
			convertView=viewItem.newView(mContext, data, parent);
		}
		viewItem.bindView(convertView,mContext,data,mScrollListenerImp.getScrollState());
		return convertView;
	}
	
	@SuppressWarnings("unchecked")
	private final ViewItem<Object> getViewItem(Object data,int position){
		int vit=mViewItemFactory.getItemViewType(data, position);
		ViewItem<Object> viewItem = (ViewItem<Object>) mViewItems.get(vit);
		if(viewItem==null){
			viewItem=(ViewItem<Object>) mViewItemFactory.newViewItem(data, position);
			mViewItems.put(vit, viewItem);
		}
		return viewItem;
	}
}//end class
