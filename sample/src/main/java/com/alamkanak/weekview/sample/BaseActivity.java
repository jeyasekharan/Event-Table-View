package com.alamkanak.weekview.sample;

import android.graphics.RectF;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alamkanak.weekview.DateTimeInterpreter;
import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.alamkanak.weekview.sample.apiclient.Event;
import com.alamkanak.weekview.sample.models.EventUtils;
import com.alamkanak.weekview.sample.models.Events;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * This is a base activity which contains week view and all the codes necessary to initialize the
 * week view.
 * Created by Raquib-ul-Alam Kanak on 1/3/2014.
 * Website: http://alamkanak.github.io
 */
public abstract class BaseActivity extends AppCompatActivity implements WeekView.EventClickListener, MonthLoader.MonthChangeListener, WeekView.EventLongPressListener, WeekView.EmptyViewLongPressListener {

    ArrayList<List<Events>> arrayList;

    int[] colors = {R.color.event_color_01, R.color.event_color_02, R.color.event_color_03, R.color.event_color_04};

    private static final int TYPE_DAY_VIEW = 1;
    private static final int TYPE_THREE_DAY_VIEW = 2;
    private static final int TYPE_WEEK_VIEW = 3;
    private int mWeekViewType = TYPE_THREE_DAY_VIEW;
    private WeekView mWeekView;

    ImageView iv_left_arrow_users;
    ImageView iv_right_arrow_users;

    EventUtils eventUtils = EventUtils.INSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        arrayList = EventUtils.INSTANCE.getData();

        // Get a reference for the week view in the layout.
        mWeekView = (WeekView) findViewById(R.id.weekView);

        // Show a toast message about the touched event.
        mWeekView.setOnEventClickListener(this);

        // The week view has infinite scrolling horizontally. We have to provide the events of a
        // month every time the month changes on the week view.
        mWeekView.setMonthChangeListener(this);

        // Set long press listener for events.
        mWeekView.setEventLongPressListener(this);

        // Set long press listener for empty view
        mWeekView.setEmptyViewLongPressListener(this);

        mWeekView.setNumberOfVisibleDays(5);

        mWeekView.setUsers(new String[] {"Anitha", "John", "Clement", "Amitab", "Joseph"});

        // Set up a date time interpreter to interpret how the date and time will be formatted in
        // the week view. This is optional.
        setupDateTimeInterpreter(false);


        String[] usernames = EventUtils.INSTANCE.setEngineerColumnNames();

        for (String username : usernames) {
            Log.e("username  ", " "+username  );
        }

        mWeekView.setUsers(new String[] {usernames[0], usernames[1], usernames[2], usernames[3], usernames[4]});

        initViews();
        setClickListeners();
    }

    private void setClickListeners() {
        iv_left_arrow_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = eventUtils.decreaseIndex();
                if (arrayList != null) {
                    mWeekView.setNumberOfVisibleDays(arrayList.size());
                }
                mWeekView.notifyDatasetChanged();
            }
        });

        iv_right_arrow_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = eventUtils.increaseIndex();
                if (arrayList != null) {
                    mWeekView.setNumberOfVisibleDays(arrayList.size());
                }
                mWeekView.notifyDatasetChanged();
            }
        });
    }

    private void initViews() {
        iv_left_arrow_users = findViewById(R.id.iv_left_arrow_users);
        iv_right_arrow_users = findViewById(R.id.iv_right_arrow_users);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        setupDateTimeInterpreter(id == R.id.action_week_view);
        switch (id){
            case R.id.action_today:
                mWeekView.goToToday();
                return true;
            case R.id.action_day_view:
                if (mWeekViewType != TYPE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(1);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_three_day_view:
                if (mWeekViewType != TYPE_THREE_DAY_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_THREE_DAY_VIEW;
                    mWeekView.setNumberOfVisibleDays(3);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics()));
                }
                return true;
            case R.id.action_week_view:
                if (mWeekViewType != TYPE_WEEK_VIEW) {
                    item.setChecked(!item.isChecked());
                    mWeekViewType = TYPE_WEEK_VIEW;
                    mWeekView.setNumberOfVisibleDays(7);

                    // Lets change some dimensions to best fit the view.
                    mWeekView.setColumnGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                    mWeekView.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                    mWeekView.setEventTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics()));
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Set up a date time interpreter which will show short date values when in week view and long
     * date values otherwise.
     * @param shortDate True if the date values should be short.
     */
    private void setupDateTimeInterpreter(final boolean shortDate) {
        mWeekView.setDateTimeInterpreter(new DateTimeInterpreter() {
            @Override
            public String interpretDate(Calendar date) {
                SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
                String weekday = weekdayNameFormat.format(date.getTime());
                SimpleDateFormat format = new SimpleDateFormat(" M/d", Locale.getDefault());

                // All android api level do not have a standard way of getting the first letter of
                // the week day name. Hence we get the first char programmatically.
                // Details: http://stackoverflow.com/questions/16959502/get-one-letter-abbreviation-of-week-day-of-a-date-in-java#answer-16959657
                if (shortDate)
                    weekday = String.valueOf(weekday.charAt(0));
                return weekday.toUpperCase() + format.format(date.getTime());
            }

            @Override
            public String interpretTime(int hour) {
                return hour > 11 ? (hour - 12) + " PM" : (hour == 0 ? "12 AM" : hour + " AM");
            }
        });
    }

    protected String getEventTitle(Calendar time) {
        return String.format("Event of %02d:%02d %s/%d", time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), time.get(Calendar.MONTH)+1, time.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {

        // Populate the week view with some events.

        //setEngineersData();

        List<WeekViewEvent> events = new ArrayList<>();


        String[] usernames = EventUtils.INSTANCE.setEngineerColumnNames();

        for (String username : usernames) {

        }

        int todayIndex = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if(arrayList != null) {
            for (List<Events> event : arrayList) {
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

                 //   WeekViewEvent event4 = new WeekViewEvent(6, singleUser.getTitle(), calStartTime4, calEndTime4);
                    WeekViewEvent event4 = new WeekViewEvent(6, singleUser.getTitle(), singleUser.getEngineer_id(), singleUser.getLocation(), calStartTime4, calEndTime4, singleUser.getJobEventType());
                    event4.setColor(getResources().getColor(R.color.grey_job));
                    events.add(event4);

                    colorIndex = colorIndex+ 1;

                    if (colorIndex > colors.length - 1) {
                        colorIndex = 0;
                    }
                    break;
                }

                todayIndex += 1;
            }
        }
        return events;
    }



    @Override
    public void onEventClick(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Clicked Event Name" + event.getName() +" event "+ event.getEngineerName() + "  address   "+
                event.getLocation(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEventLongPress(WeekViewEvent event, RectF eventRect) {
        Toast.makeText(this, "Long pressed event: " + event.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmptyViewLongPress(Calendar time) {
        Toast.makeText(this, "Empty view long pressed: " + getEventTitle(time), Toast.LENGTH_SHORT).show();
    }

    public WeekView getWeekView() {
        return mWeekView;
    }
}
