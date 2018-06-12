package test.com.interviewpreparation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by rocky on 12/06/18.
 */

public class LoadQuestionListAsync extends AsyncTask<Void,Void,Void> {
    Context context;
    LoadQuestionCallback loadQuestionCallback;

    public LoadQuestionListAsync(Context context,LoadQuestionCallback loadQuestionCallback){
        this.context=context;
        this.loadQuestionCallback=loadQuestionCallback;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        loadQuestionFromJson();
        return null;
    }
    public void loadQuestionFromJson(){
        JSONObject response = null;
        try {

            response = new JSONObject(loadJSONFromAsset("question.json"));
            JSONArray jsonArray = response.getJSONArray("questions");
            int j=0;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Question question =new Question();
                question.setAnswer(jsonObject.getString("Answer"));
                question.setQuestion(jsonObject.getString("question"));
                if (j%3==1){
                    Question adQuestion= new Question();
                    loadQuestionCallback.onQuestionLoaded(adQuestion);
                    j++;
                }
                j++;
                loadQuestionCallback.onQuestionLoaded(question);

                Log.d("TAG", " loaded");
            }
//
        } catch (Exception e) {
            Log.d("TAG", " JSON Error" + e.getMessage());

        }

    }
    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.d("TAG", " JSON Error load" + ex.getMessage());
            return null;
        }
        return json;
    }
}
