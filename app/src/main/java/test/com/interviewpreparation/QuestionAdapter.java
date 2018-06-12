package test.com.interviewpreparation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by rocky on 15/06/17.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {
    Context context;
    ArrayList<Question> questionArrayList;

    public QuestionAdapter(Context context, ArrayList<Question> questionArrayList) {
        this.questionArrayList=questionArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thumb_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (position%3==1){
            holder.questionCardView.setVisibility(View.GONE);
            holder.adCardView.setVisibility(View.VISIBLE);
            holder.adCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://courses.learncodeonline.in"); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);

                }
            });
        }else {
            holder.adCardView.setVisibility(View.GONE);
            holder.questionCardView.setVisibility(View.VISIBLE);
            holder.question.setText(questionArrayList.get(position).getQuestion());
            holder.answer.setText(questionArrayList.get(position).getAnswer());
        }
        Log.d("TAG", " Size"+questionArrayList.size());
    }

    @Override
    public int getItemCount() {
        return questionArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public TextView answer;
        public CardView adCardView,questionCardView;

        public MyViewHolder(View view) {
            super(view);
            question = view.findViewById(R.id.question);
            answer = view.findViewById(R.id.answer);
            adCardView=view.findViewById(R.id.card_view_ad);
            questionCardView=view.findViewById(R.id.card_view);


        }
    }
    public void setQuestionArrayList(ArrayList<Question> questionArrayList){
        this.questionArrayList=questionArrayList;


    }


}
