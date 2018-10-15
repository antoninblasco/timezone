package fr.dream.antoninb.timezone;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class TimezoneViewModel extends ViewModel {
    private String[] timezones;
    private int currentTimezone;

    private MutableLiveData<Calendar> localDate;
    private MutableLiveData<Calendar> convertedDate;
    private MutableLiveData<String[]> timezoneList;

    public TimezoneViewModel() {
        localDate = new MutableLiveData<>();
        localDate.setValue(Calendar.getInstance());
        timezoneList = new MutableLiveData<>();
        timezoneList.setValue(timezones);
        convertedDate = new MutableLiveData<>();
        convertDate();
    }

    public String[] getTimezones() {
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

    public void setTimezones(String[] timezones) {
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

    private void convertDate() {
        Calendar toDate = Calendar.getInstance(TimeZone.getTimeZone(timezones[currentTimezone]));
        toDate.setTime(localDate.getValue().getTime());
        convertedDate.setValue(toDate);
    }
}
