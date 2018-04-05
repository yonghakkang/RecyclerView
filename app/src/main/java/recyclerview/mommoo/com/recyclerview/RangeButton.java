package recyclerview.mommoo.com.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by yonghak kang on 2017-09-03.
 */

public class RangeButton extends LinearLayout implements View.OnClickListener {

    public static final int TYPE_DAY = 0;
    public static final int TYPE_WEEK = 1;
    public static final int TYPE_MONTH = 2;
    public static final int TYPE_QUARTER = 3;
    public static final int TYPE_YEAR = 4;

    private Button leftButton;
    private Button rightButton;
    private Button periodButton;
    private RangeType type;
    private int increase = 0;

    public RangeButton(Context context) {
        super(context);
        initLayout(context);
    }
    public RangeButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initLayout(context);

        initAttribute(context,attrs,-1);

    }


    public RangeButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initLayout(context);
        initAttribute(context,attrs,defStyleAttr);

    }

    public String getWeek(){
        Date _date = new Date();
        Calendar _calendar = Calendar. getInstance();
        _calendar.setTime(_date);

        int _month = _calendar.get(Calendar.MONTH)+1;
        int _dayOfWeek = _calendar.get(Calendar.DAY_OF_WEEK);
        int _dayOfWeekInMonth = _calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);

        String[] engNumbers = new String[]{"th","st","nd"};
        String strMonth = Integer.toString(_month);
        String strDayOfWeek = Integer.toString(_dayOfWeek);
        String strDayOfWeekInMonth = (_dayOfWeekInMonth<=2)?Integer.toString(_dayOfWeekInMonth)+engNumbers[_dayOfWeekInMonth]:Integer.toString(_dayOfWeekInMonth)+engNumbers[0];


        return (strMonth)+"/"+strDayOfWeekInMonth;

    }

    public String getDay() {
        DateFormat df = new DateFormat();

        Date _date = new Date();
        Calendar _calendar = Calendar. getInstance();
        _calendar.setTime(_date);

        String _day = df.format("yyyy-MM/dd", new java.util.Date()).toString();


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM/dd");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(_day));
            c.add(Calendar.DATE, this.increase);  // number of days to add
            _day = sdf.format(c.getTime());
            return _day;
        }catch (ParseException e ){
            e.getMessage();
        }

        return null;


    }
    public String getMonth() {
        DateFormat df = new DateFormat();

        Date _date = new Date();
        Calendar _calendar = Calendar. getInstance();
        _calendar.setTime(_date);

        String _month = df.format("MMM", new java.util.Date()).toString();


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MMM");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(_month));
            c.add(Calendar.MONTH, this.increase);  // number of Month to add
            _month = sdf.format(c.getTime());
            return _month;
        }catch (ParseException e ){
            e.getMessage();
        }

        return null;


    }

    public String getQuarter() {
        DateFormat df = new DateFormat();


        Date _date = new Date();

        Calendar _calendar = Calendar. getInstance();
        _calendar.setTime(_date);
        
        int _courrentYear = _calendar.get(Calendar.YEAR);

        String _month = df.format("M", new java.util.Date()).toString();



        SimpleDateFormat sdf = new SimpleDateFormat("M");
        Calendar c = Calendar.getInstance();
        c.setTime(_date);
        c.add(Calendar.MONTH, this.increase*3);  // number of Month to add

        int newYear = c.get(Calendar.YEAR);

        _month = sdf.format(c.getTime());
        int quarter = (Integer.parseInt(_month) / 3) + 1;

        if(newYear !=_courrentYear){
            return newYear+" Q"+ String.valueOf(quarter);
        }else{
            return "Q"+ String.valueOf(quarter);
        }

    }

    private void initAttribute(Context context, @Nullable AttributeSet attrs, int defStyleAttr){

        if(attrs != null){

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RangeButton, 0, 0);
            try {

                Boolean visible =  (Boolean)ta.getBoolean(R.styleable.RangeButton_visibleDirectionbuttons, false);

                String label = (String)ta.getString(R.styleable.RangeButton_label);

                type = RangeType.fromId(ta.getInt(R.styleable.RangeButton_type,0));

                leftButton.setVisibility((visible)?VISIBLE:GONE);
                rightButton.setVisibility((visible)?VISIBLE:GONE);
                periodButton.setText(getWeek());

            } finally {
                ta.recycle();
            }



        }

    }


    public Button getPeriodButton() {
        return periodButton;
    }

    private void initLayout(Context context){

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.com_range_button, this);

        leftButton = (Button)findViewById(R.id.comRangeBtnLeft);
        leftButton.setOnClickListener(this);

        rightButton = (Button)findViewById(R.id.comRangeButtonRight);
        rightButton.setOnClickListener(this);

        periodButton = (Button)findViewById(R.id.comRangePeriod);
        periodButton.setOnClickListener(this);

    }

   @Override
   public void onClick(View v){
        if(v == periodButton){
            setSideButtonVisible(true);
        }else if(v == leftButton){
            this.increase--;

            //int value = Integer.parseInt((String)periodButton.getText());
            //periodButton.setText(String.valueOf(--value));
            periodButton.setText(getQuarter());
        }else if(v == rightButton){
            this.increase++;
            //int value = Integer.parseInt((String)periodButton.getText());
            //periodButton.setText(String.valueOf(++value));
            periodButton.setText(getQuarter());
        }

   }

   public void setSideButtonVisible(boolean visible){

    int visibleState = (visible)?VISIBLE:GONE;
    leftButton.setVisibility(visibleState);
    rightButton.setVisibility(visibleState);

   }

}
