package com.droidwolf.easyadapter;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

/*public*/final class ScrollListenerImp implements OnScrollListener {
	private int mScrollState = OnScrollListener.SCROLL_STATE_IDLE;
	private OnScrollListener mOutsideOnScrollListener;
	
	public int getScrollState() {
		return mScrollState;
	}
	
	public void setOutsideOnScrollListener(OnScrollListener li){
		mOutsideOnScrollListener=li;
	}
	
	public OnScrollListener getOutsideOnScrollListener(){
		return mOutsideOnScrollListener;
	}
	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& (mScrollState == OnScrollListener.SCROLL_STATE_FLING || mScrollState == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)) {
			mScrollState = scrollState;
			updateVisibleItem(view);
		} else {
			mScrollState = scrollState;
		}
		
		if (mOutsideOnScrollListener != null)
			mOutsideOnScrollListener.onScrollStateChanged(view, scrollState);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		if (mOutsideOnScrollListener != null)
			mOutsideOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
	}
	
	private final void updateVisibleItem(AbsListView view) {
		final ListAdapter la= view.getAdapter();
		if(la==null||la.getCount()<1)
			return;
		final int fvp = view.getFirstVisiblePosition(), lvp = view.getLastVisiblePosition();
		if (fvp < 0 || fvp > lvp)
			return;
		for (int i = fvp; i <= lvp; i++) {
			la.getView(i, view.getChildAt(i - fvp), view);
		}
	}
}//end ScrollListenerImp
