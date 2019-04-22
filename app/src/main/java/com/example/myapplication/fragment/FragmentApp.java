package com.example.myapplication.fragment;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.event.BluetoothEvent;
import com.example.myapplication.event.BluetoothEventIn;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class FragmentApp extends Fragment {

    public FragmentApp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app, container, false);
    }



    private final static String TAG = com.example.myapplication.MainActivity.class.getSimpleName();
    final String LOG_TAG = "myLogs";

    //Timer
    TextView text1;
    TextView timer;
    int dataTimer = 10;
    int dataTimertt; //установка таймера
    int dataPar = 40;
    int dataIR = 40;
    int dataVibro = 10;
    int vibor;
    CountDownTimer MyTimer;
    //timerTemp
    Timer timerT;
    TimerTask mTimerTask;
    //End Timer
    String dataTempB; //получение температуры String
    String dataVibroSet; //показания вибро
    String dataSvetSet;  //показатель света
    long then = 0; //продолжительное нажатие кнопки света
    char[] sbprintToArray;
    String[] sbprintArrayStr;


    boolean flag = false, flag2 = true, flag5 = false, flag6 = false, flag7 = true, flag8 = true, flag9 = true, flag10 = true, flag11 = true,
            flag12 = true, flag15 = false, flag16 = false, flag18 = false, flag19 = false, flag4 = false, flagVanna = false, flagVanna2 = false, flagVanna3 = false,
            flagVanna4 = false, flagVanna5 = false;


    boolean falgImageButton13 = false;


    private static final int REQUEST_ENABLE_BT = 1;
    BluetoothAdapter bluetoothAdapter;
    ArrayList<String> pairedDeviceArrayList;
    ListView listViewPairedDevice;
    FrameLayout ButPanel;
    ArrayAdapter<String> pairedDeviceAdapter;
    private UUID myUUID;

    private StringBuilder sb = new StringBuilder();
    public TextView textInfo, temp, temp2, textTemp, textTempIR, textVibro, textSvet, d10, textVishi, d11, d12, d13;

    ImageButton ImageButton1, ImageButton2, ImageButton4, ImageButton5, ImageButton6, ImageButton7, ImageButton8, ImageButton9, ImageButton10, ImageButton11, ImageButton12, ImageButton13,
            ImageButton15, ImageButton16, ImageButton17, ImageButton18, ImageButton19,
            imageButton, imageButton2, imageButton3, imageButton4, imageButton5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    } // END onCreate


    @Override
    public void onStart() { // Запрос на включение Bluetooth
        super.onStart();
        EventBus.getDefault().register(this);

        d10 = getActivity().findViewById(R.id.d10);
        temp = getActivity().findViewById(R.id.temp);
        temp2 = getActivity().findViewById(R.id.temp2);
        textTemp = getActivity().findViewById(R.id.textTemp);
        textTempIR = getActivity().findViewById(R.id.textTempIR);
        textVibro = getActivity().findViewById(R.id.textVibro);
        textVishi = getActivity().findViewById(R.id.textVishi);
        //textSvet = (TextView)findViewById(R.id.textSvet);

        text1 = getActivity().findViewById(R.id.text1);
        timer = getActivity().findViewById(R.id.timer);

        ImageButton1 = getActivity().findViewById(R.id.ImageButton1);
        ImageButton2 = getActivity().findViewById(R.id.ImageButton2);
        ImageButton4 = getActivity().findViewById(R.id.ImageButton4);
        ImageButton7 = getActivity().findViewById(R.id.ImageButton7);
        ImageButton5 = getActivity().findViewById(R.id.ImageButton5);
        ImageButton6 = getActivity().findViewById(R.id.ImageButton6);
        //ImageButton8 = (ImageButton) findViewById(R.id.ImageButton8);
        ImageButton9 = getActivity().findViewById(R.id.ImageButton9);
        //ImageButton10 = (ImageButton) findViewById(R.id.ImageButton10);
        ImageButton11 = getActivity().findViewById(R.id.ImageButton11);
        ImageButton12 = getActivity().findViewById(R.id.ImageButton12);
        ImageButton13 = getActivity().findViewById(R.id.ImageButton13);
        ImageButton15 = getActivity().findViewById(R.id.ImageButton15);
        ImageButton16 = getActivity().findViewById(R.id.ImageButton16);
        ImageButton18 = getActivity().findViewById(R.id.ImageButton18); //душ виши
        ImageButton19 = getActivity().findViewById(R.id.ImageButton19);
        ImageButton17 = getActivity().findViewById(R.id.ImageButton17);

        imageButton = getActivity().findViewById(R.id.imageButton);
        imageButton2 = getActivity().findViewById(R.id.imageButton2);
        imageButton3 = getActivity().findViewById(R.id.imageButton3);
        imageButton4 = getActivity().findViewById(R.id.imageButton4);
        imageButton5 = getActivity().findViewById(R.id.imageButton5);


        ImageButton13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            { //плюс
                switch(vibor) {
                    case 1: //таймер
                        EventBus.getDefault().post(new BluetoothEventIn("T"));
                        break;

                    case 2: //установка температуры пара
                        EventBus.getDefault().post(new BluetoothEventIn("P"));
                        break;

                    case 4: //установка температуры IR
                        EventBus.getDefault().post(new BluetoothEventIn("X"));
                        break;

                    case 6:
                        EventBus.getDefault().post(new BluetoothEventIn("N"));
                        break;

                    case 7: //установка вибро
                        EventBus.getDefault().post(new BluetoothEventIn("v"));

                    default:
                        break;
                }
            }});

        ImageButton17.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            { //минус
                switch(vibor) {
                    case 1:
                        EventBus.getDefault().post(new BluetoothEventIn("t"));
                        break;

                    case 2:
                        EventBus.getDefault().post(new BluetoothEventIn("p"));
                        break;

                    case 4:
                        EventBus.getDefault().post(new BluetoothEventIn("x"));

                        break;

                    case 6:
                        EventBus.getDefault().post(new BluetoothEventIn("n"));
                        break;

                    case 7:
                        EventBus.getDefault().post(new BluetoothEventIn("V"));
                        break;

                    default:
                        break;
                }
            }});

