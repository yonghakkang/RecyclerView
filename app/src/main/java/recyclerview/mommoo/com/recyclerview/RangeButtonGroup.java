package recyclerview.mommoo.com.recyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by yonghak kang on 2017-09-06.
 */

public class RangeButtonGroup extends LinearLayout implements View.OnClickListener{
    public LinearLayout container;
    public RangeButton[] children;
    public RangeButtonGroup(Context context) {
        super(context);
        initLayout(context);
    }
    public RangeButtonGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initLayout(context);

        initAttribute(context,attrs,-1);

    }


    public RangeButtonGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initLayout(context);
        initAttribute(context,attrs,defStyleAttr);

    }

    private void initAttribute(Context context, @Nullable AttributeSet attrs, int defStyleAttr){

        if(attrs != null){

            /*TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RangeButton, 0, 0);
            try {

                Boolean visible =  (Boolean)ta.getBoolean(R.styleable.RangeButton_visibleDirectionbuttons, false);

                leftButton.setVisibility((visible)?VISIBLE:GONE);
                rightButton.setVisibility((visible)?VISIBLE:GONE);


            } finally {
                ta.recycle();
            }*/



        }

    }
    private void initLayout(Context context){

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.com_range_button_group, this);


        container = (LinearLayout) this.getChildAt(0);

        int  count  =container.getChildCount();
        children = new RangeButton[count];
        for(int i = 0; i < count ; i++){
          children[i] =(RangeButton) container.getChildAt(i);
          children[i].getPeriodButton().setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v){
        Button selectedButton = (Button) v;
       int  count  =children.length;
       for(int i = 0; i < count ; i++){
         if(selectedButton == children[i].getPeriodButton() ){

             children[i].setSideButtonVisible(true);
         }else{
          children[i].setSideButtonVisible(false);
         }
       }
    }
}
