//皇帝サイドのActivityです
//エラー落ち参考URL:https://onl.sc/f1NP5ax
//インテント持ち込み参考URL:https://onl.sc/W2E9DnS
package com.example.ecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ThirdActivity extends AppCompatActivity {
    //activityに置いたUIを変数に宣言
    ImageButton EmperorSide_Card1;      //市民カードの宣言
    ImageButton EmperorSide_Card2;      //皇帝カードの宣言
    //市民カード部分
    ImageView El_CardCounter1;
    ImageView El_CardCounter2;
    ImageView El_CardCounter3;
    ImageView El_CardCounter4;
    //皇帝カード部分
    ImageView El_CardCounter5;

    //ゲーム進行に必要なデータ
    public static int EmperorSide_CitizenCounter;     //市民カードの枚数を保持
    public static int EmperorSide_EmperorCounter;     //皇帝カードの枚数を保持
    //多分勝敗判定で使うはず
    public static int EmperorSide_SelectedCards = 0;       //選ばれたカードを番号で保持(1->市民.2->奴隷)
    //遷移先のインテントを保持しておく
    Intent fourthIntent;
    //データを受け取るのに必要なインテントを保持しておく
    Intent valueGetIntent;

    /**
     * カード枚数の設定を行う
     */
    void setNumOfCards() {
        //初期設定
        //else通ってないっぽい
        if(valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789) == -123456789) {
            Log.d("thirdPut", "ThirdActivity:setNumOfCardsのtrueまでいけてる");
            EmperorSide_CitizenCounter = 4;
        } else {
            Log.d("thirdPut", "ThirdActivity:setNumOfCardsのfalseまでいけてる");
            EmperorSide_CitizenCounter = valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789);
        }
        if(valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789) == -123456789) {
            EmperorSide_EmperorCounter = 1;
        } else {
            EmperorSide_EmperorCounter = valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789);
        }
    }
    /**
     * 手札のカード枚数と選択したカード枚数を一致させる
     */
    void matchPlayingCards() {
        //切られた手札を消していく
        //手札０枚になったら画像ボタンのクリックを禁止する
        //手札０枚になったら画像ボタンのグレーアウトを行う
        switch (EmperorSide_CitizenCounter) {
            case 3:
                //ここまで走ってないっぽい
                Log.d("thirdPut", "ThirdActivity:matchPlayingCardsまでいけてる");
                El_CardCounter4.setVisibility(View.GONE);
                break;
            case 2:
                El_CardCounter4.setVisibility(View.GONE);
                El_CardCounter3.setVisibility(View.GONE);
                break;
            case 1:
                El_CardCounter4.setVisibility(View.GONE);
                El_CardCounter3.setVisibility(View.GONE);
                El_CardCounter2.setVisibility(View.GONE);
                break;
            case 0:
                El_CardCounter4.setVisibility(View.GONE);
                El_CardCounter3.setVisibility(View.GONE);
                El_CardCounter2.setVisibility(View.GONE);
                El_CardCounter1.setVisibility(View.GONE);
                EmperorSide_Card1.setEnabled(false);            //ボタンクリック禁止
                EmperorSide_Card1.setColorFilter(0xaaCCCCCC);   //ボタンのグレーアウトを行う
            break;
        }
            if (EmperorSide_EmperorCounter == 0) {
                El_CardCounter5.setVisibility(View.GONE);
                EmperorSide_Card2.setEnabled(false);            //ボタンクリック禁止
                EmperorSide_Card2.setColorFilter(0xaaCCCCCC);   //ボタンのグレーアウトを行う
            }
    }

    /**
     * 次のアクティビティに移動する際に必要な値を持っていくメソッドです
     */
    void nextActivity() {
        fourthIntent.putExtra("SlaveSide_CitizenCounter", valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789));
        fourthIntent.putExtra("SlaveSide_SlaveCounter", valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789));
        fourthIntent.putExtra("EmperorSide_CitizenCounter", EmperorSide_CitizenCounter);
        Log.d("thirdPut", "ThirdActivity:nextActivity.EmperorSide_Citizencounter" + EmperorSide_CitizenCounter);
        fourthIntent.putExtra("EmperorSide_EmperorCounter", EmperorSide_EmperorCounter);
        Log.d("thirdPut", "ThirdActivity:nextActivity.EmperorSide_Emperorcounter" + EmperorSide_EmperorCounter);
        fourthIntent.putExtra("EmperorSide_Win", valueGetIntent.getIntExtra("EmperorSide_Win", -123456789));
        fourthIntent.putExtra("SlaveSide_Win", valueGetIntent.getIntExtra("SlaveSide_Win", -123456789));
        fourthIntent.putExtra("countTurn", valueGetIntent.getIntExtra("countTurn", -123456789));
        fourthIntent.putExtra("emperorTurn", valueGetIntent.getIntExtra("emperorTurn", -123456789));
        fourthIntent.putExtra("slaveTurn", valueGetIntent.getIntExtra("slaveTurn", -123456789));

        startActivity(fourthIntent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //定義した変数とレイアウト内のウィジェットを結びつける
        EmperorSide_Card1 = findViewById(R.id.EmperorSide_Card1);
        EmperorSide_Card2 = findViewById(R.id.EmperorSide_Card2);
        El_CardCounter1 = findViewById(R.id.El_CardCounter1);
        El_CardCounter2 = findViewById(R.id.El_CardCounter2);
        El_CardCounter3 = findViewById(R.id.El_CardCounter3);
        El_CardCounter4 = findViewById(R.id.El_CardCounter4);
        El_CardCounter5 = findViewById(R.id.El_CardCounter5);

        //putExtraのログ確認
        //受け取れている事を確認済み
        valueGetIntent = getIntent();
        Log.d("thirdActivity", "---ThirdActivity---");
        Log.d("thirdActivity", "奴隷サイド市民カウント:" + String.valueOf(valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789)));
        Log.d("thirdActivity", "奴隷サイド奴隷カウント:" + String.valueOf(valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789)));
        Log.d("thirdActivity", "皇帝サイド市民カウント:" + String.valueOf(valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789)));
        Log.d("thirdActivity", "皇帝サイド皇帝カウント:" + String.valueOf(valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789)));
        Log.d("thirdActivity", "皇帝サイド勝ち数:" + valueGetIntent.getIntExtra("EmperorSide_Win", -123456789));
        Log.d("thirdActivity", "奴隷サイド勝ち数:" + valueGetIntent.getIntExtra("SlaveSide_Win", -123456789));
        Log.d("thirdActivity", "現在のターン数:" + valueGetIntent.getIntExtra("countTurn", -123456789));
        Log.d("thirdActivity", "皇帝カード出現ターン:" + valueGetIntent.getIntExtra("emperorTurn", -123456789));
