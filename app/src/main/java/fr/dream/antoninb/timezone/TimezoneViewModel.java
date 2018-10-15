package fr.dream.antoninb.timezone;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;

public class TimezoneViewModel extends ViewModel {
    private ArrayList<String> timezones;
    private int currentTimezone;

    private MutableLiveData<Calendar> localDate;
    private MutableLiveData<Calendar> convertedDate;
    private MutableLiveData<String[]> timezoneList;

    public TimezoneViewModel(ArrayList<String> timezones, MutableLiveData<Calendar> localDate, MutableLiveData<Calendar> convertedDate, MutableLiveData<String[]> timezoneList) {
        this.timezones = timezones;
        this.currentTimezone = 0;
        this.localDate = localDate;
        this.convertedDate = convertedDate;
        this.timezoneList = timezoneList;
    }

    public ArrayList<String> getTimezones() {
        return timezones;
    }

    public int getCurrentTimezone() {
        return currentTimezone;
    }

    public MutableLiveData<Calendar> getLocalDate() {
        return localDate;
    }

    public MutableLiveData<Calendar> getConvertedDate() {
        return convertedDate;
    }

    public MutableLiveData<String[]> getTimezoneList() {
        return timezoneList;
    }

    public void setTimezones(ArrayList<String> timezones) {
        this.timezones = timezones;
    }

    public void setCurrentTimezone(int currentTimezone) {
        this.currentTimezone = currentTimezone;
    }

    public void setLocalDate(MutableLiveData<Calendar> localDate) {
        this.localDate = localDate;
    }

    public void setConvertedDate(MutableLiveData<Calendar> convertedDate) {
        this.convertedDate = convertedDate;
    }

    public void setTimezoneList(MutableLiveData<String[]> timezoneList) {
        this.timezoneList = timezoneList;
    }
}
