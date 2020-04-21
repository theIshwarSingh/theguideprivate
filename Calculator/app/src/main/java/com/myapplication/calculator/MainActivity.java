package com.myapplication.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    Button btn0,btn1, btn2 , btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn_add, btn_sub, btn_mul, btn_div, btn_dot, btn_clear, btn_equal;
    TextView txtView_equation, txtView_result;
    ImageView image_del;

    private  boolean btnpres = false;

    private double input1 = 0;
    private int input2 = 0;
    private char oper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn_dot = findViewById(R.id.dotbtn);
        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_minus);
        btn_mul = findViewById(R.id.btn_mult);
        btn_div = findViewById(R.id.btn_div);
        txtView_equation = findViewById(R.id.textView2);
        txtView_result = findViewById(R.id.textView3);
        btn_clear = findViewById(R.id.clear);
        image_del = findViewById(R.id.del);
        btn_equal = findViewById(R.id.btn_equal);





       final View.OnClickListener cal = new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               final int id = v.getId();
               String src = txtView_equation.getText().toString();
               String sec = src.substring(input2,src.length());
               switch(id){
                   case R.id.btn_0:
                       txtView_equation.setText(txtView_equation.getText() + "0");
                       break;

                   case R.id.btn_1:
                       txtView_equation.setText(txtView_equation.getText() + "1");
                       break;

                   case R.id.btn_2:
                       txtView_equation.setText(txtView_equation.getText() + "2");
                       break;
                   case R.id.btn_3:
                       txtView_equation.setText(txtView_equation.getText() + "3");
                       break;
                   case R.id.btn_4:
                       txtView_equation.setText(txtView_equation.getText() + "4");
                       break;
                   case R.id.btn_5:
                       txtView_equation.setText(txtView_equation.getText() + "5");
                       break;
                   case R.id.btn_6:
                       txtView_equation.setText(txtView_equation.getText() + "6");
                       break;
                   case R.id.btn_7:
                       txtView_equation.setText(txtView_equation.getText() + "7");
                       break;
                   case R.id.btn_8:
                       txtView_equation.setText(txtView_equation.getText() + "8");
                       break;
                   case R.id.btn_9:
                       txtView_equation.setText(txtView_equation.getText() + "9");
                       break;

                   case R.id.dotbtn:
                       txtView_equation.setText(txtView_equation.getText() + ".");
                       break;

                   case R.id.btn_equal:
                       if(btnpres) {
                           if (oper == '+') {
                               double input3 = Double.parseDouble(sec);
                               input3 +=input1;
                               txtView_result.setText(String.valueOf(input3));
                           }
                           if (oper == '*') {
                               double input3 = Double.parseDouble(sec);
                               input3 *=input1;
                               txtView_result.setText(String.valueOf(input3));
                           }
                           if (oper == '/') {
                               double input3 = Double.parseDouble(sec);
                               input3 = input1 / input3;
                               txtView_result.setText(String.valueOf(input3));
                           }
                           if (oper == '-') {
                               double input3 = Double.parseDouble(sec);
                               input3 = input1 - input3;
                               txtView_result.setText(String.valueOf(input3));
                           }
                       }
                       break;
                       case R.id.btn_add:

                       input2 = src.length() +1;
                       input1 = Double.parseDouble(src);
                       txtView_equation.setText(txtView_equation.getText() + "+");
                       btnpres = true;
                       oper = '+';
                       break;

                   case R.id.btn_mult:
                       input2 = src.length() +1;
                       input1 = Double.parseDouble(src);
                       txtView_equation.setText(txtView_equation.getText() + "*");
                       btnpres = true;
                       oper = '*';
                       break;
                   case R.id.btn_div:
                       input2 = src.length() +1;
                       input1 = Double.parseDouble(src);
                       txtView_equation.setText(txtView_equation.getText() + "/");
                       btnpres = true;
                       oper = '/';
                       break;
                   case R.id.btn_minus:
                       input2 = src.length() +1;
                       input1 = Double.parseDouble(src);
                       txtView_equation.setText(txtView_equation.getText() + "-");
                       btnpres = true;
                       oper = '-';
                       break;
               }

           }
       };
        btn0.setOnClickListener(cal);
        btn1.setOnClickListener(cal);
        btn2.setOnClickListener(cal);
        btn3.setOnClickListener(cal);
        btn4.setOnClickListener(cal);
        btn5.setOnClickListener(cal);
        btn6.setOnClickListener(cal);
        btn7.setOnClickListener(cal);
        btn8.setOnClickListener(cal);
        btn9.setOnClickListener(cal);
        btn_dot.setOnClickListener(cal);
        btn_add.setOnClickListener(cal);
        btn_sub.setOnClickListener(cal);
        btn_div.setOnClickListener(cal);
        btn_mul.setOnClickListener(cal);
        btn_equal.setOnClickListener(cal);


        image_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String equa = txtView_equation.getText().toString();
                int len = equa.length();
                if(len> 0){
                    equa = equa.substring(0,len-1);
                    txtView_equation.setText(equa);
                }
            }
        });


        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtView_equation.setText("");
                input1 = 0;
                input2 = 0;
            }
        });
        
    }
}
