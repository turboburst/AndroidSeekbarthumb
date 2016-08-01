package app.num.androidseekbarthumb;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.florescu.android.rangeseekbar.RangeSeekBar;

import java.util.Arrays;

import app.num.androidseekbarthumb.CustomerViews.WheelView;
import app.num.androidseekbarthumb.Fragments.WarningDialog;

public class MainActivity extends AppCompatActivity {

    private RangeSeekBar seekBarInteger, seekBarDouble;
    private TextView minTextInt, maxtextInt, minTextDouble, maxTextDouble;
    private EditText minValueEdit, maxValueEdit;
    private WheelView minwheelView, maxwheelView;
    private int absoluteminvalue, absolutemaxvalue;
    private String[] datas = new String[]{"140", "141", "142", "143", "144", "145", "146",
                                            "147", "148", "149", "150", "151", "152", "153",
                                            "154", "155", "156", "157", "158", "159", "160",
                                            "161", "162", "163", "164", "165", "166"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBarInteger = (RangeSeekBar) findViewById(R.id.seekbar);
        minTextInt = (TextView) findViewById(R.id.seekValuemin);
        maxtextInt = (TextView) findViewById(R.id.seekValuemax);
        seekBarDouble = (RangeSeekBar) findViewById(R.id.seekbarDouble);
        minTextDouble = (TextView) findViewById(R.id.seekValueminDouble);
        maxTextDouble = (TextView) findViewById(R.id.seekValuemaxDouble);

        minValueEdit = (EditText) findViewById(R.id.MinSelectedid);
        maxValueEdit = (EditText) findViewById(R.id.MaxSelectedid);

        minwheelView = (WheelView) findViewById(R.id.minwheelviewid);
        minwheelView.setOffset(1);
        minwheelView.setItems(Arrays.asList(datas));
        minwheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {

            }
        });

        maxwheelView = (WheelView) findViewById(R.id.maxwheelviewid);
        maxwheelView.setOffset(1);
        maxwheelView.setItems(Arrays.asList(datas));
        maxwheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {

            }
        });

        seekBarInteger.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {

            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                Log.e("value", minValue + "  " + maxValue);
                minValueEdit.setText(minValue + "");
                maxValueEdit.setText(maxValue + "");
            }
        });
        absoluteminvalue = seekBarInteger.getAbsoluteMinValue().intValue();
        absolutemaxvalue = seekBarInteger.getAbsoluteMaxValue().intValue();

        seekBarDouble.setRangeValues(0.0, 100.0); // if we want to set progrmmatically set range of seekbar
        seekBarDouble.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Double>() {


            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Double minValue, Double maxValue) {
                Log.e("value", minValue + "  " + maxValue);
                minTextDouble.setText("Min Value " + minValue);
                maxTextDouble.setText("Max value " + maxValue);

            }

        });

        minValueEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s)
            {
                try
                {
                    if(minValueEdit.getText().toString() != null && !minValueEdit.getText().toString().equals(""))
                    {
                        seekBarInteger.setSelectedMinValue(Integer.valueOf(minValueEdit.getText().toString()));
                    }
                }
                catch(NumberFormatException exp)
                {
                    WarningDialog warningDialog = new WarningDialog();
                    warningDialog.show(getSupportFragmentManager(), "warning");

                }
            }
        });

        maxValueEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s)
            {
                try
                {
                    if(maxValueEdit.getText().toString() != null && !maxValueEdit.getText().toString().equals(""))
                    {
                        if(Integer.valueOf(maxValueEdit.getText().toString()) != absolutemaxvalue)
                        {
                            absolutemaxvalue = Integer.valueOf(maxValueEdit.getText().toString()) + 100;
                        }

                        seekBarInteger.setRangeValues(absoluteminvalue,  absolutemaxvalue);
                        seekBarInteger.setSelectedMinValue(Integer.valueOf(minValueEdit.getText().toString()));
                        seekBarInteger.setSelectedMaxValue(Integer.valueOf(maxValueEdit.getText().toString()));
                    }
                }
                catch(NumberFormatException exp)
                {
                    WarningDialog warningDialog = new WarningDialog();
                    warningDialog.show(getSupportFragmentManager(), "warning");
                }

            }
        });
    }
}
