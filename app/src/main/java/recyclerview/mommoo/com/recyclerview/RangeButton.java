package recyclerview.mommoo.com.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * Created by yonghak kang on 2017-09-03.
 */

public class RangeButton extends LinearLayout implements View.OnClickListener {

    private Button leftButton;
    private Button rightButton;
    private Button periodButton;

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

    private void initAttribute(Context context, @Nullable AttributeSet attrs, int defStyleAttr){

        if(attrs != null){

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RangeButton, 0, 0);
            try {

                Boolean visible =  (Boolean)ta.getBoolean(R.styleable.RangeButton_visibleDirectionbuttons, false);

                leftButton.setVisibility((visible)?VISIBLE:GONE);
                rightButton.setVisibility((visible)?VISIBLE:GONE);


            } finally {
                ta.recycle();
            }



        }

    }
    private void initLayout(Context context){

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.com_range_button, this);

        leftButton = (Button)findViewById(R.id.comRangeBtnLeft);
        rightButton = (Button)findViewById(R.id.comRangeButtonRight);
        periodButton = (Button)findViewById(R.id.comRangePeriod);
        periodButton.setOnClickListener(this);

    }

   @Override
   public void onClick(View v){
      setSideButtonVisible(true);

   }

   public void setSideButtonVisible(boolean visible){

    int visibleState = (visible)?VISIBLE:GONE;
    leftButton.setVisibility(visibleState);
    rightButton.setVisibility(visibleState);

   }

}
