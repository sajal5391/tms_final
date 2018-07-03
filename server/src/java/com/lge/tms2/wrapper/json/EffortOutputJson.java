/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.lge.tms2.wrapper.json;

import com.lge.tms2.utils.Util;
import com.lge.tms2.wrapper.CalendarDate;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ramesh.nagarajan
 */
public class EffortOutputJson implements Serializable {  
    private static long serialVersionUID = 1L;
    private int week_number;
    private CalendarDate color_dates;
    private List<EffortInputJson> time_sheet;

    /**
     * @return the time_sheet
     */
    public List<EffortInputJson> getTime_sheet() {
        return time_sheet;
    }

    /**
     * @param time_sheet the time_sheet to set
     */
    public void setTime_sheet(List<EffortInputJson> time_sheet) {
        this.time_sheet = time_sheet;
    }

    /**
     * @return the week_number
     */
    public int getWeek_number() {
        return week_number;
    }

    /**
     * @param week_number the week_number to set
     */
    public void setWeek_number(int week_number) {
        this.week_number = week_number;
    }
    
    /**
     * @param date the week_number to set
     */
    public void setWeekNumber(String date) {
        System.out.println("String date: " + date);
        setWeekNumber(Util.getDateFromSQL(date));
    }
    
    public void setWeekNumber(Date date) {
        try {
            System.out.println("Date date: " + date);
           this.week_number =  Util.getWeekNo(date);
        }catch(Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * @return the color_dates
     */
    public CalendarDate getColor_dates() {
        return color_dates;
    }

    /**
     * @param color_dates the color_dates to set
     */
    public void setColor_dates(CalendarDate color_dates) {
        this.color_dates = color_dates;
    }
}
