package com.alamkanak.weekview.sample;

import android.graphics.Color;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;
import com.alamkanak.weekview.sample.models.EventData;
import com.alamkanak.weekview.sample.models.EventUtils;
import com.alamkanak.weekview.sample.models.Events;
import com.alamkanak.weekview.sample.models.EventsClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A basic example of how to use week view library.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public class BasicActivity extends BaseActivity {

    ArrayList<List<Events>> arrayList;

    int[] colors = {R.color.event_color_01, R.color.event_color_02, R.color.event_color_03, R.color.event_color_04};

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.
        arrayList = EventUtils.INSTANCE.getData();


        //setEngineersData();

        List<WeekViewEvent> events = new ArrayList<>();

    /*  Calendar calStartTime = Calendar.getInstance();
        calStartTime.set(Calendar.HOUR_OF_DAY, 10);
        calStartTime.set(Calendar.MINUTE, 0);
        calStartTime.set(Calendar.MONTH, newMonth-1);
        calStartTime.set(Calendar.YEAR, newYear);

        Calendar calEndTime = Calendar.getInstance();
        calEndTime.set(Calendar.HOUR_OF_DAY, 12);
        calEndTime.set(Calendar.MINUTE, 0);
        calEndTime.set(Calendar.MONTH, newMonth-1);
        calEndTime.set(Calendar.YEAR, newYear);

        WeekViewEvent event0 = new WeekViewEvent(2, "Test Event", calStartTime, calEndTime);
        event0.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event0);


        *//* Calculate today's date *//*
        int today = calStartTime.get(Calendar.DAY_OF_MONTH);

        Calendar calStartTime2 = Calendar.getInstance();
        calStartTime2.set(Calendar.HOUR_OF_DAY, 7);
        calStartTime2.set(Calendar.MINUTE, 0);
        calStartTime2.set(Calendar.DAY_OF_MONTH, today+1);
        calStartTime2.set(Calendar.MONTH, newMonth-1);
        calStartTime2.set(Calendar.YEAR, newYear);

        Calendar calEndTime2 = Calendar.getInstance();
        calEndTime2.set(Calendar.HOUR_OF_DAY, 9);
        calEndTime2.set(Calendar.DAY_OF_MONTH, today+1);
        calEndTime2.set(Calendar.MINUTE, 0);
        calEndTime2.set(Calendar.MONTH, newMonth-1);
        calEndTime2.set(Calendar.YEAR, newYear);


        WeekViewEvent event2 = new WeekViewEvent(10, "7 to 8", calStartTime2, calEndTime2);
        event2.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event2);

        Calendar calStartTime3 = Calendar.getInstance();
        calStartTime3.set(Calendar.HOUR_OF_DAY, 7);
        calStartTime3.set(Calendar.MINUTE, 0);
        calStartTime3.set(Calendar.DAY_OF_MONTH, today+2);
        calStartTime3.set(Calendar.MONTH, newMonth-1);
        calStartTime3.set(Calendar.YEAR, newYear);

        Calendar calEndTime3 = Calendar.getInstance();
        calEndTime3.set(Calendar.HOUR_OF_DAY, 9);
        calEndTime3.set(Calendar.DAY_OF_MONTH, today+2);
        calEndTime3.set(Calendar.MINUTE, 0);
        calEndTime3.set(Calendar.MONTH, newMonth-1);
        calEndTime3.set(Calendar.YEAR, newYear);


        WeekViewEvent event3 = new WeekViewEvent(6, "7 to 8", calStartTime3, calEndTime3);
        event3.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event3);*/



       /*  Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR, 1);
        endTime.set(Calendar.MONTH, newMonth-1);
        WeekViewEvent event = new WeekViewEvent(1, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 4);
        endTime.set(Calendar.MINUTE, 30);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 4);
        startTime.set(Calendar.MINUTE, 20);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 5);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 2);
        endTime.set(Calendar.MONTH, newMonth-1);
        event = new WeekViewEvent(2, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, 5);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        startTime.add(Calendar.DATE, 1);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        endTime.set(Calendar.MONTH, newMonth - 1);
        event = new WeekViewEvent(3, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_03));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(4, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_04));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 1);
        startTime.set(Calendar.HOUR_OF_DAY, 3);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_01));
        events.add(event);

        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, startTime.getActualMaximum(Calendar.DAY_OF_MONTH));
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.add(Calendar.HOUR_OF_DAY, 3);
        event = new WeekViewEvent(5, getEventTitle(startTime), startTime, endTime);
        event.setColor(getResources().getColor(R.color.event_color_02));
        events.add(event);
*/

        ArrayList<List<Events>> engineerEvents = EventUtils.INSTANCE.getData();

        String[] usernames = EventUtils.INSTANCE.setEngineerColumnNames();

        for (String username : usernames) {
            Log.e("username  ", " "+username  );
        }

        int todayIndex = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        assert engineerEvents != null;
        for (List<Events> event : engineerEvents) {
            int colorIndex = 0;

            for (Events singleUser : event) {

                String startTime = singleUser.getStartDate().split(" ")[1];
                String endTime = singleUser.getEndDate().split(" ")[1];

                int startTimeInt = Integer.parseInt(startTime.substring(0,2));
                int endTimeInt = Integer.parseInt(endTime.substring(0,2));


                Calendar calStartTime4 = Calendar.getInstance();
                calStartTime4.set(Calendar.DAY_OF_MONTH, todayIndex);
                calStartTime4.set(Calendar.HOUR_OF_DAY, startTimeInt);
                calStartTime4.set(Calendar.MINUTE, 0);
                calStartTime4.set(Calendar.MONTH, newMonth-1);
                calStartTime4.set(Calendar.YEAR, newYear);

                Calendar calEndTime4 = Calendar.getInstance();
                calEndTime4.set(Calendar.DAY_OF_MONTH, todayIndex);
                calEndTime4.set(Calendar.HOUR_OF_DAY, endTimeInt);
                calEndTime4.set(Calendar.MINUTE, 0);
                calEndTime4.set(Calendar.MONTH, newMonth-1);
                calEndTime4.set(Calendar.YEAR, newYear);

                WeekViewEvent event4 = new WeekViewEvent(6, singleUser.getTitle(), calStartTime4, calEndTime4);
                event4.setColor(getResources().getColor(colors[colorIndex]));

                events.add(event4);

                colorIndex = colorIndex+ 1;
            }

            todayIndex += 1;
        }
        return events;
    }

/*    private void setEngineersData() {
        ArrayList<List<Events>> events = EventUtils.INSTANCE.getData();

        String[] usernames = EventUtils.INSTANCE.setEngineerColumnNames();

        for (String username : usernames) {
            Log.e("username  ", " "+username  );
        }

        int today = 0;

        assert events != null;
        for (List<Events> event : events) {

            for (Events singleUser : event) {

                Calendar calStartTime3 = Calendar.getInstance();
                calStartTime3.set(Calendar.HOUR_OF_DAY, 7);
                calStartTime3.set(Calendar.MINUTE, 0);
                calStartTime3.set(Calendar.DAY_OF_MONTH, today+2);
                calStartTime3.set(Calendar.MONTH, newMonth-1);
                calStartTime3.set(Calendar.YEAR, newYear);

                Calendar calEndTime3 = Calendar.getInstance();
                calEndTime3.set(Calendar.HOUR_OF_DAY, 9);
                calEndTime3.set(Calendar.DAY_OF_MONTH, today+2);
                calEndTime3.set(Calendar.MINUTE, 0);
                calEndTime3.set(Calendar.MONTH, newMonth-1);
                calEndTime3.set(Calendar.YEAR, newYear);
            }

            today += 1;
        }

    }*/
}
