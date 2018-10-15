package fr.dream.antoninb.timezone;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Calendar localDate = Calendar.getInstance();
    String[] timezones = new String[] {"America/Cayenne", "Asia/Tokyo" ,
            "Europe/Paris"};
    TimeZone selectedTimeZone;
    TextView convertedTimeTv;
    TextView convertedDateTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertedTimeTv = findViewById(R.id.convertedTime);
        convertedDateTv = findViewById(R.id.convertedDate);

        SeekBar seekBar = findViewById(R.id.seekBar);
        final TextView userTime = findViewById(R.id.userTime);
        ListView listView = findViewById(R.id.listView);


        setLocalDate(localDate.get(Calendar.YEAR),localDate.get(Calendar.MONTH), localDate.get(Calendar.DAY_OF_MONTH));
        seekBar.setProgress(localDate.get(Calendar.HOUR_OF_DAY));
        userTime.setText((seekBar.getProgress() < 10 ? "0" +
                Integer.toString(seekBar.getProgress()) :
                Integer.toString(seekBar.getProgress())) + ":00");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                userTime.setText((seekBar.getProgress() < 10 ? "0" +
                        Integer.toString(seekBar.getProgress()) :
                        Integer.toString(seekBar.getProgress())) + ":00");
                localDate.set(Calendar.HOUR_OF_DAY, seekBar.getProgress());
                if (fromUser) {
                    localDate.set(Calendar.MINUTE, 0);
                }
                convertDate(selectedTimeZone);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i,
                                    long l) {
                selectedTimeZone = TimeZone.getTimeZone(timezones[i]);
                convertDate(selectedTimeZone);
            }
        });



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,timezones);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }

    public void showDatePicker(View view){
        DialogFragment dialog = new DatePickerFragment();
        dialog.show(getSupportFragmentManager(), "datePicker");
    }

    public void setLocalDate(int year, int month, int day) {
        localDate.set(Calendar.YEAR, year);
        localDate.set(Calendar.MONTH, month);
        localDate.set(Calendar.DAY_OF_MONTH, day);
        Button dateBtn = findViewById(R.id.dateButton);

        dateBtn.setText(DateFormat.getDateInstance().format(localDate.getTime()));
        convertDate(selectedTimeZone);
    }

    private void convertDate(TimeZone toTimeZone) {
        if (toTimeZone != null) {
            Calendar toDate = Calendar.getInstance(toTimeZone);
            toDate.setTime(localDate.getTime());
            int hours = toDate.get(Calendar.HOUR_OF_DAY);
            int minutes = toDate.get(Calendar.MINUTE);
            String time = (hours < 10 ? "0" + Integer.toString(hours) :
                    Integer.toString(hours))
                    + ":" + (minutes < 10 ? "0" + Integer.toString(minutes) :
                    Integer.toString(minutes));
            convertedTimeTv.setText(time);
            DateFormat parser = DateFormat.getDateInstance();
            parser.setTimeZone(selectedTimeZone);
            convertedDateTv.setText(parser.format(toDate.getTime()));
        }
    }

}
