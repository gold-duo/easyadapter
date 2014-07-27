package com.droidwolf.easyadapter;

/**
 * 抽象视图(ViewItem)类,简化ViewItem接口实现，常用于{@link ViewItemFactory}
 */
public abstract class AbsViewItem<T> implements ViewItem<T> {
	private int mPosition;
	public boolean isEnabled(T data, int position) {
		return true;
	}
	
	public void setPosition(int pos){
		mPosition=pos;
	 }
	public int getPosition(){
		return mPosition;
	}
}
