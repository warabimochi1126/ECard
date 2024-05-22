package com.example.ecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ナビゲーションバーを非表示にする
        //バックボタンで不正されるのを防ぎたかったがナビゲーションバーを完全に無効化するのは無理そう
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //activityに置いたUIを変数に宣言
        Button startButton = findViewById(R.id.Bt_start);
        //startボタンが押された場合
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gameIntent->SecondActivityへのintentを保持している
                //第１引数getApplication()->自動的にメモリ開放するっぽい？
                Intent gameIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(gameIntent);
            }
        });
    }
}