//勝敗決定を担うActivityです
//参考URL①:https://aaatoyo.com/card-design.htm
//参考URL②:https://onl.sc/FkwvqX4
package com.example.ecard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FourthActivity extends AppCompatActivity {
    //activityに置かれたUiを変数に宣言
    ImageView SlaveSide_Behind;         //奴隷サイドのカード
    ImageView EmperorSide_Behind;       //皇帝サイドのカード
    Button Bt_ready;                    //押されたら伏せられたカードが開かれるボタン
    Button Bt_next;                     //押されたら次のゲームに進むボタン
    TextView Tv_Fourth;                 //タイトルメッセージが入ってるビュー

    //遷移先のインテントを保持しておく
    Intent slaveIntent;
    Intent fifthIntent;
    //データを受け取るのに必要なインテントを保持しておく
    Intent valueGetIntent;

    //competitionLog用
    int emperorTurn;
    int slaveTurn;
    int countTurn;

    /**
     * 変数の初期設定を行う
     */
    void setNumOfCards() {
        //初期設定
        if(valueGetIntent.getIntExtra("countTurn", -123456789) == -123456789) {
            countTurn = 1;
        } else {
            countTurn = valueGetIntent.getIntExtra("countTurn", -123456789);
        }
        if(valueGetIntent.getIntExtra("emperorTurn", -123456789) == -123456789) {
            Log.d("naeta", "皇帝ターンtrue");
            emperorTurn = -500;
        } else {
            Log.d("naeta", "皇帝ターンfalse");
            emperorTurn = valueGetIntent.getIntExtra("emperorTurn", -123456789);
        }
        if(valueGetIntent.getIntExtra("slaveTurn", -123456789) == -123456789) {
            Log.d("naeta", "奴隷ターンtrue");
            slaveTurn = -500;
        } else {
            Log.d("naeta", "奴隷ターンfalse");
            Log.d("fourthActivity", "" + valueGetIntent.getIntExtra("slaveTurn", -500));
            slaveTurn = valueGetIntent.getIntExtra("slaveTurn", -123456789);
        }
    }
    /**
     * 結果発表で使うためカードの組を保存しておく
     * 全ての対戦履歴を保存するわけではなく各サイドにおける特殊なカードの出現ターンのみを記録する
     */
    void competitionLog(int EmperorSide_Num, int SlaveSide_Num) {
        Log.d("naeta2", "competitionLog呼ばれてる");
        Log.d("naeta2", "countTurn:" + countTurn);
        if (EmperorSide_Num == 2) {
            emperorTurn = countTurn;
            Log.d("naeta2", "competitionLog:true");
            Log.d("naeta2", "emperorTurn:" + emperorTurn);
        }

        if(SlaveSide_Num == 2) {
            slaveTurn = countTurn;
            Log.d("naeta2", "competitionLog:false");
            Log.d("naeta2", "slaveTurn:" + slaveTurn);
        }
        countTurn++;
        if(emperorTurn == 5) {

        }
        if (slaveTurn == 5) {

        }
    }

    /**
     * 終了処理に使うメソッド
     * 皇帝サイドの残りカード枚数を利用して判定します
     * @return true 終了条件が揃った時
     * @return false 終了条件が揃わなかった時
     */
    public boolean isFinish() {
        if (ThirdActivity.EmperorSide_CitizenCounter == 0 && ThirdActivity.EmperorSide_EmperorCounter == 0) {
            return true;
        }
        return false;
    }

    //judgeメソッド用
    private int EmperorSide_Win = 0;                //皇帝サイドの勝ち数を保持
    private int SlaveSide_Win = 0;                  //奴隷サイドの勝ち数を保持

    /**
     * 勝敗判定に使うメソッド
     * 奴隷サイド=>1:市民.2:奴隷
     * 皇帝サイド=>1:市民.2:皇帝
     */
    public void judge() {
        int SlaveSide_Num = SecondActivity.SlaveSide_SelectedCards;     //奴隷サイドが選択した値を取得しています
        int EmperorSide_Num = ThirdActivity.EmperorSide_SelectedCards;  //皇帝サイドが選択した値を取得しています
        boolean Emp_Win = false;            //皇帝サイドが勝った場合flagを立てる
        boolean Sla_Win = false;            //奴隷サイドが勝った場合flagを立てる
        boolean draw = false;

        //勝敗を判別してflagを立てます
        //最後のelseifは可読性のためにわざと残しています
        if (SlaveSide_Num == 1 && EmperorSide_Num == 1) {               //奴隷サイド:市民.皇帝サイド:市民->引き分け
            draw = true;
            Log.d("iflog","draw");
        } else if (SlaveSide_Num == 1 && EmperorSide_Num == 2) {        //奴隷サイド:市民.皇帝サイド:皇帝->皇帝サイドの勝ち
            Emp_Win = true;
            Log.d("iflog","皇帝勝ち①");
        } else if (SlaveSide_Num == 2 && EmperorSide_Num == 1) {        //奴隷サイド:奴隷.皇帝サイド:市民->皇帝サイドの勝ち
            Emp_Win = true;
            Log.d("iflog","皇帝勝ち②");
        } else if (SlaveSide_Num == 2 && EmperorSide_Num == 2) {        //奴隷サイド:奴隷.皇帝サイド:皇帝->奴隷サイドの勝ち
            Sla_Win = true;
            Log.d("iflog","奴隷勝ち");
        }

        //立っているflagによって勝ち点を振り分けます
        if (Emp_Win) {                  //皇帝サイドが勝った場合
            EmperorSide_Win++;
        } else if (Sla_Win) {           //奴隷サイドが勝った場合
            SlaveSide_Win++;
        }

        Log.d("iflog", "ここまではいってる①");
        //立っているflagを元にタイトル文字列に勝者を代入する
        //Emperor_msgがnullになっててエラー
        if(draw) {
            Log.d("iflog", "ここまではいってる②");
            Tv_Fourth.setText("引き分け");
        } else if(Emp_Win) {
            Log.d("iflog", "ここまではいってる③");
            Tv_Fourth.setText("皇帝サイドの勝ち");
        } else if(Sla_Win) {
            Log.d("iflog", "ここまではいってる④");
            Tv_Fourth.setText("奴隷サイドの勝ち");
        }
        Tv_Fourth.setTextColor(Color.RED);
    }

    /**
     * 最終アクティビティに移動する際に必要な値を持っていくメソッドです
     */
    void finishActivity() {
        if (emperorTurn == 5 && slaveTurn == 5) {
            fifthIntent.putExtra("emperorTurn", 5);
            fifthIntent.putExtra("slaveTurn", 5);
        } else if (emperorTurn == 5) {
            fifthIntent.putExtra("emperorTurn", 5);
            fifthIntent.putExtra("slaveTurn", valueGetIntent.getIntExtra("slaveTurn", -123456789));
        } else if (slaveTurn == 5) {
            fifthIntent.putExtra("emperorTurn", valueGetIntent.getIntExtra("emperorTurn", -123456789));
            fifthIntent.putExtra("slaveTurn", 5);
        } else {
            fifthIntent.putExtra("emperorTurn", valueGetIntent.getIntExtra("emperorTurn", -123456789));
            fifthIntent.putExtra("slaveTurn", valueGetIntent.getIntExtra("slaveTurn", -123456789));
        }
        startActivity(fifthIntent);
    }

    /**
     * 次のアクティビティに移動する際に必要な値を持っていくメソッドです
     */
    void nextActivity() {
        slaveIntent.putExtra("SlaveSide_CitizenCounter", valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789));
        slaveIntent.putExtra("SlaveSide_SlaveCounter", valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789));
        slaveIntent.putExtra("EmperorSide_CitizenCounter", valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789));
        slaveIntent.putExtra("EmperorSide_EmperorCounter", valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789));
        slaveIntent.putExtra("EmperorSide_Win",EmperorSide_Win);
        slaveIntent.putExtra("SlaveSide_Win", SlaveSide_Win);
        slaveIntent.putExtra("countTurn", countTurn);
        slaveIntent.putExtra("emperorTurn", emperorTurn);
        slaveIntent.putExtra("slaveTurn", slaveTurn);
        startActivity(slaveIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        //定義された変数とレイアウト内のウィジェットを結びつける
        SlaveSide_Behind = findViewById(R.id.SlaveSide_Behind);
        EmperorSide_Behind = findViewById(R.id.EmperorSide_Behind);
        Bt_ready = findViewById(R.id.Bt_ready);
        Bt_next = findViewById(R.id.Bt_next);
        Tv_Fourth = findViewById(R.id.Tv_fourth);
        Log.d("emmsg", "" + Bt_next);
        Log.d("emmsg", "" + Tv_Fourth);
        //putExtraのログ確認
        //データの受け取り確認済み
        valueGetIntent = getIntent();
        Log.d("fourthActivity", "---FourthActivity---");
        Log.d("fourthActivity", "奴隷サイド市民カウント:" + String.valueOf(valueGetIntent.getIntExtra("SlaveSide_CitizenCounter", -123456789)));
        Log.d("fourthActivity", "奴隷サイド奴隷カウント:" +String.valueOf(valueGetIntent.getIntExtra("SlaveSide_SlaveCounter", -123456789)));
        Log.d("fourthActivity", "皇帝サイド市民カウント:" + String.valueOf(valueGetIntent.getIntExtra("EmperorSide_CitizenCounter", -123456789)));
        Log.d("fourthActivity", "皇帝サイド皇帝カウント:" + String.valueOf(valueGetIntent.getIntExtra("EmperorSide_EmperorCounter", -123456789)));
        Log.d("fourthActivity", "皇帝サイド勝ち数:" + valueGetIntent.getIntExtra("EmperorSide_Win", -123456789));
        Log.d("fourthActivity", "奴隷サイド勝ち数:" + valueGetIntent.getIntExtra("SlaveSide_Win", -123456789));
        Log.d("fourthActivity", "現在のターン数:" + valueGetIntent.getIntExtra("countTurn", -123456789));
        Log.d("fourthActivity", "皇帝カード出現ターン:" + valueGetIntent.getIntExtra("emperorTurn", -123456789));
        Log.d("fourthActivity", "奴隷カード出現ターン" + valueGetIntent.getIntExtra("slaveTurn", -123456789));
        Log.d("slaveLog", "slaveTurn監視用" + valueGetIntent.getIntExtra("slaveTurn", -21497041));


        //ナビゲーションバーを非表示にする
        View decor = getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //Bt_nextに対してBt_readyが押されるまでは非表示にしておきます
        Bt_next.setVisibility(View.GONE);

        //画面遷移に使うインテントの中身を代入する
        slaveIntent = new Intent(this, SecondActivity.class);
        fifthIntent = new Intent(this, FifthActivity.class);

        //readyボタンが押された時
        int SlaveSide_Num = SecondActivity.SlaveSide_SelectedCards;     //奴隷サイドが選択した値を取得しています
        int EmperorSide_Num = ThirdActivity.EmperorSide_SelectedCards;  //皇帝サイドが選択した値を取得しています
        Bt_ready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //奴隷サイドのカードを開く
                if (SlaveSide_Num == 1) {
                    SlaveSide_Behind.setImageResource(R.drawable.citizen);
                } else if (SlaveSide_Num == 2) {
                    SlaveSide_Behind.setImageResource(R.drawable.slave);
                }
                //皇帝サイドのカードを開く
                if (EmperorSide_Num == 1) {
                    EmperorSide_Behind.setImageResource((R.drawable.citizen));
                } else if (EmperorSide_Num == 2) {
                    EmperorSide_Behind.setImageResource(R.drawable.employee);
                }
                //タイトル部分を勝者に変える
                judge();
                setNumOfCards();
                competitionLog(EmperorSide_Num, SlaveSide_Num);
                //Bt_readyを非表示
                Bt_ready.setVisibility(View.GONE);
                //Bt_nextを表示
                Bt_next.setVisibility(View.VISIBLE);
            }
        });

        //Bt_nextがクリックされた場合
        Bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //終了条件の比較を行います
                if (isFinish()) {
                    finishActivity();
                } else {
                    nextActivity();
                }
            }
        });
    }
}