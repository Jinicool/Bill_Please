package sg.edu.rp.c346.id20022543.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText userAmt;
    EditText userPax;
    ToggleButton tgSvs;
    ToggleButton tgGst;
    TextView totalBill;
    TextView eachPay;
    EditText discount;
    RadioGroup paymentMode;
    Button split;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userAmt = findViewById(R.id.userAmt);
        userPax = findViewById(R.id.userPax);
        tgSvs = findViewById(R.id.svs);
        tgGst = findViewById(R.id.gst);
        totalBill = findViewById(R.id.totalBill);
        eachPay = findViewById(R.id.eachPay);
        discount = findViewById(R.id.discount);
        paymentMode = findViewById(R.id.paymentMode);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userAmt.getText().toString().trim().length()!= 0 && userPax.getText().toString().trim().length()!=0) {
                    double totalAmt = Double.parseDouble(userAmt.getText().toString());
                    double endAmt = 0.0;
                    if(!tgSvs.isChecked() && !tgGst.isChecked()) {
                        endAmt = totalAmt;
                        
                    }
                    else if(tgSvs.isChecked() && !tgGst.isChecked()) {
                        endAmt = Double.parseDouble(userAmt.getText().toString()) * 1.1;
                    }
                    else if(!tgSvs.isChecked() && tgGst.isChecked()) {
                        endAmt = Double.parseDouble(userAmt.getText().toString()) * 1.07;
                    }
                    else {
                        endAmt = Double.parseDouble(userAmt.getText().toString()) * 1.17;
                    }

                    if(discount.getText().toString().trim().length() != 0) {
                        endAmt *= 1 - Double.parseDouble(discount.getText().toString()) / 100;
                        totalBill.setText("Total  Bill: $" + String.format("%.2f",endAmt));
                    }

                    int person = Integer.parseInt(userPax.getText().toString());

                    if(person != 1) {
                        eachPay.setText("Each Pays: $" + String.format("%.2f",endAmt/person) + " in cash");
                    } else {
                        eachPay.setText("Each Pays: $" + endAmt + " via PayNow to 912345678");
                    }
                    reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            userAmt.setText("");
                            userPax.setText("");
                            discount.setText("");
                            tgSvs.setChecked(false);
                            tgGst.setChecked(false);
                            }
                        });

                }
            }
        });

    }

}