//установка таймера
        ImageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag2 == true) {
                    ImageButton2.setImageResource(R.drawable.timerg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton7.setImageResource(R.drawable.steam);
                    flag2 = false;
                    flag7 = true;
                    flag8 = true;
                    flag9 = true;
                    flag10 = true;
                    flag11 = true;
                    flag12 = true;
                    vibor = 1;
                } else {
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag2 = true;
                    vibor = 0;
                }
            }
        });
//установка вибромассажа
        ImageButton7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag7 == true) {
                    ImageButton7.setImageResource(R.drawable.steamg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag7 = false;
                    flag2 = true;
                    vibor = 2;
                } else {
                    ImageButton7.setImageResource(R.drawable.steam);
                    flag7 = true;
                    vibor = 0;
                }
            }
        });

        //установка IR
        ImageButton9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag9 == true) {
                    ImageButton9.setImageResource(R.drawable.infaredg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton7.setImageResource(R.drawable.steam);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag9 = false;
                    flag7 = true;
                    flag8 = true;
                    flag2 = true;
                    flag10 = true;
                    flag11 = true;
                    flag12 = true;
                    vibor = 4;
                } else {
                    ImageButton9.setImageResource(R.drawable.infared);
                    flag9 = true;
                    vibor = 0;
                }
            }
        });

//установка душа виши
        ImageButton11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag11 == true) {
                    ImageButton11.setImageResource(R.drawable.vichyg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    ImageButton7.setImageResource(R.drawable.steam);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag11 = false;
                    flag7 = true;
                    flag8 = true;
                    flag9 = true;
                    flag10 = true;
                    flag2 = true;
                    flag12 = true;
                    vibor = 6;
                } else {
                    ImageButton11.setImageResource(R.drawable.vichy);
                    flag11 = true;
                    vibor = 0;
                }
            }
        });