//        Log.d("thirdActivity", "奴隷カード出現ターン" + valueGetIntent.getIntExtra("slaveTurn", -123456789));
        Log.d("cardLog", "emperorTurn監視用" + valueGetIntent.getIntExtra("emperorTurn", -30000000));
        Log.d("cardLog", "slaveTurn監視用" + valueGetIntent.getIntExtra("slaveTurn", -21497041));

        //ナビゲーションバーを非表示にする
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //カード枚数更新
        setNumOfCards();

        //EmperorSide_CitizenCounterとEmperorSide_EmperorCounterに入った値をチェック
        Log.d("thirdPut", "EmperorSide_CitizenCounter" + EmperorSide_CitizenCounter);
        Log.d("thirdPut", "EmperorSide_EmperorCounter" + EmperorSide_EmperorCounter);

        //手札の枚数とカードの所持枚数を同期させる
        matchPlayingCards();

        //画像ボタンに対しての検知登録を行う
        SelectListener selectListener = new SelectListener();
        //エラー原因:selectListnerがnull
        EmperorSide_Card1.setOnClickListener(selectListener);
        EmperorSide_Card2.setOnClickListener(selectListener);

        //画面遷移に使うインテントの中身を代入する
        fourthIntent = new Intent(this, FourthActivity.class);
    }

    /**
     * ボタンをクリックした時に呼ばれるクラス
     */
    private class SelectListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //どのウィジェットがクリックされたかをidを元に分岐
            switch (view.getId()) {
                case R.id.EmperorSide_Card1:
                    EmperorSide_CitizenCounter--;       //市民カード所持枚数をデクリメント
                    EmperorSide_SelectedCards = 1;      //勝敗判定用変数に1を代入
                    nextActivity();
                    break;
                case R.id.EmperorSide_Card2:
                    EmperorSide_EmperorCounter--;       //皇帝カード所持枚数をデクリメント
                    EmperorSide_SelectedCards = 2;      //勝敗判定用変数に2を代入
                    nextActivity();
                    break;
            }
        }
    }
}