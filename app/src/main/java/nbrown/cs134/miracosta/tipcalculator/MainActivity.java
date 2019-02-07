package nbrown.cs134.miracosta.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //member variables to format as currency or percent (NumberFormat)
    NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.getDefault());
    NumberFormat percent = NumberFormat.getPercentInstance(Locale.getDefault());

    private EditText amountEditText;
    private TextView percentTextView;
    private TextView tipTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

    //member variables for our model
    private Bill currentBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize all member variables in onCreate
        amountEditText = findViewById(R.id.amountEditText);
        percentTextView = findViewById(R.id.percentTextView);
        tipTextView = findViewById(R.id.tipTextView);
        totalTextView = findViewById(R.id.totalTextView);
        percentSeekBar = findViewById(R.id.percentSeekbar);

        //Initialize our Bill model
        currentBill = new Bill();
        //set tip percent to match the seek bar
        currentBill.setTipPercent(percentSeekBar.getProgress()/100.0);

        //connect code to the event onProgressChanges for seekBar
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //update our model
                currentBill.setTipPercent(progress / 100.0);

                //change the label of the tip percent
                percentTextView.setText(percent.format(currentBill.getTipPercent()));
                tipTextView.setText(currency.format(currentBill.getTipAmount()));
                totalTextView.setText(currency.format(currentBill.getTotalAmount()));
                //update our model

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //does nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //does nothing
            }
        });

        //connect code to the event onTextChanged for EditText
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //does nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Update our model (currentBill)
                currentBill.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {
                //does nothing
            }
        });
    }
}
