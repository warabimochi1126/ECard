//結果発表を担うActivityです
//参考URL①:https://onl.sc/FqwP3Ds
//参考URL②:https://onl.sc/RPwP3wx
//参考URL③:https://onl.sc/sEEUBEj     ビルド時にエラー発生.APIレベルが~~~みたいなエラー
//アニメーション①の終了とアニメーション②の終了を繋げたい
package com.example.ecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FifthActivity extends AppCompatActivity {
    //activityに置いたUIを宣言
    TextView Tv_animation;
    TextView Counter;
    TextView Tv_Winner;
    ImageView resultEmperorCard;
    ImageView resultSlaveCard;
    Button Bt_Escape;
    Button Bt_MainMenu;

    //データ受け取り用のインテントを宣言
    Intent valueGetintent;

    //デバッグ用です
    Intent debugIntent;

    //文字を浮かび上がらせるアニメーション
    //アニメーションをチェーンさせて現在のアニメーションが終了->次のアニメーションを実行を繰り返します
    void wordFloatAnimation(View view) {
        //第１引数:開始時の透明度.第２引数:開始後の透明度
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        //1.5秒かけてアニメーションする
        alpha.setDuration(1500);
        //アニメーションの終了状態を維持する
        alpha.setFillEnabled(true);
        alpha.setFillAfter(true);
        //アニメーションリスナーの登録
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                //アニメーションの終了時に呼び出されます
                wordMoveAnimation(view);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        //アニメーションを開始する
        view.startAnimation(alpha);
    }

    //文字を移動させるアニメーション
    void wordMoveAnimation(View view) {
        TranslateAnimation trans = new TranslateAnimation(
                //横には移動しない
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                //縦
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, -4);
        //アニメーションの終了をアニメーションの開始とチェーンさせる事により解決
        trans.setDuration(2000);
        //アニメーション終了時の表示状態を維持する
        trans.setFillEnabled(true);
        trans.setFillAfter(true);
        //アニメーションリスナーの登録
        trans.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                counterAnimation1(Counter);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        //アニメーションを開始
        view.startAnimation(trans);
    }
    //ここまではまとも

    //カウンター用のアニメーションです
    void counterAnimation1(View view) {
        //第１引数:開始時の透明度.第２引数:開始後の透明度
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        //2.5秒かけてアニメーションする
        alpha.setDuration(2500);
        //アニメーションの終了状態を維持する
        alpha.setFillEnabled(true);
        alpha.setFillAfter(true);
        //アニメーションリスナーを登録
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                    openCard();
                    setVictoryOrDefeat();
                    victoryAnimation1(Tv_Winner);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        //アニメーションを開始する
        view.startAnimation(alpha);
    }

    void victoryAnimation1(View view) {
        //第１引数:開始時の透明度.第２引数:開始後の透明度
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        //2.5秒かけてアニメーションする
        alpha.setDuration(2500);
        //アニメーションリスナーを登録
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                if(cardcheck == 6) {
                    Counter.setVisibility(View.GONE);
                    Bt_MainMenu.setVisibility(View.VISIBLE);
                } else {
                    setCounter();
                    closeCard();                    counterAnimation1(Counter);
                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        //アニメーションを開始する
        view.startAnimation(alpha);
    }

    //openCard用の変数
    int cardcheck = 1;
    //カードを開くメソッド
    void openCard() {
        Log.d("fifthActivity", "cardcheck値:" + cardcheck);
        if(valueGetintent.getIntExtra("emperorTurn", -123456789) == cardcheck) {
            resultEmperorCard.setImageResource(R.drawable.employee);
        } else {
            resultEmperorCard.setImageResource(R.drawable.citizen);
        }
        if(valueGetintent.getIntExtra("slaveTurn", -123456789) == cardcheck) {
            resultSlaveCard.setImageResource(R.drawable.slave);
        } else {
            resultSlaveCard.setImageResource(R.drawable.citizen);
        }
        cardcheck++;
    }
    //カードを伏せるメソッド
    void closeCard() {
        resultEmperorCard.setImageResource(R.drawable.masterbehind2);
        resultSlaveCard.setImageResource(R.drawable.slavebehind2);
    }
    //勝敗判定して勝者を設定する
    void setVictoryOrDefeat() {
        if(valueGetintent.getIntExtra("emperorTurn", -123456789) == cardcheck - 1 && valueGetintent.getIntExtra("slaveTurn", -123456789) != cardcheck - 1) {
            //皇帝+市民
            Tv_Winner.setText("皇帝の勝ち");
            Tv_Winner.setTextColor(Color.RED);
        } else if (valueGetintent.getIntExtra("emperorTurn", -123456789) == cardcheck - 1 && valueGetintent.getIntExtra("slaveTurn", -123456789) == cardcheck - 1) {
            //皇帝+奴隷
            Tv_Winner.setText("奴隷の勝ち");
            Tv_Winner.setTextColor(Color.BLUE);
        } else if (valueGetintent.getIntExtra("emperorTurn", -123456789) != cardcheck - 1 && valueGetintent.getIntExtra("slaveTurn", -123456789) == cardcheck - 1) {
            //市民+奴隷
            Tv_Winner.setText("皇帝の勝ち");
            Tv_Winner.setTextColor(Color.RED);
        } else {
            //市民+市民
            Tv_Winner.setText("引き分け");
            Tv_Winner.setTextColor(Color.WHITE);
        }
    }
    //回数のTextViewを動的に変更
    void setCounter() {
        Counter.setText("第" + cardcheck + "回");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        //ナビゲーションバーを非表示にする
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //定義された変数とレイアウト内のウィジェットを結びつける
        Tv_animation = findViewById(R.id.Tv_animation);
        Counter = findViewById(R.id.Counter);
        resultEmperorCard = findViewById(R.id.resultEmperorCard);
        resultSlaveCard = findViewById(R.id.resultSlaveCard);
        Tv_Winner = findViewById(R.id.Tv_Winner);
        Bt_Escape = findViewById(R.id.Bt_Escape);
        Bt_MainMenu = findViewById(R.id.Bt_MainMenu);

        //デバッグ用ボタンなので機能は保持したまま非表示にしておきます
//        Bt_Escape.setVisibility(View.INVISIBLE);
        //putExtraのログ確認
        //確認した
        valueGetintent = getIntent();
        Log.d("fifthActivity", "---FifthActivity---");
        Log.d("fifthActivity", "emperorTurn値:" + valueGetintent.getIntExtra("emperorTurn", -123456789));
        Log.d("fifthActivity", "slaveTurn値:" + valueGetintent.getIntExtra("slaveTurn", -123456789));

        //始めは非表示にしておく
        Counter.setVisibility(View.INVISIBLE);
        Tv_Winner.setVisibility(View.INVISIBLE);
        Bt_MainMenu.setVisibility(View.INVISIBLE);

        //デバッグ用インテントの設定
        debugIntent = new Intent(this, MainActivity.class);

        //チェーンさせているアニメーションメソッドを呼び出す
        wordFloatAnimation(Tv_animation);

        Bt_Escape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(debugIntent);
            }
        });
        Bt_MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //同じくメインに戻れる
                startActivity(debugIntent);
            }
        });
    }
}