package fr.dream.antoninb.timezone;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class TimezoneViewModel extends ViewModel {
    private ArrayList<String> timezones;
    private int currentTimezone;


    public TimezoneViewModel(ArrayList<String> timezones) {
        this.timezones = timezones;
        this.currentTimezone = 0;
    }

}
