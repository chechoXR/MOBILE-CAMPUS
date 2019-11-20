package com.app.college.mobilecampus.ui.calendario;

import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Cache;
import com.android.volley.toolbox.StringRequest;
import com.app.college.mobilecampus.MainActivity;
import com.app.college.mobilecampus.R;
import com.app.college.mobilecampus.utils.utils;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.UserRecoverableAuthIOException;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.app.Activity.RESULT_OK;


public class CalendarioFragment extends Fragment {

    private CalendarioViewModel calendarioViewModel;
    private CompactCalendarView calendarView;
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForDayAndMonth = new SimpleDateFormat("DD - MMM -yyyy", Locale.getDefault());

    private TextView textView,descripcion,info_text;
    private HashMap<String,List<String>> description_dates = new HashMap<String, List<String>>();

    private SignInButton signInButton;
    static final int SIGN_IN_CODE = 400;
    private GoogleSignInAccount account;

    @Override
    public void onStart() {
        super.onStart();
        hideLogin();
        descripcion.setText("");
        account = GoogleSignIn.getLastSignedInAccount(getActivity());
        if(account!=null) handleAlreadySignIn(account);
        else showLogin();

    }

    @Override
    public void onResume() {
        super.onResume();
        descripcion.setText("");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        calendarView.removeAllEvents();
        descripcion.setText("");

    }


    @Override
    public void onPause() {
        super.onPause();
        calendarView.removeAllEvents();
        descripcion.setText("");
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calendario, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calendarioViewModel = ViewModelProviders.of(this).get(CalendarioViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        calendarView = (CompactCalendarView) getActivity().findViewById(R.id.calendario_academico);
        textView = (TextView) getActivity().findViewById(R.id.month_year);
        descripcion = (TextView) getActivity().findViewById(R.id.descripcion);
        info_text = (TextView) getActivity().findViewById(R.id.info_text);
        textView.setText(dateFormatForMonth.format(calendarView.getFirstDayOfCurrentMonth()));
        calendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(final Date dateClicked) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String descripcion_="";
                        if(description_dates.get(dateFormatForDayAndMonth.format(dateClicked))!=null)
                        for(String value:description_dates.get(dateFormatForDayAndMonth.format(dateClicked))){
                            descripcion_+= value + "\n";
                        }
                        descripcion.setText(descripcion_);
                        descripcion_ = "";
                    }
                });
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textView.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });

        signInButton = (SignInButton) getActivity().findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSignIn();
            }
        });
    }

    private void requestSignIn() {
        GoogleSignInOptions signInOptions = null;
        if(account == null) {
            signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .requestScopes(new Scope(CalendarScopes.CALENDAR))
                    .build();

            GoogleSignInClient client = GoogleSignIn.getClient(getActivity(), signInOptions);
            startActivityForResult(client.getSignInIntent(), SIGN_IN_CODE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SIGN_IN_CODE:
                if(resultCode == RESULT_OK)
                    handleSignInIntent(data);
                break;
        }
    }

    private void handleSignInIntent(Intent data) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnSuccessListener(new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(final GoogleSignInAccount googleSignInAccount) {
                        hideLogin();
                        try {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    GoogleAccountCredential credetial = GoogleAccountCredential.usingOAuth2(getContext(), Collections.singleton(CalendarScopes.CALENDAR_EVENTS));
                                    credetial.setSelectedAccount(googleSignInAccount.getAccount());
                                    Calendar calendar = new Calendar.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), credetial)
                                            .setApplicationName("Mobile Campus")
                                            .build();
                                    DateTime now = new DateTime(System.currentTimeMillis());
                                    Events events = null;
                                    try {
                                        events = calendar.events().list("t38odp0eg92pi2ij6d9qp25ttk@group.calendar.google.com")
                                                .setOrderBy("startTime")
                                                .setSingleEvents(true)
                                                .execute();
                                        List<Event> items = events.getItems();
                                        if (items.isEmpty()) {
                                            System.out.println("No upcoming events found.");
                                        } else {
                                            System.out.println("Upcoming events");
                                            for (Event event : items) {
                                                DateTime start = event.getStart().getDateTime();
                                                if (start == null) {
                                                    start = event.getStart().getDate();
                                                }
                                                Log.i("event_sumary",event.getSummary());
                                                Log.i("event_description",event.getDescription());

                                                setEventInCalendar(event);
                                            }
                                        }
                                    }catch (UserRecoverableAuthIOException e){
                                        startActivityForResult(e.getIntent(), SIGN_IN_CODE);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();

                        }


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                     public void onFailure(@NonNull Exception e) {
                     }
        });
    }

    private void handleAlreadySignIn(final GoogleSignInAccount account) {

                        try {
                            AsyncTask.execute(new Runnable() {
                                @Override
                                public void run() {
                                    GoogleAccountCredential credetial = GoogleAccountCredential.usingOAuth2(getContext(), Collections.singleton(CalendarScopes.CALENDAR_EVENTS));
                                    credetial.setSelectedAccount(account.getAccount());
                                    Calendar calendar = new Calendar.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), credetial)
                                            .setApplicationName("Mobile Campus")
                                            .build();
                                    DateTime now = new DateTime(System.currentTimeMillis());
                                    Events events = null;
                                    try {
                                        events = calendar.events().list("t38odp0eg92pi2ij6d9qp25ttk@group.calendar.google.com")
                                                .setOrderBy("startTime")
                                                .setSingleEvents(true)
                                                .execute();
                                        List<Event> items = events.getItems();
                                        if (items.isEmpty()) {
                                            System.out.println("No upcoming events found.");
                                        } else {
                                            System.out.println("Upcoming events");
                                            for (Event event : items) {
                                                DateTime start = event.getStart().getDateTime();
                                                if (start == null) {
                                                    start = event.getStart().getDate();
                                                }
                                                Log.i("event_sumary",event.getSummary());
                                                Log.i("event_description",event.getDescription());

                                                setEventInCalendar(event);
                                            }
                                        }
                                    }catch (UserRecoverableAuthIOException e){
                                        startActivityForResult(e.getIntent(), SIGN_IN_CODE);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();

                        }


                    }



    private void setEventInCalendar(final Event event) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                Date date = new Date(event.getStart().getDate().getValue());
                calendarView.addEvent(new com.github.sundeepk.compactcalendarview.domain.Event
                        (Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)),event.getStart().getDate().getValue(),date));

                if(description_dates.get(dateFormatForDayAndMonth.format(date) ) == null)
                description_dates.put(dateFormatForDayAndMonth.format(date),new LinkedList<String>());
                description_dates.get(dateFormatForDayAndMonth.format(date)).add(event.getSummary());

                System.out.println(description_dates.toString());

            }
        });

    }

    private void hideLogin(){
        this.signInButton.setVisibility(View.GONE);
        this.info_text.setVisibility(View.GONE);
    }

    private void showLogin(){
        this.signInButton.setVisibility(View.VISIBLE);
        this.info_text.setVisibility(View.VISIBLE);
    }
}
