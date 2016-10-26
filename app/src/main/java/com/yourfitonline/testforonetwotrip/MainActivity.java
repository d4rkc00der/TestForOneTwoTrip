package com.yourfitonline.testforonetwotrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.ExtractedTextRequest;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    final static String WORD = "onetwotrip";

    private int m;
    private int n;

    private EditText etInput;
    private TextView tvOutput;
    private String result="";
    private String delimiter = "\n";
    private boolean isFileLoaded = false;
    ArrayList<Character> matrix;
    static long timeStart;
    static long timeStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = (EditText)findViewById(R.id.etInput);
        tvOutput = (TextView)findViewById(R.id.tvOutput);
        matrix = new ArrayList<>();
    }

    public void findEntriesLinear(View view){
        boolean isFound = false ;

        timeStart = System.currentTimeMillis();
        // Looking for each letter in our word
        // Difficulty of algorithm is O(длинна слова * m*n)
        // Memory Usage O(1)
        for(int letterCount = 0; letterCount < WORD.length(); letterCount++) {

            // Looking for each String line in ArrayList
            isFound = false;
            for(int dataIndex = 0; dataIndex < matrix.size(); dataIndex++) {
                if(WORD.toLowerCase().charAt(letterCount)==matrix.get(dataIndex)) {
                    result+= WORD.charAt(letterCount) + "-"+findIndex(dataIndex) + "\n";
                    matrix.set(dataIndex, ' ');
                    isFound=true;
                    break;
                }

            }
            if(!isFound){
                System.out.println("Impossible");
                break;
            }

        }

        timeStop = System.currentTimeMillis();
        System.out.println((timeStop-timeStart) + "ms");
        if(isFound) {
            tvOutput.setText(result);
        }
    }

    public String findIndex(int lineIndex){
        if(lineIndex!=0)  {
            return "(" + Math.abs(lineIndex/n) + "," + lineIndex % n + ")";
        } else {
            return "(0,0)";
        }
    }
    public void loadFromFile(View view){

        if(!isFileLoaded) {
            String[] data = {};
            ArrayList<Character> list = new ArrayList<>();
            Character c;
            data = etInput.getText().toString().split(delimiter);
            isFileLoaded = true;


            if(data!=null) {
                m = Integer.parseInt(data[0].split(" ")[0]);
                n = Integer.parseInt(data[0].split(" ")[1]);
                Log.d("Log","m = " + m);
                Log.d("Log","n = " + n);


                for(int i = 1; i < data.length; i++) {

                    for(int j =0; j< data[i].length(); j++) {
                        c = (data[i]).charAt(j);
                        matrix.add(c);
                    }


                }
            }
        }



    }
}
