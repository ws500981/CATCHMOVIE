package com.swl.catchmovie.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.swl.catchmovie.Movie;
import com.swl.catchmovie.R;
import com.swl.catchmovie.app.MyApplication;

public class MovieShowTimeFragment extends Fragment  {

    private static final String TAG = "MovieShowTimeFragment";
    //private static final String TAG = MovieFragment.class.getSimpleName();

    // url to fetch movie show time
    private static final String URL = "https://jsonstorage.net/api/items/0e871540-c264-4048-b9db-88b5310fee4e";

    private RecyclerView recyclerView;
    private List<Movie> showtimelist;
    private String moviename;
    private TextView txtShowtime;

    private List<Movie> items;
    private List<Movie> movieList;
    private List<Movie> itemsList;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private ArrayAdapter<String> adapter3;
    private ArrayAdapter<String> adapter4;
    private ArrayAdapter<String> adapter5;

    private ListView listViewShowtime;
    private JSONObject movie;
    private ArrayList<String> listDay1;
    private ArrayList<String> listDay2;
    private ArrayList<String> listDay3;
    private ArrayList<String> listDay4;
    private ArrayList<String> listDay5;

    private ProgressBar progress;

    private String movieName = null;
    private String showDateshref = null;

    private String setByMovie = " ";
    private int setByDate = -1;
    private String setByDateName = " ";
    private boolean setBySearch = false;

    public MovieShowTimeFragment() {
        // Required empty public constructor
    }

