package com.example.json_0325_gson;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;


import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private static final String API_KEY = "e93251906ee525664a41e827ddc74fdd";
    private static  String CITY = "Taoyuan";
    private static final String COUNTRY_CODE = "TW";

    private TextView weatherInfoTextView;
    private String weatherText;
    private TextView textTaipei,textView_Taoyuan,textView_Hsinchu,textView_Taichun,textView_Tainan,textView_Kaohsiung,textView_Pingtong;
    private boolean flagPush;
    private String citycode;

    private TextView selectedTextView; // 新增選中的 TextView 變量


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTaipei=(TextView)findViewById(R.id.textView_Taipei);
        textView_Taoyuan=(TextView)findViewById(R.id.textView_Taoyuan);
        textView_Hsinchu=(TextView)findViewById(R.id.textView_Hsinchu);
        textView_Taichun=(TextView)findViewById(R.id.textView_Taichun);
        textView_Tainan=(TextView)findViewById(R.id.textView_Tainan);
        textView_Kaohsiung=(TextView)findViewById(R.id.textView_Kaohsiung);
        textView_Pingtong=(TextView)findViewById(R.id.textView_Pingtong);
        selectedTextView = textView_Taoyuan;

        textTaipei.setOnClickListener(new chooseButton());
        textView_Taoyuan.setOnClickListener(new chooseButton());
        textView_Hsinchu.setOnClickListener(new chooseButton());
        textView_Taichun.setOnClickListener(new chooseButton());
        textView_Tainan.setOnClickListener(new chooseButton());
        textView_Kaohsiung.setOnClickListener(new chooseButton());
        textView_Pingtong.setOnClickListener(new chooseButton());




     //   http://api.openweathermap.org/data/2.5/weather?q=Taoyuan,TW&appid=e93251906ee525664a41e827ddc74fdd



    }

    private class chooseButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            textTaipei.setBackgroundColor(0xFF0000ff); // 設定背景顏色
            textView_Taoyuan.setBackgroundColor(0xFF0000ff); // 設定背景顏色
            textView_Hsinchu.setBackgroundColor(0xFF0000ff); // 設定背景顏色
            textView_Taichun.setBackgroundColor(0xFF0000ff); // 設定背景顏色
            textView_Tainan.setBackgroundColor(0xFF0000ff); // 設定背景顏色
            textView_Kaohsiung.setBackgroundColor(0xFF0000ff); // 設定背景顏色
            textView_Pingtong.setBackgroundColor(0xFF0000ff); // 設定背景顏色



            // 將當前按下的TextView的背景色設定為選擇狀態的顏色
            v.setBackgroundColor(0xFFD3D3D3);

                switch (v.getId()){
                    case R.id.textView_Taipei:
                        CITY="Taipei";
                        citycode="台北天氣\n";
                        break;
                    case R.id.textView_Taoyuan:
                        CITY="Taoyuan";
                        citycode="桃園天氣\n";
                        break;
                    case R.id.textView_Hsinchu:
                        CITY="Hsinchu";
                        citycode="新竹天氣\n";
                        break;
                    case R.id.textView_Taichun:
                        CITY="Taichung";
                        citycode="台中天氣\n";
                        break;
                    case R.id.textView_Tainan:
                        CITY="Tainan";
                        citycode="台南天氣\n";
                        break;
                    case R.id.textView_Kaohsiung:
                        CITY="Kaohsiung";
                        citycode="高雄天氣\n";
                        break;
                    case R.id.textView_Pingtong:
                        CITY="Pingtung";
                        citycode="屏東天氣\n";
                        break;
                }



            weatherInfoTextView = findViewById(R.id.weather_info);

            String url = "http://api.openweathermap.org/data/2.5/weather?q=" + CITY + "," + COUNTRY_CODE + "&appid=" + API_KEY;

            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);




            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    WeatherInfo weatherInfo = gson.fromJson(response, WeatherInfo.class);





                    String weatherDescription = weatherInfo.getWeather().get(0).getDescription();
                    double temperature = weatherInfo.getMain().getTemp() - 273.15;

                    weatherText = String.format("%s, %.1f °C", weatherDescription, temperature);
                    weatherInfoTextView.setText(citycode);
                    weatherInfoTextView.append(weatherText);

                }
            };

            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    weatherInfoTextView.setText("Error: " + error.getMessage());
                }
            };
            StringRequest stringRequest = new StringRequest(
                    Request.Method.GET,
                    url,
                    responseListener,
                    errorListener
            );
            queue.add(stringRequest);

        }
    }
}