//установка вибро
        ImageButton12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag12 == true) {
                    ImageButton12.setImageResource(R.drawable.vibromassajeg);
                    //ImageButton8.setImageResource(R.drawable.facefan);
                    ImageButton9.setImageResource(R.drawable.infared);
                    //ImageButton10.setImageResource(R.drawable.chromo);
                    ImageButton11.setImageResource(R.drawable.vichy);
                    ImageButton7.setImageResource(R.drawable.steam);
                    ImageButton2.setImageResource(R.drawable.timer);
                    flag12 = false;
                    flag7 = true;
                    flag8 = true;
                    flag9 = true;
                    flag10 = true;
                    flag11 = true;
                    flag2 = true;
                    vibor = 7;
                } else {
                    ImageButton12.setImageResource(R.drawable.vibromassaje);
                    flag12 = true;
                    vibor = 0;
                }
            }
        });
//onoff
        ImageButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flag == true) {
                    EventBus.getDefault().post(new BluetoothEventIn("a"));
                    ImageButton1.setEnabled(false);
                    ImageButton1.setImageAlpha(100);
                    flag = false;
                    flag6 = false;
                }
                else {
                    EventBus.getDefault().post(new BluetoothEventIn("A"));
                    ImageButton1.setEnabled(false);
                    ImageButton1.setImageAlpha(100);

                    flag = true;
                }
            }
        });
//вентилятор
        ImageButton5.setOnClickListener(new View.OnClickListener() {public void onClick(View v) //вентилятор
        {   if (flag5 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("B"));
            flag5 = true;
            // ImageButton5.setImageResource(R.drawable.ventg);

        }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("b"));
            flag5 = false;
            // ImageButton5.setImageResource(R.drawable.vent);
        } }});
//пар
        ImageButton6.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {
            if (flag6 == false && flag == true ) {
                EventBus.getDefault().post(new BluetoothEventIn("E"));
                //ImageButton6.setEnabled(false);
                ImageButton6.setImageAlpha(100);
                ImageButton6.setImageResource(R.drawable.parg);
                flag6 = true;
                ImageButton15.setImageResource(R.drawable.ir);
                flag15 = false;
            }
            else {
                EventBus.getDefault().post(new BluetoothEventIn("e"));
                ImageButton6.setImageResource(R.drawable.par);
                ImageButton6.setImageAlpha(255);
                flag6 = false;
            }
        }});
//IR
        ImageButton15.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flag15 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("D"));
            ImageButton15.setImageResource(R.drawable.irg);
            flag15 = true;
            ImageButton6.setImageResource(R.drawable.par);
            flag6 = false; ImageButton6.setImageAlpha(255);}
        else {
            EventBus.getDefault().post(new BluetoothEventIn("d"));
            ImageButton15.setImageResource(R.drawable.ir);
            flag15 = false;}}});
//свет
        ImageButton16.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flag16 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("C"));
            flag16 = true;
            //ImageButton16.setImageResource(R.drawable.svetg);
        }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("c"));
            //ImageButton16.setImageResource(R.drawable.svet);
            flag16 = false;}}});
//душ виши
        ImageButton18.setOnClickListener(new View.OnClickListener() {public void onClick(View v) //душ виши
        {   if (flag18 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("G"));
            ImageButton18.setImageResource(R.drawable.vodag);

        }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("g"));
            ImageButton18.setImageResource(R.drawable.voda);
        }
            flag18 = !flag18; }});
//вибро
        ImageButton19.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flag19 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("H"));
            ImageButton19.setImageResource(R.drawable.vibrog);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("h"));
            ImageButton19.setImageResource(R.drawable.vibro);  }
            flag19 = !flag19; }});
//ТАЙМЕР
        ImageButton4.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flag4 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("I"));
            ImageButton4.setImageResource(R.drawable.timeg);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("i"));
            ImageButton4.setImageResource(R.drawable.time);  }
            flag4 = !flag4; }});