    public static MovieShowTimeFragment newInstance(String param1, String param2)  {
        MovieShowTimeFragment fragment = new MovieShowTimeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MovieShowTimeFragment(String moviename)
    {
        this.moviename = moviename;
    }

    public MovieShowTimeFragment(String setByMovieName, int setByDatePos, String setByDateName)
    {
        setByMovie = setByMovieName;
        setByDate = setByDatePos;
        setBySearch = true;
        this.setByDateName = setByDateName;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_showtime, container, false);
        progress = view.findViewById(R.id.progressBar1);
        itemsList = new ArrayList<>();
        items = new ArrayList<>();
        listViewShowtime = view.findViewById(R.id.showtimelist);
        listViewShowtime.setVisibility(View.INVISIBLE);
        final Button day1 = view.findViewById(R.id.day1);
        final Button day2 = view.findViewById(R.id.day2);
        final Button day3 = view.findViewById(R.id.day3);
        final Button day4 = view.findViewById(R.id.day4);
        final Button day5 = view.findViewById(R.id.day5);
        listDay1 = new ArrayList<String>();
        listDay2 = new ArrayList<String>();
        listDay3 = new ArrayList<String>();
        listDay4 = new ArrayList<String>();
        listDay5 = new ArrayList<String>();

        JsonArrayRequest request = new JsonArrayRequest(URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response == null) {
                            Toast.makeText(getActivity(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
                            return;
                        }

                        items = new Gson().fromJson(response.toString(), new TypeToken<List<Movie>>() {
                        }.getType());

                        itemsList.clear();
                        itemsList.addAll(items);



                        for(int i=0; i<itemsList.size(); i++)
                        {
                            try {
                                movie = response.getJSONObject(i);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                movieName = movie.getString("title");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                showDateshref = movie.getString("showDates-href");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if(movieName.equals(moviename) || (setBySearch == true && setByMovie.equals(movieName)))
                            {
                                progress.setVisibility(View.GONE);
                                if(showDateshref.contains("tab_0"))
                                {

                                    try {
                                        Log.d(TAG, "this movie_name is " + movieName);

                                        String showDate = movie.getString("showDates");
                                        Log.d(TAG, "this movie_date is " + showDate);
                                        day1.setText(showDate);

                                        String showTime = movie.getString("showtimelocation");
                                        showTime = showTime.replace("\n", " ");
                                        showTime = showTime.replace("                                        ", "    ");
                                        showTime = showTime.replace("    ", "\n");
                                        listDay1.add(showTime);
                                        adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listDay1);
                                        // showTime = showTime.replace("   ")
                                        //String[] showlocation = showTime.split("   ");
                                        Log.d(TAG, "movie_showtime is " + showTime);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if(showDateshref.contains("tab_1"))
                                {
                                    try {
                                        Log.d(TAG, "this movie_name is " + movieName);

                                        String showDate = movie.getString("showDates");
                                        Log.d(TAG, "this movie_date is " + showDate);
                                        day2.setText(showDate);
                                        String showTime = movie.getString("showtimelocation");
                                        showTime = showTime.replace("\n", " ");
                                        showTime = showTime.replace("                                        ", "    ");
                                        showTime = showTime.replace("    ", "\n");
                                        listDay2.add(showTime);
                                        adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listDay2);
                                        // showTime = showTime.replace("   ")
                                        //String[] showlocation = showTime.split("   ");
                                        Log.d(TAG, "movie_showtime is " + showTime);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(showDateshref.contains("tab_2"))
                                {
                                    try {
                                        Log.d(TAG, "this movie_name is " + moviename);

                                        String showDate = movie.getString("showDates");
                                        Log.d(TAG, "this movie_date is " + showDate);
                                        day3.setText(showDate);

                                        String showTime = movie.getString("showtimelocation");
                                        showTime = showTime.replace("\n", " ");
                                        showTime = showTime.replace("                                        ", "    ");
                                        showTime = showTime.replace("    ", "\n");
                                        listDay3.add(showTime);
                                        // showTime = showTime.replace("   ")
                                        //String[] showlocation = showTime.split("   ");
                                        adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listDay3);
                                        Log.d(TAG, "movie_showtime is " + showTime);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if(showDateshref.contains("tab_3"))
                                {
                                    try {
                                        Log.d(TAG, "this movie_name is " + moviename);

                                        String showDate = movie.getString("showDates");
                                        Log.d(TAG, "this movie_date is " + showDate);
                                        day4.setText(showDate);
                                        String showTime = movie.getString("showtimelocation");
                                        showTime = showTime.replace("\n", " ");
                                        showTime = showTime.replace("                                        ", "    ");
                                        showTime = showTime.replace("    ", "\n");
                                        listDay4.add(showTime);
                                        adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listDay4);
                                        // showTime = showTime.replace("   ")
                                        //String[] showlocation = showTime.split("   ");
                                        Log.d(TAG, "movie_showtime is " + showTime);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                if(showDateshref.contains("tab_4"))
                                {
                                    try {
                                        Log.d(TAG, "this movie_name is " + moviename);

                                        String showDate = movie.getString("showDates");
                                        Log.d(TAG, "this movie_date is " + showDate);
                                        day5.setText(showDate);

                                        String showTime = movie.getString("showtimelocation");
                                        showTime = showTime.replace("\n", " ");
                                        showTime = showTime.replace("                                        ", "    ");
                                        showTime = showTime.replace("    ", "\n");
                                        listDay5.add(showTime);
                                        adapter5 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, listDay5);
                                        // showTime = showTime.replace("   ")
                                        //String[] showlocation = showTime.split("   ");
                                        Log.d(TAG, "movie_showtime is " + showTime);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                            Log.d(TAG,"This is the listDay1:"+ listDay1.toString());



                        }

                        if(setBySearch)
                        {
                            if(setByDate == 0 )
                            {
                                day1.performClick();
                                if(listDay1.isEmpty())
                                {
                                    Toast.makeText(getActivity(), setByMovie + " is not playing on " + setByDateName, Toast.LENGTH_LONG).show();
                                }
                            }
                            if(setByDate == 1)
                            {
                                day2.performClick();
                                if(listDay2.isEmpty())
                                {
                                    Toast.makeText(getActivity(), setByMovie + " is not playing on " + setByDateName, Toast.LENGTH_LONG).show();
                                }
                            }
                            if(setByDate == 2)
                            {
                                day3.performClick();
                                if(listDay3.isEmpty())
                                {
                                    Toast.makeText(getActivity(), setByMovie + " is not playing on " + setByDateName, Toast.LENGTH_LONG).show();
                                }
                            }
                            if(setByDate == 3)
                            {
                                day4.performClick();
                                if(listDay4.isEmpty())
                                {
                                    Toast.makeText(getActivity(), setByMovie + " is not playing on " + setByDateName, Toast.LENGTH_LONG).show();
                                }
                            }

                            if(setByDate == 4)
                            {
                                day5.performClick();
                                if(listDay5.isEmpty())
                                {
                                    Toast.makeText(getActivity(), setByMovie + " is not playing on " + setByDateName, Toast.LENGTH_LONG).show();
                                }
                            }
                        }


                        // adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, list);
                        //listViewMovie.setAdapter(adapter);
                        //Toast.makeText(getActivity(),"This is num of items: " +itemsList.size(), Toast.LENGTH_SHORT).show();
                        // refreshing recycler view
                        //set default display to TODAY
                        if(setBySearch == false)
                        {
                            day1.performClick();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error in getting json
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MyApplication.getInstance().addToRequestQueue(request);

        listViewShowtime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                    String selectedItem = (String) parent.getItemAtPosition(position);
                    Intent viewIntent = null;
                    if(selectedItem.contains("GV"))
                    {
                        viewIntent = new Intent("android.intent.action.VIEW",
                                        Uri.parse("https://www.gv.com.sg/GVBuyTickets#/"));
                    }
                    else if(selectedItem.contains("Shaw"))
                    {
                        viewIntent = new Intent("android.intent.action.VIEW",
                                Uri.parse("https://shaw.sg/Showtimes/"));
                    }
                    else if(selectedItem.contains("Filmgarde"))
                    {
                        viewIntent = new Intent("android.intent.action.VIEW",
                                Uri.parse("https://fgcineplex.com.sg/buyticket"));
                    }
                    else if(selectedItem.contains("Cathay"))
                    {
                        viewIntent = new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.cathaycineplexes.com.sg/showtimes.aspx"));
                    }
                    startActivity(viewIntent);

            }
        });


        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewShowtime.setAdapter(adapter1);
                listViewShowtime.setVisibility(View.VISIBLE);
                day1.setBackgroundColor(Color.parseColor("#7b4bff"));
                day1.setTextColor(Color.WHITE);
                //
                day2.setBackgroundColor(Color.LTGRAY);
                day2.setTextColor(Color.BLACK);
                day3.setBackgroundColor(Color.LTGRAY);
                day3.setTextColor(Color.BLACK);
                day4.setBackgroundColor(Color.LTGRAY);
                day4.setTextColor(Color.BLACK);
                day5.setBackgroundColor(Color.LTGRAY);
                day5.setTextColor(Color.BLACK);
            }
        });


        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewShowtime.setAdapter(adapter2);
                listViewShowtime.setVisibility(View.VISIBLE);
                day2.setBackgroundColor(Color.parseColor("#7b4bff"));
                day2.setTextColor(Color.WHITE);
                //
                day1.setBackgroundColor(Color.LTGRAY);
                day1.setTextColor(Color.BLACK);
                day3.setBackgroundColor(Color.LTGRAY);
                day3.setTextColor(Color.BLACK);
                day4.setBackgroundColor(Color.LTGRAY);
                day4.setTextColor(Color.BLACK);
                day5.setBackgroundColor(Color.LTGRAY);
                day5.setTextColor(Color.BLACK);
            }
        });




        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewShowtime.setAdapter(adapter3);
                listViewShowtime.setVisibility(View.VISIBLE);
                day3.setBackgroundColor(Color.parseColor("#7b4bff"));
                day3.setTextColor(Color.WHITE);
                //
                day1.setBackgroundColor(Color.LTGRAY);
                day1.setTextColor(Color.BLACK);
                day2.setBackgroundColor(Color.LTGRAY);
                day2.setTextColor(Color.BLACK);
                day4.setBackgroundColor(Color.LTGRAY);
                day4.setTextColor(Color.BLACK);
                day5.setBackgroundColor(Color.LTGRAY);
                day5.setTextColor(Color.BLACK);
            }
        });



        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewShowtime.setAdapter(adapter4);
                listViewShowtime.setVisibility(View.VISIBLE);
                day4.setBackgroundColor(Color.parseColor("#7b4bff"));
                day4.setTextColor(Color.WHITE);
                //
                day1.setBackgroundColor(Color.LTGRAY);
                day1.setTextColor(Color.BLACK);
                day2.setBackgroundColor(Color.LTGRAY);
                day2.setTextColor(Color.BLACK);
                day3.setBackgroundColor(Color.LTGRAY);
                day3.setTextColor(Color.BLACK);
                day5.setBackgroundColor(Color.LTGRAY);
                day5.setTextColor(Color.BLACK);
            }
        });



        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewShowtime.setAdapter(adapter5);
                listViewShowtime.setVisibility(View.VISIBLE);
                day5.setBackgroundColor(Color.parseColor("#7b4bff"));
                day5.setTextColor(Color.WHITE);
                //
                day1.setBackgroundColor(Color.LTGRAY);
                day1.setTextColor(Color.BLACK);
                day2.setBackgroundColor(Color.LTGRAY);
                day2.setTextColor(Color.BLACK);
                day3.setBackgroundColor(Color.LTGRAY);
                day3.setTextColor(Color.BLACK);
                day4.setBackgroundColor(Color.LTGRAY);
                day4.setTextColor(Color.BLACK);
            }
        });
        return view;
    }

}
