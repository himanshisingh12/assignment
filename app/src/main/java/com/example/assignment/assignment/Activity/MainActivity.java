package com.example.assignment.assignment.Activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.assignment.assignment.Adapter.MatchCardListAdapter;
import com.example.assignment.assignment.MyApplication;
import com.example.assignment.assignment.Pojo.DataResultPojo;
import com.example.assignment.assignment.Pojo.MainPojo;
import com.example.assignment.assignment.R;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<DataResultPojo> cardList;
    private MatchCardListAdapter mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ProgressBar progressBar;
    private CoordinatorLayout coordinatorLayout;


    // url to fetch menu json
    private static final String URL = "https://randomuser.me/api/?results=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
        coordinatorLayout = findViewById(R.id.coordinator_layout);
        progressBar = findViewById(R.id.progressBar_cyclic);


        mSwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        loadData();
                        mSwipeRefreshLayout.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);

                    }
                }
        );
        cardList = new ArrayList<>();
        mAdapter = new MatchCardListAdapter(this, cardList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        loadData();


    }

    /**
     * method make volley network call and parses json (here retrofit call with proper mvc/mvp/mvvm can also be used)
     */


    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                URL, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                progressBar.setVisibility(View.GONE);

                // Parsing json object response
                // response will be a json object
                if (response == null) {
                    Toast.makeText(getApplicationContext(), "Couldn't fetch the menu! Pleas try again.", Toast.LENGTH_LONG).show();
                    return;
                }

                MainPojo items = new Gson().fromJson(response.toString(), new TypeToken<MainPojo>() {
                }.getType());

                // adding items to cardlist


                cardList.clear();

                //list added and sent to adapter


                cardList.addAll(items.getResults());
                recyclerView.setAdapter(mAdapter);


                // refreshing recycler view
                mAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
            }
        });

        // singleton class used to initiate the volley library.


        MyApplication.getInstance().addToRequestQueue(jsonObjReq);
    }


}
