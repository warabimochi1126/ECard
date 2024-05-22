//奴隷フェーズのActivityです
package com.example.ecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    //activityに置いたUIを変数に宣言
    ImageButton SlaveSide_Card1;    //市民カードの宣言
    ImageButton SlaveSide_Card2;    //奴隷カードの宣言
    //市民カード部分
    ImageView Sl_CardCounter1;
    ImageView Sl_CardCounter2;
    ImageView Sl_CardCounter3;
    ImageView Sl_CardCounter4;
    //奴隷カード部分
    ImageView Sl_CardCounter5;

    //ゲーム進行に必要なデータ
    private int SlaveSide_CitizenCounter;           //市民カードの枚数を保持
    private int SlaveSide_SlaveCounter;             //奴隷カードの枚数を保持

    //多分勝敗判定で使うはず
    public static int SlaveSide_SelectedCards = 0;      //選ばれたカードを番号で保持(1->市民.2=>奴隷)
    private int SlaveSide_Win = 0;                      //奴隷サイドの勝ち数を保持している
    //遷移先のインテントを作成しておく
    Intent emperorIntent;
    //データ受け取り用のインテントを作成しておく
    Intent valueGetIntent;

    /**
     * カード枚数の設定を行う
     */
    void setNumOfCards() {
        //初期設定
        if(valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789) == -123456789) {
            SlaveSide_CitizenCounter = 4;
        } else {
            SlaveSide_CitizenCounter = valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789);
        }
        if(valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789) == -123456789) {
            SlaveSide_SlaveCounter = 1;
        } else {
            SlaveSide_SlaveCounter = valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789);
        }
    }

    /**
     * 手札のカード枚数と選択したカード枚数を一致させる
     */
    void matchPlayingCards() {
        //切られた手札を消していく
        //手札０枚になったら画像ボタンのクリックを禁止する
        //手札０枚になったら画像ボタンのグレーアウトも行う
        switch (SlaveSide_CitizenCounter) {
            case 3:
            //setVisibilityをコメントアウトすると正常に動作したのでここが原因->onCreateの中でカードとviewの結びつけよりも早く読んでしまっていたため
            Log.d("putExtra", "SecondActivity:ここまでいけてる");  //ここまでこないからここが原因
            Sl_CardCounter4.setVisibility(View.GONE);
            break;
            case 2:
            Sl_CardCounter4.setVisibility(View.GONE);
            Sl_CardCounter3.setVisibility(View.GONE);
            break;
            case 1:
            Sl_CardCounter4.setVisibility(View.GONE);
            Sl_CardCounter3.setVisibility(View.GONE);
            Sl_CardCounter2.setVisibility(View.GONE);
            break;
            case 0:
            Sl_CardCounter4.setVisibility(View.GONE);
            Sl_CardCounter3.setVisibility(View.GONE);
            Sl_CardCounter2.setVisibility(View.GONE);
            Sl_CardCounter1.setVisibility(View.GONE);
            SlaveSide_Card1.setEnabled(false);              //ボタンクリックを禁止
            SlaveSide_Card1.setColorFilter(0xaaCCCCCC);     //ボタンのグレーアウトを行う
            break;
        }
        if (SlaveSide_SlaveCounter == 0) {
            Sl_CardCounter5.setVisibility(View.GONE);
            SlaveSide_Card2.setEnabled(false);              //ボタンクリックを禁止
            SlaveSide_Card2.setColorFilter(0xaaCCCCCC);     //ボタンのグレーアウトを行う
        }
    }

    /**
     * 次のアクティビティに移動する際に必要な値を持っていくメソッドです
     */
    void nextAcitivty() {
        emperorIntent.putExtra("SlaveSide_CitizenCounter", SlaveSide_CitizenCounter);
        emperorIntent.putExtra("SlaveSide_SlaveCounter", SlaveSide_SlaveCounter);
        emperorIntent.putExtra("EmperorSide_CitizenCounter", valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789));
        emperorIntent.putExtra("EmperorSide_EmperorCounter", valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789));
        emperorIntent.putExtra("EmperorSide_Win", valueGetIntent.getIntExtra("EmperorSide_Win", -123456789));
        emperorIntent.putExtra("SlaveSide_Win", valueGetIntent.getIntExtra("SlaveSide_Win", -123456789));
        emperorIntent.putExtra("countTurn", valueGetIntent.getIntExtra("countTurn", -123456789));
        emperorIntent.putExtra("emperorTurn", valueGetIntent.getIntExtra("emperorTurn", -123456789));
        emperorIntent.putExtra("slaveTurn", valueGetIntent.getIntExtra("slaveTurn", -123456789));
        startActivity(emperorIntent);           //皇帝フェーズに移行
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //定義した変数とレイアウト内のウィジェットを結びつける
        SlaveSide_Card1 = findViewById(R.id.SlaveSide_Card1);
        SlaveSide_Card2 = findViewById(R.id.SlaveSide_Card2);
        Sl_CardCounter1 = findViewById(R.id.Sl_CardCounter1);
        Sl_CardCounter2 = findViewById(R.id.Sl_CardCounter2);
        Sl_CardCounter3 = findViewById(R.id.Sl_CardCounter3);
        Sl_CardCounter4 = findViewById(R.id.Sl_CardCounter4);
        Sl_CardCounter5 = findViewById(R.id.Sl_CardCounter5);

        //putExtraのログ確認
        valueGetIntent = getIntent();
        Log.d("secondActivity", "---SecondActivity---");
        Log.d("secondActivity", "奴隷サイド市民カウント:" + String.valueOf(valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789)));
        Log.d("secondActivity", "奴隷サイド奴隷カウント:" + String.valueOf(valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789)));
        Log.d("secondActivity", "皇帝サイド市民カウント:" + String.valueOf(valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789)));
        Log.d("secondActivity", "皇帝サイド皇帝カウント:" + String.valueOf(valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789)));
        Log.d("secondActivity", "皇帝サイド勝ち数:" + valueGetIntent.getIntExtra("EmperorSide_Win", -123456789));
        Log.d("secondActivity", "奴隷サイド勝ち数:" + valueGetIntent.getIntExtra("SlaveSide_Win", -123456789));
        Log.d("secondActivity", "現在のターン数:" + valueGetIntent.getIntExtra("countTurn", -123456789));
        Log.d("secondActivity", "皇帝カード出現ターン:" + valueGetIntent.getIntExtra("emperorTurn", -123456789));
        Log.d("secondActivity", "奴隷カード出現ターン" + valueGetIntent.getIntExtra("slaveTurn", -123456789));
        Log.d("slaveLog", "slaveTurn監視用" + valueGetIntent.getIntExtra("slaveTurn", -21497041));

        //ナビゲーションバーを非表示にする
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //カード枚数更新
        setNumOfCards();
        //SlaveSide_CitizenCounterとSlaveSide_SlaveCounterに入った値をチェック
        Log.d("putExtra", "SlaveSide_CitizenCounter" + SlaveSide_CitizenCounter);
        Log.d("putExtra", "SlaveSide_SlaveCounter" + SlaveSide_SlaveCounter);

        //手札の枚数とカードの所持枚数を同期させる
        matchPlayingCards();

        //必要なデータが画面遷移によって消えないように保存しておく
        //宣言がonCreateメソッド内部じゃないと多分アプリごと落ちる.クリックイベントに対して値を書き換えたいのにメソッドチェーンで辿ってこれないから利用不可
//        SharedPreferences sp = this.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor e = sp.edit();

        //画像ボタンに対しての検知登録を行う
        SelectListener selectListener = new SelectListener();
        SlaveSide_Card1.setOnClickListener(selectListener);
        SlaveSide_Card2.setOnClickListener(selectListener);

        //画面遷移に使うための遷移用インテントの中身を代入する
        emperorIntent = new Intent(this, ThirdActivity.class);
    }
    /**
     * クリックされた時に呼ばれるクラス
     */
    private class SelectListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //どのウィジェットが押されたをIDを元に分岐させる
            switch (view.getId()) {
                //市民カードが選択された時
                case R.id.SlaveSide_Card1:
                    SlaveSide_CitizenCounter--;             //市民カード所持枚数をデクリメント
                    SlaveSide_SelectedCards = 1;            //勝敗判定用変数に1を代入
                    nextAcitivty();
                    break;
                //奴隷カードが選択された時
                case R.id.SlaveSide_Card2:
                    SlaveSide_SlaveCounter--;               //奴隷カード所持枚数をデクリメント
                    SlaveSide_SelectedCards = 2;            //勝敗判定用変数に2を代入
                    nextAcitivty();
                    break;
            }
        }
    }
}