package com.example.chapay.rk;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.LayoutInflater;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int[] numbers = new int [1000];
        int i = 0;
        while (i < 1000) {
            numbers[i]=(i + 1);
            i++;
        }

        RecyclerView recView =(RecyclerView) findViewById(R.id.num_view);
        recView.setLayoutManager(new GridLayoutManager(this,3));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,numbers);
        adapter.setClickListener(this);
        recView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
//        Log.i("TAG", "You clicked number " + position+1 + ", which is at cell position " + position);
        final FragmentManager manager = getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        NumFragment numFragment = new NumFragment();
        Bundle bundle = new Bundle();
        String str =Integer.toString(position+1);
        bundle.putString("num",str);
        numFragment.setArguments(bundle);
        transaction.replace(R.id.main, numFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}


class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private int[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, int[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = Integer.toString(mData[position]);
        if(position % 2 == 0)
            holder.myTextView.setTextColor(0xFFF06D2F);
        holder.myTextView.setText(animal);
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return Integer.toString(mData[id]);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
