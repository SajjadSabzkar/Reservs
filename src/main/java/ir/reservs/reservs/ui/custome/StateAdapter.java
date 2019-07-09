package ir.reservs.reservs.ui.custome;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class StateAdapter extends RecyclerView.Adapter {

    /* Static values for the views we use in the RecyclerView */
    public static final int VIEW_NORMAL = 100;
    public static final int VIEW_EMPTY = 101;
    public static final int VIEW_LOADING = 102;
    public static final int VIEW_ERROR = 103;
    /* Internally used values for Different View States */
    public static final int VIEW_TYPE_EMPTY = 201;
    public static final int VIEW_TYPE_LOADING = 202;
    public static final int VIEW_TYPE_ERROR = 203;
    /* Wrapped Adapter */
    private final RecyclerView.Adapter wrapped;
    /* Views for different states*/
    private View loadingView;
    private View emptyView;
    private View errorView;
    @CurrentSetView
    private int currentView = VIEW_NORMAL;

    public StateAdapter(@NonNull RecyclerView.Adapter wrapped, @Nullable View emptyView, @Nullable View loadingView, @Nullable View errorView) {
        super();
        this.wrapped = wrapped;
        this.emptyView = emptyView;
        this.loadingView = loadingView;
        this.errorView = errorView;

        this.wrapped.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                notifyItemRangeChanged(positionStart, itemCount, payload);
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @CurrentSetView
    public int getCurrentView() {
        return currentView;
    }

    public void setCurrentView(@CurrentSetView int currentView) {
        Log.e("StateAdapter", "state:" + currentView);
        this.currentView = currentView;
        wrapped.notifyDataSetChanged();
        notifyDataSetChanged();
    }

    public RecyclerView.Adapter getWrappedAdapter() {
        return wrapped;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (currentView) {
            case VIEW_EMPTY:
                return new RecyclerView.ViewHolder(emptyView) {
                };
            case VIEW_LOADING:
                return new RecyclerView.ViewHolder(loadingView) {
                };
            case VIEW_ERROR:
                return new RecyclerView.ViewHolder(errorView) {
                };
            case VIEW_NORMAL:
                return wrapped.onCreateViewHolder(parent, viewType);
            default:
                throw new IllegalArgumentException("No View State found matching currentView: " + currentView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (currentView) {
            case VIEW_NORMAL:
                wrapped.onBindViewHolder(holder, position);
                break;
            case VIEW_EMPTY:
                onBindEmptyViewHolder(holder, position);
                break;
            case VIEW_LOADING:
                onBindLoadingViewHolder(holder, position);
                break;
            case VIEW_ERROR:
                onBindErrorViewHolder(holder, position);
                break;
            default:
                throw new IllegalArgumentException("No View State found matching currentView: " + currentView);
        }
    }

    /**
     * Override this Method to do something when the Empty View is bound
     * This is empty by default
     */
    public void onBindEmptyViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    /**
     * Override this Method to perform actions when the Loading View is bound
     * This is empty by default
     */
    public void onBindLoadingViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    /**
     * Override this Method to perform actions when the Error View is bound
     * This is empty by default
     */
    public void onBindErrorViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        switch (currentView) {
            case VIEW_NORMAL:
                return wrapped.getItemCount();
            case VIEW_LOADING:
            case VIEW_EMPTY:
            case VIEW_ERROR:
                return 1;
            default:
                throw new IllegalArgumentException("No View State found matching currentView: " + currentView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (currentView) {
            case VIEW_NORMAL:
                return wrapped.getItemViewType(position);
            case VIEW_LOADING:
                return VIEW_TYPE_LOADING;
            case VIEW_EMPTY:
                return VIEW_TYPE_EMPTY;
            case VIEW_ERROR:
                return VIEW_TYPE_ERROR;
            default:
                throw new IllegalArgumentException("No View State found matching currentView: " + currentView);
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        wrapped.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return wrapped.getItemId(position);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        wrapped.onViewRecycled(holder);
    }

    @Override
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
        return wrapped.onFailedToRecycleView(holder);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        wrapped.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        wrapped.onViewDetachedFromWindow(holder);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        wrapped.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        wrapped.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        wrapped.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        wrapped.onDetachedFromRecyclerView(recyclerView);
    }

    /**
     * Make sure that the Views are only one of the defined values
     * Don't want the good devs having a mess with wrong code!
     */
    @IntDef({VIEW_NORMAL, VIEW_EMPTY, VIEW_LOADING, VIEW_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface CurrentSetView {
    }

}