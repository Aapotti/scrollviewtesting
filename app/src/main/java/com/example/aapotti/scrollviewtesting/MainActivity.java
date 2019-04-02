package com.example.aapotti.scrollviewtesting;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager topImagesVP;
    private TopImagesAdapter topImagesAdapter;
    private RecyclerView newsRV;
    private NavigationView mainMenuNavigationView;

    private DatabaseReference newsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topImagesVP = (ViewPager)findViewById(R.id.topImagesViewPager);

        topImagesAdapter = new TopImagesAdapter(getSupportFragmentManager());

        newsRV = (RecyclerView)findViewById(R.id.newsRV);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        newsRV.setLayoutManager(linearLayoutManager);

        mainMenuNavigationView = (NavigationView)findViewById(R.id.mainMenuNnavigationView);
        mainMenuNavigationView.setItemIconTintList(null);

        newsRef = FirebaseDatabase.getInstance().getReference().child("News");

        topImagesVP.setAdapter(topImagesAdapter);

        //RUN THROUGH THE TO IMAGES
        final Handler handler = new Handler();
        final int delay = 5000; //milliseconds

        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                if(topImagesVP.getCurrentItem() == 4)
                {
                    topImagesVP.setCurrentItem(0);
                }
                else
                {
                    topImagesVP.setCurrentItem(topImagesVP.getCurrentItem() + 1);
                }
                handler.postDelayed(this, delay);
            }
        }, delay);


        //NEWS ADAPTER
        FirebaseRecyclerAdapter<News, NewsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>
                (News.class, R.layout.news_template_layout, NewsViewHolder.class, newsRef)
        {
            @Override
            protected void populateViewHolder(NewsViewHolder viewHolder, News model, int position)
            {
                final String newsKey = getRef(position).getKey();
                final DatabaseReference currentNews = FirebaseDatabase.getInstance().getReference().child("News").child(newsKey);

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDate(model.getDate());
                viewHolder.setImage(model.getImage());
            }
        };

        newsRV.setAdapter(firebaseRecyclerAdapter);
    }


    public static class NewsViewHolder extends RecyclerView.ViewHolder
    {
        View mView;

        CircleImageView thumbNailIW;
        TextView titleTV;
        TextView dateTV;

        public NewsViewHolder(View itemView)
        {
            super(itemView);

            this.mView = itemView;

            thumbNailIW = (CircleImageView)mView.findViewById(R.id.newsThumbnail);
            titleTV = (TextView)mView.findViewById(R.id.newsTitle);
            dateTV = (TextView)mView.findViewById(R.id.newsDate);
        }

        public void setTitle(String title) {
            titleTV.setText(title);
        }

        public void setDate(String date) {
            dateTV.setText(date);
        }

        public void setImage(String image) {
            Picasso.get().load(image).into(thumbNailIW);
        }
    }
}
