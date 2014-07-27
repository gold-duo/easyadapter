package com.droidwolf.easyadapter;

/**
 * 视图(ViewItem)创建工厂ViewItem接口，用于EasyAdapter
 */
public interface ViewItemFactory {
    	/**
    	 * 创建一个新的ViewItem
    	 * @param data 当前行数据
    	 * @param position data在适配器中的position
    	 * @return ViewItem
    	 */
	<T> ViewItem<?> newViewItem(T data, int position);
	
	/**
	 * 参考{@link android.widget.Adapter} getItemViewType函数
	 * @param data 当前行数据
	 * @param position data在适配器中的position
	 * @return 
	 */
	<T> int getItemViewType(T data, int position);
	
	/**
	 * 参考{@link android.widget.Adapter} getViewTypeCount函数
	 * @return
	 */
	int getViewTypeCount();
}