//ванна гидро1
        imageButton.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flagVanna == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("J"));
            imageButton.setImageResource(R.drawable.gidro1);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("j"));
            imageButton.setImageResource(R.drawable.gidro1g);  }
            flagVanna = !flagVanna; }});
//ванна гидро2
        imageButton2.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flagVanna2 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("K"));
            imageButton2.setImageResource(R.drawable.gidro2);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("k"));
            imageButton2.setImageResource(R.drawable.gidro2g);  }
            flagVanna2 = !flagVanna2; }});
//ванна гидро3
        imageButton3.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flagVanna3 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("L"));
            imageButton3.setImageResource(R.drawable.gidro3);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("l"));
            imageButton3.setImageResource(R.drawable.gidro3g);  }
            flagVanna3 = !flagVanna3; }});
//ванна гидро4
        imageButton4.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flagVanna4 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("M"));
            imageButton4.setImageResource(R.drawable.gidro4);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("m"));
            imageButton4.setImageResource(R.drawable.gidro4g);  }
            flagVanna4 = !flagVanna4; }});

//ванна свет
        imageButton5.setOnClickListener(new View.OnClickListener() {public void onClick(View v)
        {   if (flagVanna5 == false && flag == true) {
            EventBus.getDefault().post(new BluetoothEventIn("O"));
            imageButton5.setImageResource(R.drawable.svetvanna);  }
        else {
            EventBus.getDefault().post(new BluetoothEventIn("o"));
            imageButton5.setImageResource(R.drawable.svetvannag);  }
            flagVanna5 = !flagVanna5; }});
    }

    @Override
    public void onDestroy() { // Закрытие приложения
        super.onDestroy();
    }

    //Bluetooth данные
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(BluetoothEvent event) {
        sbprintArrayStr = event.getData().split(",");

        if (sbprintArrayStr[0].equals("A")) {
            ImageButton1.setEnabled(true);
            ImageButton1.setImageResource(R.drawable.onoffg);
            ImageButton1.setImageAlpha(255);
            flag = true;
            d10.setText("ON");
        }
        if (sbprintArrayStr[0].equals("a")) {
            ImageButton1.setEnabled(true);
            ImageButton1.setImageAlpha(255);
            //ImageButton1.setBackgroundColor(Color.parseColor("#ffffff"));
            ImageButton1.setImageResource(R.drawable.onoff);
            ImageButton5.setImageResource(R.drawable.vent);
            ImageButton6.setImageResource(R.drawable.par);
            ImageButton15.setImageResource(R.drawable.ir);
            ImageButton16.setImageResource(R.drawable.svet);
            ImageButton18.setImageResource(R.drawable.voda);
            ImageButton19.setImageResource(R.drawable.vibro);
            ImageButton4.setImageResource(R.drawable.time);
            flag = false;
            flag6 = false;
            flagVanna = false;
            flagVanna2 = false;
            flagVanna3 = false;
            flagVanna4 = false;
            flag4 = false;
            flag5 = false;
            flag15 = false;
            flag18 = false;
            flag16 = false;
            flag6 = false;
            flag19 = false;
            d10.setText("OFF");
        }

        if (sbprintArrayStr[1].equals("b")) {// отключение вентилятор
            ImageButton5.setImageResource(R.drawable.vent);
            flag5 = false;
        }

        if (sbprintArrayStr[1].equals("B")) {//включение вентилятор
            ImageButton5.setImageResource(R.drawable.ventg);
            flag5 = true;
        }

        if (sbprintArrayStr[2].equals("c")) {// отключение света
            ImageButton16.setImageResource(R.drawable.svet);
            flag16 = false;
        }

        if (sbprintArrayStr[2].equals("C")) {//включение света
            ImageButton16.setImageResource(R.drawable.svetg);
            flag16 = true;
        }

        if (sbprintArrayStr[3].equals("d")) {//отключение IR
            ImageButton15.setImageResource(R.drawable.ir);
            flag15 = false;
        }

        if (sbprintArrayStr[3].equals("D")) {  //включение IR
            ImageButton15.setImageResource(R.drawable.irg);
            flag15 = true;
        }

        if ((sbprintArrayStr[4].equals("e"))) {//отключение Пара
            ImageButton6.setImageResource(R.drawable.par);
            flag6 = false;
            //ImageButton6.setEnabled(true);

        }
        if (sbprintArrayStr[4].equals("E")) {//включение Пара
            ImageButton6.setImageResource(R.drawable.parg);
            //ImageButton6.setEnabled(true);
            ImageButton6.setImageAlpha(255);
            flag6 = true;
        }

        if (sbprintArrayStr[7].equals("g")) {// отключение виши
            ImageButton18.setImageResource(R.drawable.voda);
            flag18 = false;
        }

        if (sbprintArrayStr[7].equals("G")) {//включение виши
            ImageButton18.setImageResource(R.drawable.vodag);
            flag18 = true;
        }

        if (sbprintArrayStr[8].equals("h")) {// отключение вибро
            ImageButton19.setImageResource(R.drawable.vibro);
            flag19 = false;
        }

        if (sbprintArrayStr[8].equals("H")) {//включение вибро
            ImageButton19.setImageResource(R.drawable.vibrog);
            flag19 = true;
        }

        if (sbprintArrayStr[9].equals("i")) {//отключение таймера
            ImageButton4.setImageResource(R.drawable.time);
            flag4 = false;
        }
        if (sbprintArrayStr[9].equals("I")) {//включение таймера
            ImageButton4.setImageResource(R.drawable.timeg);
            flag4 = true;
        }

        textVishi.setText(sbprintArrayStr[5]); //установка виши
        temp2.setText(sbprintArrayStr[6]); //температура воды
        textVibro.setText(sbprintArrayStr[10]); //установка вибро
        timer.setText(sbprintArrayStr[11]);  //установка таймера
        text1.setText(sbprintArrayStr[12]);  //работа таймера
        temp.setText(sbprintArrayStr[13]);  //температура
        textTemp.setText(sbprintArrayStr[14]);  //установка пара
        textTempIR.setText(sbprintArrayStr[15]);  //установка IR

        if (sbprintArrayStr[16].equals("j")) {//ванна хромотерапия
            imageButton.setImageResource(R.drawable.gidro1);
            flagVanna = false;
        }

        if (sbprintArrayStr[16].equals("J")) {
            imageButton.setImageResource(R.drawable.gidro1g);
            flagVanna = true;
        }

        if (sbprintArrayStr[17].equals("k")) {//ванна массаж
            imageButton2.setImageResource(R.drawable.gidro2);
            flagVanna2 = false;
        }

        if (sbprintArrayStr[17].equals("K")) {
            imageButton2.setImageResource(R.drawable.gidro2g);
            flagVanna2 = true;
        }

        if (sbprintArrayStr[18].equals("l")) {//ванна гидромассаж
            imageButton3.setImageResource(R.drawable.gidro3);
            flagVanna3 = false;
        }
        if (sbprintArrayStr[18].equals("L")) {
            imageButton3.setImageResource(R.drawable.gidro3g);
            flagVanna3 = true;
        }

        if (sbprintArrayStr[19].equals("m")) {//ванна пустая функция
            imageButton4.setImageResource(R.drawable.gidro4);
            flagVanna4 = false;
        }
        if (sbprintArrayStr[19].equals("M")) {
            imageButton4.setImageResource(R.drawable.gidro4g);
            flagVanna4 = true;
        }

        if (sbprintArrayStr[20].equals("o")) {//ванна пустая функция
            imageButton5.setImageResource(R.drawable.svetvanna);
            flagVanna5 = false;
        }
        if (sbprintArrayStr[20].equals("O")) {
            imageButton5.setImageResource(R.drawable.svetvannag);
            flagVanna5 = true;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}// END
