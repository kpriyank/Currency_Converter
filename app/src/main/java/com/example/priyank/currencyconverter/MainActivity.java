package com.example.priyank.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner spinner,spinner2;
    TextView result;
    Button changer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        amount=findViewById(R.id.editText);
        result=findViewById(R.id.textView);
        changer=findViewById(R.id.convert);

        spinner=findViewById(R.id.spinner);
        String[] items = new String[]{"INR","AUD","BHD","CHF","EUR","GBP","JOD","KWD","KYD","OMR","USD"};
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        spinner.setAdapter(adapter);

        spinner2=findViewById(R.id.spinner2);
        String[] item = new String[]{"INR","AUD","BHD","CHF","EUR","GBP","JOD","KWD","KYD","OMR","USD"};
        ArrayAdapter<String>adapter2=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,item);
        spinner2.setAdapter(adapter2);

        changer.setOnClickListener(new View.OnClickListener() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://currency-exchange.p.mashape.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            @Override
            public void onClick(View v) {
                final String value=amount.getText().toString();
                CurrnConvService service=retrofit.create(CurrnConvService.class);
                String val1=spinner.getSelectedItem().toString();
                String val2=spinner2.getSelectedItem().toString();
                Call<String> stringCall=service.Currency("Fm7BtZLe9EmshYcc4HDw7LPN7Ofsp1iZ2OEjsnt5YozbekWyfx",val1,value,val2);
                stringCall.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String responsestring=response.body();
                        Float res=Float.valueOf(responsestring);
                        Float res2=Float.valueOf(value);
                        Float Disp=res*res2;
                        String s=Float.toString(Disp);
                        result.setText(s);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });

    }


}
