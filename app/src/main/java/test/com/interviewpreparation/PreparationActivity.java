package test.com.interviewpreparation;

import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Timer;

public class PreparationActivity extends AppCompatActivity implements LoadQuestionCallback {

    ArrayList<Question> questionArrayList;
    RecyclerView questionlist_recycler_view;
    QuestionAdapter questionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        questionArrayList = new ArrayList<>();
        questionAdapter = new QuestionAdapter(this, questionArrayList);
        questionlist_recycler_view = findViewById(R.id.questionlist_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        questionlist_recycler_view.setLayoutManager(layoutManager);
        questionlist_recycler_view.setAdapter(questionAdapter);
        new LoadQuestionListAsync(this,this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        final RelativeLayout launch=findViewById(R.id.launch);
        new CountDownTimer(1000, 3000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                launch.setVisibility(View.GONE);

            }
        }.start();


    }

    @Override
    public void onQuestionLoaded(Question question) {
        Log.d("TAG", " onloaded");
        questionArrayList.add(question);
        questionAdapter.setQuestionArrayList(questionArrayList);
        questionAdapter.notifyDataSetChanged();
    }
}
