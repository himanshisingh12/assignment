package com.example.assignment.assignment.Adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment.assignment.Pojo.DataResultPojo;
import com.example.assignment.assignment.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MatchCardListAdapter extends RecyclerView.Adapter<MatchCardListAdapter.MyViewHolder> {
    private Context context;
    private List<DataResultPojo> cardList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, gender, location, email;
        public ImageView picture;
        public RelativeLayout viewBackground;
        public CardView viewForeground;
        public Button button;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
            gender = view.findViewById(R.id.gender);
            picture = view.findViewById(R.id.profile_picture);
            location = view.findViewById(R.id.location);
            email = view.findViewById(R.id.email);
            button = view.findViewById(R.id.remove_button);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }


    public MatchCardListAdapter(Context context, List<DataResultPojo> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final DataResultPojo item = cardList.get(position);

        holder.name.setText(item.getName().getFirst() + " " + item.getName().getLast());
        holder.gender.setText(item.getGender() + " , " + item.getDob().getAge());
        holder.location.setText(item.getLocation().getCity() + " , " + item.getLocation().getState());
        holder.email.setText(item.getEmail());

        String myDate = item.getDob().getDate();
        DateFormat iFormatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat oFormatter = new SimpleDateFormat("dd/MM/yy");
        try {
            String strDateTime = oFormatter.format(iFormatter.parse(myDate));
            holder.age.setText(strDateTime);
        } catch (ParseException e) {

        }

        Glide.with(context)
                .load(item.getPicture().getMedium())
                .into(holder.picture);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                removeListItem(holder.viewForeground, cardList.get(position));
            }

        });


    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }


    protected void removeListItem(View view, final DataResultPojo positon) {
        cardList.remove(positon);
        final Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_out_right);
        view.startAnimation(animation);

        Handler handle = new Handler();

        // handler to remove list icon before the animation gets canceled for a smooth transition

        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();

            }
        }, 400);

        // handler for animation cancellation.


        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                //notifyDataSetChanged();
                animation.cancel();

            }

            //This can be modified to make animation last longer

        }, 1200);

    }


}
