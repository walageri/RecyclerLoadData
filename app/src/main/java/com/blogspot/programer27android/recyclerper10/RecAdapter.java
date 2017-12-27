package com.blogspot.programer27android.recyclerper10;


import android.app.Activity;
import android.content.ClipData;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gery on 12/24/17.
 */
class LoadingHolder extends RecyclerView.ViewHolder {
    public ProgressBar pb;

    public LoadingHolder(View itemView) {
        super(itemView);
        pb=(ProgressBar)itemView.findViewById(R.id.p);
    }
}
class ItemHolder extends RecyclerView.ViewHolder{
    TextView negara;
    public ItemHolder(View itemView) {
        super(itemView);
        negara=itemView.findViewById(R.id.nNama);
    }
}

public class RecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<String> list;
    private final  int VIEW_TYPE_ITEM=0,VIEW_TYPE_LOADING=1;
    Boolean isLoading ;
    Activity activity;
    int vTHold=10;
    int lastItem,totalItem;
    ILoad loadMore;
    RecyclerView r;


    public RecAdapter(final ArrayList<String> list) {
        this.list = list;
        activity=new Activity();
        r=new RecyclerView(null);
        final LinearLayoutManager l =(LinearLayoutManager)r.getLayoutManager();
        r.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastItem = l.findLastVisibleItemPosition();
                if (!isLoading && totalItem <=(lastItem+vTHold)){
                    if(loadMore !=null )loadMore.onLoad();
                }
                isLoading =true;
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position)== null ? VIEW_TYPE_ITEM:VIEW_TYPE_LOADING;
    }
    public void setLoadMore(ILoad loadMore){
        this.loadMore=loadMore;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.progrees,parent,false);
            return new ItemHolder(v);
        }else if(viewType == VIEW_TYPE_LOADING)
        {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
            return new LoadingHolder(view);
        }
            return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder){
            String t=list.get(position);
            ItemHolder vh=(ItemHolder) holder;
            vh.negara.setText(list.get(position));
        }else if (holder instanceof LoadingHolder){

        }
    }

//    @Override
//    public void onBindViewHolder(RecAdapter.MyHolder h, int position) {
//        h.negara.setText(list.get(position));
//    }

    @Override
    public int getItemCount() {
            return list.size();
    }

    public void setIsLoaded() {
        isLoading = false;
    }

    //    public class MyHolder extends RecyclerView.ViewHolder {
//        TextView negara;
//        ProgressBar pb;
//        public MyHolder(View v) {
//            super(v);
//            pb=v.findViewById(R.id.p);
//            negara=v.findViewById(R.id.nNama);
//        }
//
//
//    }

}
